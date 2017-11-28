package retail;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class AddUserFrame extends JFrame {
	
	private String first;
	private String last;
	private String username;
	private String password;
	private String password2;
	private boolean admin = false;
	private String error;
	ArrayList<String> push = new ArrayList<String>(4);
	
	public AddUserFrame() {
		JFrame User = new JFrame("Add User");
		User.setResizable(false);
		User.setTitle("Add User");
		User.setSize(400, 225);
		
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
				push = null;
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
		
	}

	public void pushUser(String FN, String LN, String userID, String pass, boolean admin) {
		// TODO Auto-generated method stub
		System.out.println("Nope");
	}
	
	public void vData() {
		
		try {
			validFN();
			validLN();
			validUser();
			validPass();
			pushUser(push.get(0).toString(), push.get(1).toString(), push.get(2).toString(), push.get(3).toString(), admin);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, error);
		}
	}
	
	public  Object validFN() {
		if (first.length() >= 2) {
			push.add(first);
			return push;
		} else {
			error = error + "Invalid First Name | ";
			return push;
		}
	}
	
	public Object validLN() {
		if (last.length() >= 2) {
			push.add(last);
			return push;
		} else {
			error = error + "Invalid Last Name | ";
			return push;
		}
	}
	
	public Object validUser() {
		if(username.length() >= 4) {
			push.add(username);
			return push;
		} else {
			error = error + "Invalid Username | ";
			return push;
		}
	}
	
	public Object validPass() {
		if(password.length() <= 3) {
			if(password.equals(password2)) {
				push.add(password);
				return push;
			} else {
				error = error + "Passwords do not match | ";
				return push;
			}
		} else {
			error = error + "Invalid Password | ";
			return push;
		}
	}

}
