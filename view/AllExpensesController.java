package elaborato_ing_sw.view;

import elaborato_ing_sw.model.User;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class AllExpensesController {
	private Stage dialogStage;
	@SuppressWarnings("unused")
	private User loggedUser;

	@FXML
	private void handleClose() {
		dialogStage.close();
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
}
