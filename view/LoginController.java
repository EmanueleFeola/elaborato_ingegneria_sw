package elaborato_ing_sw.view;

import java.io.IOException;

import elaborato_ing_sw.MainApp;
import elaborato_ing_sw.dataManager.ManagerDaoImpl;
import elaborato_ing_sw.dataManager.UserDaoImpl;
import elaborato_ing_sw.model.Credentials;
import elaborato_ing_sw.model.Manager;
import elaborato_ing_sw.model.Person;
import elaborato_ing_sw.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class LoginController {
	@FXML
	private TextField usernameTextField;

	@FXML
	private TextField passwordTextField;

	@FXML
	private RadioButton loginAsManagerRB;

	private UserDaoImpl userDao = UserDaoImpl.getUserDaoImpl();
	private ManagerDaoImpl managerDao = ManagerDaoImpl.getManagerDaoImpl();
	
	private MainApp mainApp;

	public LoginController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

	}

	@FXML
	private void handleLogin() throws IOException {
		String userTextField = usernameTextField.getText();
		String pwdTextField = passwordTextField.getText();
		boolean lam = loginAsManagerRB.isSelected();

		Person p;
		if (lam)
			p = (Manager) managerDao.getItem(userTextField);
		else
			p = (User) userDao.getItem(userTextField);

		if (p != null && p.getCredentials().getMd5Pwd().equals(Credentials.getMd5(pwdTextField))) {
			if (lam) {
				mainApp.showManagerDashboard();
			} else {
				mainApp.showHomeView((User)p);
				
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(MainApp.getPrimaryStage());
			alert.setTitle("Login Failed");
			alert.setHeaderText("Please correct invalid fields");
			alert.showAndWait();
		}
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	private void handleRegistration() throws IOException {
		mainApp.showUserProfileView(null);
	}
}
