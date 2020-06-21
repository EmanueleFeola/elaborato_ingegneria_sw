package elaborato_ing_sw.imageProxy;

import elaborato_ing_sw.model.Product;
import javafx.scene.image.Image;

public class ProxyImage implements MyImage{
	private RealImage realImage;
	
	public ProxyImage(){
		// quando lo creo la prima volta metto gia dentro l immagine di default
		realImage = new RealImage();
		getImage(Product.getDefaultIconPath());
	}

	@Override
	public Image getImage(String imagePath) {
		return realImage.getImage(imagePath);
	}
}
