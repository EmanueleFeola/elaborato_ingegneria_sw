package elaborato_ing_sw.view;

import java.util.ArrayList;
import elaborato_ing_sw.MainApp;
import elaborato_ing_sw.dataManager.ShoppingCartDaoImpl;
import elaborato_ing_sw.model.Product;
import elaborato_ing_sw.model.Section;
import elaborato_ing_sw.model.User;
import elaborato_ing_sw.utils.AlertUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ShoppingCartController {
	@FXML
	private TableView<Product> shoppingCartTable;
	@FXML
	private TableColumn<Product, String> name;
	@FXML
	private TableColumn<Product, String> brand;
	@FXML
	private TableColumn<Product, Section> section;
	@FXML
	private TableColumn<Product, Integer> pcs;
	@FXML
	private TableColumn<Product, Double> price;
	@FXML
	private TableColumn<Product, Integer> quantity;

	private ShoppingCartDaoImpl shoppingCartDao = ShoppingCartDaoImpl.getShoppingCartDaoImpl();
	private Stage dialogStage;
	private MainApp mainApp;
	private User loggedUser;
	
	private String user;
	
	private ArrayList<Product> cartProducts;
	ObservableList<Product> tableProducts;

	@FXML
	private void initialize() {					
	}
	
	@FXML
	private void handleTable() {
		name.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		brand.setCellValueFactory(cellData -> cellData.getValue().getBrandProperty());
		section.setCellValueFactory(cellData -> cellData.getValue().getSectionProperty());
		pcs.setCellValueFactory(cellData -> cellData.getValue().getPcsProperty());
		price.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
		quantity.setCellValueFactory(cellData -> cellData.getValue().getQuantityProperty());
		
		this.user = loggedUser.getCredentials().getUser();
		this.cartProducts = shoppingCartDao.getCartProducts(user);
		this.tableProducts = FXCollections.observableArrayList(cartProducts);
		shoppingCartTable.setItems(tableProducts);
	}
	
	@FXML
	private void handleDeleteProduct() {
		int selectedIndex = shoppingCartTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			shoppingCartDao.removeCartProduct(user, shoppingCartTable.getItems().get(selectedIndex));
			tableProducts.remove(selectedIndex);
		} else
			AlertUtil.Alert(AlertType.WARNING, "No Selection", "No Product Selected", "Please select a product in the table");
	}

	@FXML
	private void handleAddOne() {
		int selectedIndex = shoppingCartTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Product prod = shoppingCartTable.getItems().get(selectedIndex);
			int qty = prod.getQuantity();
			qty++;
			prod.setQuantity(qty);
			shoppingCartDao.updateSource();
		} else
			AlertUtil.Alert(AlertType.WARNING, "No Selection", "No Product Selected", "Please select a product in the table");
	}

	@FXML
	private void handleRemoveOne() {
		int selectedIndex = shoppingCartTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Product prod = shoppingCartTable.getItems().get(selectedIndex);
			int qty = prod.getQuantity();
			
			if (qty - 1 == 0) {
				shoppingCartDao.removeCartProduct(user, shoppingCartTable.getItems().get(selectedIndex));
				tableProducts.remove(selectedIndex);
			} else {
				qty--;
				prod.setQuantity(qty);
			}
			shoppingCartDao.updateSource();
		} else
			AlertUtil.Alert(AlertType.WARNING, "No Selection", "No Product Selected", "Please select a product in the table");
	}

	@FXML
	private void handleDeliver() {
		mainApp.showDeliveryView(loggedUser);
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
		handleTable();
	}
	
	@FXML
	private void handleClose() {
		dialogStage.close();
	}
}
