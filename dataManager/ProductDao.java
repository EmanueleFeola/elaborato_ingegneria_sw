package elaborato_ing_sw.dataManager;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import elaborato_ing_sw.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductDao {
	private ObservableList<Product> products;
	private String filepath; // filepath per storage

	public ProductDao(String filepath) {
		this.filepath = filepath;

		products = FXCollections.observableArrayList();

		Product p;
		boolean eof = false;

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(filepath));

			while (!eof) {
				p = (Product) in.readObject();

				if (p != null)
					products.add(p);
				else
					eof = true;
			}

			System.out.println(products);

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

	public ObservableList<Product> getAllProducts() {
		return products;
	}

	public Product getProduct(Product product) {
		for (Product p : products)
			if (p.equals(product))
				return p;

		return null;
	}

	public boolean updateProduct(Product product) {
		if (!products.contains(product))
			return false;

		for (int i = 0; i < products.size(); i++)
			if (products.get(i).equals(product))
				products.set(i, product);

		updateSource();

		return true;
	}

	public boolean deleteProduct(Product product) {
		if (!products.contains(product))
			return false;

		products.remove(product);

		updateSource();

		return true;
	}

	public boolean addProduct(Product product) {
		if (products.contains(product))
			return false;

		products.add(product);

		updateSource();

		return true;
	}

	public boolean updateSource() {
		// NB: da richiamare quando l utente esce dall app, in modo da salvare i dati
		// correnti su file
		try {
			FileOutputStream fout = new FileOutputStream(filepath);
			ObjectOutputStream out = new ObjectOutputStream(fout);

			for (Product p : products)
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