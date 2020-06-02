package elaborato_ing_sw.view;

import elaborato_ing_sw.MainApp;
import elaborato_ing_sw.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController {
	@FXML
	private Button groceryShoppingBtn;
	@FXML
	private Button logoutBtn;
	
	private MainApp mainApp;
	private User loggedUser;
	
	public void setLoggedUser(User user){
		this.loggedUser = user;
	}
	
	@FXML
	private void handleGroceryShopping() {
		mainApp.showGroceryShoppingView();
	}
	
	@FXML
	private void handleLogout() {
		mainApp.showLoginView();
		System.out.println("Logged out successfully");
	}
	
	@FXML
	private void handleYourProfile() {
		System.out.println(loggedUser);
		mainApp.showUserProfileView(loggedUser);
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}
