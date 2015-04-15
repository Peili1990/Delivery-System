package frames;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import windows.WorkPanel;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1699502238507335787L;
	
	private String userName;
	
	private JTabbedPane tabPane;
	
	
	public MainFrame(String name){
		
		setTitle("Delivery System");
		
		userName = name;
		
		tabPane = new JTabbedPane();
		
		WorkPanel workPanel = new WorkPanel();
		
		tabPane.add("Update information", workPanel);
		
		if(name.length() == 0){
			tabPane.setEnabledAt(0, false);
			tabPane.setSelectedIndex(1);
		}
		
		tabPane.setPreferredSize(workPanel.getPreferredSize());
		
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
}
