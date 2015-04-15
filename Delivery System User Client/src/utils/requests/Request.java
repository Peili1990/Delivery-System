package utils.requests;

import java.io.Serializable;

public class Request implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int ADD = 0;
	public static final int QUERY = 1;
	public static final int SIGNIN = 2;
	public static final int SIGNUP = 3;
	public static final int WORKERSIGNIN = 4;
	public static final int REFRESH = 5;
	public static final int UPDATE = 6;
	public static final int ADDLOCATION = 7;
	
	
	private int type;
	
	public Request(int type){
		this.type = type;
	}
	
	public int getType(){
		return type;
	}
}
