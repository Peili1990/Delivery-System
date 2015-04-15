package windows;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import interfaces.Receiver;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import main.Connection;
import utils.Address;
import utils.Person;
import utils.replies.Reply;
import utils.requests.SignUpRequest;

public class SignUpPanel extends JPanel implements Receiver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7189669100769084409L;
	
	private JFrame parent;
	
	private JLabel userNameLabel;
	
	private JTextField userNameField;
	
	private JLabel userNameInfoLabel;
	
	private JLabel passwordLabel;
	
	private JPasswordField passwordField;
	
	private JLabel passwordInfoLabel;
	
	private JLabel confirmLabel;
	
	private JPasswordField confirmField;
	
	private JLabel confirmInfoLabel;
	
	private JLabel emailLabel;
	
	private JTextField emailField;
	
	private JLabel emailInfoLabel;
	
	private JLabel phoneLabel;
	
	private JTextField phoneField;
	
	private JLabel streetLabel;
	
	private JTextField streetField;
	
	private JLabel cityLabel;
	
	private JTextField cityField;
	
	private JLabel stateLabel;
	
	private JComboBox<String> stateBox;
	
	private JLabel zipLabel;
	
	private JTextField zipField;
	
	private JPanel userInfoPanel;
	
	private JLabel standerInfoLabel;
	
	private JLabel userInfoLabel;
	
	private JLabel addInfoLabel;
	
	private JLabel blanckLabel;
	
	private JLabel titleLabel;
	
	private JButton OKButton;
	
	private JButton cancelButton;
	
	private Connection conn;
	
	public SignUpPanel(JFrame f){
		init();
		
		parent = f;
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans/Beans.xml");
		
		conn = (Connection) context.getBean("connection");//new Connection(this);
		
		conn.setReceiver(this);
		
		((ClassPathXmlApplicationContext) context).close();
	}
	
	private void init(){
		
		Insets in = new Insets(10, 10, 10, 10);
		blanckLabel = new JLabel("");
		
		userInfoPanel = new JPanel();
		//userInfoPanel.setMinimumSize(new Dimension(400, 580));
		userInfoPanel.setLayout(new GridBagLayout());
		userInfoPanel.setBorder(BorderFactory.createEtchedBorder());
		
		// stander info
		
		standerInfoLabel = new JLabel("User Infomation:");
		addGridBag(standerInfoLabel, userInfoPanel, 0, 0, 3, 1, 0.8, 0.0, GridBagConstraints.BOTH,GridBagConstraints.WEST, in);
		addGridBag(blanckLabel, userInfoPanel, 3, 0, 1, 1, 0.2, 0.0, GridBagConstraints.NONE,GridBagConstraints.CENTER, in);
		
		userNameLabel = new JLabel("User Name");
		addGridBag(userNameLabel, userInfoPanel, 0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NONE,GridBagConstraints.WEST, in);
		
		userNameField = new JTextField(16);
		addGridBag(userNameField, userInfoPanel, 1, 1, 2, 1, 0.0, 0.0, GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER, in);
		
		userNameInfoLabel = new JLabel("                  ");
		addGridBag(userNameInfoLabel, userInfoPanel, 3, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NONE,GridBagConstraints.WEST, in);
		
		passwordLabel = new JLabel("Password");
		addGridBag(passwordLabel, userInfoPanel, 0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NONE,GridBagConstraints.WEST, in);
		
		passwordField = new JPasswordField(32);
		addGridBag(passwordField, userInfoPanel, 1, 2, 2, 1, 0.0, 0.0, GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER, in);
		
		passwordInfoLabel = new JLabel("                  ");
		addGridBag(passwordInfoLabel, userInfoPanel, 3, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NONE,GridBagConstraints.WEST, in);
		
		confirmLabel = new JLabel("Confirm Password");
		addGridBag(confirmLabel, userInfoPanel, 0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NONE,GridBagConstraints.WEST, in);
		
		confirmField = new JPasswordField(32);
		addGridBag(confirmField, userInfoPanel, 1, 3, 2, 1, 0.0, 0.0, GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER, in);
		
		confirmInfoLabel = new JLabel("                  ");
		addGridBag(confirmInfoLabel, userInfoPanel, 3, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NONE,GridBagConstraints.WEST, in);
		
		// personal info
		
		userInfoLabel = new JLabel("Personal Infomation:");
		addGridBag(userInfoLabel, userInfoPanel, 0, 4, 4, 1, 0.0, 0.0, GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST, in);
		
		
		emailLabel = new JLabel("E-Mail");
		addGridBag(emailLabel, userInfoPanel, 0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.NONE,GridBagConstraints.WEST, in);
		
		emailField = new JTextField(45);
		addGridBag(emailField, userInfoPanel, 1, 5, 2, 1, 0.0, 0.0, GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER, in);
		
		emailInfoLabel = new JLabel("                  ");
		addGridBag(emailInfoLabel, userInfoPanel, 3, 5, 1, 1, 0.0, 0.0, GridBagConstraints.NONE,GridBagConstraints.WEST, in);
		
		phoneLabel = new JLabel("Phone Number");
		addGridBag(phoneLabel, userInfoPanel, 0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.NONE,GridBagConstraints.WEST, in);
		
		phoneField = new JTextField(16);
		addGridBag(phoneField, userInfoPanel, 1, 6, 3, 1, 0.0, 0.0, GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER, in);
		
		addInfoLabel = new JLabel("Address Infomation:");
		addGridBag(addInfoLabel, userInfoPanel, 0, 7, 4, 1, 0.0, 0.0, GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST, in);
		
		streetLabel = new JLabel("Street");
		addGridBag(streetLabel, userInfoPanel, 0, 8, 1, 1, 0.0, 0.0, GridBagConstraints.NONE,GridBagConstraints.WEST, in);
		
		streetField = new JTextField(16);
		addGridBag(streetField, userInfoPanel, 1, 8, 3, 1, 0.0, 0.0, GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER, in);
		
		cityLabel = new JLabel("City");
		addGridBag(cityLabel, userInfoPanel, 0, 9, 1, 1, 0.0, 0.0, GridBagConstraints.NONE,GridBagConstraints.WEST, in);
		
		cityField = new JTextField(16);
		addGridBag(cityField, userInfoPanel, 1, 9, 3, 1, 0.0, 0.0, GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER, in);
		
		stateLabel = new JLabel("State");
		addGridBag(stateLabel, userInfoPanel, 0, 10, 1, 1, 0.0, 0.0, GridBagConstraints.NONE,GridBagConstraints.WEST, in);
		
		stateBox = new JComboBox<String>();
		addStateBox();
		addGridBag(stateBox, userInfoPanel, 1, 10, 3, 1, 0.0, 0.0, GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER, in);
		
		zipLabel = new JLabel("ZIP Code");
		addGridBag(zipLabel, userInfoPanel, 0, 11, 1, 1, 0.0, 0.0, GridBagConstraints.NONE,GridBagConstraints.WEST, in);
		
		zipField = new JTextField(6);
		addGridBag(zipField, userInfoPanel, 1, 11, 3, 1, 0.0, 0.0, GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER, in);
		
		titleLabel = new JLabel("Sign Up");
		
		OKButton = new JButton("Submit");
		OKButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(checkError()){
					Address add = new Address(streetField.getText(), cityField.getText(), stateBox.getSelectedItem().toString(), zipField.getText());
					
					Person p = new Person("", "", phoneField.getText(), emailField.getText(), add);
					
					SignUpRequest req = new SignUpRequest(p, userNameField.getText(), String.valueOf(passwordField.getPassword()), Person.USER);
					
					try {
						conn.sendMessage(req);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		});
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				parent.dispose();
			}
			
		});
		
		setPreferredSize(new Dimension(560, 680));
		setLayout(new GridBagLayout());
		
		addGridBag(titleLabel, this, 0, 0, 2, 1, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, in);
		addGridBag(userInfoPanel, this, 0, 1, 2, 1, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, in);
		addGridBag(OKButton, this, 0, 2, 1, 1, 0.5, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, in);
		addGridBag(cancelButton, this, 1, 2, 1, 1, 0.5, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, in);
	}
	
	private void addGridBag(Component o, JPanel p, int x, int y, int gw, int gh, double wx, double wy, int fill, int a, Insets in){
		GridBagConstraints s = new GridBagConstraints(x, y, gw, gh, wx, wy, a, fill, in, 0, 0);
		p.add(o, s);
	}
	
	private void addStateBox(){
		stateBox.addItem("DC");
		stateBox.addItem("AL");
		stateBox.addItem("AK");
		stateBox.addItem("AZ");
		stateBox.addItem("AR");
		stateBox.addItem("CA");
		stateBox.addItem("CO");
		stateBox.addItem("CT");
		stateBox.addItem("DE");
		stateBox.addItem("FL");
		stateBox.addItem("GA");
		stateBox.addItem("HI");
		stateBox.addItem("ID");
		stateBox.addItem("IL");
		stateBox.addItem("IN");
		stateBox.addItem("IA");
		stateBox.addItem("KS");
		stateBox.addItem("KY");
		stateBox.addItem("LA");
		stateBox.addItem("ME");
		stateBox.addItem("MD");
		stateBox.addItem("MA");
		stateBox.addItem("MI");
		stateBox.addItem("MN");
		stateBox.addItem("MS");
		stateBox.addItem("MO");
		stateBox.addItem("MT");
		stateBox.addItem("NE");
		stateBox.addItem("NV");
		stateBox.addItem("NH");
		stateBox.addItem("NJ");
		stateBox.addItem("NM");
		stateBox.addItem("NY");
		stateBox.addItem("NC");
		stateBox.addItem("ND");
		stateBox.addItem("OH");
		stateBox.addItem("OK");
		stateBox.addItem("OR");
		stateBox.addItem("PA");
		stateBox.addItem("RI");
		stateBox.addItem("SC");
		stateBox.addItem("SD");
		stateBox.addItem("TN");
		stateBox.addItem("TX");
		stateBox.addItem("UT");
		stateBox.addItem("VT");
		stateBox.addItem("VA");
		stateBox.addItem("WA");
		stateBox.addItem("WV");
		stateBox.addItem("WI");
		stateBox.addItem("WY");		
	}

	private boolean checkError(){
		boolean flag = true;
		
		if(!String.valueOf(passwordField.getPassword()).equals(String.valueOf(confirmField.getPassword()))){
			flag = false;
			passwordInfoLabel.setText("The passwords you ");
			confirmInfoLabel.setText("typed do not match.");
		}
		else{
			passwordInfoLabel.setText("                  ");
			confirmInfoLabel.setText("                  ");
		}
		String[] tmp = emailField.getText().split("@");
		if(tmp.length !=2){
			flag = false;
			emailInfoLabel.setText("Invalid E-Mail.");
		}
		else{
			emailInfoLabel.setText("                  ");
		}
		
		return flag;
	}
	
	@Override
	public void receive(Reply msg) {
		// TODO Auto-generated method stub
		if(msg.getSuccess()){
			parent.dispose();
			JOptionPane.showMessageDialog(this, "Success.", "Success", JOptionPane.PLAIN_MESSAGE);
		}
		else{
			userNameInfoLabel.setText("User Name Exists.");
		}
	}

}
