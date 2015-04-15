package utils;

import java.io.Serializable;

public class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int WORKER = 2;
	
	public static final int USER = 1;
	
	public static final int SYS = 0;

	private String firstName;
	
	private String lastName;
	
	private String phoneNumber;
	
	private String emailAddress;
	
	private Address address;
	
	public Person(String firstName, String lastName, String phoneNumber,
			String emailAddress, Address address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.address = address;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public Address getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName
				+ "\nPhone Number: " + phoneNumber + "\nE-Mail Address: "
				+ emailAddress + "\n" + address;
	}
	
	public String toUserSql(String to, String name, String pass, int type){
		String sql = "INSERT INTO " + to + 
				" (user_name, password, email, phone, street, city, state, zip, user_type)"
				+ " VALUES ('" + name + "', '" + pass + "', '" + emailAddress + "', '" +
				phoneNumber + "', " + address.toSql() + ", '" + String.valueOf(type) + "');";
		return sql;
	}
	
	
}
