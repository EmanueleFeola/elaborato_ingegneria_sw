package elaborato_ing_sw.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import elaborato_ing_sw.dataManager.ExpensesDaoImpl;
import elaborato_ing_sw.dataManager.FidelityCardDaoImpl;
import elaborato_ing_sw.dataManager.ShoppingCartDaoImpl;
import elaborato_ing_sw.model.Delivery;
import elaborato_ing_sw.model.Expense;
import elaborato_ing_sw.model.FidelityCard;
import elaborato_ing_sw.model.Payment;
import elaborato_ing_sw.model.Product;
import elaborato_ing_sw.model.TimeSlot;
import elaborato_ing_sw.model.User;
import elaborato_ing_sw.utils.AlertUtil;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class DeliveryController {
	private static final int MAX_DELIVERY_YEARS = 2;

	@FXML
	private DatePicker deliveryDate;
	@FXML
	private ChoiceBox<TimeSlot> timeSlot;
	@FXML
	private ChoiceBox<Payment> paymentType;

	private ShoppingCartDaoImpl shoppingCartDao = ShoppingCartDaoImpl.getShoppingCartDaoImpl();
	private ExpensesDaoImpl expensesDao = ExpensesDaoImpl.getExpensesDaoImpl();
	private FidelityCardDaoImpl fcardDao = FidelityCardDaoImpl.getFidelityCardImpl();

	private boolean okClicked = false;
	private Stage dialogStage;
	private User loggedUser;
	private String user;

	@FXML
	private void initialize() {
		// faccio in modo che l'utente non possa selezionare una data precedente a
		// quella odierna
		deliveryDate.setDayCellFactory(d -> new DateCell() {
			@Override
			public void updateItem(LocalDate item, boolean empty) {
				super.updateItem(item, empty);
				LocalDate now = LocalDate.now();
				setDisable(item.isBefore(now) || item.isAfter(now.plusYears(MAX_DELIVERY_YEARS)));
			}
		});

		timeSlot.getItems().setAll(TimeSlot.values());
		paymentType.getItems().setAll(Payment.values());
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	@FXML
	private void onDateSelected() {
		// memorizzo giorno e ora
		LocalDate today = LocalDate.now();
		Calendar calendar = Calendar.getInstance();
		int h = calendar.get(Calendar.HOUR_OF_DAY);

		ArrayList<TimeSlot> newTs = new ArrayList<>();

		// svuoto il choicebox per sicurezza
		timeSlot.getItems().removeAll(TimeSlot.values());

		// faccio in modo che se un timeslot e' gia'� passato non venga visualizzato
		for (TimeSlot ts : TimeSlot.values()) {
			if ((deliveryDate.getValue().equals(today)) && ts.getStart() <= h && h <= ts.getEnd())
				newTs.add(ts);
			else if (!deliveryDate.getValue().equals(today))
				newTs.add(ts);
		}

		if (newTs.size() == 0)
			AlertUtil.Alert(AlertType.INFORMATION, "No timeslots available",
					"No more timeslots are available for the selected date", "Please select another delivery date");
		else
			timeSlot.getItems().setAll(newTs);
	}

	@FXML
	private void handleOk() {
		if (!isInputValid())
			return;

		this.user = loggedUser.getCredentials().getUser();

		int id = expensesDao.getAllItems().size();

		double priceTotPerProd;
		double priceTot = 0;

		HashMap<Product, Double> products = new HashMap<Product, Double>();
		for (Product p : shoppingCartDao.getItem(user).getProducts()) {
			priceTot += p.getPrice() * p.getQuantity();
			priceTotPerProd = p.getPrice() * p.getQuantity();
			products.put(p, priceTotPerProd);
		}

		if (fcardDao.getItem(user) != null) {
			FidelityCard card = fcardDao.getItem(user);
			double points = card.getPointsTot();
			points += priceTot;
			card.setPointsTot(points);
			fcardDao.updateItem(card);
		} else {
			AlertUtil.Alert(AlertType.INFORMATION, "No Fidelity Card found", "Your points will not be added",
					"Please consider requesting a Fidelity Card");
		}

		Expense expense = new Expense(id, deliveryDate.getValue(), timeSlot.getValue(), loggedUser, priceTot,
				paymentType.getValue(), Delivery.CONFERMATA, products);

		expensesDao.addItem(expense);
		shoppingCartDao.deleteItem(shoppingCartDao.getItem(user));

		okClicked = true;
		dialogStage.close();
	}

	private boolean isInputValid() {
		String errorMessage = "";

		if (deliveryDate.getValue() == null)
			errorMessage += "No valid date!\n";
		if (timeSlot.getValue() == null)
			errorMessage += "No valid time slot!\n";
		if (paymentType.getValue() == null)
			errorMessage += "No valid payment type!\n";

		if (errorMessage.length() == 0)
			return true;
		else {
			AlertUtil.Alert(AlertType.ERROR, "Invalid Fields", "Please correct invalid fields", errorMessage);
			return false;
		}
	}

	@FXML
	private void handleClose() {
		dialogStage.close();
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
}
