package elaborato_ing_sw.utils;

import java.io.IOException;

import elaborato_ing_sw.MainApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ShowDialog {

	public static FXMLLoader getLoader(String path) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource(path));
		
		return loader;
	}
	
	public static Stage getDialogStage(String title, FXMLLoader loader, Stage primaryStage) {
		AnchorPane view = null;
        
		try {
			view = (AnchorPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Stage dialogStage = new Stage();
		dialogStage.setTitle(title);
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(primaryStage);
		Scene scene = new Scene(view);
		dialogStage.setScene(scene);
		
		return dialogStage;
	}
}
