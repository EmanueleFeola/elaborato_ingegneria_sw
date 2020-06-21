package elaborato_ing_sw.utils;

import elaborato_ing_sw.MainApp;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertUtil {
	
	public static void Alert(AlertType at, String title, String headerText, String contentText) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.initOwner(MainApp.getPrimaryStage());
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.showAndWait();
	}
}
