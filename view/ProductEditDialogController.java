package elaborato_ing_sw.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.nio.file.Paths;

import elaborato_ing_sw.model.Product;
import elaborato_ing_sw.model.Section;
import elaborato_ing_sw.model.SpecialProductProperty;
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
	private TextField iconPathField;
	@FXML
	private RadioButton isAvailable;
	@FXML
	private ChoiceBox<Section> sectionField;
	@FXML
	private ChoiceBox<SpecialProductProperty> propertyField;
	
	private String iconPath;

	private Stage dialogStage;
	private static Product product;
	private boolean okClicked = false;

	@SuppressWarnings("unused")
	private boolean isNewMode; // 1 per new, 0 per edit

	@FXML
	private void initialize() {
		this.iconPath = Product.getDefaultIconPath();
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setProduct(Product p) {
		product = p;
		System.out.println("---");
		System.out.println("product: " + product);

		sectionField.getItems().setAll(Section.values());
		propertyField.getItems().setAll(SpecialProductProperty.values());

		if (product == null) {
			isNewMode = true;
			return;
		}

		nameField.setText(product.getName());
		brandField.setText(product.getBrand());
		sectionField.setValue(product.getSection());
		pcsField.setText(String.valueOf(product.getPcsPerPack()));
		priceField.setText(String.valueOf(product.getPrice()));
		iconPathField.setText(product.getIconPath());
		isAvailable.setSelected(product.isAvailable());
		propertyField.setValue(product.getSpecialPty());
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	@FXML
	private void handleOk() {
		if (!isInputValid())
			return;

		if (product == null) {
			product = new Product(nameField.getText(), brandField.getText(), sectionField.getValue(),
					Integer.parseInt(pcsField.getText()), Double.parseDouble(priceField.getText()),
					iconPath, isAvailable.isSelected(), 1, propertyField.getValue());
		} else {
			product.setName(nameField.getText());
			product.setBrand(brandField.getText());
			product.setSection(sectionField.getValue());
			product.setPcsPerPack(Integer.parseInt(pcsField.getText()));
			product.setPrice(Double.parseDouble(priceField.getText()));
			product.setIconPath(iconPath);
			product.setAvailable(isAvailable.isSelected());
			product.setQuantity(1);
			product.setSpecialPty(propertyField.getValue());
		}
		
		// aggiorno i campi della view
		// se ci sono stati cambiamenti, li vedo subito
		setProduct(product); 
		
		okClicked = true;
		dialogStage.close();
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	@FXML
	private void handleSetImage() {
		// path di base per le immagini
		String imgPath = "src/elaborato_ing_sw/view/images"; // final costant basePath

		// genero il file chooser
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select an image");

		// imposto come directory iniziale quella indicata da imgPath
		fileChooser.setInitialDirectory(new File(imgPath));
		
		// restringo il dominio di selezione dei file {jpg, png}
		FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
		fileChooser.getExtensionFilters().add(imageFilter);

		// apro la finestra
		File selectedFile = fileChooser.showOpenDialog(dialogStage);
		
		// creo il path dell'immagine e lo imposto al prodotto
		if (selectedFile != null) {
			if(Paths.get(imgPath + "/" + selectedFile.getName()).toFile().exists()) {
				System.out.println("File selected: " + selectedFile.getName());
				this.iconPath = imgPath + "/" + selectedFile.getName();
				System.out.println("image path updated successfully");	
				iconPathField.setText(iconPath);
			} else{
				AlertUtil.Alert(AlertType.ERROR, "Wrong directory", "The selected image is not contained in the application images folder", "Please consider moving the selected image in the application images folder, then try again");
			}
		} else {
			System.out.println("File selection cancelled.");
		}
	}

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
		
		if (propertyField.getValue() == null)
			errorMessage += "No valid property!\n";

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