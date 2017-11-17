package retail;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AddUserFrame extends JFrame {

	private String username;
	private String password;
	private boolean admin = false;
	
	public AddUserFrame() {
		JFrame User = new JFrame("Add User");
		User.setResizable(false);
		User.setTitle("Add User");
		User.setSize(400, 200);
		
		Panel greeting = new Panel();
		JTextArea greet = new JTextArea("Please enter the following information");
		greet.setBackground(null);
		greet.setEditable(false);
		greeting.add(greet, BorderLayout.CENTER);
		
		Panel information = new Panel();
		information.setLayout(new GridLayout(4,1));
		
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
				if(ADMIN.isSelected()) {
					admin = true;
				}
				String PASS1 = new String(pass.getPassword());
				String PASS2 = new String(pass2.getPassword());
				pushUser(userID.getText(), PASS1, PASS2, admin);
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

	public void pushUser(String userID, String pass, String pass2, boolean admin) {
		// TODO Auto-generated method stub
		if (pass == pass2) {
			System.out.println(userID);
		} else {
			JOptionPane.showMessageDialog( null, "Passwords do not match");
		}
	}

}
