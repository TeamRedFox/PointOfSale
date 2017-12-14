package retail;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import login.User;

public class RegisterPanel extends JPanel {
//panel that shows personal info and later the saved results from the previous login session
	private Cart cartPanel;

	// admin buttons (implement role specific buttons)
	private JButton addUserBtn = new JButton("Add User"),
					delUserBtn = new JButton("Delete User"),
					addProductBtn = new JButton("Add Product"),
					delProductBtn = new JButton("Delete Product"),
					voidTransactionBtn = new JButton("Void Transaction"),
					voidItemBtn = new JButton("Void Item"),
					checkOutBtn = new JButton("Checkout"),
					// basic user buttons
					searchBtn = new JButton("Search Item"),
					logOutBtn = new JButton("Log Out"),
					clearBtn = new JButton("Clear");
	
	
	public static JTextField searchTxt = new JTextField(12); 		
	private JTextArea itemsTextArea = new JTextArea("Test");

	private JPanel registerPnl = new JPanel();
	



	public String searchString = "";
	
	public RegisterPanel(User user, Cart cart) {
		cartPanel = cart;
		this.setLayout(new BorderLayout());
		displayInfo(user);
	}
	
	
	public void addListeners(){
		/* Add listeners to
		 * Search button
		 * Clear
		 * Search textfield
		 * Add Product
		 * Delete Product
		 */
		

	}
	public void displayInfo(User user) {
		this.setLayout(new BorderLayout());
		// Logout Panel
		//JPanel topPnl = new JPanel(new BorderLayout());
		
		//topPnl.add(searchTxt, BorderLayout.CENTER);
		JPanel logOutPnl = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	
		// if the user is an admin they will have added admin privileges in the form of buttons
		if(user.isAdmin()== true){ // change to isAdmin or some boolean later on?
			logOutPnl.add(addUserBtn);
			logOutPnl.add(delUserBtn);
			logOutPnl.add(addProductBtn);
			logOutPnl.add(delProductBtn);
			
		}
		logOutPnl.add(voidTransactionBtn);
		logOutPnl.add(voidItemBtn);
		logOutPnl.add(searchBtn);
		logOutPnl.add(checkOutBtn);
		logOutPnl.add(logOutBtn);
		this.add(logOutPnl, BorderLayout.NORTH);
		
		// Right barcode Panel - used to input barcode to search
		// move search item button next to search textfield
		// add action listener to search/clear buttons and search text 
		JPanel barcodePnl = new JPanel(new BorderLayout()); 
		
		barcodePnl.setBorder(new TitledBorder("Barcode input"));
		JPanel searchPnl = new JPanel(new BorderLayout());
		
		JPanel zeroPnl = new JPanel(new BorderLayout());
		searchPnl.add(searchTxt, BorderLayout.WEST);
		searchPnl.add(searchBtn, BorderLayout.EAST);
		barcodePnl.add(searchPnl, BorderLayout.NORTH);
		
		JPanel numPnl = new JPanel(new GridLayout(3,3));
		
		for(int i = 1; i < 10; i++){
			JButton numBtn = new JButton();
			numBtn.setText(String.valueOf(i));
			searchString = searchTxt.getText();
			numBtn.addActionListener(e -> searchTxt.setText(searchTxt.getText() + "" + numBtn.getText())) ;
			numPnl.add(numBtn);
			
			
			if(i == 9){
				JButton zeroBtn = new JButton();
				zeroBtn.setText("0");
				zeroBtn.addActionListener(e -> searchTxt.setText(searchTxt.getText()+ "" + zeroBtn.getText()));
				zeroPnl.add(zeroBtn, BorderLayout.CENTER);
				//barcodePnl.add(b, BorderLayout.SOUTH);
			}
		}
		JPanel eastNumPnl = new JPanel();
		eastNumPnl.setLayout(new BoxLayout(eastNumPnl, BoxLayout.PAGE_AXIS));
		// add listeners - maybe move to a method in POS GUI?
		clearBtn.addActionListener(e -> searchTxt.setText(""));
		
		barcodePnl.add(clearBtn, BorderLayout.EAST);
		
		Dimension size = new Dimension(100, 100);
		zeroPnl.setPreferredSize(size);
		zeroPnl.setSize(size);
		zeroPnl.setMaximumSize(size);
		
		barcodePnl.add(zeroPnl, BorderLayout.SOUTH);
		barcodePnl.add(numPnl, BorderLayout.CENTER);
		
		
		
		// Left User info panel - is this needed? 
		
		JPanel infoPnl = new JPanel(new GridLayout(1, 2));
		infoPnl.setBorder(new TitledBorder("User Information"));
		
		JPanel userPnl = new JPanel();
		userPnl.setLayout(new BoxLayout(userPnl, BoxLayout.PAGE_AXIS));
		userPnl.setBorder(new TitledBorder("User"));
		
		JLabel lblFirstName = new JLabel("First Name:");
		JLabel lblLastName = new JLabel("Last Name:");
		//JLabel lblEmail = new JLabel("Email:");
		JLabel lblUsername = new JLabel("Username:");
		JTextField txtFirstName = new JTextField(user.getFirstName(), 15);
		txtFirstName.setEditable(false);
		JTextField txtLastName = new JTextField(user.getLastName(), 15);
		txtLastName.setEditable(false);
	//	JTextField txtEmail = new JTextField(user.getEmail(), 15);
	//	txtEmail.setEditable(false);
		JTextField txtUsername = new JTextField(user.getUsername(), 15);
		txtUsername.setEditable(false);
		
		JPanel firstNamePnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
		firstNamePnl.add(lblFirstName);
		firstNamePnl.add(txtFirstName);
		userPnl.add(firstNamePnl);
		JPanel lastNamePnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lastNamePnl.add(lblLastName);
		lastNamePnl.add(txtLastName);
		userPnl.add(lastNamePnl);
	//	JPanel emailPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
	//	emailPnl.add(lblEmail);
	//	emailPnl.add(txtEmail);
	//	userPnl.add(emailPnl);
		JPanel usernamePnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
		usernamePnl.add(lblUsername);
		usernamePnl.add(txtUsername);
		userPnl.add(usernamePnl);
		//userPnl.add(Cart.pricing);
		
		/* TODO 
		 * Add Item Register
		 * Add NumPad buttons
		 * Add Search textfield for Item barcode searching (search button as well?)
		 * Add scroll bar on item register panel
		 * 		
		*/
		
		// change register panel to boxlayout for single file drop?
		registerPnl.setLayout(new BorderLayout()); 
		registerPnl.setBorder(new TitledBorder("Item Register"));
		JLabel registerLbl = new JLabel("Item list:");
		//code for creating item cart list on GUI

		registerPnl.add(registerLbl, BorderLayout.NORTH);
		registerPnl.add(cartPanel, BorderLayout.CENTER);
		//registerPnl.add(Cart.pricing, BorderLayout.SOUTH);
		
		//TODO Use a JSpinner for item quantities when added to the item register
		
		
//		registerPnl.add(itemsTextArea, BorderLayout.CENTER);
		
		infoPnl.add(userPnl);
		infoPnl.add(registerPnl);
		infoPnl.add(barcodePnl);
		
		
		
		this.add(infoPnl, BorderLayout.CENTER);
		
	}
	
	/**Clear all listeners from the panel, used for re-logging in*/
	public void removeAllListeners()
	{
		removeActionListeners(logOutBtn);
		removeActionListeners(searchBtn);
		removeActionListeners(searchTxt);
		removeActionListeners(addUserBtn);
		removeActionListeners(voidItemBtn);
		removeActionListeners(delUserBtn);
		removeActionListeners(addProductBtn);
		removeActionListeners(delProductBtn);
		removeActionListeners(checkOutBtn);
		removeActionListeners(voidTransactionBtn);
	}
	
	void removeActionListeners(JButton button)
	{
		ActionListener[] listeners = button.getActionListeners();
		for(ActionListener listener : listeners) {
			button.removeActionListener(listener);
		}
	}

	void removeActionListeners(JTextField textField)
	{
		ActionListener[] listeners = textField.getActionListeners();
		for(ActionListener listener : listeners) {
			textField.removeActionListener(listener);
		}
	}
	
	// getters and setters
	public JPanel getRegisterPnl() {
		return registerPnl;
	}


	public void setRegisterPnl(JPanel registerPnl) {
		this.registerPnl = registerPnl;
	}
	
	public JButton getAddProdBtn() {
		return addProductBtn;
	}
	
	public JButton getCheckoutBtn() {
		return checkOutBtn;
	}
	
	public JButton getDelProdBtn() {
		return delProductBtn;
	}
	
	public JButton getAddUserdBtn() {
		return addUserBtn;
	}
	
	public JButton getDelUserBtn() {
		return delUserBtn;
	}
	
	public JButton getSearchBtn() {
		return searchBtn;
	}
	
	public JButton getLogOutBtn() {
		return logOutBtn;
	}
	
	public JButton getVoidTransactionBtn() {
		return voidTransactionBtn;
	}


	public JTextField getSearchTxt() {
		return searchTxt;
	}


	public JTextArea getItemsTextArea() {
		return itemsTextArea;
	}


	public void setItemsTextArea(JTextArea itemsTextArea) {
		this.itemsTextArea = itemsTextArea;
	}


	public JButton getVoidItemBtn() {
		// TODO Auto-generated method stub
		return voidItemBtn;
	}
	
}
