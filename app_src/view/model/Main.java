package elaborato_ing_sw.model;

import java.time.LocalDate;

/*import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;*/

//import java.io.File;

public class Main {

	public static void main(String[] args) {
		Product p1 = new Product("peanutsPack", "holyPeanuts", 33, 0.99, "img", true);
		Product p2 = new Product("Banananas", "Fruit", 1, 2, "img", true);
		
		Credentials cred1 = new Credentials("efeola99@gmail.com", "pwd");
		
		User u1 = new User("emanuele", "feola", LocalDate.of(2014, 2, 14), cred1, "via antonio varisco, 6", "Bussolengo", 37012, "+39123456");

		
		System.out.println(u1);
		
		String test = "pwd sbagliata";
		String test1 = "pwd";
		
		if(Credentials.getMd5(test).compareTo(u1.getCredentials().getMd5Pwd()) == 0)
			System.out.println("la pwd e' " + test);
		
		if(Credentials.getMd5(test1).compareTo(u1.getCredentials().getMd5Pwd()) == 0)
			System.out.println("la pwd e' " + test1);
		
		// Example of GroceryShopping with products
		GroceryShopping gs1 = new GroceryShopping(1, LocalDate.of(2014, 2, 14), TimeSlot.LATE_AFTERNOON, u1, 0, Payment.PAYPAL);
		gs1.getProducts().put(p1, 2);
		gs1.getProducts().put(p2, 6);
		
		
		// TODO:
			// serialize GroceryShopping su file "groceryShoppings"
			// tira su il file e de-serializzalo
			// stampa le spese
		
	}
}
