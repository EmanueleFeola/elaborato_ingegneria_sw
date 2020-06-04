package elaborato_ing_sw.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.function.Predicate;

import elaborato_ing_sw.MainApp;
import elaborato_ing_sw.dataManager.ProductDaoImpl;
import elaborato_ing_sw.model.Product;
import elaborato_ing_sw.model.Section;
import elaborato_ing_sw.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GroceryShoppingController {
	@FXML
	private TableView<Product> vegetablesTable, fruitTable, meat_fishTable, grain_foodsTable, dairy_productsTable, beveragesTable;

	@FXML
	private TableColumn<Product, String> n0, n1, n2, n3, n4, n5;

	@FXML
	private TableColumn<Product, String> b0, b1, b2, b3, b4, b5;

	@FXML
	private TableColumn<Product, Double> pr0, pr1, pr2, pr3, pr4, pr5;

	@FXML
	private Label nameLabel;
	
	@FXML
	private Label brandLabel;
	
	@FXML
	private Label priceLabel;
	
	@FXML
	private Label npcsLabel;
	
	@FXML
	private Label isAvailable;

	@FXML
	private ImageView image;

	private ProductDaoImpl productDao = ProductDaoImpl.getProductDaoImpl();
	private MainApp mainApp;
	private Stage dialogStage;
	private User loggedUser;

	private File standardLogoFile = new File("src/elaborato_ing_sw/view/logo.png");
	private Image standardLogoImage = new Image(standardLogoFile.toURI().toString());

	@FXML
	private void initialize() {

	}

	private void handleSection
		(
			TableColumn<Product, String> f0,
			TableColumn<Product, String> f1,
			TableColumn<Product, Double> f2,
			TableView<Product> tv,
			Section section
		)
	{
		f0.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		f1.setCellValueFactory(cellData -> cellData.getValue().getBrandProperty());
		f2.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
		
		tv.setItems(productDao.getAllItems().filtered(new Predicate<Product>() {

			@Override
			public boolean test(Product p) {
				if (p.getSection().equals(section))
					return true;
				return false;
			}
		}));
		
		tv.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			showProductDetails((Product) newValue);
		});
	}
	
	private void showProductDetails(Product product) {
		if (product != null) {
			nameLabel.setText(product.getName());
			brandLabel.setText(product.getBrand());
			priceLabel.setText(String.valueOf(product.getPrice()) + " $");
			isAvailable.setText(product.isAvailable() ? "Yes" : "No");
			npcsLabel.setText(String.valueOf(product.getPcsPerPack()));
			
			File fileImage = new File(product.getIconPath());
			Image icon = new Image(fileImage.toURI().toString());
			image.setImage(icon);
		} else {
			// Person is null, remove all the text.
			nameLabel.setText("");
			brandLabel.setText("");
			priceLabel.setText("");
			isAvailable.setText("");
			npcsLabel.setText("");
			image.setImage(standardLogoImage);
		}
	}
	
	@FXML
	private void handleVegetables() {
		handleSection(n0, b0, pr0, vegetablesTable, Section.VEGETABLES);
	}

	@FXML
	private void handleFruit() {
		handleSection(n1, b1, pr1, fruitTable, Section.FRUIT);
	}

	@FXML
	private void handleMeatFish() {
		handleSection(n2, b2, pr2, meat_fishTable, Section.MEAT_FISH);
	}

	@FXML
	private void handleGrainFoods() {
		handleSection(n3, b3, pr3, grain_foodsTable, Section.GRAIN_FOODS);
	}

	@FXML
	private void handleDairyProducts() {
		handleSection(n4, b4, pr4, dairy_productsTable, Section.DAIRY_PRODUCTS);
	}

	@FXML
	private void handleBeverages() {
		handleSection(n5, b5, pr5, beveragesTable, Section.BEVERAGES);
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
