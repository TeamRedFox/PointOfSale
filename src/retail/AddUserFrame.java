package retail;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

import database_comm.UserDatabase;
import login.SecurityHelper;
import login.User;

public class AddUserFrame extends JFrame {
	
	private String first;
	private String last;
	private String username;
	private String password;
	private String password2;
	private boolean admin = false;
	private String error;
	private JFrame User;
	ArrayList<String> push = new ArrayList<String>(4);
	
	public AddUserFrame() {
	    User = new JFrame("Add User");
		User.setResizable(false);
		User.setTitle("Add User");
		User.setSize(400, 225);
		User.setLocationRelativeTo(null);
		
		Panel greeting = new Panel();
		JTextArea greet = new JTextArea("Please enter the following information");
		greet.setBackground(null);
		greet.setEditable(false);
		greeting.add(greet, BorderLayout.CENTER);
		
		Panel information = new Panel();
		information.setLayout(new GridLayout(6,2));
		
		JTextArea firstName = new JTextArea("  First Name:");
		firstName.setEditable(false);
		firstName.setBackground(null);
		JTextField fName = new JTextField();
		fName.setColumns(7);
		information.add(firstName);
		information.add(fName);
		
		JTextArea lastName = new JTextArea("  Last Name:");
		lastName.setEditable(false);
		lastName.setBackground(null);
		JTextField lName = new JTextField();
		lName.setColumns(7);
		information.add(lastName);
		information.add(lName);
		
		JTextArea USERNAME = new JTextArea("  Create Username:");
		USERNAME.setEditable(false);
		USERNAME.setBackground(null);
		JTextField userID = new JTextField();
		userID.setColumns(7);
		information.add(USERNAME);
		information.add(userID);
		
		JTextArea PASSWORD = new JTextArea("  Create Password:");
		PASSWORD.setEditable(false);
		PASSWORD.setBackground(null);
		JPasswordField pass = new JPasswordField();
		pass.setColumns(7);
		information.add(PASSWORD);
		information.add(pass);
		
		JTextArea PASSWORD1 = new JTextArea("  Re-Enter Password:");
		PASSWORD1.setEditable(false);
		PASSWORD1.setBackground(null);
		JPasswordField pass2 = new JPasswordField();
		pass2.setColumns(7);
		information.add(PASSWORD1);
		information.add(pass2);
		
		JCheckBox ADMIN = new JCheckBox("Are you an Admin?");
		information.add(ADMIN);
		
		Panel buttons = new Panel();
		buttons.setLayout(new GridLayout(1,2));
		
		JButton exit = new JButton("Exit");
		buttons.add(exit);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				User.dispose();
			}
		});
		
		JButton enter = new JButton("Create User");
		buttons.add(enter);
		enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "Invalid Entry: ";
				

				if(ADMIN.isSelected()) {
					admin = true;
				}
				first = fName.getText();
				last = lName.getText();
				username = userID.getText();
				password = new String(pass.getPassword());
				password2 = new String(pass2.getPassword());
				
				vData();
				
			}
		});
		
		Panel combo = new Panel();
		combo.setLayout(new BorderLayout());
		combo.add(greeting, BorderLayout.NORTH);
		combo.add(information, BorderLayout.CENTER);
		combo.add(buttons, BorderLayout.SOUTH);
		
		User.add(combo);
		User.setVisible(true);
		User.setAlwaysOnTop(true);
		
	}

	public void pushUser(String FN, String LN, String userID, String pass, boolean admin) {
		// TODO Auto-generated method stub
		
		User addNew = new User(userID);
		addNew.setFirstName(FN);
		addNew.setLastName(LN);
		addNew.setAdmin(admin);
		addNew.setPaswordHash(SecurityHelper.generatePasswordHash(pass));
		try {
			UserDatabase.addUser(addNew);
			JOptionPane.showMessageDialog(null, "User Successfully Added");
			User.dispose();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void vData() {
		
		if (error.length() > 15) {
			JOptionPane.showMessageDialog(null, error);
		}
		else {
			pushUser(first, last, username, password, admin);
		}
		
		
	}
	
	public  void validFN() {
		if (first.length() <= 2) {
			error = error + "Invalid First Name | ";
		}
	}
	
	public void validLN() {
		if (last.length() <= 2) {
			error = error + "Invalid Last Name | ";
		}
	}
	
	public void validUser() {
		if(username.length() <= 4) {
			
			error = error + "Invalid Username | ";
		}
	}
	
	public void validPass() {
		if(password.length() <= 3) {
			if(password.equals(password2)) {
				
			} else {
				error = error + "Passwords do not match | ";
			}
		} else {
			error = error + "Invalid Password | ";
		}
	}

}
