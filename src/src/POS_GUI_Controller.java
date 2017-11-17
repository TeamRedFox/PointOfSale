import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.*;

import database_comm.UserDatabase;
import login.InvalidUsernameOrPasswordException;
import login.LoginPanel;
import retail.AddUserFrame;
import retail.Item;
import retail.RegisterPanel;
import login.User;


public class POS_GUI_Controller extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//instance variables
	private POS_Driver register;
	private static final int MIN_WIDTH = 425;
	private static final int MIN_HEIGHT = 425;

	//component variables
	private RegisterPanel registerPanel;
	private LoginPanel loginPanel;

	CardLayout card = new CardLayout();
	JPanel c = new JPanel();

	//Constructor
	public POS_GUI_Controller(POS_Driver website) {
		super("POS");
		this.register = register;

		loginPanel = new LoginPanel();

		setUpLayout();
		startScreen();
		addListeners();

	}

	public void setUpLayout() {
		c.setLayout(card);
		this.add(c);
		c.add(loginPanel, "login");
		card.show(c, "login");

	}

	//sets screen upon start up
	public void startScreen() {
		this.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}


	//reformats the GUI after panels are switched
	public void reformatScreen() {
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
	}

	//can add actionlisteners later when needed instead of when class is constructed
	public void addListeners() {
		loginPanel.getSignInBtn().addActionListener(signIn);

		// makes the username textfield the focus when the program is launched
		this.addWindowListener( new WindowAdapter() {
			public void windowOpened( WindowEvent e){
				loginPanel.getUsernameTxt().requestFocus();
			}
		});

		// makes it so pressing enter after inputting a password is used to login aside from pressing sign in button
		loginPanel.getPasswordTxt().addActionListener(signIn); 
	}

	public void setFrameTitle(String title) {
		this.setTitle(title);
	}

	//Creating ActionListeners here

	//sign in


	ActionListener signIn = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Authenticating connection...");
			try {
				String username = loginPanel.getUsernameTxt().getText();
				String password = new String(loginPanel.getPasswordTxt().getPassword());
				User newUser;
				try {
					newUser = UserDatabase.getUserFromLogin(username, password);
					System.out.println(newUser);
					if(newUser != null) {
						//selectionScreen();
						setFrameTitle(username);
						registerPanel = new RegisterPanel(newUser);
						registerPanel.getLogOutBtn().addActionListener(logOut);
						registerPanel.getSearchBtn().addActionListener(addItem);
						registerPanel.getAddUserdBtn().addActionListener(addUser);

						c.add("profile", registerPanel);
						card.show(c, "profile");
						// this time the search textfield is the focus after successful login	
						registerPanel.getSearchTxt().requestFocus();
						reformatScreen();	
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			catch (InvalidUsernameOrPasswordException ex) {
				loginPanel.getPasswordTxt().setText("");
				loginPanel.getErrorLbl().setText(ex.getErrorMessage());
				loginPanel.getErrorLbl().setHorizontalAlignment(JLabel.CENTER);
				loginPanel.getErrorLbl().setForeground(Color.RED);
			}
		}


	};

	ActionListener logOut = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			    Object[] options = { "Yes", "No" };
			    int n = JOptionPane.showOptionDialog(new JFrame(),
			            "Are you sure you want to log out?", "LOG OUT?",
			            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
			            options, options[1]);
			    if(n == JOptionPane.OK_OPTION){ // Afirmative
			        //.... 
					card.show(c,"login");
					setFrameTitle("POS");
					card.removeLayoutComponent(registerPanel);
					c.remove(registerPanel);
		
					loginPanel.getErrorLbl().setText("");
					reformatScreen();
					loginPanel.getPasswordTxt().requestFocus(); // focuses on the password when logging out
			    }
			    
		}
	};

	ActionListener addItem = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//FIXME iterate through item list csv and then compare the barcode id from barcode textfield
			String barcodeID = registerPanel.getSearchTxt().getText();
			//			HashMap<String, Item> itemList = website.getItems();

			if(registerPanel.getSearchTxt().getText().equals("")) {
				// do nothing maybe throw an exception or dialog box?
			}
			//	if(itemList.containsKey(barcodeID)) {
			//	JOptionPane.showMessageDialog(null, "Item Info: " + itemList.get(barcodeID).getBarcodeID() + " " +itemList.get(barcodeID).getItemName() + " " +
			//		itemList.get(barcodeID).getItemDesc() + " " + itemList.get(barcodeID).getQuantity() + " " + 
			//	itemList.get(barcodeID).getPrice());

			reformatScreen();
			loginPanel.getPasswordTxt().requestFocus();
		}
	};// focuses on the password when logging out
	

	ActionListener addUser = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			AddUserFrame user = new AddUserFrame();
		}
	};
	
	
}
