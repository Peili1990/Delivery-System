package windows;

import interfaces.Receiver;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import main.Connection;
import utils.replies.QueryReply;
import utils.replies.Reply;
import utils.requests.QueryRequest;

public class QueryPanel extends JPanel implements Receiver{

	/**
	 * 
	 */
	private static final long serialVersionUID = -921481504836438522L;
	
	private JLabel orderIDLabel;
	
	private JTextField orderIDField;
	
	private JLabel idLabel;
	
	private JLabel statusLabel;
	
	private JTable locationTable;
	
	private DefaultTableModel tableModel;
	
	private JButton trackButton;
	
	private JScrollPane tabelPanel;
	
	private Connection conn;
	
	public QueryPanel(){
		init();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans/Beans.xml");
		
		conn = (Connection) context.getBean("connection");//new Connection(this);
		
		conn.setReceiver(this);
		
		((ClassPathXmlApplicationContext) context).close();
	}
	
	private void init(){
		this.setLayout(new GridBagLayout());
		
		Insets in = new Insets(5, 5, 5, 5);
		
		orderIDLabel = new JLabel("Order ID");
		addGridBag(orderIDLabel, this, 0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST, in);
		
		orderIDField = new JTextField(16);
		addGridBag(orderIDField, this, 1, 0, 1, 1, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, in);
		
		trackButton = new JButton("Track");
		trackButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(orderIDField.getText().length() == 0){
					JOptionPane.showMessageDialog(null, "Please insert an ID.", "Invalid ID", JOptionPane.ERROR_MESSAGE);
				}
				else{
					query();				
				}
			}
			
		});
		addGridBag(trackButton, this, 2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.CENTER, in);

		idLabel = new JLabel("Order ID: N/A");
		addGridBag(idLabel, this, 0, 1, 3, 1, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, in);
		
		statusLabel = new JLabel("Order Status: N/A");
		addGridBag(statusLabel, this, 0, 2, 3, 1, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, in);
		
		locationTable = new JTable();
		
		tableModel = (DefaultTableModel) locationTable.getModel();
		
		tableModel.addColumn("Date");
		tableModel.addColumn("Location");
		
		locationTable.getColumnModel().getColumn(0).setPreferredWidth(200);
		locationTable.getColumnModel().getColumn(1).setPreferredWidth(500);
		
		tabelPanel = new JScrollPane(locationTable);
		addGridBag(tabelPanel, this, 0, 3, 3, 1, 1.0, 1.0, GridBagConstraints.BOTH, GridBagConstraints.NORTHWEST, in);

	}
	
	private void query(){
		QueryRequest req = new QueryRequest(orderIDField.getText());
		
		try {
			conn.sendMessage(req);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void addGridBag(Component o, JPanel p, int x, int y, int gw, int gh, double wx, double wy, int fill, int a, Insets in){
		GridBagConstraints s = new GridBagConstraints(x, y, gw, gh, wx, wy, a, fill, in, 0, 0);
		p.add(o, s);
	}

	@Override
	public void receive(Reply msg) {
		if(msg.getType() == Reply.QUERY){
			QueryReply rep = (QueryReply) msg;
			if(rep.getSuccess()){
				tableModel.setRowCount(0);
				for(int i = 0; i < rep.getTime().size(); i ++){
					Object[] o = {rep.getTime().get(i), rep.getLocation().get(i)};
					
					tableModel.addRow(o);
				}
				
				idLabel.setText("Order ID: " + orderIDField.getText());
				
				statusLabel.setText("Order Status: " + rep.getStatus());
				
			}
			else{
				JOptionPane.showMessageDialog(this, msg.getNote(), "Invalid order ID", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
