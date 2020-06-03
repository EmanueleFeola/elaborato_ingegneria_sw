package elaborato_ing_sw.dataManager;

import javafx.collections.ObservableList;

public interface Dao<T> {
	public ObservableList<T> getAllItems();
	public T getItem(String user);
	public boolean addItem(T item);
	public boolean updateItem(T item);
	public boolean deleteItem(T item);
	public boolean updateSource();
}
