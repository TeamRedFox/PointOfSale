package retail;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import database_comm.ItemDatabase;


public class RemoveProductFrame {
	
	private String Barcode;
	private String Barcode2;
	private JFrame ItemR;

	private String error;
	
	public RemoveProductFrame() {
		ItemR = new JFrame("Delete Product From Database");
		ItemR.setResizable(false);
		ItemR.setTitle("Delete Product From Database");
		ItemR.setSize(400, 225);
		ItemR.setLocationRelativeTo(null);
		
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
		
		Panel buttons = new Panel();
		buttons.setLayout(new GridLayout(1,2));
		
		JButton exit = new JButton("Exit");
		buttons.add(exit);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ItemR.dispose();
			}
		});
		
		JButton enter = new JButton("Delete Item");
		buttons.add(enter);
		enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				

				Barcode = barcodeE.getText();
				Barcode2 = barcode1E.getText();
				
				deleteItem(Barcode);
				
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
		
		ItemR.add(combo);
		ItemR.setVisible(true);
		ItemR.setAlwaysOnTop(true);
	}
	
	public void deleteItem(String barcode) {
		// TODO Auto-generated method stub
		
		if(validBarcode() == true) {
			Object[] options = { "Yes", "No" };
		    int n = JOptionPane.showOptionDialog(new JFrame(),
		            "Are you sure you want to permenatly delete Item?", "Delete Item?",
		            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
		            options, options[1]);
		    if(n == JOptionPane.OK_OPTION){ // Afirmative
		        //.... 
				try {
					ItemDatabase.removeItem(barcode);
					JOptionPane.showMessageDialog(null, "Item Successfully Deleted");
					ItemR.dispose();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Cannot find Barcode");
				}
		    }
			
		} else {
			JOptionPane.showMessageDialog(null, error);
		}
		
		
	}
	
	
	public Boolean validBarcode() {
		if (Barcode.length() >= 6 && Barcode.length() < 12) {
			
			if(Barcode.equals(Barcode2)) {
				return true;
			} else {
				
				error = error + "Barcodes do not match | ";
				return false;
			}
		} else {
			error = error + "Invalid Entry | ";
			return false;
		}
		
	}
}