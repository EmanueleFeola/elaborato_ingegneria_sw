package elaborato_ing_sw;

import java.io.IOException;
import java.time.LocalDate;

import elaborato_ing_sw.dataManager.ManagerDaoImpl;
import elaborato_ing_sw.dataManager.UserDaoImpl;
import elaborato_ing_sw.model.Manager;
import elaborato_ing_sw.model.Role;
import elaborato_ing_sw.utils.ShowView;
import elaborato_ing_sw.view.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	
    private static Stage primaryStage;
    private static BorderPane rootLayout;

	private UserDaoImpl userDao = UserDaoImpl.getUserDaoImpl();
	private ManagerDaoImpl managerDao = ManagerDaoImpl.getManagerDaoImpl();

    @Override
    public void start(Stage primaryStage) {
        MainApp.primaryStage = primaryStage;
        MainApp.primaryStage.setTitle("Shopping Online");

        System.out.println("Users from file: ");
        System.out.println(userDao.getAllUsers());

        /*
        Manager m1 = new Manager("man", "ager", LocalDate.of(1999, 8, 30), "pwd", 12356, Role.ADMIN);
        System.out.println(m1);
        managerDao.addUser(m1);
        managerDao.updateSource();
        */
        
        System.out.println("Managers from file: ");
        System.out.println(managerDao.getAllUsers());
        
        initRootLayout();
    	ShowView.showView("view/ManagerDashboard.fxml");

        // showLoginView();
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
    
    public void showLoginView() {
    	FXMLLoader loader = ShowView.showView("view/Login.fxml");
    	
        LoginController controller = loader.getController();
        
        // a cosa serve sta riga che funziona anche se viene tolta?
        //controller.setMainApp(this);
    }
    
	public void showUserPageDialog() {
		ShowView.showView("view/UserPage.fxml");
	}
    
    public static BorderPane getRootLayout() {
    	return MainApp.rootLayout;
    }
    
	public static Stage getPrimaryStage() {
		return MainApp.primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
