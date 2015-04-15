package frames;

import java.awt.BorderLayout;
import java.awt.Insets;

import javax.swing.JFrame;

import windows.SignUpPanel;

public class SignUpFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8920468777155764568L;
	
	SignUpPanel signUpPanel;
	
	public SignUpFrame(){
		setTitle("Delivery System Sign Up");
		
		signUpPanel = new SignUpPanel(this);
		
		setPreferredSize(signUpPanel.getPreferredSize());
		setMinimumSize(signUpPanel.getPreferredSize());
		
		this.setLayout(new BorderLayout());
		
		add(signUpPanel, BorderLayout.CENTER);
		
		pack();
		
		setVisible(true);
		
		//setResizable(false);
	}
	
	public Insets getInsets(){ 
	    Insets squeeze=new Insets(0,30,10,30); 
	    return squeeze;
	} 

}
