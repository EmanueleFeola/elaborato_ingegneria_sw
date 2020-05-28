package elaborato_ing_sw.view;

import java.io.IOException;

import elaborato_ing_sw.MainApp;
import elaborato_ing_sw.model.Credentials;
import elaborato_ing_sw.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	@FXML
	private TextField usernameTextField;

	@FXML
	private PasswordField passwordTextField;

	@FXML
	private RadioButton loginAsManagerRB;

	@FXML
	public Button loginButton;

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
	 * @throws IOException Called when the user clicks on the delete button. @throws
	 */
	@FXML
	private void handleLogin() throws IOException {
		String user = usernameTextField.getText();
		String pwd = passwordTextField.getText();

		boolean found = false;

		for (User u : mainApp.getUsers()) {

			// System.out.println(u);

			if ((u.getCredentials().getUser()).equals(user)
					&& (u.getCredentials().getMd5Pwd()).equals(Credentials.getMd5(pwd))) {
				found = true;
			}

			/*
			 * if (u.getCredentials().getUser().equals(user) &&
			 * u.getCredentials().getMd5Pwd().equals(Credentials.getMd5(pwd))) found = true;
			 */
		}

		if (!found) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Login Failed");
			alert.setHeaderText("Please correct invalid fields");

			alert.showAndWait();
		} else {
			mainApp.showUserPageDialog();
			Stage stage = (Stage) loginButton.getScene().getWindow();
			stage.close();
		}
	}

	@FXML
	private void handleRegister() {
		mainApp.showRegisterUserDialog();
	}

}
