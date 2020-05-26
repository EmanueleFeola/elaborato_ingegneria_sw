package elaborato_ing_sw.view;

import elaborato_ing_sw.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class LoginController {
	@FXML
	private TextField usernameTextField;

	@FXML
	private TextField passwordTextField;

	@FXML
	private RadioButton loginAsManagerRB;

	private MainApp mainApp;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public LoginController() {

	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleLogin() {
		if ((usernameTextField.getText()).equals(passwordTextField.getText())) {
			mainApp.showUserPageDialog();
		} else {
			System.out.println("usernameTextField: " + usernameTextField.getText());
		}

	}
}
