package elaborato_ing_sw.dataManager;

import elaborato_ing_sw.model.Person;
import javafx.collections.ObservableList;

public interface PersonDao {
	public ObservableList<Person> getAllUsers();
	public Person getUser(String user);
	public boolean addUser(Person user);
	public boolean updateUser(Person user);
	public boolean deleteUser(Person user);
	public boolean updateSource();
}
