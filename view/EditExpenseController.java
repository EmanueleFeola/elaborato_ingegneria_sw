package elaborato_ing_sw.view;

import elaborato_ing_sw.dataManager.ExpensesDaoImpl;
import elaborato_ing_sw.model.Delivery;
import elaborato_ing_sw.model.Expense;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class EditExpenseController {
	@FXML
	private ChoiceBox<Delivery> status;
	
	private ExpensesDaoImpl expensesDao = ExpensesDaoImpl.getExpensesDaoImpl();
	
	private Stage dialogStage;
	private int expenseId;
	
	@FXML
	private void handleOk() {
		Expense e = expensesDao.getItemById(expenseId);
		e.setDelivery(status.getValue());
		expensesDao.updateItem(e);
		dialogStage.close();
	}
	
	@FXML
	private void initialize() {
		status.getItems().setAll(Delivery.values());
	}
	
	public void setExpenseId(int id) {
		this.expenseId = id;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	@FXML
	private void handleClose() {
		dialogStage.close();
	}
}


