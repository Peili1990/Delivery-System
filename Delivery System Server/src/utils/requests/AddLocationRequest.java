package utils.requests;

public class AddLocationRequest extends Request {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1292241284581902232L;

	private int ID;
	
	private String location;

	public AddLocationRequest(int id, String loc) {
		super(ADDLOCATION);
		
		ID = id;
		
		location = loc;
	}

	public int getID() {
		return ID;
	}

	public String getLocation() {
		return location;
	}

}
