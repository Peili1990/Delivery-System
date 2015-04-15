package utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Entry implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static int ORDERED = 0;
	public static int SHIPPED = 1;
	public static int DELIVERED = 2;
	public static int CANCELED = 3;
	
	private String orderID;
	private String userID;
	
	private Person poster;
	private Person receiver;
	
	private Merchandise item;
	
	private Date orderDate;
	private Date shipDate;
	private Date estimateDate;
	private String ps;
	private ArrayList<LocationRecord> locationList;
	private int state;
	
	
	
	public Entry(String orderID, String userID, Person poster, Person receiver,
			Merchandise item, Date orderDate, Date shipDate, Date estimateDate,
			String ps) {
		super();
		this.orderID = orderID;
		this.userID = userID;
		this.poster = poster;
		this.receiver = receiver;
		this.item = item;
		this.orderDate = orderDate;
		this.shipDate = shipDate;
		this.estimateDate = estimateDate;
		this.ps = ps;
		
		locationList = new ArrayList<LocationRecord>();
		state = ORDERED;
	}
	
	

	public String getOrderID() {
		return orderID;
	}



	public String getUserID() {
		return userID;
	}



	public Person getPoster() {
		return poster;
	}



	public Person getReceiver() {
		return receiver;
	}



	public Merchandise getItem() {
		return item;
	}



	public Date getOrderDate() {
		return orderDate;
	}



	public Date getShipDate() {
		return shipDate;
	}



	public Date getEstimateDate() {
		return estimateDate;
	}



	public String getPs() {
		return ps;
	}



	public ArrayList<LocationRecord> getLocationList() {
		return locationList;
	}



	public int getState() {
		return state;
	}



	public void setState(int s){
		if(s >= 0 && s < CANCELED){
			state = s;
		}
		if(s == CANCELED){
			locationList.add(new LocationRecord("Canceled"));
		}
	}
	
	public void addLocationRecord(LocationRecord r){
		if(state != CANCELED)
			locationList.add(r);
	}



	@Override
	public String toString() {
		String entry = "Order ID: " + orderID  + "\nState: ";
		switch(state){
		case 0:
			entry += "ordered";
			break;
		case 1:
			entry += "shipped";
			break;
		case 2:
			entry += "delivered";
			break;
		case 4:
			entry += "canceled";
			break;
		default:
			break;
		}
		entry +=  "\nUser ID: " + userID + "\n\nPoster:\n"
				+ poster + "\n\nReceiver:\n" + receiver + "\n\n" + item
				+ "\n\nOrder Date:" + orderDate + "\nShip Date: " + shipDate
				+ "\nEstimate Date: " + estimateDate + "\nPS: " + ps;
		for(LocationRecord r: locationList){
			entry += ("\n" + r);
		}
		return entry;
	}
	
	
}
