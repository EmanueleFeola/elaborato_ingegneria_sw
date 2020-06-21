package elaborato_ing_sw.view;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.function.Predicate;

import elaborato_ing_sw.dataManager.ExpensesDaoImpl;
import elaborato_ing_sw.model.Delivery;
import elaborato_ing_sw.model.Expense;
import elaborato_ing_sw.model.Payment;
import elaborato_ing_sw.model.TimeSlot;
import elaborato_ing_sw.model.User;
import elaborato_ing_sw.utils.AlertUtil;
import elaborato_ing_sw.utils.wrapperShowView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AllExpensesController {
	private static final int MAX_BEFORE_DELIVERY = 3;
	
	@FXML
	TableView<Expense> expensesTable;
	@FXML
	TableColumn<Expense, LocalDate> date;
	@FXML
	TableColumn<Expense, TimeSlot> timeslot;
	@FXML
	TableColumn<Expense, Double> priceTot;
	@FXML
	TableColumn<Expense, Payment> payment;
	@FXML
	TableColumn<Expense, Delivery> delivery;
	
	private ExpensesDaoImpl expensesDao = ExpensesDaoImpl.getExpensesDaoImpl();
	ObservableList<Expense> tableExpenses;

	private Stage dialogStage;
	private User loggedUser;
	private String user;
	
	@FXML
	private void initialize() {
		// get giorno e ora
		LocalDate today = LocalDate.now();
		Calendar calendar = Calendar.getInstance();
		int h = calendar.get(Calendar.HOUR_OF_DAY);
		
		for (Expense e : expensesDao.getAllItems()) {
			// se mancano massimo 3 giorni alla consegna, giorno della consegna escluso, 
			// e lo stato e' ancora CONFERMATA passa in automatico a IN_PREPARAZIONE
			if ((e.getDeliveryDate().getDayOfMonth()-1) - today.getDayOfMonth() <= MAX_BEFORE_DELIVERY && !e.getDeliveryDate().equals(today)) {
				if (e.getDelivery().equals(Delivery.CONFERMATA)) {
					e.setDelivery(Delivery.IN_PREPARAZIONE);
					expensesDao.updateItem(e);
				}
				
			} else if (e.getDeliveryDate().equals(today)) {
				TimeSlot ts = e.getTimeSlot();
				// controllo se l'ora del giorno viene dopo l'inizio del timeslot della consegna
				if (ts.getStart() <= h) {
					// controllo se la spesa era in preparazione
					// se no una spesa passa da confermata a consegnata skippando uno stato
					if (e.getDelivery().equals(Delivery.IN_PREPARAZIONE)) {
						e.setDelivery(Delivery.CONSEGNATA);
						expensesDao.updateItem(e);
					}
				}
			}
		}
	}

	@FXML
	private void handleTable() {
		date.setCellValueFactory(cellData -> cellData.getValue().getDateProperty());
		timeslot.setCellValueFactory(cellData -> cellData.getValue().getTimeSlotProperty());
		priceTot.setCellValueFactory(cellData -> cellData.getValue().getPriceTotProperty());
		payment.setCellValueFactory(cellData -> cellData.getValue().getPaymentProperty());
		delivery.setCellValueFactory(cellData -> cellData.getValue().getDeliveryProperty());
		
		this.user = loggedUser.getCredentials().getUser();
		
		if (expensesDao.getItem(user) == null) {
			System.out.println("No expenses available");
		} else {
			this.tableExpenses = FXCollections.observableArrayList(expensesDao.getAllItems().filtered(new Predicate<Expense>() {
				@Override
				public boolean test(Expense expense) {
					return expense.getUser().equals(loggedUser);
				}
			}));
			
			expensesTable.setItems(this.tableExpenses);
		}
	}
	
	@FXML
	private void handleProductDetails() {
		int selectedIndex = expensesTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			int id = expensesTable.getItems().get(selectedIndex).getId();
			wrapperShowView.showDeliveryProductsView(id);
		} else
			AlertUtil.Alert(AlertType.WARNING, "No Selection", "No Expense Selected", "Please select an expense in the table");
	}
	
	@FXML
	private void handleClose() {
		dialogStage.close();
	}
	
	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
		handleTable();
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
}
