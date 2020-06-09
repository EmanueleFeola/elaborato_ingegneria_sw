package elaborato_ing_sw.dataManager;

import java.util.ArrayList;

import elaborato_ing_sw.model.Product;
import elaborato_ing_sw.model.ShoppingCart;

public class ShoppingCartDaoImpl extends DaoImpl<ShoppingCart> {
	private static ShoppingCartDaoImpl instance;
	private static String filename = "shopping_cart";

	protected ShoppingCartDaoImpl(String filepath) {
		super(filepath);
	}

	public static ShoppingCartDaoImpl getShoppingCartDaoImpl() {
		if (instance == null)
			instance = new ShoppingCartDaoImpl(filename);

		return (ShoppingCartDaoImpl) instance;
	}

	@Override
	public ShoppingCart getItem(String user) {
		for (ShoppingCart sp : getAllItems())
			if (sp.getUser().getCredentials().getUser().equals(user))
				return sp;

		return null;
	}

	@Override
	public boolean addItem(ShoppingCart cart) {
		if (objs.contains(cart))
			return false;

		objs.add(cart);
		updateSource();
		
		return true;
	}
	
	public ArrayList<Product> getCartProducts(String user){
		return getItem(user).getProducts();
	}
	
	public void addCartProduct(String user, Product p) {
		getItem(user).getProducts().add(p);
		updateSource();
	}
	
	public void removeCartProduct(String user, Product p){
		getItem(user).getProducts().remove(p);
		updateSource();
	}
}
