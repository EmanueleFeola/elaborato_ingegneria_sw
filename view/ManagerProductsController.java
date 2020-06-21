package elaborato_ing_sw.view;

import javafx.fxml.FXML;

import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import elaborato_ing_sw.dataManager.ProductDaoImpl;
import elaborato_ing_sw.facadeView.wrapperShowView;
import elaborato_ing_sw.model.Product;
import elaborato_ing_sw.model.Section;
import elaborato_ing_sw.model.SpecialProductProperty;
import elaborato_ing_sw.utils.AlertUtil;

public class ManagerProductsController {
	@FXML
	private TableView<Product> productsTable;
	@FXML
	private TableColumn<Product, String> nameColumn;
	@FXML
	private TableColumn<Product, String> brandColumn;
	@FXML
	private TableColumn<Product, Section> sectionColumn;
	@FXML
	private TableColumn<Product, Integer> pcsColumn;
	@FXML
	private TableColumn<Product, Double> priceColumn;
	@FXML
	private TableColumn<Product, SpecialProductProperty> property;

	@FXML
	private Label nameLabel;
	@FXML
	private Label brandLabel;
	@FXML
	private Label priceLabel;
	@FXML
	private Label pcsLabel;
	@FXML
	private Label sectionLabel;
	@FXML
	private Label isAvailableLabel;
	@FXML
	private Label iconPathLabel;
	@FXML
	private Label specialLabel;

	private ProductDaoImpl productDao = ProductDaoImpl.getProductDaoImpl();

	private Stage dialogStage;

	public ManagerProductsController() {
	}

	@FXML
	private void initialize() {
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		brandColumn.setCellValueFactory(cellData -> cellData.getValue().getBrandProperty());
		sectionColumn.setCellValueFactory(cellData -> cellData.getValue().getSectionProperty());
		pcsColumn.setCellValueFactory(cellData -> cellData.getValue().getPcsProperty());
		priceColumn.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
		property.setCellValueFactory(cellData -> cellData.getValue().getSpecialProperty());

		showProductDetails(null);

		productsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			showProductDetails((Product) newValue);
		});

		productsTable.setItems(productDao.getAllItems());
	}

	private void showProductDetails(Product p) {
		if (p != null) {
			// Fill the labels with info from the m object.
			nameLabel.setText(p.getName());
			brandLabel.setText(p.getBrand());
			sectionLabel.setText(p.getSection().toString());
			pcsLabel.setText(String.valueOf(p.getPcsPerPack()));
			priceLabel.setText(String.valueOf(p.getPrice()) + " euro");
			
			String[] splitFilename = p.getIconPath().split("/");
			iconPathLabel.setText(splitFilename[splitFilename.length - 1]);
			
			isAvailableLabel.setText(p.isAvailable() ? "Si" : "No");
			specialLabel.setText(p.getSpecialPty().toString());
		} else {
			// Product is null, remove all the text.
			nameLabel.setText("");
			brandLabel.setText("");
			sectionLabel.setText("");
			pcsLabel.setText("");
			priceLabel.setText("");
			iconPathLabel.setText("");
			isAvailableLabel.setText("");
			specialLabel.setText("");
		}
	}

	@FXML
	private void handleDeleteProduct() {
		int selectedIndex = productsTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			productDao.deleteItem(productsTable.getItems().get(selectedIndex));
		} else {
			AlertUtil.Alert(AlertType.WARNING, "No Selection", "No Product Selected",
					"Please select a product in the table.");
		}
	}

	@FXML
	private void handleNewProduct() {
		boolean okClicked = wrapperShowView.showProductEditDialog(null);

		if (okClicked) {
			System.out.println("Hai cliccato OK");
			productDao.addItem(ProductEditDialogController.getProduct());
		} else {
			System.out.println("Hai cliccato Cancel");
		}
	}

	@FXML
	private void handleEditProduct() {
		Product selectedManager = (Product) productsTable.getSelectionModel().getSelectedItem();
		if (selectedManager != null) {
			boolean okClicked = wrapperShowView.showProductEditDialog(selectedManager);

			if (okClicked) {
				productDao.updateItem(ProductEditDialogController.getProduct());
				showProductDetails(ProductEditDialogController.getProduct());
			}

		} else {
			AlertUtil.Alert(AlertType.WARNING, "No Selection", "No Product Selected",
					"Please select a product in the table.");
		}
	}

	@FXML
	private void handleClose() {
		dialogStage.close();
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
}
