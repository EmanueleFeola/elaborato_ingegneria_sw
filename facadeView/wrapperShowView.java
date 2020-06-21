package elaborato_ing_sw.facadeView;

import java.io.IOException;

import elaborato_ing_sw.MainApp;
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
import elaborato_ing_sw.view.ManagerEditDialogController;
import elaborato_ing_sw.view.ManagerExpensesController;
import elaborato_ing_sw.view.ManagerProductsController;
import elaborato_ing_sw.view.ProductEditDialogController;
import elaborato_ing_sw.view.ShoppingCartController;
import elaborato_ing_sw.view.UserProfileController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class wrapperShowView {
	
    public static void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootView.fxml"));
            MainApp.setRootLayout((BorderPane) loader.load());
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(MainApp.getRootLayout());
            MainApp.getPrimaryStage().setScene(scene);
            MainApp.getPrimaryStage().show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	public static void showLoginView() {
		FXMLLoader loader = ShowView.showView("view/LoginView.fxml");
		loader.getController();
	}
	
    public static void showManagerProducts() {
    	FXMLLoader loader = ShowDialog.getLoader("view/ManagerProductsView.fxml");
    	Stage dialogStage = ShowDialog.getDialogStage("Manager Products", loader, MainApp.getPrimaryStage());
    	ManagerProductsController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		dialogStage.showAndWait();
    }
    
    public static void showGroceryShoppingView(User user) {
    	FXMLLoader loader = ShowDialog.getLoader("view/GroceryShoppingView.fxml");
    	Stage dialogStage = ShowDialog.getDialogStage("Grocery Shopping", loader, MainApp.getPrimaryStage());
		GroceryShoppingController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setLoggedUser(user);
		dialogStage.showAndWait();
    }
    
    public static void showShoppingCartView(User user) {
    	FXMLLoader loader = ShowDialog.getLoader("view/ShoppingCartView.fxml");
    	Stage dialogStage = ShowDialog.getDialogStage("Shopping Cart", loader, MainApp.getPrimaryStage());
		ShoppingCartController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setLoggedUser(user);
		dialogStage.showAndWait();
    }
    
    public static boolean showDeliveryView(User user) {
    	FXMLLoader loader = ShowDialog.getLoader("view/DeliveryView.fxml");
    	Stage dialogStage = ShowDialog.getDialogStage("Delivery", loader, MainApp.getPrimaryStage());
		DeliveryController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setLoggedUser(user);
		dialogStage.showAndWait();
		return controller.isOkClicked();
    }
    
    public static void showManagerDashboard() {
		FXMLLoader loader = ShowView.showView("view/ManagerDashboardView.fxml");
		loader.getController();
	}
	
    public static User showUserProfileView(User user) {
    	FXMLLoader loader = ShowDialog.getLoader("view/UserProfileView.fxml");
    	Stage dialogStage = ShowDialog.getDialogStage("Edit User", loader, MainApp.getPrimaryStage());
		UserProfileController controller = loader.getController();
		controller.setLoggedUser(user);
		controller.setDialogStage(dialogStage);
		dialogStage.showAndWait();
		return controller.getLoggedUser();
    }
    
    public static void showFidelityCardView(User user) {
    	FXMLLoader loader = ShowDialog.getLoader("view/FidelityCardView.fxml");
    	Stage dialogStage = ShowDialog.getDialogStage("Fidelity Card", loader, MainApp.getPrimaryStage());
		FidelityCardController controller = loader.getController();
		controller.setLoggedUser(user);
		controller.setDialogStage(dialogStage);
		dialogStage.showAndWait();
    }
    
    public static void showAllExpensesView(User user) {
    	FXMLLoader loader = ShowDialog.getLoader("view/AllExpensesView.fxml");
    	Stage dialogStage = ShowDialog.getDialogStage("All Expenses", loader, MainApp.getPrimaryStage());
		AllExpensesController controller = loader.getController();
		controller.setLoggedUser(user);
		controller.setDialogStage(dialogStage);
		dialogStage.showAndWait();
    }
    
    public static void showDeliveryProductsView(int id) {
    	FXMLLoader loader = ShowDialog.getLoader("view/DeliveryProductsView.fxml");
    	Stage dialogStage = ShowDialog.getDialogStage("Expense Products Details", loader, MainApp.getPrimaryStage());
		DeliveryProductsController controller = loader.getController();
		controller.setExpenseId(id);
		controller.setDialogStage(dialogStage);
		dialogStage.showAndWait();
    }
    
    public static boolean showManagerEditDialog(Manager manager) {
    	FXMLLoader loader = ShowDialog.getLoader("view/ManagerEditView.fxml");
    	Stage dialogStage = ShowDialog.getDialogStage("Edit Managers", loader, MainApp.getPrimaryStage());
		ManagerEditDialogController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setManager(manager);
		dialogStage.showAndWait();
		return controller.isOkClicked();
	}
    
    public static boolean showProductEditDialog(Product product) {
    	FXMLLoader loader = ShowDialog.getLoader("view/ProductEditView.fxml");
    	Stage dialogStage = ShowDialog.getDialogStage("Edit Products", loader, MainApp.getPrimaryStage());
		ProductEditDialogController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setProduct(product);
		dialogStage.showAndWait();
		return controller.isOkClicked();
	}
    
    public static void showManagerExpensesView() {
    	FXMLLoader loader = ShowDialog.getLoader("view/ManagerExpensesView.fxml");
    	Stage dialogStage = ShowDialog.getDialogStage("Manage Expenses", loader, MainApp.getPrimaryStage());
		ManagerExpensesController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		dialogStage.showAndWait();
    }
    
    public static void showEditDeliveryStatusView(int id) {
    	FXMLLoader loader = ShowDialog.getLoader("view/EditDeliveryStatusView.fxml");
    	Stage dialogStage = ShowDialog.getDialogStage("Expense Status Edit", loader, MainApp.getPrimaryStage());
		EditExpenseController controller = loader.getController();
		controller.setExpenseId(id);
		controller.setDialogStage(dialogStage);
		dialogStage.showAndWait();
    }
    
}
