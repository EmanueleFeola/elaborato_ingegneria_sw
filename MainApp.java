package elaborato_ing_sw;

import java.io.IOException;

import elaborato_ing_sw.dataManager.UserDaoImpl;
import elaborato_ing_sw.view.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	
    private Stage primaryStage;
    private BorderPane rootLayout;

	private UserDaoImpl userDao = UserDaoImpl.getUserDaoImpl();
	
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Shopping Online");

        
        System.out.println("Users from file: ");
        System.out.println(userDao.getAllUsers());
        
        initRootLayout();
        showLoginView();
    }
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Shows the person overview inside the root layout.
     */
    public void showLoginView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Login.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            LoginController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public BorderPane getRootLayout() {
    	return rootLayout;
    }
    
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
