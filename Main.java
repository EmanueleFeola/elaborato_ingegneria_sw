package elaborato_ing_sw;

import java.util.Date;

public class Main {

	public static void main(String[] args) {
		Product p1 = new Product("peanutsPack", "holyPeanuts", 33, 0.99, "img", true);
		Product p2 = new Product("Banananas", "Fruit", 1, 2, "img", true);
		
		User u1 = new User(); // da implementare user
		
		GroceryShopping gs1 = new GroceryShopping(1, new Date("1988-09-29"), TimeSlot.LATE_AFTERNOON, u1, 0, Payment.PAYPAL);
		gs1.getProducts().put(p1, 2);
		gs1.getProducts().put(p2, 6);
		
		// serialize GroceryShopping su file "groceryShoppings"
		// tira su il file e de-serializzalo
		// stampa le spese
		
	}

}
