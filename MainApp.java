package elaborato_ing_sw;

import java.io.IOException;
import java.time.LocalDate;

import elaborato_ing_sw.dataManager.ManagerDaoImpl;
import elaborato_ing_sw.dataManager.ProductDaoImpl;
import elaborato_ing_sw.dataManager.UserDaoImpl;
import elaborato_ing_sw.model.Manager;
import elaborato_ing_sw.model.Product;
import elaborato_ing_sw.model.Role;
import elaborato_ing_sw.model.Section;
import elaborato_ing_sw.model.User;
import elaborato_ing_sw.utils.ShowView;
import elaborato_ing_sw.view.AllExpensesController;
import elaborato_ing_sw.view.GroceryShoppingController;
import elaborato_ing_sw.view.LoginController;
import elaborato_ing_sw.view.ManagerDashboardController;
import elaborato_ing_sw.view.ManagerEditDialogController;
import elaborato_ing_sw.view.ShoppingCartController;
import elaborato_ing_sw.view.UserProfileController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {
	
    private static Stage primaryStage;
    private static BorderPane rootLayout;

	private UserDaoImpl userDao = UserDaoImpl.getUserDaoImpl();
	private ManagerDaoImpl managerDao = ManagerDaoImpl.getManagerDaoImpl();
	private ProductDaoImpl productDao = ProductDaoImpl.getProductDaoImpl();

    @Override
    public void start(Stage primaryStage) {
        MainApp.primaryStage = primaryStage;
        MainApp.primaryStage.setTitle("Shopping Online");

        System.out.println("Users from file: ");
        System.out.println(userDao.getAllItems());
        
//        User u1 = new User("test", "test", LocalDate.of(1999, 8, 30), new Credentials("test", "test"), "street", "city", 123, "456");
//        userDao.addUser(u1);
        
//        Manager m1 = new Manager("man", "ager", LocalDate.of(1999, 8, 30), "pwd", 12356, Role.ADMIN);
//        System.out.println(m1);
//        managerDao.addItem(m1);
          
        System.out.println("Managers from file: ");
        System.out.println(managerDao.getAllItems());
        
//        Product p1 = new Product("pasta alla cazzo", "barilla", Section.GRAIN_FOODS, 100, 1.50, "src/elaborato_ing_sw/view/images/test.png", true);
//        Product p2 = new Product("banane", "fruttilandia", Section.FRUIT, 3, 1.00, "src/elaborato_ing_sw/view/images/test.png", true);
        
//        productDao.addItem(p1);
//        productDao.addItem(p2);
        
        System.out.println("Products from file: ");
        System.out.println(productDao.getAllItems());
        
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
    
    public void showLoginView() {
		FXMLLoader loader = ShowView.showView("view/Login.fxml");
		LoginController controller = loader.getController();
		controller.setMainApp(this);
    }
    
    public void showGroceryShoppingView(User user) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/GroceryShopping.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Grocery Shopping");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			GroceryShoppingController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setMainApp(this);
			controller.setLoggedUser(user);
			
			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();
    	} catch (IOException e) {
			e.printStackTrace();
		}    	
    	
    }
    
    public void showShoppingCartView() {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ShoppingCartView.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Shopping Cart");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			ShoppingCartController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			
			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();
    	} catch (IOException e) {
			e.printStackTrace();
		}
    }
       
    public void showManagerDashboard() {
		FXMLLoader loader = ShowView.showView("view/ManagerDashboard.fxml");
		ManagerDashboardController controller = loader.getController();
		controller.setMainApp(this);
	}
    
    public void showUserProfileView(User user) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/UserProfile.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit User");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			// Set the person into the controller.
			UserProfileController controller = loader.getController();
			controller.setLoggedUser(user);
			controller.setDialogStage(dialogStage);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}    	
    }
    
    public void showAllExpensesView(User user) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AllExpenses.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("All Expenses");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			// Set the person into the controller.
			AllExpensesController controller = loader.getController();
			controller.setLoggedUser(user);
			controller.setDialogStage(dialogStage);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}  
    }
    
    public boolean showManagerEditDialog(Manager manager) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ManagerEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Manager");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			// Set the person into the controller.
			ManagerEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setManager(manager);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
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
