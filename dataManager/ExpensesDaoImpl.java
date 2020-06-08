package elaborato_ing_sw.dataManager;

import elaborato_ing_sw.model.Expense;

public class ExpensesDaoImpl extends DaoImpl<Expense> {
	private static ExpensesDaoImpl instance;
	private static String filename = "expenses";

	protected ExpensesDaoImpl(String filepath) {
		super(filepath);
	}

	public static ExpensesDaoImpl getGroceryShoppingDaoImpl() {
		if (instance == null)
			instance = new ExpensesDaoImpl(filename);

		return (ExpensesDaoImpl) instance;
	}

	@Override
	public Expense getItem(String id) {
		for (Expense gs : getAllItems())
			if (gs.getId() == Integer.valueOf(id))
				return gs;

		return null;
	}
	
	@Override
	public boolean addItem (Expense expense) {
		if (objs.contains(expense)) {
			return false;
		}

		objs.add(expense);

		updateSource();

		return true;
	}
}

