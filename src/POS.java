import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;


public class POS {
	public static void main(String[] sa) throws IOException{
		doIt();
	}

	private static void doIt() throws IOException {
		JFrame POS = new JFrame("Point of Sales System");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenSize.setSize(screenSize.getWidth(), screenSize.getHeight()- 40);
		POS.setExtendedState(JFrame.MAXIMIZED_BOTH);
		POS.setSize(screenSize);
		POS.setResizable(false);
		POS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Login LI = new Login(POS);
		LI.setOpaque(true);
		//POS.setContentPane(LI);
		POS.setVisible(true);
		
	}
}
