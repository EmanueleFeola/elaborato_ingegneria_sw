package elaborato_ing_sw.dataManager;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class DaoImpl<T> implements Dao<T>{
	protected ObservableList<T> objs;
	protected String filepath; // filepath per storage
	
	@SuppressWarnings("unchecked")
	protected DaoImpl(String filepath){
		this.filepath = filepath;

		objs = FXCollections.observableArrayList();

		T p;
		boolean eof = false;

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(filepath));

			while (!eof) {
				p = (T) in.readObject();

				if (p != null)
					objs.add(p);
				else
					eof = true;
			}

			System.out.println(objs);

			in.close();

		} catch (EOFException e) {
			// no error, giusto che arrivi alla fine del file
			System.out.println("Read successfully");
		} catch (FileNotFoundException e) {
			// se il file ancora non esiste, lo creo
			try {
				File fd = new File(filepath);
				if (fd.createNewFile())
					System.out.println("File created: " + fd.getName());
			} catch (IOException ioe) {
				System.out.println("An error occurred.");
				ioe.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("Read error");
			System.out.println(e);
		}
	}
	
	@Override
	public ObservableList<T> getAllItems() {
		return objs;
	}

	@Override
	// NB: l implementazione dipende da T
	public abstract T getItem(String objId);

	@Override
	public abstract boolean addItem(T item);

	@Override
	public boolean updateItem(T item) {
		if (!objs.contains(item))
			return false;

		for (int i = 0; i < objs.size(); i++)
			if (objs.get(i).equals(item))
				objs.set(i, item);

		updateSource();

		return true;
	}

	@Override
	public boolean deleteItem(T item) {
		if (!objs.contains(item))
			return false;

		objs.remove(item);

		updateSource();

		return true;
	}

	@Override
	public boolean updateSource() {
		// NB: da richiamare quando l utente esce dall app, in modo da salvare i dati
		// correnti su file
		try {
			FileOutputStream fout = new FileOutputStream(filepath);
			ObjectOutputStream out = new ObjectOutputStream(fout);

			for (T p : objs)
				out.writeObject(p);

			out.flush();
			out.close();

			System.out.println("Serialized successfully");
		} catch (Exception e) {
			System.out.println("Write error");
			System.out.println(e);

			return false;
		}

		return true;
	}

}
