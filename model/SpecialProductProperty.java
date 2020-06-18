package elaborato_ing_sw.model;

public enum SpecialProductProperty {
	NONE("None"), BIO("Bio"), GLUTEN_FREE("Gluten Free"), KM_0("Km 0");
	
	private String property;
	
	private SpecialProductProperty(String property) {
		this.property = property;
	}
		 
	public String toString() {
		return this.property;
	}
	
	public static SpecialProductProperty getValue(String property) {
		for (SpecialProductProperty pp : SpecialProductProperty.values())
	      // Use equalsIgnoreCase to make the getValue method a little more robust
			if (pp.toString().equalsIgnoreCase(property))
				return pp;
		return null;
	}
}