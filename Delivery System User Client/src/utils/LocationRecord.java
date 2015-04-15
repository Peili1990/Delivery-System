package utils;

import java.io.Serializable;
import java.util.Date;

public class LocationRecord implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date date;
	
	private String location;
	
	private String description;
	
	public LocationRecord(String loc){
		date = new Date();
		location = loc;
		description = "";
	}
	
	public LocationRecord(Date d, String loc, String des){
		date = d;
		location = loc;
		description = des;
	}
	
	public Date getDate(){
		return date;
	}
	
	public String getLocation(){
		return location;
	}
	
	public String getDescription(){
		return description;
	}

	@Override
	public String toString() {
		return date + ", arrived at " + location
				+ ", " + description;
	}
	
	
}
