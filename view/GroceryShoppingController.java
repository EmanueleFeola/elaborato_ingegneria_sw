package elaborato_ing_sw.view;

import elaborato_ing_sw.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GroceryShoppingController {
	@FXML
	private Button logoutBtn;
	
	private MainApp mainApp;
	
	@FXML
	private void handleLogout() {
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
}
