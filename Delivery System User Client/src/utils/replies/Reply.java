package utils.replies;

import java.io.Serializable;

public class Reply implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6553424715027638646L;
	
	public static final int ADD = 0;
	public static final int QUERY = 1;
	public static final int SIGNIN = 2;
	public static final int SIGNUP = 3;
	
	private int type;
	
	private boolean success;
	
	private String note;
	
	public Reply(int type, boolean success, String note) {
		this.type = type;
		this.success = success;
		this.note = note;
	}

	public int getType() {
		return type;
	}
	
	public boolean getSuccess(){
		return success;
	}
	
	public String getNote(){
		return note;
	}
}
