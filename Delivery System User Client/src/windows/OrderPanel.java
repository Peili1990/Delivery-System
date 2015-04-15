package windows;

import interfaces.Receiver;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import main.Connection;
import utils.Address;
import utils.Merchandise;
import utils.Person;
import utils.replies.Reply;
import utils.requests.AddOrderRequest;
import frames.MainFrame;

public class OrderPanel extends JPanel implements Receiver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8284821000575777489L;
	
	private JLabel orderTypeLabel;
	
	private JComboBox<String> orderTypeBox;
	
	private JLabel itemNameLabel;
	
	private JTextField itemNameField;
	
	private JLabel itemWeightLabel;
	
	private JTextField itemWeightField;
	
	private JLabel itemVolumeLabel;
	
	private JTextField itemVolumeField;
	
	private JLabel itemQuantityLabel;
	
	private JTextField itemQuantityField;
	
	private JLabel itemNoteLabel;
	
	private JTextField itemNoteField;
	
	private JLabel receiverFirstNameLabel;
	
	private JTextField receiverFirstNameField;
	
	private JLabel receiverLastNameLabel;
	
	private JTextField receiverLastNameField;
	
	private JLabel receiverStreetLabel;
	
	private JTextField receiverStreetField;
	
	private JLabel receiverCityLabel;
	
	private JTextField receiverCityField;
	
	private JLabel receiverStateLabel;
	
	private JComboBox<String> receiverStateBox;
	
	private JLabel receiverZipLabel;
	
	private JTextField receiverZipField;
	
	private JLabel receiverEmailLabel;
	
	private JTextField receiverEmailField;
	
	private JLabel receiverPhoneLabel;
	
	private JTextField receiverPhoneField;
	
	private JLabel payMethodLabel;
	
	private JComboBox<String> payMethodBox;
	
	private JLabel estimateDateLabel;
	
	private JTextField estimateDateField;
	
	private JLabel priceLabel;
	
	private JTextField priceField;
	
	private JLabel posterNameLabel;
	
	private JTextField posterNameField;
	
	private JButton submitButton;
	
	
	private JLabel orderInfoLabel;
	
	private JLabel itemInfoLabel;
	
	private JLabel receiverInfoLabel;
	
	private JLabel posterInfoLabel;
	
	private JPanel itemInfoPanel;
	
	private JPanel orderInfoPanel;
	
	private JPanel posterInfoPanel;
	
	private JPanel receiverInfoPanel;
	
	private MainFrame parent;
	
	private Connection conn;
	
	public OrderPanel(MainFrame p){
		parent = p;
		
		init();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans/Beans.xml");
		
		conn = (Connection) context.getBean("connection");//new Connection(this);
		
		conn.setReceiver(this);
		
		((ClassPathXmlApplicationContext) context).close();
	}
	
	private void init(){
		
		Insets in = new Insets(5, 5, 5, 5);
		
		// item info
		
		itemInfoPanel = new JPanel();
		
		itemInfoPanel.setLayout(new GridBagLayout());
		itemInfoPanel.setBorder(BorderFactory.createEtchedBorder());
								   
		itemInfoLabel = new JLabel("Item Infomation:                                              ");
		addGridBag(itemInfoLabel, itemInfoPanel, 0, 0, 4, 1, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, in);
		
		itemNameLabel = new JLabel("Name");
		addGridBag(itemNameLabel, itemInfoPanel, 0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST, in);
		
		itemNameField = new JTextField(32);
		addGridBag(itemNameField, itemInfoPanel, 1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, in);
		
		itemQuantityLabel = new JLabel("Qty");
		addGridBag(itemQuantityLabel, itemInfoPanel, 2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST, in);

		itemQuantityField = new JTextField(11);
		itemQuantityField.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e){
				int key = e.getKeyChar();
				if(key < KeyEvent.VK_0 || key > KeyEvent.VK_9){
					e.consume();
				}
			}
			
		});
		addGridBag(itemQuantityField, itemInfoPanel, 3, 1, 1, 1, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, in);
		
		itemWeightLabel = new JLabel("Weight");
		addGridBag(itemWeightLabel, itemInfoPanel, 0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST, in);
		
		itemWeightField = new JTextField(20);
		addGridBag(itemWeightField, itemInfoPanel, 1, 2, 1, 1, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, in);
		
		itemVolumeLabel = new JLabel("Volume");
		addGridBag(itemVolumeLabel, itemInfoPanel, 2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST, in);
		
		itemVolumeField = new JTextField(20);
		addGridBag(itemVolumeField, itemInfoPanel, 3, 2, 1, 1, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, in);
		
		itemNoteLabel = new JLabel("Note");
		addGridBag(itemNoteLabel, itemInfoPanel, 0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST, in);
		
		itemNoteField = new JTextField(128);
		addGridBag(itemNoteField, itemInfoPanel, 1, 3, 3, 1, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, in);


		// order info
		
		orderInfoPanel = new JPanel();
		
		orderInfoPanel.setLayout(new GridBagLayout());
		orderInfoPanel.setBorder(BorderFactory.createEtchedBorder());
		
		orderInfoLabel = new JLabel("Order Information:                                          ");
		addGridBag(orderInfoLabel, orderInfoPanel, 0, 0, 2, 1, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, in);

		orderTypeLabel = new JLabel("Order Type");
		addGridBag(orderTypeLabel, orderInfoPanel, 0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST, in);
		
		orderTypeBox = new JComboBox<String>();
		orderTypeBox.addItem("Seven days ship");
		orderTypeBox.addItem("Five days ship");
		orderTypeBox.addItem("Two days ship");
		orderTypeBox.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED){
					int id = orderTypeBox.getSelectedIndex();
					switch(id){
					case 0:
						priceField.setText("4.99$");
						break;
					case 1:
						priceField.setText("6.99$");
						break;
					case 2:
						priceField.setText("9.99$");
						break;
					}
				}
			}
			
		});
		addGridBag(orderTypeBox, orderInfoPanel, 1, 1, 3, 1, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, in);

		estimateDateLabel = new JLabel("Estimate Date");
		addGridBag(estimateDateLabel, orderInfoPanel, 0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST, in);
		
		estimateDateField = new JTextField();
		estimateDateField.setEditable(false);
		DateFormat d = DateFormat.getDateInstance();
		estimateDateField.setText(d.format(getDaysAfter(7)));
		addGridBag(estimateDateField, orderInfoPanel, 1, 2, 3, 1, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, in);
		
		payMethodLabel = new JLabel("Pay Method");
		addGridBag(payMethodLabel, orderInfoPanel, 0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST, in);
		
		payMethodBox = new JComboBox<String>();
		payMethodBox.addItem("Cash on delivery");
		addGridBag(payMethodBox, orderInfoPanel, 1, 3, 3, 1, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, in);
		
		priceLabel = new JLabel("Price");
		addGridBag(priceLabel, orderInfoPanel, 0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST, in);

		priceField = new JTextField(10);
		priceField.setEditable(false);
		priceField.setText("4.99$");
		addGridBag(priceField, orderInfoPanel, 1, 4, 3, 1, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, in);

		
		receiverInfoPanel = new JPanel();
		
		receiverInfoPanel.setLayout(new GridBagLayout());
		receiverInfoPanel.setBorder(BorderFactory.createEtchedBorder());
		
		receiverInfoLabel = new JLabel("Receiver¡¡Information:                                                                           ");
		addGridBag(receiverInfoLabel, receiverInfoPanel, 0, 0, 4, 1, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, in);

		receiverFirstNameLabel = new JLabel("First Name");
		addGridBag(receiverFirstNameLabel, receiverInfoPanel, 0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST, in);
		
		receiverFirstNameField = new JTextField(16);
		addGridBag(receiverFirstNameField, receiverInfoPanel, 1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, in);
		
		receiverLastNameLabel = new JLabel("Last Name");
		addGridBag(receiverLastNameLabel, receiverInfoPanel, 2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST, in);

		receiverLastNameField = new JTextField(16);
		addGridBag(receiverLastNameField, receiverInfoPanel, 3, 1, 1, 1, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, in);

		receiverStreetLabel = new JLabel("Street");
		addGridBag(receiverStreetLabel, receiverInfoPanel, 0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST, in);

		receiverStreetField = new JTextField(16);
		addGridBag(receiverStreetField, receiverInfoPanel, 1, 2, 1, 1, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, in);

		receiverCityLabel = new JLabel("City");
		addGridBag(receiverCityLabel, receiverInfoPanel, 2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST, in);
		
		receiverCityField = new JTextField(16);
		addGridBag(receiverCityField, receiverInfoPanel, 3, 2, 1, 1, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, in);

		receiverStateLabel = new JLabel("State");
		addGridBag(receiverStateLabel, receiverInfoPanel, 0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST, in);

		receiverStateBox = new JComboBox<String>();
		addReceiverStateBox();
		addGridBag(receiverStateBox, receiverInfoPanel, 1, 3, 1, 1, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, in);
		
		receiverZipLabel = new JLabel("Zip");
		addGridBag(receiverZipLabel, receiverInfoPanel, 2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST, in);

		receiverZipField = new JTextField(6);
		addGridBag(receiverZipField, receiverInfoPanel, 3, 3, 1, 1, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, in);

		receiverEmailLabel = new JLabel("E-Mail");
		addGridBag(receiverEmailLabel, receiverInfoPanel, 0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST, in);
		
		receiverEmailField = new JTextField(45);
		addGridBag(receiverEmailField, receiverInfoPanel, 1, 4, 3, 1, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, in);
		
		receiverPhoneLabel = new JLabel("Phone");
		addGridBag(receiverPhoneLabel, receiverInfoPanel, 0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST, in);
		
		receiverPhoneField = new JTextField(16);
		addGridBag(receiverPhoneField, receiverInfoPanel, 1, 5, 3, 1, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, in);
		

		
		posterInfoPanel = new JPanel();
		
		posterInfoPanel.setLayout(new GridBagLayout());
		posterInfoPanel.setBorder(BorderFactory.createEtchedBorder());
		
		posterInfoLabel = new JLabel("Poster Information:                                                                                  ");
		addGridBag(posterInfoLabel, posterInfoPanel, 0, 0, 3, 1, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, in);
		
		posterNameLabel = new JLabel("Name");
		addGridBag(posterNameLabel, posterInfoPanel, 0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST, in);

		posterNameField = new JTextField();
		posterNameField.setEditable(false);
		posterNameField.setText(parent.getUserName());
		addGridBag(posterNameField, posterInfoPanel, 1, 1, 2, 1, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, in);
		

		
		this.setPreferredSize(new Dimension(680, 400));
		
		this.setLayout(new GridBagLayout());
		
		addGridBag(orderInfoPanel, this, 0, 0, 1, 5, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST, in);
		
		addGridBag(itemInfoPanel, this, 0, 5, 1, 4, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST, in);

		addGridBag(receiverInfoPanel, this, 1, 0, 1, 6, 1.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST, in);

		addGridBag(posterInfoPanel, this, 1, 6, 1, 2, 1.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST, in);
		
		submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Address add = new Address(receiverStreetField.getText(), receiverCityField.getText(), 
						receiverStateBox.getSelectedItem().toString(), receiverZipField.getText());
				
				Person receiver = new Person(receiverFirstNameField.getText(), receiverLastNameField.getText(),
						receiverPhoneField.getText(), receiverEmailField.getText(), add);
				
				Merchandise item = new Merchandise(itemNameField.getText(), Integer.valueOf(itemQuantityField.getText()),
						itemVolumeField.getText(), itemWeightField.getText(), itemNoteField.getText());
				
				AddOrderRequest req = new AddOrderRequest(parent.getUserName(), orderTypeBox.getSelectedIndex(), receiver, item, payMethodBox.getSelectedIndex());
				
				try {
					conn.sendMessage(req);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		addGridBag(submitButton, this, 1, 8, 1, 1, 1.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST, in);

	}
	
	private void addGridBag(Component o, JPanel p, int x, int y, int gw, int gh, double wx, double wy, int fill, int a, Insets in){
		GridBagConstraints s = new GridBagConstraints(x, y, gw, gh, wx, wy, a, fill, in, 0, 0);
		p.add(o, s);
	}
	
	private Date getDaysAfter(int day){
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}
	
	private void addReceiverStateBox(){
		receiverStateBox.addItem("DC");
		receiverStateBox.addItem("AL");
		receiverStateBox.addItem("AK");
		receiverStateBox.addItem("AZ");
		receiverStateBox.addItem("AR");
		receiverStateBox.addItem("CA");
		receiverStateBox.addItem("CO");
		receiverStateBox.addItem("CT");
		receiverStateBox.addItem("DE");
		receiverStateBox.addItem("FL");
		receiverStateBox.addItem("GA");
		receiverStateBox.addItem("HI");
		receiverStateBox.addItem("ID");
		receiverStateBox.addItem("IL");
		receiverStateBox.addItem("IN");
		receiverStateBox.addItem("IA");
		receiverStateBox.addItem("KS");
		receiverStateBox.addItem("KY");
		receiverStateBox.addItem("LA");
		receiverStateBox.addItem("ME");
		receiverStateBox.addItem("MD");
		receiverStateBox.addItem("MA");
		receiverStateBox.addItem("MI");
		receiverStateBox.addItem("MN");
		receiverStateBox.addItem("MS");
		receiverStateBox.addItem("MO");
		receiverStateBox.addItem("MT");
		receiverStateBox.addItem("NE");
		receiverStateBox.addItem("NV");
		receiverStateBox.addItem("NH");
		receiverStateBox.addItem("NJ");
		receiverStateBox.addItem("NM");
		receiverStateBox.addItem("NY");
		receiverStateBox.addItem("NC");
		receiverStateBox.addItem("ND");
		receiverStateBox.addItem("OH");
		receiverStateBox.addItem("OK");
		receiverStateBox.addItem("OR");
		receiverStateBox.addItem("PA");
		receiverStateBox.addItem("RI");
		receiverStateBox.addItem("SC");
		receiverStateBox.addItem("SD");
		receiverStateBox.addItem("TN");
		receiverStateBox.addItem("TX");
		receiverStateBox.addItem("UT");
		receiverStateBox.addItem("VT");
		receiverStateBox.addItem("VA");
		receiverStateBox.addItem("WA");
		receiverStateBox.addItem("WV");
		receiverStateBox.addItem("WI");
		receiverStateBox.addItem("WY");		
	}
	public Insets getInsets(){ 
		Insets squeeze=new Insets(30,10,20,10); 
		return squeeze;
	}

	@Override
	public void receive(Reply msg) {
		// TODO Auto-generated method stub
		if(msg.getType() ==  Reply.ADD){
			JOptionPane.showMessageDialog(this, "Your order ID is " + msg.getNote() + ".", "Order Success", JOptionPane.PLAIN_MESSAGE);
		}
	} 
}
