package login;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class LoginPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//component variables
	//FIXME is there a limit for how many characters do username and password have later on?
	JTextField usernameTxt = new JTextField(10); 
	JPasswordField passwordTxt = new JPasswordField(10);
	JButton signInBtn = new JButton("Sign In");
	JLabel errorLbl = new JLabel();

	private BufferedImage logoImage;
	
	private static String message = System.lineSeparator() + "Welcome to TeamRedFox's POS System"+ System.lineSeparator() + "Enter your user credentials below!";
	private static final int START_ROWS = 5;
	private static final int START_COLUMNS = 1;
	
	public LoginPanel() {
		JTextPane welcome = new JTextPane();
		welcome.setEditable(false);
		welcome.setText(message);
		welcome.setBackground(this.getBackground());
		welcome.setFont(new Font("Arial", Font.BOLD, 16));
		
		//inspired by http://stackoverflow.com/questions/3213045/centering-text-in-a-jtextarea-or-jtextpane-horizontal-text-alignment
		//Centering welcome text
		StyledDocument doc = welcome.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		this.setLayout(new BorderLayout());
		this.add(welcome, BorderLayout.NORTH);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(START_ROWS, START_COLUMNS));

		JPanel userPnl = new JPanel();
		JLabel userLbl = new JLabel("Username");
		userPnl.setLayout(new FlowLayout());
		userPnl.add(userLbl);
		userPnl.add(usernameTxt);
		infoPanel.add(userPnl);
		
		JPanel passPnl = new JPanel();
		JLabel passLbl = new JLabel("Password");
		passPnl.setLayout(new FlowLayout());
		passPnl.add(passLbl);
		passPnl.add(passwordTxt);
		infoPanel.add(passPnl);
		
		JPanel signInBtnPnl = new JPanel();
		signInBtnPnl.add(signInBtn);
		infoPanel.add(errorLbl);
		infoPanel.add(signInBtnPnl);
		
		infoPanel.add(new JPanel());
		
		this.add(infoPanel, BorderLayout.SOUTH);
	
		addImage();
	}

	
	void addImage()
	{
		try {
			logoImage = ImageIO.read(new File("logo.png"));
			JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
			this.add(logoLabel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public JTextField getUsernameTxt() {
		return usernameTxt;
	}

	public void setUsernameTxt(JTextField usernameTxt) {
		this.usernameTxt = usernameTxt;
	}

	public JPasswordField getPasswordTxt() {
		return passwordTxt;
	}

	public void setPasswordTxt(JPasswordField passwordTxt) {
		this.passwordTxt = passwordTxt;
	}

	public JButton getSignInBtn() {
		return signInBtn;
	}

	public void setSignInBtn(JButton signInBtn) {
		this.signInBtn = signInBtn;
	}

	public JLabel getErrorLbl() {
		return errorLbl;
	}

	public void setErrorLbl(JLabel errorLbl) {
		this.errorLbl = errorLbl;
	}
		
}
