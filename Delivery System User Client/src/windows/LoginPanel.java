package windows;

import frames.MainFrame;
import frames.SignUpFrame;
import interfaces.Receiver;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import utils.replies.Reply;
import utils.requests.SignInRequest;
import main.Connection;

public class LoginPanel extends JPanel implements Receiver {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6335321694998091238L;
	
	private JFrame parent;
	
	private Connection conn;
	
	private JTextField userNameField;
	
	private JPasswordField passwordField;
	
	private JLabel userNameLabel;
	
	private JLabel passwordLabel;
	
	private JButton signInButton;
	
	private JButton signUpButton;
	
	private JButton queryButton;
	
	private JPanel inputPanel;
	
	private JPanel buttonPanel;
	
	private final int width = 320;
	
	private final int height = 150;
	
	
	public LoginPanel(JFrame parent){
		init();
		
		this.parent = parent;
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans/Beans.xml");
		
		conn = (Connection) context.getBean("connection");//new Connection(this);
		
		conn.setReceiver(this);
		
		((ClassPathXmlApplicationContext) context).close();
	}
	
	private void init(){
		setPreferredSize(new Dimension(width, height));
		
		inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(2, 2));
		
		userNameLabel = new JLabel("User Name");
		
		userNameField = new JTextField();
		
		passwordLabel = new JLabel("Password");
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e){
				if(e.getKeyChar() == KeyEvent.VK_ENTER){
					System.out.println("enter");
					signIn();
				}
			}
		});
		
		inputPanel.add(userNameLabel);
		inputPanel.add(userNameField);
		inputPanel.add(passwordLabel);
		inputPanel.add(passwordField);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		signInButton = new JButton("Sign In");
		
		signInButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				signIn();
			}
		});
		
		signUpButton = new JButton("Sign Up");
		signUpButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new SignUpFrame().requestFocus();
			}
			
		});
		
		queryButton = new JButton("Query Only");
		queryButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new MainFrame("");
			}
			
		});
		
		buttonPanel.add(signInButton);
		buttonPanel.add(signUpButton);
		buttonPanel.add(queryButton);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(inputPanel);
		this.add(buttonPanel);
	}	

	@Override
	public void receive(Reply msg) {
		if(msg.getSuccess()){
			//JOptionPane.showMessageDialog(this, "Success", "Sign In Success", JOptionPane.INFORMATION_MESSAGE);
			new MainFrame(msg.getNote());
			parent.dispose();
		}
		else{
			JOptionPane.showMessageDialog(this, msg.getNote(), "Sign In denied", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private void signIn(){
		String userName = userNameField.getText();
		String password = String.valueOf(passwordField.getPassword());
		
		if (0 == userName.length() || 0 == password.length()){
			JOptionPane.showMessageDialog(this, "Please insert User Name and Password", "Invalid name or password", JOptionPane.ERROR_MESSAGE);
		}
		else {
			SignInRequest req = new SignInRequest(userName, password);
			try {
				conn.sendMessage(req);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "Cannot connect to Server.", "Connection Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
