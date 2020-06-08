package elaborato_ing_sw.dataManager;

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
		for (ShoppingCart p : getAllItems())
			if (p.getUser().getCredentials().getUser().equals(user))
				return p;

		return null;
	}

	@Override
	public boolean addItem(ShoppingCart cart) {
		if (objs.contains(cart)) {
			return false;
		}

		objs.add(cart);
		updateSource();
		return true;
	}
}
