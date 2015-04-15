package utils.replies;

import java.util.ArrayList;

public class QueryReply extends Reply {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3992037409273095936L;

	private ArrayList<String> time;
	
	private ArrayList<String> location;
	
	private String status;

	public QueryReply(boolean success, String note, ArrayList<String> t, ArrayList<String> l, String s) {
		super(QUERY, success, note);
		
		time = t;
		
		location = l;
		
		status = s;
	}

	public ArrayList<String> getTime() {
		return time;
	}

	public ArrayList<String> getLocation() {
		return location;
	}

	public String getStatus(){
		return status;
	}
}
