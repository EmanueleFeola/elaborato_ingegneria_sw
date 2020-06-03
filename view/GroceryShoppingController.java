package elaborato_ing_sw.view;

import elaborato_ing_sw.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GroceryShoppingController {
	@FXML
	private Button logoutBtn;
	
	private MainApp mainApp;
	private Stage dialogStage;
	
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
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
}
