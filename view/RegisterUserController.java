package elaborato_ing_sw.view;

import java.time.LocalDate;

import elaborato_ing_sw.MainApp;
import elaborato_ing_sw.dataManager.UserDaoImpl;
import elaborato_ing_sw.model.Credentials;
import elaborato_ing_sw.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class RegisterUserController {
	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField streetField;
	@FXML
	private TextField postalCodeField;
	@FXML
	private TextField cityField;
	@FXML
	private DatePicker birthdayField;
	@FXML
	private TextField telNumberField;
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;

	private boolean okClicked = false;
	private UserDaoImpl userDao = UserDaoImpl.getUserDaoImpl();

	@FXML
	private void initialize() {
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	@FXML
	private void handleOk() {
		if(!isInputValid())
			return;
		
		Credentials c = new Credentials(usernameField.getText(), passwordField.getText());
		
        LocalDate dob = birthdayField.getValue(); 
        
		User u = new User(firstNameField.getText(), lastNameField.getText(), dob, c, streetField.getText(),
				cityField.getText(), Integer.parseInt(postalCodeField.getText()), telNumberField.getText());
		
		userDao.addItem(u);
		
		if (!userDao.updateSource())
			System.out.println("User non registered");

		okClicked = true;
		MainApp.getPrimaryStage().close();
	}

	@FXML
	private void handleCancel() {
		MainApp.getPrimaryStage().close();
	}

	/**
	 * Validates the user input in the text fields.
	 * 
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {
		String errorMessage = "";

		if (firstNameField.getText() == null || firstNameField.getText().length() == 0)
			errorMessage += "No valid first name!\n";
		
		if (lastNameField.getText() == null || lastNameField.getText().length() == 0)
			errorMessage += "No valid last name!\n";
		
		if (streetField.getText() == null || streetField.getText().length() == 0)
			errorMessage += "No valid street!\n";
		
		if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0)
			errorMessage += "No valid postal code!\n";
		else {
			// try to parse the postal code into an int.
			try {
				Integer.parseInt(postalCodeField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid postal code (must be an integer)!\n";
			}
		}

		if (cityField.getText() == null || cityField.getText().length() == 0)
			errorMessage += "No valid city!\n";

		if (birthdayField.getValue() == null)
			errorMessage += "No valid birthday!\n";

		if (telNumberField.getText() == null || telNumberField.getText().length() == 0)
			errorMessage += "No valid telephone number!\n";

		if (usernameField.getText() == null || usernameField.getText().length() == 0)
			errorMessage += "No valid username!\n";

		if (passwordField.getText() == null || passwordField.getText().length() == 0)
			errorMessage += "No valid password!\n";

		if (errorMessage.length() == 0)
			return true;
		else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(MainApp.getPrimaryStage());
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}
}