package frames;

import java.awt.BorderLayout;
import java.awt.Insets;

import javax.swing.JFrame;

import windows.LoginPanel;

public class LoginFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1699502238507335787L;
	
	LoginPanel loginPanel;
	
	public LoginFrame(){
		setTitle("Delivery System Login");
		
		loginPanel = new LoginPanel(this);
		
		setPreferredSize(loginPanel.getPreferredSize());
		
		this.setLayout(new BorderLayout());
		
		add(loginPanel, BorderLayout.CENTER);
		
		pack();
		
		setVisible(true);
		
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public Insets getInsets(){ 
	    Insets squeeze=new Insets(50,30,10,30); 
	    return squeeze;
	} 
}
