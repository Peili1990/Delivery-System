package main;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;

public class DBAConnection {
	
	private String driver;
	
	private String url;
	
	private String user;
	
	private String password;
	
	private Connection conn;
	
	private Statement statement;
	
	private void start(){
		try {
			Class.forName(driver);
			
			conn = (Connection) DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet executeQuery(String sql){
		try {
			start();
			if(!conn.isClosed()){
				statement = conn.createStatement();

				ResultSet rs = statement.executeQuery(sql);
								
				return rs;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean execute(String sql){
		try {
			start();
			if(!conn.isClosed()){
				statement = conn.createStatement();

				boolean rs = statement.execute(sql);
								
				return rs;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void close(){
		try {
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
