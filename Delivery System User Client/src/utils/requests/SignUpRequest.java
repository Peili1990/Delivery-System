package utils.requests;

import utils.Person;

public class SignUpRequest extends Request {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6271238293788455310L;

	Person person;
	
	String userName;
	
	String password;
	
	int userType;

	public SignUpRequest(Person p, String name, String pass, int type) {
		super(SIGNUP);
		
		person = p;
		
		userName = name;
		
		password = pass;
		
		userType = type;
	}

	public Person getPerson() {
		return person;
	}
	
	public String getUserName(){
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public int getUserType() {
		return userType;
	}
}
