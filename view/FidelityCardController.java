package elaborato_ing_sw.view;

import elaborato_ing_sw.dataManager.FidelityCardDaoImpl;
import elaborato_ing_sw.model.FidelityCard;
import elaborato_ing_sw.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FidelityCardController {
	@FXML
	private Label idLabel;
	@FXML
	private Label pointsTotLabel;
	
	private Stage dialogStage;
	private User loggedUser;
	
	private FidelityCardDaoImpl fcardDao = FidelityCardDaoImpl.getFidelityCardImpl();
	
	private void initializeCard() {
		String user = loggedUser.getCredentials().getUser();
		FidelityCard card = fcardDao.getItem(user);
		
		idLabel.setText(String.valueOf(card.getId()));
		pointsTotLabel.setText(String.valueOf(card.getPointsTot()));
	}
	
	@FXML
	private void handleClose() {
		dialogStage.close();
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
		initializeCard();
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
}
