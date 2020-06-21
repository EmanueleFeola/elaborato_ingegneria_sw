package elaborato_ing_sw.model;

public enum Role {
	ADMIN("Admin");
	
	private String role;
	
	private Role(String role) {
		this.role = role;
	}
		 
	public String toString() {
		return this.role;
	}
	
	public static Role getValue(String property) {
		for (Role r : Role.values())
	      // Use equalsIgnoreCase to make the getValue method a little more robust
			if (r.toString().equalsIgnoreCase(property))
				return r;
		return null;
	}
}
