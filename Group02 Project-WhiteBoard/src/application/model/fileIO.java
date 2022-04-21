package application.model;

import java.io.*;
import java.util.*;


public class fileIO {
	
	private static final String FILE_PATH = "calander.txt";
	private static final String DELIMITER = ",";
	
	File file;
	Scanner scn;
	public static ArrayList<String> eventsList = new ArrayList<String>();
	
	//method to check if csv file holding data exists, if yes reads it into
	//inventoryList, if not creates file. returns 0 if successful,
	//returns 1 if not
	public int database() {
		//check if file exists, if not creates file
		try {
			file = new File(FILE_PATH);
			if (file.createNewFile()) {
		        System.out.println("Successfully created calander data file: " + file.getName());
		      } else {
		        System.out.println("caladner file already exists.");
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
	        eventsList.add(event);
	      }
	      scn.close();
		}catch(IOException e) {
			System.out.println("An error occured reading the database file");
			System.out.println(e);
		
		}
	      return 0;
	}
	
	

}
