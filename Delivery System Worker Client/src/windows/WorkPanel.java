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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import main.Connection;
import utils.replies.RefreshReply;
import utils.replies.Reply;
import utils.requests.AddLocationRequest;
import utils.requests.Request;
import utils.requests.UpdateRequest;

public class WorkPanel extends JPanel implements Receiver{

	/**
	 * 
	 */
	private static final long serialVersionUID = -921481504836438522L;
	
	private JLabel orderIDLabel;
	
	private JLabel statusLabel;
	
	private JTable orderTable;
	
	private DefaultTableModel tableModel;
	
	private ListSelectionModel selectionMode;
	
	private JLabel locationLabel;
	
	private JTextField locationField;
	
	private JComboBox<String> statusBox;
	
	private JButton updateButton;
	
	private JButton addLocationButton;
	
	private JButton refreshButton;
	
	private JScrollPane tabelPanel;
	
	private Connection conn;
	
	private int id;
	
	public WorkPanel(){
		init();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans/Beans.xml");
		
		conn = (Connection) context.getBean("connection");//new Connection(this);
		
		conn.setReceiver(this);
		
		((ClassPathXmlApplicationContext) context).close();
		
		refresh();
		
		id = -1;
	}
	
	private void init(){
		this.setLayout(new GridBagLayout());
		
		Insets in = new Insets(5, 5, 5, 5);
		
		orderTable = new JTable();
		
		tableModel = (DefaultTableModel) orderTable.getModel();
		
		tableModel.addColumn("Order ID");
		tableModel.addColumn("Status");
		
		orderTable.getColumnModel().getColumn(0).setPreferredWidth(200);
		orderTable.getColumnModel().getColumn(1).setPreferredWidth(500);
		
		selectionMode = orderTable.getSelectionModel();
		selectionMode.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if(orderTable.getSelectedRow() != -1){
					id = (int) tableModel.getValueAt(orderTable.getSelectedRow(), 0);
					
					orderIDLabel.setText("Order ID: " + id);
					
					statusBox.setSelectedItem(tableModel.getValueAt(orderTable.getSelectedRow(), 1));
				}		
			}
			
		});
		
		tabelPanel = new JScrollPane(orderTable);
		addGridBag(tabelPanel, this, 0, 0, 4, 1, 1.0, 1.0, GridBagConstraints.BOTH, GridBagConstraints.NORTHWEST, in);
		
		orderIDLabel = new JLabel("Order ID: N/A");
		addGridBag(orderIDLabel, this, 0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, in);
		
		statusLabel = new JLabel("Status");
		addGridBag(statusLabel, this, 1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.CENTER, in);

		statusBox = new JComboBox<String>();
		addGridBag(statusBox, this, 2, 1, 1, 1, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, in);
		
		updateButton = new JButton("Update");
		updateButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				update();
			}
			
		});
		addGridBag(updateButton, this, 3, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.CENTER, in);
		
		locationLabel = new JLabel("Location");
		addGridBag(locationLabel, this, 0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.CENTER, in);
		
		locationField = new JTextField();
		addGridBag(locationField, this, 1, 2, 2, 1, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, in);
		
		addLocationButton = new JButton("Add");
		addLocationButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				add();
			}
			
		});
		addGridBag(addLocationButton, this, 3, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.CENTER, in);
		
		refreshButton = new JButton("Refresh");
		refreshButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				refresh();
			}
			
		});
		addGridBag(refreshButton, this, 0, 3, 4, 1, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, in);
	}
	
	private void refresh(){
		Request req = new Request(Request.REFRESH);
		
		try {
			conn.sendMessage(req);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void add(){
		if(id == -1){
			JOptionPane.showMessageDialog(this, "Please select a row", "No DATA selected", JOptionPane.ERROR_MESSAGE);
			
			return;
		}
		
		AddLocationRequest req = new AddLocationRequest(id, locationField.getText());
		
		try {
			conn.sendMessage(req);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void update(){
		if(id == -1){
			JOptionPane.showMessageDialog(this, "Please select a row", "No DATA selected", JOptionPane.ERROR_MESSAGE);
			
			return;
		}
		
		UpdateRequest req = new UpdateRequest(id, statusBox.getSelectedIndex());
		
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
			RefreshReply re = (RefreshReply) msg;
			tableModel.setRowCount(0);
			for(int i = 0; i < re.getID().size(); i ++){
				Object[] o = {re.getID().get(i), re.getTypeList().get(re.getStatus().get(i))};
				tableModel.addRow(o);
			}
			statusBox.removeAllItems();
			for(String s : re.getTypeList()){
				statusBox.addItem(s);
			}
		}
		else if(msg.getType() == Reply.ADD){
			if(msg.getSuccess()){
				JOptionPane.showMessageDialog(this, "Infomation Updated.", "Update Success", JOptionPane.PLAIN_MESSAGE);
				refresh();
			}
		}
	}
}
