package elaborato_ing_sw.view;

import java.time.LocalDate;
import elaborato_ing_sw.dataManager.ExpensesDaoImpl;
import elaborato_ing_sw.model.Delivery;
import elaborato_ing_sw.model.Expense;
import elaborato_ing_sw.model.Payment;
import elaborato_ing_sw.model.TimeSlot;
import elaborato_ing_sw.utils.AlertUtil;
import elaborato_ing_sw.utils.wrapperShowView;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ManagerExpensesController {
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
	@FXML
	TableColumn<Expense, String> user;
	
	private ExpensesDaoImpl expensesDao = ExpensesDaoImpl.getExpensesDaoImpl();

	private Stage dialogStage;
	
	@FXML
	private void initialize() {
		date.setCellValueFactory(cellData -> cellData.getValue().getDateProperty());
		timeslot.setCellValueFactory(cellData -> cellData.getValue().getTimeSlotProperty());
		priceTot.setCellValueFactory(cellData -> cellData.getValue().getPriceTotProperty());
		payment.setCellValueFactory(cellData -> cellData.getValue().getPaymentProperty());
		delivery.setCellValueFactory(cellData -> cellData.getValue().getDeliveryProperty());
		user.setCellValueFactory(cellData -> cellData.getValue().getUser().getCredentials().getUsernameProperty());
		
		expensesTable.setItems(expensesDao.getAllItems());
	}
	
	@FXML
	private void handleEdit() {
		int selectedIndex = expensesTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			int id = expensesTable.getItems().get(selectedIndex).getId();
			wrapperShowView.showEditDeliveryStatusView(id);
		} else
			AlertUtil.Alert(AlertType.WARNING, "No Selection", "No Expense Selected", "Please select an expense in the table");
	}
	
	@FXML
	private void handleClose() {
		dialogStage.close();
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
}
