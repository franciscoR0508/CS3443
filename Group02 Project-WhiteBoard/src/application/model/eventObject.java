package application.model;

public class eventObject {	

	String title;
	String time;
	String description;
	String day;
	
	
	public eventObject(String title, String time,
			String description, String day) {
		this.title = title;
		this.time = time;
		this.description = description;
		this.day = day;
	}
	
	public String toString() {
		return title + " at " + time + "\n" + description + "\n-----------\n";
	}
	
	public String toCSV() {
		String format = title + "," + time + "," + description + "," + day;
		return format;
	}

}
