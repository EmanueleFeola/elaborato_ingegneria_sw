package elaborato_ing_sw.view;

import elaborato_ing_sw.MainApp;
import elaborato_ing_sw.model.User;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class GroceryShoppingController {
	private MainApp mainApp;
	private Stage dialogStage;
	private User loggedUser;
	
	@FXML
	private void handleLogout() {
		dialogStage.close();
		mainApp.showLoginView();
		System.out.println("Logged out successfully");
	}
	
	@FXML
	private void handleShoppingCart() {
		mainApp.showShoppingCartView();
	}
	
	@FXML
	private void handleProfile() {
		mainApp.showUserProfileView(loggedUser);
	}
	
	@FXML
	private void handleAllExpenses() {
		mainApp.showAllExpensesView(loggedUser);
	}
	
	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
}
