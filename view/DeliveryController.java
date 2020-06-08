package elaborato_ing_sw.view;

import java.util.HashMap;
import java.util.Random;

import elaborato_ing_sw.dataManager.ExpensesDaoImpl;
import elaborato_ing_sw.dataManager.ShoppingCartDaoImpl;
import elaborato_ing_sw.model.Expense;
import elaborato_ing_sw.model.Payment;
import elaborato_ing_sw.model.Product;
import elaborato_ing_sw.model.TimeSlot;
import elaborato_ing_sw.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class DeliveryController {
	@FXML
	private DatePicker deliveryDate;
	@FXML
	private ChoiceBox<TimeSlot> timeSlot;
	@FXML
	private ChoiceBox<Payment> paymentType;
	
	private ShoppingCartDaoImpl shoppingCartDao = ShoppingCartDaoImpl.getShoppingCartDaoImpl();
	private ExpensesDaoImpl groceryShoppingDao = ExpensesDaoImpl.getGroceryShoppingDaoImpl();
	
	private boolean okClicked = false;
	private Stage dialogStage;
	private User loggedUser;
	private String user;
	
	@FXML
	private void initialize() {
		timeSlot.getItems().setAll(TimeSlot.values());
		paymentType.getItems().setAll(Payment.values());
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
	
	@FXML
	private void handleOk() {
		if(!isInputValid())
			return;
		
		this.user = loggedUser.getCredentials().getUser();
		
		Random rnd = new Random();
		int id;
		
		do {
			id = rnd.nextInt();
		} while (groceryShoppingDao.getItem(String.valueOf(id)) != null);
		
		double priceTotPerProd;
		HashMap<Product, Double> products = new HashMap<Product, Double>();
		for (Product p : shoppingCartDao.getItem(user).getProducts()) {
			priceTotPerProd = p.getPrice() * p.getQuantity();
			products.put(p, priceTotPerProd);
		}
		
		double priceTot = 0;
		for (Double prc : products.values())
			priceTot += prc;
		
		Expense expense = new Expense(id, deliveryDate.getValue(), timeSlot.getValue(), loggedUser, priceTot, paymentType.getValue(), products);
		
		groceryShoppingDao.addItem(expense);

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
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();

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
