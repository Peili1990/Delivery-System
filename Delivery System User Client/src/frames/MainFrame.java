package frames;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import windows.OrderPanel;
import windows.QueryPanel;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1699502238507335787L;
	
	private String userName;
	
	private JTabbedPane tabPane;
	
	private OrderPanel orderPanel;
	
	public MainFrame(String name){
		
		setTitle("Delivery System");
		
		userName = name;
		
		tabPane = new JTabbedPane();
		
		orderPanel = new OrderPanel(this);
		
		tabPane.add("Add New Order", orderPanel);
		
		QueryPanel queryPanel = new QueryPanel();
		
		tabPane.add("Make Query", queryPanel);
		
		if(name.length() == 0){
			tabPane.setEnabledAt(0, false);
			tabPane.setSelectedIndex(1);
		}
		
		tabPane.setPreferredSize(orderPanel.getPreferredSize());
		
		this.setPreferredSize(tabPane.getPreferredSize());
		add(tabPane);
		
		pack();
		
		setVisible(true);
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public String getUserName() {
		return userName;
	}
	
	
	
//	public Insets getInsets(){ 
//	    Insets squeeze=new Insets(30,20,20,20); 
//	    return squeeze;
//	} 
}
