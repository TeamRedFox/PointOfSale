package retail;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class CheckoutFrame extends JFrame {


	private JFrame Item;
	protected JPanel North, information;
	protected static double change;
	
	Cart cart = new Cart();
	
	public CheckoutFrame() {
		
		Item = new JFrame("Checkout");
		Item.setResizable(false);
		Item.setTitle("Checkout");
		Item.setSize(250, 300);
		Item.setLocationRelativeTo(null);
	
		DecimalFormat money = new DecimalFormat("0.00");
		
		North = new JPanel();
		North.add(cart.scroll, BorderLayout.NORTH);
		
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
		enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DecimalFormat money = new DecimalFormat("0.00");
					double payment = Double.parseDouble(input.getText());
					change = (double) cart.total - payment;
					if (change == 0) {
						JOptionPane.showMessageDialog(null, "Transaction Complete!");
						ReceiptGenerator receipt = new ReceiptGenerator();
						Item.dispose();
						
					} else if (change < 0){
						change = Math.abs(change);
						JOptionPane.showMessageDialog(null, "Transaction Complete! Change Due: $" + money.format(change));
						ReceiptGenerator receipt = new ReceiptGenerator();
						Item.dispose();
						
					} else {
						JOptionPane.showMessageDialog(null, "Transaction Incomplete! $" + change + " is due.");
					}
					
				} catch (Exception a) {
					
				}
			}
		});
		//cart.add(North);
		
		Item.add(North, BorderLayout.NORTH);
		Item.add(information, BorderLayout.CENTER);
		Item.add(buttons, BorderLayout.SOUTH);
		Item.setVisible(true);
		Item.setAlwaysOnTop(true);
	}
}
