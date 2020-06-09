package elaborato_ing_sw.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;

import elaborato_ing_sw.utils.WriteableObjectProperty;

public class Expense implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private WriteableObjectProperty<LocalDate> deliveryDate;
	private WriteableObjectProperty<TimeSlot> timeSlot;
	private HashMap<Product, Double> products;
	private User user;
	private WriteableObjectProperty<Double> priceTot;
	private WriteableObjectProperty<Payment> payment;
	private WriteableObjectProperty<Delivery> delivery;

	public Expense(int id, LocalDate deliveryDate, TimeSlot timeSlot, User user, double priceTot, Payment payment,
			Delivery delivery) {
		this.id = id;
		this.deliveryDate = new WriteableObjectProperty<LocalDate>(deliveryDate);
		this.timeSlot = new WriteableObjectProperty<TimeSlot>(timeSlot);
		this.user = user;
		this.priceTot = new WriteableObjectProperty<Double>(priceTot);
		this.payment = new WriteableObjectProperty<Payment>(payment);
		this.products = new HashMap<Product, Double>();
		this.delivery = new WriteableObjectProperty<Delivery>(delivery);
	}

	public Expense(int id, LocalDate deliveryDate, TimeSlot timeSlot, User user, double priceTot, Payment payment,
			Delivery delivery, HashMap<Product, Double> products) {
		this(id, deliveryDate, timeSlot, user, priceTot, payment, delivery);
		this.products = products;
	}

	public Delivery getDelivery() {
		return delivery.get();
	}

	public WriteableObjectProperty<Delivery> getDeliveryProperty() {
		return delivery;
	}
	
	public void setDelivery(Delivery delivery) {
		this.delivery.set(delivery);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate.get();
	}

	public WriteableObjectProperty<LocalDate> getDateProperty() {
		return deliveryDate;
	}
	
	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate.set(deliveryDate);
	}

	public TimeSlot getTimeSlot() {
		return timeSlot.get();
	}

	public WriteableObjectProperty<TimeSlot> getTimeSlotProperty() {
		return timeSlot;
	}
	
	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot.set(timeSlot);
	}

	public HashMap<Product, Double> getProducts() {
		return products;
	}

	public void setProducts(HashMap<Product, Double> products) {
		this.products = products;
	}

	public User getUser() {
		return user;
	}

	public double getPriceTot() {
		return priceTot.get();
	}
	
	public WriteableObjectProperty<Double> getPriceTotProperty() {
		return priceTot;
	}

	public void setPriceTot(double priceTot) {
		this.priceTot.set(priceTot);
	}

	public Payment getPayment() {
		return payment.get();
	}

	public WriteableObjectProperty<Payment> getPaymentProperty() {
		return payment;
	}
	
	public void setPayment(Payment payment) {
		this.payment.set(payment);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Expense && this.getId() == ((Expense) obj).getId();
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", deliveryDate=" + deliveryDate + ", timeSlot=" + timeSlot + ", products="
				+ products + ", user=" + user + ", priceTot=" + priceTot + ", payment=" + payment + ", delivery="
				+ delivery + "]";
	}

}
