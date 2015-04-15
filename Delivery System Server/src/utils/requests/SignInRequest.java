package utils.requests;

public class SignInRequest extends Request {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -165882742745959415L;

	private String userName;
	
	private String password;

	public SignInRequest(String uname, String psw) {
		super(SIGNIN);
		
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
