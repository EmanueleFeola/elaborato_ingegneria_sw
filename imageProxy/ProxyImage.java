package elaborato_ing_sw.imageProxy;

import javafx.scene.image.Image;

public class ProxyImage implements MyImage{
	private RealImage realImage;
	private String defaultIconPath = "src/elaborato_ing_sw/view/images/logo.png";
	
	public ProxyImage(){
		// quando lo creo la prima volta metto gia dentro l immagine di default
		realImage = new RealImage();
		getImage(defaultIconPath);
	}

	@Override
	public Image getImage(String imagePath) {
		return realImage.getImage(imagePath);
	}
	
	public String getDefaultIconPath(){
		return defaultIconPath;
	}
}
