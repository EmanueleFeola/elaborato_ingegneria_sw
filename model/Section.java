package elaborato_ing_sw.model;

public enum Section {
	VEGETABLES("Vegetables"),
	FRUIT("Fruit"),
	MEAT_FISH("Meat & Fish"),
	GRAIN_FOODS("Grain foods"),
	DAIRY_PRODUCTS("Dairy products"),
	BEVERAGES("Beverages");

	private String section;
	
	private Section(String section) {
		this.section = section;
	}
		 
	public String toString() {
		return this.section;
	}
	
	public static Section getValue(String property) {
		for (Section s : Section.values())
	      // Use equalsIgnoreCase to make the getValue method a little more robust
			if (s.toString().equalsIgnoreCase(property))
				return s;
		return null;
	}
}
