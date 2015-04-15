package main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import utils.Person;
import utils.replies.QueryReply;
import utils.replies.RefreshReply;
import utils.replies.Reply;
import utils.requests.AddLocationRequest;
import utils.requests.AddOrderRequest;
import utils.requests.QueryRequest;
import utils.requests.Request;
import utils.requests.SignInRequest;
import utils.requests.SignUpRequest;
import utils.requests.UpdateRequest;
import utils.requests.WorkerSignInRequest;

public class Server {
	
	private final static int PORT = 57094;
	
	private ServerSocket server;
	
	DBAConnection conn;
	
	public Server(){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans/Beans.xml");
		
		conn = (DBAConnection) context.getBean("dbaConnection");
		
		try {
			ServerSocketFactory factory = SSLServerSocketFactory.getDefault();
			server = factory.createServerSocket(PORT);//new ServerSocket(PORT);
			((SSLServerSocket) server).setNeedClientAuth(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		((ClassPathXmlApplicationContext) context).close();
	}
	public void start(){
		System.out.println("Server started, waiting for messages...");
		while(true){
			try {
				Socket socket = server.accept();
				new Thread(new Task(socket)).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private class Task implements Runnable{
		
		private Socket socket;
		
		public Task(Socket s){
			socket = s;
		}

		@Override
		public void run() {
			try {
				handleSocket();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		private void handleSocket() throws Exception{
			
			// object reader
			ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
			
			// object writer
			ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
			
			// get the object and cast to  Request
			Object obj = is.readObject();
			Request req = (Request) obj;
			
			Reply re = handleRequest(req);
			
			// write to socket
			os.writeObject(re);
			os.flush();
		
			// close
			socket.close();
			is.close();
			os.close();
		}
		
	}
	
	private Reply handleRequest(Request req) throws SQLException{
		
		Reply re = null;
		
		// if query request
		if(req.getType() == Request.QUERY){
			String sql = "SELECT type FROM delivery.order_status_table";
			
			ResultSet rs = conn.executeQuery(sql);
			
			ArrayList<String> status = new ArrayList<String>();
			
			while(rs.next()){
				status.add(rs.getString("type"));
			}
			
			conn.close();
			
			int oid = Integer.valueOf(((QueryRequest) req).getID());
			
			sql = "SELECT time,location FROM delivery.location_table WHERE order_id=" + oid;
			
			rs = conn.executeQuery(sql);
			
			ArrayList<String> time = new ArrayList<String>();
			
			ArrayList<String> location = new ArrayList<String>();
			
			while(rs.next()){
				time.add(rs.getString("time"));
				
				location.add(rs.getString("location"));
			}
			
			conn.close();
			
			sql = "SELECT status FROM delivery.order_table WHERE id=" + oid;
			
			rs = conn.executeQuery(sql);
			
			int sta = -1;
			
			if(rs.next()){
				sta = rs.getInt("status");
				re = new QueryReply(true, "", time, location, status.get(sta));
			}
			else
			{
				re = new QueryReply(false, "Order does not exist.", null, null, "");
			}
			
			conn.close();
		}
		// if sign in request
		else if (req.getType() == Request.SIGNIN){
						
			String userName = ((SignInRequest) req).getUserName();
						
			String password = ((SignInRequest) req).getPassword();
						
			System.out.println("Sign in request from client: username[" + userName + 
					"] password[" + password + "]");
						
			String sql = "SELECT password FROM delivery.user_table WHERE user_name='" + userName + "';";
						
			ResultSet rs = conn.executeQuery(sql);
						
			// build reply
			if(!rs.next()){
				re = new Reply(Reply.SIGNIN, false, "User Name dose not exists.");
			}
			else if(!rs.getString("password").equals(password)){
				re = new Reply(Reply.SIGNIN, false, "Invalid password.");
			}
			else{
				re = new Reply(Reply.SIGNIN, true, userName);
			}
						
			conn.close();
		}
		// if sign up request
		else if(req.getType() == Request.SIGNUP){
			
			String userName = ((SignUpRequest) req).getUserName();
			String password = ((SignUpRequest) req).getPassword();
			
			System.out.println("Sign up request from client: username[" + userName + "] password[" + password + "]\n" + ((SignUpRequest) req).getPerson());
			
			String sql = "SELECT * FROM delivery.user_table WHERE user_name='" + userName + "';";
			
			ResultSet rs = conn.executeQuery(sql);
			
			if(rs.next()){
				re = new Reply(Reply.SIGNUP, false, "User Name Exisits.");
			}
			else{
				sql = ((SignUpRequest) req).getPerson().toUserSql("user_table", userName, password, Person.USER);
				System.out.println(sql);
				
				conn.execute(sql);
				
				re = new Reply(Reply.SIGNUP, true, "done");
			}
			conn.close();
		}
		// if add order request
		else if(req.getType() == Request.ADD){
			System.out.println("Add order request from client");
			
			AddOrderRequest r = (AddOrderRequest) req;
			
			String sql = "SELECT id FROM user_table WHERE user_name='" + r.getUserName() + "';";
			
			ResultSet rs = conn.executeQuery(sql);
			
			//get user id
			rs.next();
			String id = rs.getString("id");
			
			Date now = new Date();
			
			int dayAfter = 0;
			
			double price = 0.0;
			
			// calculate estimate date and price;
			switch(r.getOrderType()){
			case 0:
				dayAfter = 7;
				price = 4.99;
				break;
			case 1:
				dayAfter = 5;
				price = 6.99;
				break;
			case 2:
				dayAfter = 2;
				price = 9.99;
				break;
			default:
				dayAfter = 7;
				price = 4.99;
			}
			
			String d = toDate(getDaysAfter(dayAfter));
			
			sql = "INSERT INTO order_table (user_id, order_type, submit_date, estimate_date, item_name, item_weight, item_volume, item_quantity, item_note, "
					+ "receiver_first_name, receiver_last_name, receiver_street, receiver_city, receiver_state, receiver_zip, receiver_email, receiver_phone, "
					+ "price, pay_method) VALUES (" + id + ", " + r.getOrderType() + ", '" + toDate(now) + "', '" + d + "', '" 
					+ r.getItem().getName() + "', '" + r.getItem().getWeight() + "', '" + r.getItem().getVolume() + "', " + r.getItem().getQuantity() + ", '" 
					+ r.getItem().getNote() + "', '" + r.getReceiver().getFirstName() + "', '" + r.getReceiver().getLastName() + "', '" 
					+ r.getReceiver().getAddress().getStreet() + "', '" + r.getReceiver().getAddress().getCity() + "', '" + r.getReceiver().getAddress().getState() + "', '" 
					+ r.getReceiver().getAddress().getZip() + "', '" + r.getReceiver().getEmailAddress() + "', '" + r.getReceiver().getPhoneNumber() + "', " 
					+ price + ", " + r.getPayMethod() + ");";
			conn.execute(sql);
			System.out.println(sql);
			conn.close();
			
			sql = "SELECT MAX(id) maxid FROM order_table WHERE (user_id=" + id + ");";
			
			rs = conn.executeQuery(sql);
			
			rs.next();
			
			re = new Reply(Reply.ADD, true, rs.getString("maxid"));
			
			conn.close();
		}
		else if (req.getType() == Request.WORKERSIGNIN){
			
			String userName = ((WorkerSignInRequest) req).getUserName();
						
			String password = ((WorkerSignInRequest) req).getPassword();
						
			System.out.println("Worker sign in request from client: username[" + userName + 
					"] password[" + password + "]");
						
			String sql = "SELECT password,user_type FROM delivery.user_table WHERE user_name='" + userName + "';";
						
			ResultSet rs = conn.executeQuery(sql);
						
			// build reply
			if(!rs.next()){
				re = new Reply(Reply.SIGNIN, false, "User Name dose not exists.");
			}
			else if(!rs.getString("password").equals(password)){
				re = new Reply(Reply.SIGNIN, false, "Invalid password.");
			}
			else if(rs.getInt("user_type") == 1){
				re = new Reply(Reply.SIGNIN, false, "Not enough privilege.");
			}
			else{
				re = new Reply(Reply.SIGNIN, true, userName);
			}
						
			conn.close();
		}
		else if (req.getType() == Request.REFRESH){
			String sql = "SELECT type FROM delivery.order_status_table";
			
			ResultSet rs = conn.executeQuery(sql);
			
			ArrayList<String> status = new ArrayList<String>();
			
			while(rs.next()){
				status.add(rs.getString("type"));
			}
			
			conn.close();
			
			sql = "SELECT id,status FROM delivery.order_table WHERE (status<>2 AND status<>3);";
			
			rs = conn.executeQuery(sql);
			
			ArrayList<Integer> oid = new ArrayList<Integer>();
			ArrayList<Integer> otype = new ArrayList<Integer>();
			
			while(rs.next()){
				System.out.println(rs.getInt("id") + " " + status.get(rs.getInt("status")));
				oid.add(rs.getInt("id"));
				otype.add(rs.getInt("status"));
			}
			
			re = new RefreshReply(oid, otype, status);
			
			conn.close();
		}
		else if (req.getType() == Request.ADDLOCATION){
			int oid = ((AddLocationRequest) req).getID();
			
			String loc = ((AddLocationRequest) req).getLocation();
			
			String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			
			String sql = "INSERT INTO delivery.location_table (order_id,time,location) VALUES (" + oid + ",'" + time + "','" + loc + "');";
			
			conn.execute(sql);
			
			conn.close();
			
			sql = "UPDATE delivery.order_table SET status=1 WHERE id=" + oid + ";";
			
			conn.execute(sql);
			
			re = new Reply(Reply.ADD, true, "");
			
			conn.close();
		}
		else if(req.getType() == Request.UPDATE){
			int oid = ((UpdateRequest) req).getID();
			
			int osta = ((UpdateRequest) req).getStatus();
			
			String sql = "UPDATE delivery.order_table SET status=" + osta + " WHERE id=" + oid + ";";
			
			conn.execute(sql);
			
			re = new Reply(Reply.ADD, true, "");
			
			conn.close();
		}
		return re;
	}
	
	private Date getDaysAfter(int day){
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}
	private static String toDate(Date d){
		String tmp = "";
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		tmp += f.format(d);
		return tmp;
	}
	
	public static void main(String[] args){
		new Server().start();
	}
}
