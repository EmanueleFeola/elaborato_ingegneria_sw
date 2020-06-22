package elaborato_ing_sw.view;

import java.io.IOException;

import elaborato_ing_sw.dataManager.ManagerDaoImpl;
import elaborato_ing_sw.dataManager.UserDaoImpl;
import elaborato_ing_sw.model.Credentials;
import elaborato_ing_sw.model.Manager;
import elaborato_ing_sw.model.Person;
import elaborato_ing_sw.model.User;
import elaborato_ing_sw.utils.AlertUtil;
import elaborato_ing_sw.utils.wrapperShowView;
import javafx.fxml.FXML;
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
			if (lam)
				wrapperShowView.showManagerDashboard();
			else
				wrapperShowView.showGroceryShoppingView((User)p);
		} else {
			AlertUtil.Alert(AlertType.ERROR, "Login Failed", "Please correct invalid fields", null);
		}
	}
	
	@FXML
	private void handleRegistration() throws IOException {
		wrapperShowView.showUserProfileView(null);
	}
}
