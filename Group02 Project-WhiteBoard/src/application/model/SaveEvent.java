/**
 * This model contains the respective setters, getters, and constructor to set the date and event the user specifies. 
 * A toString is used to format how the information will be shown to the user.
 * 
 * @author Gia German (dnp645)
 * UTSA CS 3443 - Group Project (Group 02)
 * Spring 2022
 */
package application.model;

import java.time.LocalDate;
import java.util.Date;

public class SaveEvent {
	private String descrip; 
	private LocalDate date;
	
	public SaveEvent(String descrip, LocalDate date) {
		super();
		this.descrip = descrip;
		this.setDate(date);
	}
	
	public String getDescrip() {
		return descrip;
	}
	
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	} 
	
	@Override
	public String toString() {
		return "Date: "+this.date+"\n"+"\tEvent: "+this.descrip;
	}

}
