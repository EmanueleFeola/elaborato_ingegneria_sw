package elaborato_ing_sw.imageProxy;

import java.io.File;
import java.util.HashMap;

import javafx.scene.image.Image;

public class RealImage implements MyImage{
	private HashMap<String, Image> allImages = new HashMap<String, Image>();
	
	@Override
	public Image getImage(String imagePath) {
		// se nell hashmap non c'e' l img corrispondente al path
		// la creo e la ritorno, altrimenti la ritorno e basta
		// quindi la creo una volta sola! --> proxy

		if(!alreadyLoaded(imagePath))
			setImage(imagePath);
		
		return allImages.get(imagePath);
	}
	
	public void setImage(String imagePath) {
		File imageFile = new File(imagePath);
		Image icon = new Image(imageFile.toURI().toString());
		
		allImages.put(imagePath, icon);
	}
	
	public boolean alreadyLoaded(String imagePath){
		return allImages.containsKey(imagePath);
	}

	
}
