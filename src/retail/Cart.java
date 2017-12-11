package retail;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.util.*;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Cart extends JPanel {
	
	public static ArrayList<Item> list;
	protected static int size, subtotal = 0;
	protected static int total = 0;
	protected static int totalTax = 0;
	
	protected static DefaultListModel listModel = new DefaultListModel();
	protected JList shoppingList = new JList(listModel);
	protected JScrollPane scroll = new JScrollPane();
	protected JTextArea sub, tot, fTot;
	protected static JPanel subP, totP,fTotP, pricing = new JPanel();
	
	
	//constructor to create an arraylist for cart with a default size of 100
	public Cart() {
		DecimalFormat money = new DecimalFormat("0.00");
		
		list = new ArrayList<Item> (100);
		this.setCartSize(0);
		this.setSubtotal(0);
		this.setTotalTax(0);
		this.setTotal(0);
		scroll.setViewportView(shoppingList);
		scroll.setSize(300, 150);
		//add(shoppingList);
		
		
		sub = new JTextArea("Sub-Total: $" + money.format((double) subtotal/100));
		tot = new JTextArea("Tax: $" + money.format((double) totalTax/100));
		fTot = new JTextArea("Total: $" + money.format((double) total/100));
		
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
	public Cart(int cartSize) {
		
		list = new ArrayList<Item> (cartSize);
		this.setCartSize(0);
		this.setSubtotal(0);
		this.setTotalTax(0);
		this.setTotal(0);
		
		
		
	}
	
	//add an item to cart
	public static void addItem(Item newItem){
		DecimalFormat money = new DecimalFormat("0.00");
		list.add(newItem);
		String barcode = newItem.getBarcode();
		String description = newItem.getDescription();
		Double price = ((double) newItem.getPrice()) / 100;
		
		String fprice = money.format(price);
		
		listModel.addElement(barcode + " " + description + " $" + fprice);
		
		size++;
		subtotal += newItem.getPrice();
		
		if (newItem.isTaxable()) {
			
			total += (newItem.getPrice() * 7);
			totalTax += (newItem.getPrice() * 7);
			
		}
		
		total += newItem.getPrice();
		
	}
	
	//remove item at specified index
	public static void removeItem(Item newItem){
		
		DecimalFormat money = new DecimalFormat("0.00");
		list.add(newItem);
		String barcode = newItem.getBarcode();
		String description = newItem.getDescription();
		Double price = ((double) newItem.getPrice()) / 100;
		
		String fprice = money.format(price);
		
		listModel.removeElement(barcode + " " + description + " $" + fprice);
		
		size++;
		subtotal -= newItem.getPrice();
		
		
		if (newItem.isTaxable()) {
			
			total -= (newItem.getPrice() * 7);
			totalTax -= (newItem.getPrice() * 7);
			
		}
		
		total -= newItem.getPrice();
		
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