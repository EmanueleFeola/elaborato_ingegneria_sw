package elaborato_ing_sw.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ShoppingCart implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Product> products;
	private User user;
	
	public ShoppingCart (ArrayList<Product> products, User user) {
		this.products = new ArrayList<Product>();
		this.user = user;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "Shopping Cart [" + products + ", " + user + "]";
	}
}
