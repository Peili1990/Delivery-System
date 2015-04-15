package utils.requests;

public class UpdateRequest extends Request {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7384855243641085318L;

	private int ID;
	
	private int status;

	public UpdateRequest(int id, int sta) {
		super(UPDATE);
		
		ID = id;
		
		status = sta;
	}

	public int getID() {
		return ID;
	}

	public int getStatus() {
		return status;
	}

}
