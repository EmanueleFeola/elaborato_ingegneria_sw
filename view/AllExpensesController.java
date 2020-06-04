package elaborato_ing_sw.view;

import elaborato_ing_sw.MainApp;
import elaborato_ing_sw.model.User;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class AllExpensesController {
	@SuppressWarnings("unused")
	private MainApp mainApp;
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

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
}
