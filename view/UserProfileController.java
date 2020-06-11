package elaborato_ing_sw.view;

import elaborato_ing_sw.dataManager.UserDaoImpl;
import elaborato_ing_sw.model.Credentials;
import elaborato_ing_sw.model.User;
import elaborato_ing_sw.utils.AlertUtil;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class UserProfileController {
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

	private Stage dialogStage;
	private boolean okClicked = false;
	private UserDaoImpl userDao = UserDaoImpl.getUserDaoImpl();
	private User loggedUser;

	@FXML
	private void initialize() {
	}

	public boolean isOkClicked() {
		return okClicked;
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setLoggedUser(User user){
		this.loggedUser = user;
		showUserDetails();
	}
	
	public User getLoggedUser() {
		return this.loggedUser;
	}

	private void showUserDetails() {
		if (loggedUser != null) {
			firstNameField.setText(loggedUser.getName());
			lastNameField.setText(loggedUser.getSurname());
			streetField.setText(loggedUser.getAddress());
			cityField.setText(loggedUser.getCity());
			telNumberField.setText(loggedUser.getTelNum());
			postalCodeField.setText(String.valueOf(loggedUser.getPostalCode()));
			//passwordField.setText("hidden");
			birthdayField.setValue(loggedUser.getDateOfBirth());
			usernameField.setText(loggedUser.getCredentials().getUser());
			usernameField.setDisable(true);
		} else {
			firstNameField.setText("");
			lastNameField.setText("");
			streetField.setText("");
			cityField.setText("");
			telNumberField.setText("");
			postalCodeField.setText("");
			passwordField.setText("");
			birthdayField.setPromptText("");
			usernameField.setText("");
			usernameField.setDisable(false);
		}
	}
	
	@FXML
	private void handleOk() {
		if(!isInputValid())
			return;
		
		Credentials c = new Credentials(usernameField.getText(), passwordField.getText());        
		User u = new User(firstNameField.getText(), lastNameField.getText(), birthdayField.getValue(), c, streetField.getText(),
				cityField.getText(), Integer.parseInt(postalCodeField.getText()), telNumberField.getText());
			
		this.setLoggedUser(u);
		
		if(loggedUser == null)
			userDao.addItem(u);			
		else
			userDao.updateItem(u);
		
		if (!userDao.updateSource())
			System.out.println("User non registered");

		okClicked = true;
		dialogStage.close();
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
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
			AlertUtil.Alert(AlertType.ERROR, "Invalid Fields", "Please correct invalid fields", errorMessage);
			return false;
		}
	}
}