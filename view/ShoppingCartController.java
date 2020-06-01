package elaborato_ing_sw.view;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class ShoppingCartController {
	private Stage dialogStage;
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	@FXML
	private void handleClose() {
		dialogStage.close();
	}
}
