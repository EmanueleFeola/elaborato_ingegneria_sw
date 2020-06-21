package elaborato_ing_sw.model;

public enum Payment {
	PAYPAL("Paypal"), CREDIT_CARD("Credit Card"), COD("Cash On Delivery");
	
	private String payment;
	
	private Payment(String payment) {
		this.payment = payment;
	}
		 
	public String toString() {
		return this.payment;
	}
	
	public static Payment getValue(String property) {
		for (Payment p : Payment.values())
	      // Use equalsIgnoreCase to make the getValue method a little more robust
			if (p.toString().equalsIgnoreCase(property))
				return p;
		return null;
	}
}
