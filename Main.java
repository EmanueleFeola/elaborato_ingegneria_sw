package elaborato_ing_sw;

import java.util.Date;

public class Main {

	public static void main(String[] args) {
		Product p1 = new Product("peanutsPack", "holyPeanuts", 33, 0.99, "img", true);
		Product p2 = new Product("Banananas", "Fruit", 1, 2, "img", true);
		
		Credentials cred1 = new Credentials("efeola99@gmail.com", "pwd");
		
		User u1 = new User("emanuele", "feola", new Date(1999, 30, 8), cred1, "via antonio varisco, 6", "Bussolengo", 37012, "+39123456");
		System.out.println(u1);
		
		String test = "pwd sbagliata";
		String test1 = "pwd";
		
		if(Credentials.getMd5(test).compareTo(u1.getCredentials().getMd5Pwd()) == 0)
			System.out.println("la pwd è " + test);
		
		if(Credentials.getMd5(test1).compareTo(u1.getCredentials().getMd5Pwd()) == 0)
			System.out.println("la pwd è " + test1);
		
		// Example of GroceryShopping with products
		/*
		GroceryShopping gs1 = new GroceryShopping(1, new Date(1999, 30, 8), TimeSlot.LATE_AFTERNOON, u1, 0, Payment.PAYPAL);
		gs1.getProducts().put(p1, 2);
		gs1.getProducts().put(p2, 6);
		*/
		
		// ToDo
		// serialize GroceryShopping su file "groceryShoppings"
		// tira su il file e de-serializzalo
		// stampa le spese
		
	}

}
