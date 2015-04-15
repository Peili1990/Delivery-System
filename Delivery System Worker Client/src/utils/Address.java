package utils;

import java.io.Serializable;

public class Address implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String street;
	
	private String city;
	
	private String state;
	
	private String zip;

	public Address(String street, String city, String state, String zip) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZip() {
		return zip;
	}

	@Override
	public String toString() {
		return "Address: " + street + " " + city + " "
				+ state + " " + zip;
	}
	
	public String toSql(){
		String sql = "'" + street + "', '" + city + "', '" + state + "', '" + zip + "'";
		return sql;
	}

}
