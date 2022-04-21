package application.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class week {
	
	//arraylists of eventObjects to represent days in week
	public static ArrayList<eventObject> mon = new ArrayList<eventObject>();
	public static ArrayList<eventObject> tues = new ArrayList<eventObject>();
	public static ArrayList<eventObject> wed = new ArrayList<eventObject>();
	public static ArrayList<eventObject> thur = new ArrayList<eventObject>();
	public static ArrayList<eventObject> fri = new ArrayList<eventObject>();
	
	public static File file;
	public static Scanner scn;
	private static final String FILE_PATH = "calander.txt";
	
	//checks if database file exists, creates if not and then reads entries
	//into corresponding arraylist
	public static void database() {
		//check if file exists, if not creates file
		try {
			file = new File(FILE_PATH);
			if (file.createNewFile()) {
		        System.out.println("Successfully created calander data file: " + file.getName());
		      } else {
		        System.out.println("calander file already exists.");
		      }
		//catch block to detect IOExceptions
		}catch(IOException e){
			System.out.println("An error occurred opening the database file");
			System.out.println(e);
		}
		
		//creates scanner and uses it to read file into arraylist
		try {
			
		scn =  new Scanner(file);		
		while (scn.hasNextLine()) {
	        String event = scn.nextLine();
	        createEvent(event);
	      }
	      scn.close();
		}catch(IOException e) {
			System.out.println("An error occured reading the database file");
			System.out.println(e);
		
		}
	}
	
	//writes current version of arrayLists to file
	public static int writeFile() {
		//creates FileWriter to write contents of inventoryList to database csv file
	    try {
	    	FileWriter myWriter = new FileWriter(FILE_PATH);
	        
	    	//TODO: parse and write to file
	    	String outputText = "";
	    	
	    	for (int i = 0; i < mon.size(); i++) {
	    		outputText += mon.get(i).toCSV() + "\n";
	    	}
	    	for (int i = 0; i < tues.size(); i++) {
	    		outputText += tues.get(i).toCSV() + "\n";
	    	}
	    	for (int i = 0; i < wed.size(); i++) {
	    		outputText += wed.get(i).toCSV() + "\n";
	    	}
	    	for (int i = 0; i < thur.size(); i++) {
	    		outputText += thur.get(i).toCSV() + "\n";
	    	}
	    	for (int i = 0; i < fri.size(); i++) {
	    		outputText += fri.get(i).toCSV() + "\n";
	    	}
	    	
	    	myWriter.write(outputText);
	    	
	    	
	        myWriter.close();
	        System.out.println("Successfully wrote to the file.");
	    }catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	    }
		return 0;
	}
	
	//takes csv record and parses it into an event object
	//compares and puts object into corresponding arrayList
	public static eventObject createEvent(String csvRecord) {
		
		String[] split = csvRecord.split(",");
		eventObject event = new eventObject(split[0], split[1], split[2], split[3]);
		
		switch (event.day) {
		case "Monday":
			mon.add(event);
			break;
		case "Tuesday":
			tues.add(event);
			break;
		case "Wednesday":
			wed.add(event);
			break;
		case "Thursday":
			thur.add(event);
			break;
		case "Friday":
			fri.add(event);
			break;
		}
		return event;
	}
	
	//iterates through eventObject arraylist and returns in formated string form
	public static String listToString(ArrayList<eventObject> arrList) {
		String format = "";
		for (int i = 0; i < arrList.size(); i++) {
			format += arrList.get(i).toString();
		}
		return format;
	}
}
