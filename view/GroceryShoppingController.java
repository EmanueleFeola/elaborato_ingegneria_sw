package elaborato_ing_sw.view;

import java.util.function.Predicate;

import elaborato_ing_sw.MainApp;
import elaborato_ing_sw.dataManager.ProductDaoImpl;
import elaborato_ing_sw.model.Product;
import elaborato_ing_sw.model.Section;
import elaborato_ing_sw.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class GroceryShoppingController {
	@FXML
	private TableView<Product> vegetablesTable;
	@FXML
	private TableView<Product> fruitTable;
	@FXML
	private TableView<Product> meat_fishTable;
	@FXML
	private TableView<Product> grain_foodsTable;
	@FXML
	private TableView<Product> dairy_productsTable;
	@FXML
	private TableView<Product> beveragesTable;

	@FXML
	private TableColumn<Product, String> n0;
	@FXML
	private TableColumn<Product, String> n1;
	@FXML
	private TableColumn<Product, String> n2;
	@FXML
	private TableColumn<Product, String> n3;
	@FXML
	private TableColumn<Product, String> n4;
	@FXML
	private TableColumn<Product, String> n5;

	@FXML
	private TableColumn<Product, String> b0;
	@FXML
	private TableColumn<Product, String> b1;
	@FXML
	private TableColumn<Product, String> b2;
	@FXML
	private TableColumn<Product, String> b3;
	@FXML
	private TableColumn<Product, String> b4;
	@FXML
	private TableColumn<Product, String> b5;

	@FXML
	private TableColumn<Product, Integer> pc0;
	@FXML
	private TableColumn<Product, Integer> pc1;
	@FXML
	private TableColumn<Product, Integer> pc2;
	@FXML
	private TableColumn<Product, Integer> pc3;
	@FXML
	private TableColumn<Product, Integer> pc4;
	@FXML
	private TableColumn<Product, Integer> pc5;

	@FXML
	private TableColumn<Product, Double> pr0;
	@FXML
	private TableColumn<Product, Double> pr1;
	@FXML
	private TableColumn<Product, Double> pr2;
	@FXML
	private TableColumn<Product, Double> pr3;
	@FXML
	private TableColumn<Product, Double> pr4;
	@FXML
	private TableColumn<Product, Double> pr5;

	private ProductDaoImpl productDao = ProductDaoImpl.getProductDaoImpl();

	private MainApp mainApp;
	private Stage dialogStage;
	private User loggedUser;

	@FXML
	private void initialize() {

	}

	@FXML
	private void handleVegetables() {
		n0.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		b0.setCellValueFactory(cellData -> cellData.getValue().getBrandProperty());
		pc0.setCellValueFactory(cellData -> cellData.getValue().getPcsProperty());
		pr0.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());

		vegetablesTable.setItems(productDao.getAllItems().filtered(new Predicate<Product>() {

			@Override
			public boolean test(Product p) {
				if (p.getSection().equals(Section.VEGETABLES))
					return true;
				return false;
			}
		}));
	}

	@FXML
	private void handleFruit() {
		n1.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		b1.setCellValueFactory(cellData -> cellData.getValue().getBrandProperty());
		pc1.setCellValueFactory(cellData -> cellData.getValue().getPcsProperty());
		pr1.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());

		fruitTable.setItems(productDao.getAllItems().filtered(new Predicate<Product>() {

			@Override
			public boolean test(Product p) {
				if (p.getSection().equals(Section.FRUIT))
					return true;
				return false;
			}
		}));
	}

	@FXML
	private void handleMeatFish() {
		n2.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		b2.setCellValueFactory(cellData -> cellData.getValue().getBrandProperty());
		pc2.setCellValueFactory(cellData -> cellData.getValue().getPcsProperty());
		pr2.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());

		meat_fishTable.setItems(productDao.getAllItems().filtered(new Predicate<Product>() {

			@Override
			public boolean test(Product p) {
				if (p.getSection().equals(Section.MEAT_FISH))
					return true;
				return false;
			}
		}));
	}

	@FXML
	private void handleGrainFoods() {
		n3.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		b3.setCellValueFactory(cellData -> cellData.getValue().getBrandProperty());
		pc3.setCellValueFactory(cellData -> cellData.getValue().getPcsProperty());
		pr3.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());

		grain_foodsTable.setItems(productDao.getAllItems().filtered(new Predicate<Product>() {

			@Override
			public boolean test(Product p) {
				if (p.getSection().equals(Section.GRAIN_FOODS))
					return true;
				return false;
			}
		}));
	}

	@FXML
	private void handleDairyProducts() {
		n4.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		b4.setCellValueFactory(cellData -> cellData.getValue().getBrandProperty());
		pc4.setCellValueFactory(cellData -> cellData.getValue().getPcsProperty());
		pr4.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());

		dairy_productsTable.setItems(productDao.getAllItems().filtered(new Predicate<Product>() {

			@Override
			public boolean test(Product p) {
				if (p.getSection().equals(Section.DAIRY_PRODUCTS))
					return true;
				return false;
			}
		}));
	}

	@FXML
	private void handleBeverages() {
		n5.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		b5.setCellValueFactory(cellData -> cellData.getValue().getBrandProperty());
		pc5.setCellValueFactory(cellData -> cellData.getValue().getPcsProperty());
		pr5.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());

		beveragesTable.setItems(productDao.getAllItems().filtered(new Predicate<Product>() {

			@Override
			public boolean test(Product p) {
				if (p.getSection().equals(Section.BEVERAGES))
					return true;
				return false;
			}
		}));
	}

	@FXML
	private void handleLogout() {
		dialogStage.close();
		mainApp.showLoginView();
		System.out.println("Logged out successfully");
	}

	@FXML
	private void handleShoppingCart() {
		mainApp.showShoppingCartView();
	}

	@FXML
	private void handleProfile() {
		mainApp.showUserProfileView(loggedUser);
	}

	@FXML
	private void handleAllExpenses() {
		mainApp.showAllExpensesView(loggedUser);
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
}
