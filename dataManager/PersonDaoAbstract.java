package elaborato_ing_sw.dataManager;

import elaborato_ing_sw.model.Person;

public abstract class PersonDaoAbstract extends DaoImpl<Person> {

	protected PersonDaoAbstract(String filepath) {
		super(filepath);
	}

	@Override
	public Person getItem(String user) {
		for (Person p : getAllItems())
			if (p.getCredentials().getUser().equals(user))
				return p;

		return null;
	}

}
