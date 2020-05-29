package elaborato_ing_sw.view;

import java.io.IOException;

import elaborato_ing_sw.MainApp;
import elaborato_ing_sw.dataManager.UserDaoImpl;
import elaborato_ing_sw.model.Credentials;
import elaborato_ing_sw.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginController {
    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;
    
    @FXML
    private RadioButton loginAsManagerRB;
    
    private MainApp mainApp;
    
	private UserDaoImpl userDao = UserDaoImpl.getUserDaoImpl();

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public LoginController() {
    	
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
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
     * Shows the person overview inside the root layout.
     */
    public void showHomeView() {
        try {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/Home.fxml"));
	        AnchorPane home = (AnchorPane) loader.load();
	        
	        // Set person overview into the center of root layout.
	        mainApp.getRootLayout().setCenter(home);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @throws IOException 
     * Called when the user clicks on the delete button.
     * @throws  
     */
    @FXML
    private void handleLogin() throws IOException{
    	String userTextField = usernameTextField.getText();
    	String pwdTextField = passwordTextField.getText();

    	User user = (User)userDao.getUser(userTextField);
    	if(user != null && user.getCredentials().getMd5Pwd().equals(Credentials.getMd5(pwdTextField)))
    		showHomeView();
    	else {
		    Alert alert = new Alert(AlertType.ERROR);
		    alert.initOwner(mainApp.getPrimaryStage());
		    alert.setTitle("Login Failed");
		    alert.setHeaderText("Please correct invalid fields");
		    alert.showAndWait();    		
    	}
    }
    
    /*
    handleRegister(){
		Credentials cred1 = new Credentials("efeola99@gmail.com", "pwd");
		User u1 = new User("giacomino", "mira", new Date(1999, 5, 2), cred1, "via dell amicizia, 6", "bolzano", 37012, "+39123456");
		
		users.add(u1);
		
		fu.serializeArrayList(users);	
	}
	*/	

}
