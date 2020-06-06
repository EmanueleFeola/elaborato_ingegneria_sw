package elaborato_ing_sw.view;

import elaborato_ing_sw.MainApp;
import elaborato_ing_sw.dataManager.ShoppingCartDaoImpl;
import elaborato_ing_sw.model.Product;
import elaborato_ing_sw.model.Section;
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
	
	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		name.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		brand.setCellValueFactory(cellData -> cellData.getValue().getBrandProperty());
		section.setCellValueFactory(cellData -> cellData.getValue().getSectionProperty());
		pcs.setCellValueFactory(cellData -> cellData.getValue().getPcsProperty());
		price.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
		quantity.setCellValueFactory(cellData -> cellData.getValue().getQuantityProperty());

		shoppingCartTable.setItems(shoppingCartDao.getAllItems());
	}
	
	@FXML
	private void handleDeleteProduct() {
		int selectedIndex = shoppingCartTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			shoppingCartDao.deleteItem(shoppingCartTable.getItems().get(selectedIndex));
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
			shoppingCartDao.updateItem(prod);
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
			if (qty-1 == 0) {
				shoppingCartDao.deleteItem(shoppingCartTable.getItems().get(selectedIndex));
			} else {
				qty--;
				Product prod = shoppingCartTable.getItems().get(selectedIndex);
				prod.setQuantity(qty);
				shoppingCartDao.updateItem(prod);
			}
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
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	@FXML
	private void handleClose() {
		dialogStage.close();
	}
}
