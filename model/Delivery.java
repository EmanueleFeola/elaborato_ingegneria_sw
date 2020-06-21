package elaborato_ing_sw.model;

public enum Delivery {
	CONFERMATA("Confermata"), IN_PREPARAZIONE("In preparazione"), CONSEGNATA("Consegnata");
	
	private String status;
	
	private Delivery(String status) {
		this.status = status;
	}
		 
	public String toString() {
		return this.status;
	}
	
	public static Delivery getValue(String property) {
		for (Delivery d : Delivery.values())
	      // Use equalsIgnoreCase to make the getValue method a little more robust
			if (d.toString().equalsIgnoreCase(property))
				return d;
		return null;
	}
}