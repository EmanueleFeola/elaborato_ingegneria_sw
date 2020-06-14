package elaborato_ing_sw;

import java.io.IOException;
import elaborato_ing_sw.dataManager.ExpensesDaoImpl;
import elaborato_ing_sw.dataManager.FidelityCardDaoImpl;
import elaborato_ing_sw.dataManager.ManagerDaoImpl;
import elaborato_ing_sw.dataManager.ProductDaoImpl;
import elaborato_ing_sw.dataManager.ShoppingCartDaoImpl;
import elaborato_ing_sw.dataManager.UserDaoImpl;
import elaborato_ing_sw.model.Manager;
import elaborato_ing_sw.model.Product;
import elaborato_ing_sw.model.User;
import elaborato_ing_sw.utils.ShowDialog;
import elaborato_ing_sw.utils.ShowView;
import elaborato_ing_sw.view.AllExpensesController;
import elaborato_ing_sw.view.DeliveryController;
import elaborato_ing_sw.view.DeliveryProductsController;
import elaborato_ing_sw.view.EditExpenseController;
import elaborato_ing_sw.view.FidelityCardController;
import elaborato_ing_sw.view.GroceryShoppingController;
import elaborato_ing_sw.view.LoginController;
import elaborato_ing_sw.view.ManagerDashboardController;
import elaborato_ing_sw.view.ManagerEditDialogController;
import elaborato_ing_sw.view.ManagerExpensesController;
import elaborato_ing_sw.view.ManagerProductsController;
import elaborato_ing_sw.view.ProductEditDialogController;
import elaborato_ing_sw.view.ShoppingCartController;
import elaborato_ing_sw.view.UserProfileController;
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
	private ProductDaoImpl productDao = ProductDaoImpl.getProductDaoImpl();
	private ShoppingCartDaoImpl shoppingCartDao = ShoppingCartDaoImpl.getShoppingCartDaoImpl();
	private ExpensesDaoImpl expensesDao = ExpensesDaoImpl.getExpensesDaoImpl();
	private FidelityCardDaoImpl fcardDao = FidelityCardDaoImpl.getFidelityCardImpl();
	
    @Override
    public void start(Stage primaryStage) {
        MainApp.primaryStage = primaryStage;
        MainApp.primaryStage.setTitle("Shopping Online");

        System.out.println("Users from file: ");
        System.out.println(userDao.getAllItems());
        
        System.out.println("Fidelity Cards from file: ");
        System.out.println(fcardDao.getAllItems());
        
        System.out.println("Managers from file: ");
        System.out.println(managerDao.getAllItems());

        System.out.println("Products from file: ");
        System.out.println(productDao.getAllItems());
        
        System.out.println("Shopping Cart from file: ");
        System.out.println(shoppingCartDao.getAllItems());
        
        System.out.println("Expenses from file: ");
        System.out.println(expensesDao.getAllItems());
        
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
            loader.setLocation(MainApp.class.getResource("view/RootView.fxml"));
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
		FXMLLoader loader = ShowView.showView("view/LoginView.fxml");
		LoginController controller = loader.getController();
		controller.setMainApp(this);
    }
    
    public void showManagerProducts() {
		FXMLLoader loader = ShowView.showView("view/ManagerProductsView.fxml");
		ManagerProductsController controller = loader.getController();
		controller.setMainApp(this);
    }
    
    public void showGroceryShoppingView(User user) {
    	FXMLLoader loader = ShowDialog.getLoader("view/GroceryShoppingView.fxml");
    	
    	Stage dialogStage = ShowDialog.getDialogStage("Grocery Shopping", loader, primaryStage);
    	
		GroceryShoppingController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setMainApp(this);
		controller.setLoggedUser(user);

		dialogStage.showAndWait();
    }
    
    public void showShoppingCartView(User user) {
    	FXMLLoader loader = ShowDialog.getLoader("view/ShoppingCartView.fxml");
    	
    	Stage dialogStage = ShowDialog.getDialogStage("Shopping Cart", loader, primaryStage);
    	
		ShoppingCartController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setMainApp(this);
		controller.setLoggedUser(user);

		dialogStage.showAndWait();
    }
    
    public boolean showDeliveryView(User user) {
    	FXMLLoader loader = ShowDialog.getLoader("view/DeliveryView.fxml");
    	
    	Stage dialogStage = ShowDialog.getDialogStage("Delivery", loader, primaryStage);
    	
		DeliveryController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setLoggedUser(user);

		dialogStage.showAndWait();
		
		return controller.isOkClicked();
    }
       
    public void showManagerDashboard() {
		FXMLLoader loader = ShowView.showView("view/ManagerDashboardView.fxml");
		ManagerDashboardController controller = loader.getController();
		controller.setMainApp(this);
	}
    
    public User showUserProfileView(User user) {
    	FXMLLoader loader = ShowDialog.getLoader("view/UserProfileView.fxml");
    	
    	Stage dialogStage = ShowDialog.getDialogStage("Edit User", loader, primaryStage);
    	
		UserProfileController controller = loader.getController();
		controller.setLoggedUser(user);
		controller.setDialogStage(dialogStage);

		dialogStage.showAndWait();
		
		return controller.getLoggedUser();
    }
    
    public void showFidelityCardView(User user) {
    	FXMLLoader loader = ShowDialog.getLoader("view/FidelityCardView.fxml");
    	
    	Stage dialogStage = ShowDialog.getDialogStage("Fidelity Card", loader, primaryStage);
    	
		FidelityCardController controller = loader.getController();
		controller.setLoggedUser(user);
		controller.setDialogStage(dialogStage);

		dialogStage.showAndWait();
    }
    
    public void showAllExpensesView(User user) {
    	FXMLLoader loader = ShowDialog.getLoader("view/AllExpensesView.fxml");
    	
    	Stage dialogStage = ShowDialog.getDialogStage("All Expenses", loader, primaryStage);
    	
		AllExpensesController controller = loader.getController();
		controller.setMainApp(this);
		controller.setLoggedUser(user);
		controller.setDialogStage(dialogStage);

		dialogStage.showAndWait();
    }
    
    public void showDeliveryProductsView(int id) {
    	FXMLLoader loader = ShowDialog.getLoader("view/DeliveryProductsView.fxml");
    	
    	Stage dialogStage = ShowDialog.getDialogStage("Expense Products Details", loader, primaryStage);
    	
		DeliveryProductsController controller = loader.getController();
		controller.setExpenseId(id);
		controller.setDialogStage(dialogStage);

		dialogStage.showAndWait();
    }
    
    public boolean showManagerEditDialog(Manager manager) {
    	FXMLLoader loader = ShowDialog.getLoader("view/ManagerEditView.fxml");
    	
    	Stage dialogStage = ShowDialog.getDialogStage("Edit Managers", loader, primaryStage);
    	
		ManagerEditDialogController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setManager(manager);

		dialogStage.showAndWait();
		
		return controller.isOkClicked();
	}
    
    public boolean showProductEditDialog(Product product) {
    	FXMLLoader loader = ShowDialog.getLoader("view/ProductEditView.fxml");
    	
    	Stage dialogStage = ShowDialog.getDialogStage("Edit Products", loader, primaryStage);
    	
		ProductEditDialogController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setProduct(product);

		dialogStage.showAndWait();
		
		return controller.isOkClicked();
	}
    
    public void showManagerExpensesView() {
    	FXMLLoader loader = ShowDialog.getLoader("view/ManagerExpensesView.fxml");
    	
    	Stage dialogStage = ShowDialog.getDialogStage("Manage Expenses", loader, primaryStage);
    	
		ManagerExpensesController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setMainApp(this);
		

		dialogStage.showAndWait();
    }
    
    public void showEditDeliveryStatusView(int id) {
    	FXMLLoader loader = ShowDialog.getLoader("view/EditDeliveryStatusView.fxml");
    	
    	Stage dialogStage = ShowDialog.getDialogStage("Expense Status Edit", loader, primaryStage);
    	
		EditExpenseController controller = loader.getController();
		controller.setExpenseId(id);
		controller.setDialogStage(dialogStage);

		dialogStage.showAndWait();
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
