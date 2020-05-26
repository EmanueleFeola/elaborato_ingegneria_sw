package elaborato_ing_sw.model;

import java.time.LocalDate;
import java.util.HashMap;

public class GroceryShopping {
	private int id;
	private LocalDate deliveryDate;
	private TimeSlot timeSlot;
	private HashMap<Product, Integer> products;
	private User user;
	private double priceTot;
	private Payment payment;
	
	public GroceryShopping(int id, LocalDate deliveryDate, TimeSlot timeSlot, User user, double priceTot, Payment payment) {
		this.id = id;
		this.deliveryDate = deliveryDate;
		this.timeSlot = timeSlot;
		this.user = user;
		this.priceTot = priceTot;
		this.payment = payment;
		this.products = new HashMap<Product, Integer>();
	}

	public GroceryShopping(int id, LocalDate deliveryDate, TimeSlot timeSlot, User user, double priceTot, Payment payment, HashMap<Product, Integer> products) {
		this(id, deliveryDate, timeSlot, user, priceTot, payment);
		this.products = products;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public TimeSlot getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}

	public HashMap<Product, Integer> getProducts() {
		return products;
	}

	public void setProducts(HashMap<Product, Integer> products) {
		this.products = products;
	}

	public User getUser() {
		return user;
	}

	public double getPriceTot() {
		return priceTot;
	}

	public void setPriceTot(double priceTot) {
		this.priceTot = priceTot;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "GroceryShopping [id=" + id + ", deliveryDate=" + deliveryDate + ", timeSlot=" + timeSlot + ", products="
				+ products + ", user=" + user + ", priceTot=" + priceTot + ", payment=" + payment + "]";
	}
	
	

}
