package elaborato_ing_sw.model;

public enum TimeSlot {
//	EARLY_MORNING, // 6 - 9
//	LATE_MORNING,  // 10 - 13
//	EARLY_AFTERNOON, // 14 - 16
//	LATE_AFTERNOON // 17 - 18

	EARLY_MORNING("Late afternoon"), LATE_MORNING("Late morning"), EARLY_AFTERNOON("Early afternoon"), LATE_AFTERNOON("Late afternoon");
	
	private String slot;
	
	private TimeSlot(String slot) {
		this.slot = slot;
	}
		 
	public String toString() {
		return this.slot;
	}
	
	public static TimeSlot getValue(String property) {
		for (TimeSlot ts : TimeSlot.values())
	      // Use equalsIgnoreCase to make the getValue method a little more robust
			if (ts.toString().equalsIgnoreCase(property))
				return ts;
		return null;
	}
}
