package retail;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CheckoutFrame extends JFrame {


	private JFrame Item;
	protected JPanel North, information;
	protected static int change;
	
	
	public CheckoutFrame(Cart cart) {
		
		Item = new JFrame("Checkout");
		Item.setResizable(false);
		Item.setTitle("Checkout");
		Item.setSize(550, 350);
		Item.setLocationRelativeTo(null);
	
		DecimalFormat money = new DecimalFormat("0.00");
		
		North = new JPanel();
		North.add(cart.generateScrollPane(), BorderLayout.NORTH);
		
		JTextArea totals = new JTextArea("Sub-Total: $" + money.format(((double) cart.subtotal)/100) + "\n" + 
											 "Tax: $" + money.format(((double) cart.totalTax)/100) + "\n" +
											"Total: $" + money.format(((double) cart.total)/100));
		
		totals.setBackground(null);
		North.add(totals, BorderLayout.CENTER);
		JTextArea prompt = new JTextArea("  Enter Payment Amount:");
		prompt.setEditable(false);
		prompt.setBackground(null);
		
		JTextField input = new JTextField();
		input.setColumns(7);
		information = new JPanel();
		information.add(prompt);
		information.add(input);
		
		Panel buttons = new Panel();
		buttons.setLayout(new GridLayout(1,2));
		
		JButton exit = new JButton("Exit");
		buttons.add(exit);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Item.dispose();
			}
		});
		
		JButton enter = new JButton("Checkout");
		buttons.add(enter);
		
		ActionListener checkOut = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Item.setAlwaysOnTop(false);
					
					DecimalFormat money = new DecimalFormat("0.00");
					int payment = (int)(Double.parseDouble(input.getText()) * 100);
					change = cart.total - payment;
					if (change == 0) {
						JOptionPane.showMessageDialog(null, "Transaction Complete!");
						ReceiptGenerator receipt = new ReceiptGenerator(cart);

						cart.removeAllItems();
						Item.dispose();
						
					} else if (change < 0){
						change = Math.abs(change);
						JOptionPane.showMessageDialog(null, "Transaction Complete!" + System.lineSeparator()+ " Change Due: $" + RetailHelper.getCashString(change));
						ReceiptGenerator receipt = new ReceiptGenerator(cart);
						
						cart.removeAllItems();
						Item.dispose();
						
					} else {
						JOptionPane.showMessageDialog(null, "Transaction Incomplete!" + System.lineSeparator()+ " $" + RetailHelper.getCashString(change) + " is due.");
						Item.setAlwaysOnTop(true);
					}
					
					
				} catch (Exception a) {
					
				}
			}
		};

		enter.addActionListener(checkOut);
		input.addActionListener(checkOut);
		
		//cart.add(North);
		
		Item.add(North, BorderLayout.NORTH);
		Item.add(information, BorderLayout.CENTER);
		//Item.add(buttons, BorderLayout.SOUTH);
		Item.setVisible(true);
		Item.setAlwaysOnTop(true);
		
		Panel numPanel = new Panel();
		addNumpad(numPanel, input);
		Item.add(numPanel, BorderLayout.SOUTH);
		numPanel.add(buttons, BorderLayout.SOUTH);
	}
	
	public void addNumpad(Panel panel, JTextField input)
	{
		JPanel numPnl = new JPanel(new GridLayout(4,3));
		JPanel zeroPnl = new JPanel(new BorderLayout());
		
		for(int i = 1; i < 13; i++){
			
			JButton numBtn = new JButton();


			if(i == 10){
				numBtn.setText(String.valueOf("."));
				numBtn.addActionListener(e -> RetailHelper.addToTextField(input, numBtn.getText()));
			}
			else if (i == 11) {
				numBtn.setText(String.valueOf("0"));
				numBtn.addActionListener(e -> RetailHelper.addToTextField(input, numBtn.getText()));
			}
			else if (i == 12) {
				numBtn.setText(String.valueOf("clr"));
				numBtn.addActionListener(e -> RetailHelper.setTextField(input, ""));
			}
			else {
				numBtn.setText(String.valueOf(i));
				numBtn.addActionListener(e -> RetailHelper.addToTextField(input, numBtn.getText()));				
			}
			numPnl.add(numBtn);
				
//				JButton zeroBtn = new JButton();
//				zeroBtn.setText("0");
//				zeroBtn.addActionListener(e -> input.setText(input.getText()+ "" + zeroBtn.getText()));
//				zeroPnl.add(zeroBtn, BorderLayout.CENTER);
//				barcodePnl.add(b, BorderLayout.SOUTH);
//			}
		}
		JPanel eastNumPnl = new JPanel();
		eastNumPnl.setLayout(new BoxLayout(eastNumPnl, BoxLayout.PAGE_AXIS));

		Dimension size = new Dimension(100, 100);
		zeroPnl.setPreferredSize(size);
		zeroPnl.setSize(size);
		zeroPnl.setMaximumSize(size);

		
		panel.add(numPnl, BorderLayout.SOUTH);
		//panel.add(zeroPnl, BorderLayout.SOUTH);
	}
}
