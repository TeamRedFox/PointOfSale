import java.awt.*;
import javax.swing.*;

public class Login extends JPanel {
	protected JButton login;
	
	public Login(JFrame POS) {
		//Login LI = new Login(POS);
		Point p = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		
		setLayout(new BorderLayout(p.x, p.y));
		
		Panel panelN = new Panel();
		//add(panelN, BorderLayout.NORTH);
		
		Panel panel = new Panel();
		panel.setBackground(Color.WHITE);
		
		panel.setMaximumSize(new Dimension(100,100));
		panel.setLocation(p);
		add(panel);
		
		JTextArea text1 = new JTextArea();
		panel.add(text1);
		text1.setEditable(false);
		text1.setText("Username: ");
		
		JTextField textField = new JTextField();
		panel.add(textField);
		textField.setColumns(7);
		
		JTextArea text2 = new JTextArea();
		panel.add(text2);
		text2.setEditable(false);
		text2.setText("Password: ");
		
		JTextField textField2 = new JTextField();
		panel.add(textField2);
		textField2.setColumns(7);
		
		login = new JButton("Login");
//		panel.add(login);
		
	}
	
}