package elaborato_ing_sw.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import elaborato_ing_sw.model.Product;
import elaborato_ing_sw.model.Section;
import elaborato_ing_sw.utils.AlertUtil;

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
	private RadioButton isAvailable;
	@FXML
	private ChoiceBox<Section> sectionField;
	
	private String iconPath;

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
		this.iconPath = "src/elaborato_ing_sw/view/images/logo.png";
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
		isAvailable.setSelected(product.isAvailable());
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
					Integer.parseInt(pcsField.getText()), Double.parseDouble(priceField.getText()),
					iconPath, isAvailable.isSelected(), 1);
		} else {
			product.setName(nameField.getText());
			product.setBrand(brandField.getText());
			product.setSection(sectionField.getValue());
			product.setPcsPerPack(Integer.parseInt(pcsField.getText()));
			product.setPrice(Double.parseDouble(priceField.getText()));
			product.setIconPath(iconPath);
			product.setAvailable(isAvailable.isSelected());
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

	@FXML
	private void handleSetImage() {
		// path di base per le immagini
		String imgPath = "src/elaborato_ing_sw/view/images";

		// genero il file chooser
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select an image");

		// imposto come directory iniziale quella indicata da imgPath
		fileChooser.setInitialDirectory(new File(imgPath));
		
		// aggiungo dei possibili filtri, selezionabili nella parte bassa della finestra
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.*"),
				new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("PNG", "*.png"));

		// apro la finestra
		File selectedFile = fileChooser.showOpenDialog(dialogStage);

		// creo il path dell'immagine e lo imposto al prodotto
		if (selectedFile != null) {
			System.out.println("File selected: " + selectedFile.getName());
			this.iconPath = imgPath + "/" + selectedFile.getName();
			System.out.println("product image set");			
		} else {
			System.out.println("File selection cancelled.");
		}
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

		if (pcsField.getText().length() == 0 || Integer.parseInt(pcsField.getText()) < 1)
			errorMessage += "No valid pieces per packet!\n";

		if (priceField.getText().length() == 0 || Double.parseDouble(priceField.getText()) < 0)
			errorMessage += "No valid price!\n";

		if (errorMessage.length() == 0)
			return true;
		else {
			AlertUtil.Alert(AlertType.ERROR, "Invalid Fields", "Please correct invalid fields", errorMessage);
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