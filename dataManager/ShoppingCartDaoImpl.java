package elaborato_ing_sw.dataManager;

import elaborato_ing_sw.model.Product;

public class ShoppingCartDaoImpl extends DaoImpl<Product> {
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
	public Product getItem(String product) {
		for (Product p : getAllItems())
			if (p.getName().equals(product))
				return p;

		return null;
	}
}
