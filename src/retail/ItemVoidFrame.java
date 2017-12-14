package retail;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import database_comm.ItemDatabase;

public class ItemVoidFrame extends JFrame {
	
	private JFrame Item;
	public ItemVoidFrame(Cart cart) {
		
		Item = new JFrame("Void Item");
		Item.setResizable(false);
		Item.setTitle("Void Item");
		Item.setSize(400, 100);
		Item.setLocationRelativeTo(null);
		
		Panel greeting = new Panel();
		JTextArea greet = new JTextArea("Please scan the item you wish to void");
		greet.setBackground(null);
		greet.setEditable(false);
		greeting.add(greet, BorderLayout.CENTER);
		
		Panel information = new Panel();
		information.setLayout(new GridLayout(1,2));
		
		JTextArea barcode = new JTextArea("  Enter Barcode:");
		barcode.setEditable(false);
		barcode.setBackground(null);
		JTextField input = new JTextField();
		input.setColumns(7);
		information.add(barcode);
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
		
		JButton enter = new JButton("Void Item");
		buttons.add(enter);
		
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Item.setAlwaysOnTop(false);
				
				try {
					String barcode = input.getText();
					cart.removeItem(barcode);
				} catch(Exception e3) {
					JOptionPane.showMessageDialog(null, "Product not Found");
				}

				Item.setAlwaysOnTop(true);
				Item.dispose();
				cart.requestItemSearchFocus();
			}
		};
		enter.addActionListener(listener);
		input.addActionListener(listener);
		
		Panel combo = new Panel();
		combo.setLayout(new BorderLayout());
		combo.add(greeting, BorderLayout.NORTH);
		combo.add(information, BorderLayout.CENTER);
		combo.add(buttons, BorderLayout.SOUTH);
		
		Item.add(combo);
		Item.setVisible(true);
		Item.setAlwaysOnTop(true);

		input.requestFocus();
	}

}
