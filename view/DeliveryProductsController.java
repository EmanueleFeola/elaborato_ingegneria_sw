package elaborato_ing_sw.view;

import elaborato_ing_sw.dataManager.ExpensesDaoImpl;
import elaborato_ing_sw.model.Product;
import elaborato_ing_sw.model.Section;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class DeliveryProductsController {
	@FXML
	private TableView<Product> productsDetailsTable;

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

	private ExpensesDaoImpl expensesDao = ExpensesDaoImpl.getExpensesDaoImpl();
	private Stage dialogStage;
	private int expenseId;

	ObservableList<Product> prods;

	private void handleTable() {
		name.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		brand.setCellValueFactory(cellData -> cellData.getValue().getBrandProperty());
		section.setCellValueFactory(cellData -> cellData.getValue().getSectionProperty());
		pcs.setCellValueFactory(cellData -> cellData.getValue().getPcsProperty());
		price.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
		quantity.setCellValueFactory(cellData -> cellData.getValue().getQuantityProperty());

		this.prods = FXCollections.observableArrayList(expensesDao.getItemById(expenseId).getProducts().keySet());
		productsDetailsTable.setItems(prods);

	}

	public void setExpenseId(int id) {
		this.expenseId = id;
		handleTable();
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	@FXML
	private void handleClose() {
		dialogStage.close();
	}
}
