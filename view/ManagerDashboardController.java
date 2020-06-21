package elaborato_ing_sw.view;

import javafx.fxml.FXML;

import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import elaborato_ing_sw.dataManager.ManagerDaoImpl;
import elaborato_ing_sw.model.Manager;
import elaborato_ing_sw.model.Person;
import elaborato_ing_sw.model.Role;
import elaborato_ing_sw.utils.AlertUtil;
import elaborato_ing_sw.utils.wrapperShowView;

public class ManagerDashboardController {
	@FXML
	private TableView<Person> managerTable;
	@FXML
	private TableColumn<Manager, String> firstNameColumn;
	@FXML
	private TableColumn<Manager, String> lastNameColumn;
	@FXML
	private TableColumn<Manager, Role> roleColumn;

	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label birthdayLabel;
	@FXML
	private Label serialNumberLabel;
	@FXML
	private Label roleLabel;

	private ManagerDaoImpl managerDao = ManagerDaoImpl.getManagerDaoImpl();

	public ManagerDashboardController() {
	}

	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().getSurnameProperty());
		roleColumn.setCellValueFactory(cellData -> cellData.getValue().getRoleProperty());

		// Clear manager details.
		showManagerDetails(null);

		// Listen for selection changes and show the person details when changed.
		managerTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			showManagerDetails((Manager) newValue);
		});

		managerTable.setItems(managerDao.getAllItems());
	}

	private void showManagerDetails(Manager m) {
		if (m != null) {
			// Fill the labels with info from the m object.
			firstNameLabel.setText(m.getName());
			lastNameLabel.setText(m.getSurname());
			serialNumberLabel.setText(String.valueOf(m.getSerialNumber()));
			roleLabel.setText(m.getRole().toString());
			birthdayLabel.setText(m.getDateOfBirth().toString());
		} else {
			// Person is null, remove all the text.
			firstNameLabel.setText("");
			lastNameLabel.setText("");
			serialNumberLabel.setText("");
			roleLabel.setText("");
			birthdayLabel.setText("");
		}
	}

	@FXML
	private void handleDeleteManager() {
		int selectedIndex = managerTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			managerDao.deleteItem(managerTable.getItems().get(selectedIndex));
		} else {
			AlertUtil.Alert(AlertType.WARNING, "No Selection", "No Person Selected",
					"Please select a person in the table.");
		}
	}

	@FXML
	private void handleNewManager() {
		boolean okClicked = wrapperShowView.showManagerEditDialog(null);

		if (okClicked) {
			System.out.println("Hai cliccato OK");
			managerDao.addItem(ManagerEditDialogController.getManager());
		} else {
			System.out.println("Hai cliccato Cancel");
		}
	}

	@FXML
	private void handleEditManager() {
		Manager selectedManager = (Manager) managerTable.getSelectionModel().getSelectedItem();
		if (selectedManager != null) {
			boolean okClicked = wrapperShowView.showManagerEditDialog(selectedManager);

			if (okClicked) {
				managerDao.updateItem(ManagerEditDialogController.getManager());
				showManagerDetails(ManagerEditDialogController.getManager());
			}

		} else {
			AlertUtil.Alert(AlertType.WARNING, "No Selection", "No Person Selected",
					"Please select a person in the table.");
		}
	}

	@FXML
	private void handleLogout() {
		wrapperShowView.showLoginView();
		System.out.println("Logged out successfully");
	}

	@FXML
	private void handleProducts() {
		wrapperShowView.showManagerProducts();
		System.out.println("Redirected to manage products view");
	}
	
	@FXML
	private void handleExpenses() {
		wrapperShowView.showManagerExpensesView();
	}
}
