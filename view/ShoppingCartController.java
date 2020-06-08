package elaborato_ing_sw.view;

import java.util.ArrayList;
import elaborato_ing_sw.MainApp;
import elaborato_ing_sw.dataManager.ShoppingCartDaoImpl;
import elaborato_ing_sw.model.Product;
import elaborato_ing_sw.model.Section;
import elaborato_ing_sw.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
	private ArrayList<Product> prods;

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
		this.prods = shoppingCartDao.getItem(user).getProducts();
		
		ObservableList<Product> products = FXCollections.observableArrayList(prods);
		shoppingCartTable.setItems(products);
	}
	
	@FXML
	private void handleDeleteProduct() {
		int selectedIndex = shoppingCartTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			prods.remove(shoppingCartTable.getItems().get(selectedIndex));
			shoppingCartDao.updateSource();
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(MainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person Selected");
			alert.setContentText("Please select a person in the table.");

			alert.showAndWait();
		}
	}

	@FXML
	private void handleAddOne() {
		int selectedIndex = shoppingCartTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			int qty = shoppingCartTable.getItems().get(selectedIndex).getQuantity();
			qty++;
			Product prod = shoppingCartTable.getItems().get(selectedIndex);
			prod.setQuantity(qty);
			shoppingCartDao.updateSource();
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(MainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person Selected");
			alert.setContentText("Please select a person in the table.");

			alert.showAndWait();
		}
	}

	@FXML
	private void handleRemoveOne() {
		int selectedIndex = shoppingCartTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			int qty = shoppingCartTable.getItems().get(selectedIndex).getQuantity();
			if (qty - 1 == 0) {
				prods.remove(shoppingCartTable.getItems().get(selectedIndex));
			} else {
				qty--;
				Product prod = shoppingCartTable.getItems().get(selectedIndex);
				prod.setQuantity(qty);
			}
			shoppingCartDao.updateSource();
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(MainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person Selected");
			alert.setContentText("Please select a person in the table.");

			alert.showAndWait();
		}
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
	}
	
	@FXML
	private void handleClose() {
		dialogStage.close();
	}
}
