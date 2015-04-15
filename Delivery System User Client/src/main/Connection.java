package main;

import interfaces.Receiver;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

import utils.replies.Reply;
import utils.requests.Request;

public class Connection {
	private int port = 57094;
	private String host = "127.0.0.1";
	
	private Socket client;
	private Receiver receiver;
	
	public Connection(){
		
	}

	public Connection(Receiver r){
		receiver = r;
	}
	
	public void sendMessage(Request req) throws IOException{
		
		
		try {
			// create socket
			SocketFactory factory = SSLSocketFactory.getDefault();
			client = factory.createSocket(host, port);//new Socket(host, port);
			
			// object writer
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			
			// write object
			os.writeObject(req);
			os.flush();
			
			// get response from server
			ObjectInputStream is = new ObjectInputStream(client.getInputStream());
			
			Object obj = is.readObject();
			Reply re = (Reply) obj;
			
			receiver.receive(re);
			
			// close
			os.close();
			is.close();
			client.close();
		} catch(EOFException e){
			e.printStackTrace();
			throw e;
			
		}catch (IOException e) {
			throw e;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}
	
}
