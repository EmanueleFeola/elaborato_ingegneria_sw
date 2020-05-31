package elaborato_ing_sw.view;

import elaborato_ing_sw.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController {
	@FXML
	private Button groceryShoppingBtn;
	@FXML
	private Button logoutBtn;
	
	private MainApp mainApp;
	
	@FXML
	private void handleGroceryShopping() {
		mainApp.showGroceryShoppingView();
	}
	
	@FXML
	private void handleLogout() {
		mainApp.showLoginView();
		System.out.println("Logged out successfully");
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}
