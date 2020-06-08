package elaborato_ing_sw.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import elaborato_ing_sw.model.Product;
import elaborato_ing_sw.model.Section;

public class ProductEditDialogController {

	@FXML
	private TextField nameField;
	@FXML
	private TextField brandField;
	@FXML
	private TextField pcsField;
	@FXML
	private TextField priceField;
	@FXML
	private TextField isAvailable; // da trasformare in bottone on off
	@FXML
	private ChoiceBox<Section> sectionField;

	private Stage dialogStage;
	private static Product product;
	private boolean okClicked = false;

	@SuppressWarnings("unused")
	private boolean isNewMode; // 1 per new, 0 per edit

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

	}

	/**
	 * Sets the stage of this dialog.
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * Sets the person to be edited in the dialog.
	 * 
	 * @param person
	 */
	public void setProduct(Product m) {
		product = m;
		System.out.println("---");
		System.out.println("product: " + product);

		sectionField.getItems().setAll(Section.values());

		if (product == null) {
			isNewMode = true;
			return;
		}

		nameField.setText(product.getName());
		brandField.setText(product.getBrand());
		sectionField.setValue(product.getSection());
		pcsField.setText(String.valueOf(product.getPcsPerPack()));
		priceField.setText(String.valueOf(product.getPrice()));
		isAvailable.setText(product.isAvailable() ? "Si" : "No");
	}

	/**
	 * Returns true if the user clicked OK, false otherwise.
	 * 
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleOk() {
		if (!isInputValid())
			return;

		if (product == null) {
			product = new Product(nameField.getText(), brandField.getText(), sectionField.getValue(),
					Integer.parseInt(pcsField.getText()), Double.parseDouble(priceField.getText()), "test.png", true, 1);
		} else {
			product.setName(nameField.getText());
			product.setBrand(brandField.getText());
			product.setSection(sectionField.getValue());
			product.setPcsPerPack(Integer.parseInt(pcsField.getText()));
			product.setPrice(Double.parseDouble(priceField.getText()));
			product.setIconPath("test.png");
			product.setAvailable(true);
			product.setQuantity(1);
		}

		okClicked = true;
		dialogStage.close();
	}

	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	/**
	 * Validates the user input in the text fields.
	 * 
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {
		String errorMessage = "";

		if (nameField.getText() == null || nameField.getText().length() == 0)
			errorMessage += "No valid first name!\n";

		if (brandField.getText() == null || brandField.getText().length() == 0)
			errorMessage += "No valid last name!\n";

		if (sectionField.getValue() == null)
			errorMessage += "No valid section!\n";

		if (Integer.parseInt(pcsField.getText()) < 1)
			errorMessage += "No valid pieces per packet!\n";

		if (Double.parseDouble(priceField.getText()) < 0)
			errorMessage += "No valid price!\n";

		if (errorMessage.length() == 0)
			return true;
		else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}

	/**
	 * Restituisce l'oggetto product modificato oppure creato nuovo
	 */
	public static Product getProduct() {
		return product;
	}

}