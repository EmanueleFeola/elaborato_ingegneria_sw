package elaborato_ing_sw.dataManager;

import elaborato_ing_sw.model.GroceryShopping;

public class GroceryShoppingDaoImpl extends DaoImpl<GroceryShopping> {
	private static GroceryShoppingDaoImpl instance;
	private static String filename = "grocery_shopping";

	protected GroceryShoppingDaoImpl(String filepath) {
		super(filepath);
	}

	public static GroceryShoppingDaoImpl getGroceryShoppingDaoImpl() {
		if (instance == null)
			instance = new GroceryShoppingDaoImpl(filename);

		return (GroceryShoppingDaoImpl) instance;
	}

	@Override
	public GroceryShopping getItem(String id) {
		for (GroceryShopping gs : getAllItems())
			if (gs.getId() == Integer.valueOf(id))
				return gs;

		return null;
	}
	
	@Override
	public boolean addItem (GroceryShopping expense) {
		if (objs.contains(expense)) {
			return false;
		}

		objs.add(expense);

		updateSource();

		return true;
	}
}

