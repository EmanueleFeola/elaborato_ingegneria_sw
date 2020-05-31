package elaborato_ing_sw.utils;

import java.io.IOException;

import elaborato_ing_sw.MainApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class ShowView {

	public static FXMLLoader showView(String viewPath) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource(viewPath));
        AnchorPane view;
        
		try {
			view = (AnchorPane) loader.load();
	        MainApp.getRootLayout().setCenter(view);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return loader;
	}
	
}
