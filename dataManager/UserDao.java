package elaborato_ing_sw.dataManager;

import elaborato_ing_sw.model.User;
import javafx.collections.ObservableList;

public interface UserDao {
	public ObservableList<User> getAllUsers();
	public User getUser(String user);
	public boolean addUser(User user);
	public boolean updateUser(User user);
	public boolean deleteUser(User user);
	public boolean updateSource();
}
