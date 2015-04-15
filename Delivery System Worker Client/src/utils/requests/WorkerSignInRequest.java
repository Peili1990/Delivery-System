package utils.requests;

public class WorkerSignInRequest extends Request {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -165882742745959415L;

	private String userName;
	
	private String password;

	public WorkerSignInRequest(String uname, String psw) {
		super(WORKERSIGNIN);
		
		userName = uname;
		
		password = psw;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
}
