package elaborato_ing_sw.dataManager;

import elaborato_ing_sw.model.FidelityCard;

public class FidelityCardDaoImpl extends DaoImpl<FidelityCard> {
	private static FidelityCardDaoImpl instance;
	private static String filename = "fidelity_cards";

	protected FidelityCardDaoImpl(String filepath) {
		super(filepath);
	}

	public static FidelityCardDaoImpl getFidelityCardImpl() {
		if (instance == null)
			instance = new FidelityCardDaoImpl(filename);

		return (FidelityCardDaoImpl) instance;
	}

	public FidelityCard getItemById(int id) {
		for (FidelityCard c : getAllItems())
			if (c.getId() == id)
				return c;

		return null;
	}
	
	@Override
	public FidelityCard getItem(String user) {
		for (FidelityCard c : getAllItems())
			if (c.getUser().getCredentials().getUser().equals(user))
				return c;

		return null;
	}
	
	@Override
	public boolean addItem (FidelityCard card) {
		if (objs.contains(card))
			return false;

		objs.add(card);

		updateSource();

		return true;
	}
}

