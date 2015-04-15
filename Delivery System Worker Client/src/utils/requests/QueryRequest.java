package utils.requests;

public class QueryRequest extends Request {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3264013172701426028L;
	
	private String ID;

	public QueryRequest(String ID) {
		super(QUERY);
		
		this.ID = ID;
	}
	
	public String getID(){
		return ID;
	}

}
