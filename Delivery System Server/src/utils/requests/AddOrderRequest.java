package utils.requests;

import utils.Merchandise;
import utils.Person;

public class AddOrderRequest extends Request {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3293481697045228849L;
	
	private Person receiver;
	
	private Merchandise item;
	
	private String userName;
	
	private int orderType;
	
	private int payMethod;

	public AddOrderRequest(String name, int type, Person re, Merchandise it, int me) {
		super(ADD);
		
		receiver = re;
		
		userName = name;
		
		orderType = type;
		
		item = it;
		
		payMethod = me;
	}

	public Person getReceiver() {
		return receiver;
	}

	public Merchandise getItem() {
		return item;
	}

	public String getUserName() {
		return userName;
	}

	public int getOrderType() {
		return orderType;
	}
	
	public int getPayMethod(){
		return payMethod;
	}

}
