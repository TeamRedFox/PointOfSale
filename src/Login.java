import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import database_comm.UserDatabase;
import login.User;


public class Login extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField_1;
	
	public Login(JFrame POS) {
		//SelctionScreen SS = new SelectionScreen(POS);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		POS.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Point p = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		
		contentPane.setMaximumSize(new Dimension(200, 100));
		contentPane.setMinimumSize(new Dimension(100, 100));
		contentPane.setPreferredSize(new Dimension(200, 100));
		contentPane.setLocation(p.x, p.y);
		contentPane.setBounds(p.x, p.y, 200, 100);
		
		JPanel panel = new JPanel();
		
		panel.setMaximumSize(new Dimension(200, 100));
		panel.setMinimumSize(new Dimension(100, 100));
		panel.setPreferredSize(new Dimension(200, 100));
		panel.setLocation(p.x, p.y);
		panel.setBounds(553, 329, 200, 100);
		panel.setLayout(null);
		contentPane.add(panel);
		
		JTextPane txtpnUsername = new JTextPane();
		txtpnUsername.setBounds(11, 10, 66, 16);
		txtpnUsername.setEditable(false);
		txtpnUsername.setText("Username:");
		
		textField = new JTextField();
		textField.setBounds(82, 5, 106, 26);
		textField.setColumns(8);
		
		JTextPane txtpnPassword = new JTextPane();
		txtpnPassword.setBounds(13, 41, 63, 16);
		txtpnPassword.setEditable(false);
		txtpnPassword.setText("Password:");
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(81, 36, 106, 26);
		passwordField_1.setColumns(8);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.add(txtpnUsername);
		panel.add(textField);
		panel.add(txtpnPassword);
		panel.add(passwordField_1);
		
		JButton btnLogin = new JButton("Login");
		panel.add(btnLogin);
		
		btnLogin.addActionListener(new ActionListener () {
			protected String passwords;

			public void actionPerformed(ActionEvent e) {
				System.out.println("0");
				String username = textField.getText();
				passwords = new String(passwordField_1.getPassword());
				User newUser;
				try {
					newUser = UserDatabase.getUserFromLogin(username, passwords);
					System.out.println(newUser);
					if(newUser != null) {
						//selectionScreen();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});	
	}
}
