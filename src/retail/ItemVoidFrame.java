package retail;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import database_comm.ItemDatabase;

public class ItemVoidFrame extends JFrame {
	
	private JFrame Item;
	protected Cart cart = new Cart();
	
	public ItemVoidFrame() {
		
		Item = new JFrame("Void Item");
		Item.setResizable(false);
		Item.setTitle("Void Item");
		Item.setSize(400, 100);
		
		Panel greeting = new Panel();
		JTextArea greet = new JTextArea("Please enter the following information");
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
		enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String user = input.getText();
					Item product = ItemDatabase.getItemFromBarcode(user);
					cart.removeItem(product);
					Item.dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Product not Found");	
				} catch(Exception e3) {
					JOptionPane.showMessageDialog(null, "Product not Found");
				}
			}
		});
		
		Panel combo = new Panel();
		combo.setLayout(new BorderLayout());
		combo.add(greeting, BorderLayout.NORTH);
		combo.add(information, BorderLayout.CENTER);
		combo.add(buttons, BorderLayout.SOUTH);
		
		Item.add(combo);
		Item.setVisible(true);
		
	}

}
