package retail;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.util.*;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Cart extends JPanel {
	
	public static ArrayList<Item> list;
	protected  int size, subtotal = 0;
	protected  int total = 0;
	protected  int totalTax = 0;
	
	protected static DefaultListModel listModel = new DefaultListModel();
	protected JList shoppingList = new JList(listModel);
	protected JScrollPane scroll = new JScrollPane();
	protected JTextArea sub, tot, fTot;
	protected static JPanel subP, totP,fTotP, pricing = new JPanel();
	protected static String Cart = "";
	
	//constructor to create an arraylist for cart with a default size of 100
	public Cart() {
		DecimalFormat money = new DecimalFormat("0.00");
		
		resetCartFields();
		
		scroll.setViewportView(shoppingList);
		scroll.setSize(300, 150);
		//add(shoppingList);
		

		sub = new JTextArea();
		tot = new JTextArea();
		fTot = new JTextArea();
		
		updatePriceFields();
	
		sub.setBackground(null);
		tot.setBackground(null);
		fTot.setBackground(null);
		
		sub.setEditable(false);
		tot.setEditable(false);
		fTot.setEditable(false);
		
		setLayout(new GridLayout(4,1));
		
		add(scroll);
		add(sub);
		add(tot);
		add(fTot);
		
		
		//pricing.add(subP);
		//pricing.add(totP);
		//pricing.add(fTotP);
		//add(pricing);
	}
	
	//constructor to create an arraylist for cart with a specified size
//	public Cart(int cartSize) {
//		
//		list = new ArrayList<Item> (cartSize);
//		this.setCartSize(0);
//		this.setSubtotal(0);
//		this.setTotalTax(0);
//		this.setTotal(0);
//	}
	
	void resetCartFields()
	{
		list = new ArrayList<Item> (100);
		listModel.removeAllElements();
		
		this.setCartSize(0);
		this.setSubtotal(0);
		this.setTotalTax(0);
		this.setTotal(0);
	}
	
	void updatePriceFields()
	{
		System.out.println("UPDATING " + this.hashCode());
		sub.setText("Sub-Total: $" + RetailHelper.getCashString(subtotal));
		tot.setText("Tax: $" + RetailHelper.getCashString(totalTax));
		fTot.setText("Total: $" + RetailHelper.getCashString(total));
	}
	
	//add an item to cart
	public void addItem(Item newItem){
		DecimalFormat money = new DecimalFormat("0.00");
		try {
			list.add(newItem);
			String barcode = newItem.getBarcode();
			String description = newItem.getDescription();
			Double price = ((double) newItem.getPrice()) / 100;
			
			String fprice = money.format(price);
			
			listModel.addElement(barcode + " " + description + " $" + fprice);
			Cart = Cart + "\n" + 
			
			size++;
			subtotal += newItem.getPrice();
			
			
			if (newItem.isTaxable()) {
				
				this.setTotal( (int) (this.getTotal() + (newItem.getPrice() * .07)));
				totalTax += (newItem.getPrice() * .07);
				
			}
			
			total += newItem.getPrice();
			
			updatePriceFields();
			
		}catch(NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Product not Found");	
		}
		
		
	}
	
	//remove item at specified index
	public  void removeItem(Item newItem){
		
		DecimalFormat money = new DecimalFormat("0.00");
		
		
		try {
			
			list.remove(newItem);
			String barcode = newItem.getBarcode();
			String description = newItem.getDescription();
			Double price = ((double) newItem.getPrice()) / 100;
			
			String fprice = money.format(price);
				
				listModel.removeElement(barcode + " " + description + " $" + fprice);
			
			subtotal -= newItem.getPrice();
			
			
			if (newItem.isTaxable()) {
				
				total -= (newItem.getPrice() * .07);
				totalTax -= (newItem.getPrice() * .07);
				
			}
			
			total -= newItem.getPrice();

			updatePriceFields();
			
		} catch (Exception a) {
			
			JOptionPane.showMessageDialog(null, "Product not Found");
		}
		
		
	}
	
	//Remove all items in the cart to clear transaction
	public void removeAllItems()
	{
		resetCartFields();
		updatePriceFields();
	}
	
	public int getCartSize() {
		
		return this.size;
		
	}
	
	public void setCartSize(int newSize) {
		
		this.size = newSize;
		
	}
	
	public int getSubtotal() {
		
		return this.subtotal;
		
	}
	
	public void setSubtotal(int newSubtotal) {
		
		this.subtotal = newSubtotal;
		
	}
	
	public int getTotalTax() {
		
		return this.totalTax;
		
	}
	
	public void setTotalTax(int newTotalTax) {
		
		this.totalTax = newTotalTax;
		
	}
	
	public int getTotal() {
		
		return this.total;
		
	}
	
	public void setTotal(int newTotal) {
		
		this.total = newTotal;
		
	}
	
}