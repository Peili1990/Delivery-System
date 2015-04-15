package utils.replies;

import java.util.ArrayList;

public class RefreshReply extends Reply {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8493217415720054720L;

	ArrayList<Integer> ID;
	
	ArrayList<Integer> status;
	
	ArrayList<String> type;

	public RefreshReply(ArrayList<Integer> id, ArrayList<Integer> sta, ArrayList<String> ty) {
		super(QUERY, true, "");
		
		ID = id;
		
		status = sta;
		
		type = ty;
	}

	public ArrayList<Integer> getID() {
		return ID;
	}

	public ArrayList<Integer> getStatus() {
		return status;
	}

	public ArrayList<String> getTypeList() {
		return type;
	}

}
