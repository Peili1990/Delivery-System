package windows;

import interfaces.Receiver;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import utils.replies.Reply;
import utils.requests.QueryRequest;
import main.Connection;

public class UserClientPanel extends JPanel implements Receiver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Connection conn;
	
	private JScrollPane sendPanel;
	private JScrollPane receivePanel;
	
	private JTextArea sendTextArea;
	private JTextArea receiveTextArea;
	
	private JButton sendButton;
	
	private JPanel InformationPanel;
	
	private final int width = 640;
	
	private final int height = 480;
	
	public UserClientPanel(){
		init();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans/Beans.xml");
		
		conn = (Connection) context.getBean("connection");//new Connection(this);
		
		conn.setReceiver(this);
		
		((ClassPathXmlApplicationContext) context).close();
	}
		
	
	private void init(){
		this.setPreferredSize(new Dimension(width, height));
		
		this.setLayout(new BorderLayout());
		
		InformationPanel = new JPanel();
		InformationPanel.setLayout(new BoxLayout(InformationPanel, BoxLayout.X_AXIS));
		
		sendTextArea = new JTextArea();
		sendTextArea.setLineWrap(true);
		
		sendPanel = new JScrollPane(sendTextArea);
		
		receiveTextArea = new JTextArea();
		receiveTextArea.setLineWrap(true);
		
		receivePanel = new JScrollPane(receiveTextArea);
		
		InformationPanel.add(sendPanel);
		InformationPanel.add(receivePanel);
		
		sendButton = new JButton("send");
		sendButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				QueryRequest req = new QueryRequest(sendTextArea.getText());
				try {
					conn.sendMessage(req);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Bad reply.");
					e.printStackTrace();
				}
			}
			
		});
		
		this.add(InformationPanel, BorderLayout.CENTER);
		this.add(sendButton, BorderLayout.SOUTH);
		
	}

	@Override
	public void receive(Reply msg) {
//		receiveTextArea.setText(receiveTextArea.getText() + msg + "\n");
//		receiveTextArea.paintImmediately(receiveTextArea.getBounds());
	}

}
