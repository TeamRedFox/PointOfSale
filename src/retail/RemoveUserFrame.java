package retail;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

import database_comm.UserDatabase;
import login.User;

public class RemoveUserFrame extends JFrame {
	
	private String username;
	private String username2;
	private String error;
	private JFrame User;
	
	public RemoveUserFrame() {
		User = new JFrame("Delete User");
		User.setResizable(false);
		User.setTitle("Delete User");
		User.setSize(400, 225);
		User.setLocationRelativeTo(null);
		Panel greeting = new Panel();
		JTextArea greet = new JTextArea("Please enter the following information");
		greet.setBackground(null);
		greet.setEditable(false);
		greeting.add(greet, BorderLayout.CENTER);
		
		Panel information = new Panel();
		information.setLayout(new GridLayout(6,2));
		
		
		JTextArea USERNAME = new JTextArea("  Username:");
		USERNAME.setEditable(false);
		USERNAME.setBackground(null);
		JTextField userID = new JTextField();
		userID.setColumns(7);
		information.add(USERNAME);
		information.add(userID);
		
		JTextArea USERNAME2 = new JTextArea("  Re-Enter Username:");
		USERNAME2.setEditable(false);
		USERNAME2.setBackground(null);
		JTextField userID2 = new JTextField();
		userID2.setColumns(7);
		information.add(USERNAME2);
		information.add(userID2);
		
		
		
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
		
		JButton enter = new JButton("Delete User");
		buttons.add(enter);
		enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				username = userID.getText();
				username2 = userID2.getText();
				
				deleteUser(username);
				
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

	public void deleteUser(String userID) {
		// TODO Auto-generated method stub
		
		User.setAlwaysOnTop(false);
		if (validUser() == false) {
			JOptionPane.showMessageDialog(null, error);
		}
		else {
			Object[] options = { "Yes", "No" };
			 int n = JOptionPane.showOptionDialog(new JFrame(),
			            "Are you sure you want to permenantly delete User?", "Delete User?",
			            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
			            options, options[1]);
			    if(n == JOptionPane.OK_OPTION){ // Afirmative
			        //.... 
			    	try {
						UserDatabase.removeUser(userID);
						JOptionPane.showMessageDialog(null, "User Successfully Removed");
						User.dispose();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			
		}
		User.setAlwaysOnTop(true);
		}

	
	public Boolean validUser()
	{
		if(!username.equals(username2)) {
			error = "Usernames do not match";
			return false;
		}
		
		try {
			if (!UserDatabase.doesUserExist(username))
			{
				error = "Username not Found";
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
		
	}
	
}
