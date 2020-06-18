package elaborato_ing_sw.model;

public enum TimeSlot {
//	EARLY_MORNING, // 6 - 9
//	LATE_MORNING,  // 10 - 13
//	EARLY_AFTERNOON, // 14 - 16
//	LATE_AFTERNOON // 17 - 18

	EARLY_MORNING("Early morning", 6, 8),
	LATE_MORNING("Late morning", 10, 13),
	EARLY_AFTERNOON("Early afternoon", 14, 16),
	LATE_AFTERNOON("Late afternoon", 17, 18);
	
	private String slot;
	private int start;
	private int end;
	
	private TimeSlot(String slot, int start, int end) {
		this.slot = slot;
		this.start = start;
		this.end = end;
	}
		 
	public String toString() {
		return this.slot;
	}
	
	public int getStart() {
		return this.start;
	}
	
	public int getEnd() {
		return this.end;
	}
	
	public static TimeSlot getValue(String property) {
		for (TimeSlot ts : TimeSlot.values())
	      // Use equalsIgnoreCase to make the getValue method a little more robust
			if (ts.toString().equalsIgnoreCase(property))
				return ts;
		return null;
	}
}
