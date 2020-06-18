package elaborato_ing_sw.view;

import java.util.ArrayList;
import java.util.function.Predicate;

import elaborato_ing_sw.MainApp;
import elaborato_ing_sw.dataManager.FidelityCardDaoImpl;
import elaborato_ing_sw.dataManager.ProductDaoImpl;
import elaborato_ing_sw.dataManager.ShoppingCartDaoImpl;
import elaborato_ing_sw.imageProxy.ProxyImage;
import elaborato_ing_sw.model.FidelityCard;
import elaborato_ing_sw.model.Product;
import elaborato_ing_sw.model.Section;
import elaborato_ing_sw.model.ShoppingCart;
import elaborato_ing_sw.model.SpecialProductProperty;
import elaborato_ing_sw.model.User;
import elaborato_ing_sw.utils.AlertUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GroceryShoppingController {
	@FXML
	private TableView<Product> vegetablesTable, fruitTable, meat_fishTable, grain_foodsTable, dairy_productsTable,
			beveragesTable;

	@FXML
	// name columns for each tab
	private TableColumn<Product, String> n0, n1, n2, n3, n4, n5;

	@FXML
	// brand columns for each tab
	private TableColumn<Product, String> b0, b1, b2, b3, b4, b5;

	@FXML
	// price columns for each tab
	private TableColumn<Product, Double> pr0, pr1, pr2, pr3, pr4, pr5;

	@FXML
	private TabPane tabs;
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
	private Label specialProperty;
	@FXML
	private Label specialPropertyTitle;
	@FXML
	private ChoiceBox<SpecialProductProperty> property;
	@FXML
	private ImageView image;
	
	SpecialProductProperty filter = SpecialProductProperty.NONE;

	private FidelityCardDaoImpl fcardDao = FidelityCardDaoImpl.getFidelityCardImpl();
	private ProductDaoImpl productDao = ProductDaoImpl.getProductDaoImpl();
	private ShoppingCartDaoImpl shoppingCartDao = ShoppingCartDaoImpl.getShoppingCartDaoImpl();
	private MainApp mainApp;
	private Stage dialogStage;
	private User loggedUser;

	private ProxyImage proxy;

	@FXML
	private void initialize() {
		proxy = new ProxyImage();
		property.getItems().setAll(SpecialProductProperty.values());
		property.setValue(SpecialProductProperty.NONE);
		
		ChangeListener<SpecialProductProperty> changeListener = new ChangeListener<SpecialProductProperty>() { 
			@Override
			public void changed(ObservableValue<? extends SpecialProductProperty> observable, SpecialProductProperty oldValue,
					SpecialProductProperty newValue) {
				filter = newValue;

				System.out.println(newValue);
				// ogni volta che l utente cambia filtro aggiorno i dati della tabella
				// manca la parte di aggiornamento real time
			}
        };
        
        property.getSelectionModel().selectedItemProperty().addListener(changeListener);
	}

	@FXML
	private void handleAddToCart() {
		Tab selectedTab = tabs.getSelectionModel().getSelectedItem();
		AnchorPane selectedContent = (AnchorPane) selectedTab.getContent();

		@SuppressWarnings("unchecked")
		TableView<Product> selectedTable = (TableView<Product>) selectedContent.getChildren().get(0);

		int selectedIndex = selectedTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			String user = loggedUser.getCredentials().getUser();

			if (shoppingCartDao.getItem(user) == null) {
				ShoppingCart cart = new ShoppingCart(new ArrayList<Product>(), loggedUser);
				shoppingCartDao.addItem(cart);
			}

			Product p = selectedTable.getItems().get(selectedIndex);

			if (!p.isAvailable())
				AlertUtil.Alert(AlertType.INFORMATION, "Info", "Product not available", null);
			else if (shoppingCartDao.getCartProducts(user).contains(p))
				AlertUtil.Alert(AlertType.INFORMATION, "Info", "Product already in shopping cart",
						"Quantity can be modified in the shopping cart");
			else
				shoppingCartDao.addCartProduct(user, p);
		} else
			AlertUtil.Alert(AlertType.WARNING, "No Selection", "No Product Selected",
					"Please select a product in the table");
	}

	private void handleSection(TableColumn<Product, String> nameColumn, TableColumn<Product, String> brandColumn,
			TableColumn<Product, Double> priceColumn, TableView<Product> table, Section section) {
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		brandColumn.setCellValueFactory(cellData -> cellData.getValue().getBrandProperty());
		priceColumn.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());

		table.setItems(productDao.getAllItems().filtered(new Predicate<Product>() {
			@Override
			public boolean test(Product p) {
				if (p.getSection().equals(section) && filter.equals(SpecialProductProperty.NONE))
					return true;
				else if (p.getSection().equals(section) && p.getSpecialPty().equals(filter))
					return true;

				return false;
			}
		}));

		// add listener on table
		table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			showProductDetails((Product) newValue);
		});
	}

	private void showProductDetails(Product product) {
		if (product != null) {
			nameLabel.setText(product.getName());
			brandLabel.setText(product.getBrand());
			priceLabel.setText(String.valueOf(product.getPrice()) + " euro");
			isAvailable.setText(product.isAvailable() ? "Yes" : "No");
			npcsLabel.setText(String.valueOf(product.getPcsPerPack()));
			
			if(product.getSpecialPty() != SpecialProductProperty.NONE) {				
				specialProperty.setVisible(true);
				specialProperty.setText(product.getSpecialPty().toString());				
				specialPropertyTitle.setVisible(true);
			}
			else {
				specialProperty.setVisible(false);
				specialPropertyTitle.setVisible(false);
			}
			
			image.setImage(proxy.getImage(product.getIconPath()));
		} else {
			nameLabel.setText("");
			brandLabel.setText("");
			priceLabel.setText("");
			isAvailable.setText("");
			npcsLabel.setText("");
			specialProperty.setVisible(false); // perch� di default sarebbe none
			image.setImage(proxy.getImage(Product.getDefaultIconPath()));
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
		String user = loggedUser.getCredentials().getUser();

		// se per l utente "user" il carrello non esiste oppure contiene 0 prodotti non
		// ha senso far andare l utente alla prossima schermata
		if (shoppingCartDao.getItem(user) == null || shoppingCartDao.getCartProducts(user).size() == 0)
			AlertUtil.Alert(AlertType.INFORMATION, "User cart is empty", "Your cart is still empty",
					"Please add a product to your cart");
		else
			mainApp.showShoppingCartView(loggedUser);
	}

	@FXML
	private void handleProfile() {
		this.loggedUser = mainApp.showUserProfileView(loggedUser);
	}

	@FXML
	private void handleAllExpenses() {
		mainApp.showAllExpensesView(loggedUser);
	}

	@FXML
	private void handleViewFidelityCard() {
		mainApp.showFidelityCardView(loggedUser);
	}

	@FXML
	private void handleRemoveFidelityCard() {
		String user = loggedUser.getCredentials().getUser();
		FidelityCard card = fcardDao.getItem(user);

		if (card != null) {
			fcardDao.deleteItem(card);
			AlertUtil.Alert(AlertType.INFORMATION, "Fidelity Card removed",
					"Your Fidelity Card was successfully removed", null);
		} else {
			AlertUtil.Alert(AlertType.INFORMATION, "Fidelity Card not available", "You don't have a Fidelity Card",
					"You can get one in the view section");
		}
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