package elaborato_ing_sw;

import elaborato_ing_sw.dataManager.ExpensesDaoImpl;
import elaborato_ing_sw.dataManager.FidelityCardDaoImpl;
import elaborato_ing_sw.dataManager.ManagerDaoImpl;
import elaborato_ing_sw.dataManager.ProductDaoImpl;
import elaborato_ing_sw.dataManager.ShoppingCartDaoImpl;
import elaborato_ing_sw.dataManager.UserDaoImpl;
import elaborato_ing_sw.utils.wrapperShowView;
import javafx.application.Application;
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

        /* print fot debug purposes only */
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
        
        wrapperShowView.initRootLayout();
        wrapperShowView.showLoginView();
    }
    
    public static BorderPane getRootLayout() {
    	return MainApp.rootLayout;
    }
    
    public static void setRootLayout(BorderPane bp) {
    	MainApp.rootLayout = bp;
    }
    
	public static Stage getPrimaryStage() {
		return MainApp.primaryStage;
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
