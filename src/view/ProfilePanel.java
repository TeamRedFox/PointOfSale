package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.function.Function;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import model.User;

public class ProfilePanel extends JPanel {
//panel that shows personal info and later the saved results from the previous login session

	// admin buttons (implement role specific buttons)
	private JButton viewProductBtn = new JButton("View Product"),
					addProductBtn = new JButton("Add Product"),
					delProductBtn = new JButton("Delete Product"),
					// basic user buttons
					searchBtn = new JButton("Search Item"),
					logOutBtn = new JButton("Log Out"),
					clearBtn = new JButton("Clear");
	// used to search item by barcode. usually has 12 digits
	// FIXME make it so the user can only input 12 integers/digitsv 
	JTextField searchTxt = new JTextField(12); 		

	public String searchString = "";
	
	public ProfilePanel(User user) {
		this.setLayout(new BorderLayout());
		displayInfo(user);
	}
	
	
	public void addListeners(){
		/* Add listeners to
		 * Search button
		 * Clear
		 * Search textfield
		 * Add Product
		 * View Product
		 * Delete Product
		 */

	}
	public void displayInfo(User user) {
		this.setLayout(new BorderLayout());
		// Logout Panel
		//JPanel topPnl = new JPanel(new BorderLayout());
		
		//topPnl.add(searchTxt, BorderLayout.CENTER);
		JPanel logOutPnl = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	
	
		if(user.getRole().equals("Administrator")){
			logOutPnl.add(viewProductBtn);
			logOutPnl.add(addProductBtn);
			logOutPnl.add(delProductBtn);
		}
		
		logOutPnl.add(searchBtn);
		logOutPnl.add(logOutBtn);
		//topPnl.add(logOutPnl, BorderLayout.EAST);
		this.add(logOutPnl, BorderLayout.NORTH);
		
		// Right barcode Panel - used to input barcode to search?
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
		// add listeners - maybe move to a methodin POS GUI?
		clearBtn.addActionListener(e -> searchTxt.setText(""));
		
		barcodePnl.add(clearBtn, BorderLayout.EAST);
		
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
		JLabel lblEmail = new JLabel("Email:");
		JLabel lblUsername = new JLabel("Username:");
		JTextField txtFirstName = new JTextField(user.getFirstName(), 15);
		txtFirstName.setEditable(false);
		JTextField txtLastName = new JTextField(user.getLastName(), 15);
		txtLastName.setEditable(false);
		JTextField txtEmail = new JTextField(user.getEmail(), 15);
		txtEmail.setEditable(false);
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
		JPanel emailPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
		emailPnl.add(lblEmail);
		emailPnl.add(txtEmail);
		userPnl.add(emailPnl);
		JPanel usernamePnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
		usernamePnl.add(lblUsername);
		usernamePnl.add(txtUsername);
		userPnl.add(usernamePnl);
		
		/* TODO 
		 * Add Item Register
		 * Add NumPad buttons
		 * Add Search textfield for Item barcode searching (search button as well?)
		 * Add scroll bar on item register panel
		 * 		
		*/
		
		// change register panel to boxlayout for single file drop?
		JPanel registerPnl = new JPanel();
		registerPnl.setLayout(new BoxLayout(registerPnl, BoxLayout.PAGE_AXIS)); 
		registerPnl.setBorder(new TitledBorder("Item Register"));
		JLabel registerLbl = new JLabel("Item list:");
		
		registerPnl.add(registerLbl);
		
		infoPnl.add(userPnl);
		infoPnl.add(registerPnl);
		infoPnl.add(barcodePnl);
		
		
		
		this.add(infoPnl, BorderLayout.CENTER);

		
		
	}
	
	
	
	public JButton getAddProdBtn() {
		return addProductBtn;
	}
	
	public JButton getDelProdBtn() {
		return delProductBtn;
	}
	
	public JButton getViewProdBtn() {
		return viewProductBtn;
	}
	
	public JButton getSearchBtn() {
		return searchBtn;
	}
	
	public JButton getLogOutBtn() {
		return logOutBtn;
	}


	public JTextField getSearchTxt() {
		return searchTxt;
	}
	
}
