package retail;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import database_comm.ItemDatabase;

public class AddProductFrame {
	private String Barcode;
	private String Description;
	private String Price;
	private String Barcode2;
	private boolean Taxable = false;
	
	private Double cost;
	private int finalCost;
	
	private String error;
	private JFrame Item;
	
	public AddProductFrame() {
		Item = new JFrame("Add Item");
		Item.setResizable(false);
		Item.setTitle("Add Item");
		Item.setSize(400, 225);
		Item.setLocationRelativeTo(null);
		
		Panel greeting = new Panel();
		JTextArea greet = new JTextArea("Please enter the following information");
		greet.setBackground(null);
		greet.setEditable(false);
		greeting.add(greet, BorderLayout.CENTER);
		
		Panel information = new Panel();
		information.setLayout(new GridLayout(6,2));
		
		JTextArea barcode = new JTextArea("  Barcode:");
		barcode.setEditable(false);
		barcode.setBackground(null);
		JTextField barcodeE = new JTextField();
		barcodeE.setColumns(7);
		information.add(barcode);
		information.add(barcodeE);
		
		JTextArea barcode1 = new JTextArea("  Re-Enter Barcode:");
		barcode1.setEditable(false);
		barcode1.setBackground(null);
		JTextField barcode1E = new JTextField();
		barcode1E.setColumns(7);
		information.add(barcode1);
		information.add(barcode1E);
		
		JTextArea description = new JTextArea("  Description:");
		description.setEditable(false);
		description.setBackground(null);
		JTextField descriptionE = new JTextField();
		descriptionE.setColumns(7);
		information.add(description);
		information.add(descriptionE);
		
		JTextArea price = new JTextArea("  Price:");
		price.setEditable(false);
		price.setBackground(null);
		JTextField priceE = new JTextField();
		priceE.setColumns(7);
		information.add(price);
		information.add(priceE);
		
		
		
		JCheckBox taxable = new JCheckBox("Is the item Taxable?");
		information.add(taxable);
		
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
		
		JButton enter = new JButton("Add Item");
		buttons.add(enter);
		enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				

				if(taxable.isSelected()) {
					Taxable = true;
				}
				Barcode = barcodeE.getText();
				Barcode2 = barcode1E.getText();
				Description = descriptionE.getText();
				Price = priceE.getText();
				
				vData();
				
				/*
				Item a = new Item("item");
				a.set
				ItemDatabase.addItem(a);
				*/
			}
		});
		
		Panel combo = new Panel();
		combo.setLayout(new BorderLayout());
		combo.add(greeting, BorderLayout.NORTH);
		combo.add(information, BorderLayout.CENTER);
		combo.add(buttons, BorderLayout.SOUTH);
		
		Item.add(combo);
		Item.setVisible(true);
		Item.setAlwaysOnTop(true);
	}
	
	public void addItem(String name, String barcode, String description, int price, boolean taxable) {
		// TODO Auto-generated method stub
		
		Item newItem = new Item(name);
		newItem.setBarcode(barcode);
		newItem.setDescription(description);
		newItem.setPrice(price);
		newItem.setTaxable(taxable);
		
		try {
			ItemDatabase.addItem(newItem);
			JOptionPane.showMessageDialog(null, "Product Successfully Added");
			Item.dispose();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void vData() {
		
		Item.setAlwaysOnTop(false);
		error = "";
		
		validBarcode();
		validDescription();
		validPrice();
		
		if (error.isEmpty()) {
			addItem(Barcode, Barcode, Description, finalCost, Taxable);
		}
		else {
			error = error.substring(0, error.length() - 3);
			JOptionPane.showMessageDialog(null, "Invalid Entry: " + error);
		}

		Item.setAlwaysOnTop(true);
	}
	
	public void validBarcode() {
		if(!Barcode.equals(Barcode2)) {
			error = error + "Barcodes do not match | ";
		} else if (Barcode.isEmpty()) {

			error = error + "Invalid barcode | ";
		}
		else {
			try {
				if (ItemDatabase.getItemFromBarcode(Barcode) != null)
						error = error + "Item already exists | ";
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void validDescription() {
		if(Description.length() < 1) {
			
			error = error + "Invalid Description | ";
		}
	}
	
	public void validPrice() {
		
		try {
			cost = Double.parseDouble(Price);
			cost = cost * 100;
			finalCost = cost.intValue();
			
		} catch (Exception e) {
			//e.printStackTrace();
			error = error + "Invalid Price | ";
		}
	}
}
