package retail;
import java.util.*;

public class Cart {
	
	public ArrayList<Item> list;
	private int size, subtotal, total, totalTax;
	
	//constructor to create an arraylist for cart with a default size of 100
	public Cart() {
		
		list = new ArrayList<Item> (100);
		this.setSize(0);
		this.setSubtotal(0);
		this.setTotalTax(0);
		this.setTotal(0);
		
	}
	
	//constructor to create an arraylist for cart with a specified size
	public Cart(int cartSize) {
		
		list = new ArrayList<Item> (cartSize);
		this.setSize(0);
		this.setSubtotal(0);
		this.setTotalTax(0);
		this.setTotal(0);
		
	}
	
	//add an item to cart
	public void addItem(Item newItem){
		
		list.add(newItem);
		this.size++;
		this.subtotal += newItem.getPrice();
		
		if (newItem.isTaxable()) {
			
			this.total += (newItem.getPrice() * .07);
			this.totalTax += (newItem.getPrice() * .07);
			
		}
		
		this.total += newItem.getPrice();
		
	}
	
	//remove item at specified index
	public void removeItem(int cartNum){
		
		Item newItem = new Item(list.get(cartNum));
		list.remove(cartNum);
		this.size--;
		this.subtotal -= newItem.getPrice();
		
		if (newItem.isTaxable()) {
			
			this.total -= (newItem.getPrice() * .07);
			this.totalTax -= (newItem.getPrice() * .07);
			
		}
		
		this.total -= newItem.getPrice();
		
	}
	
	public int getSize() {
		
		return this.size;
		
	}
	
	public void setSize(int newSize) {
		
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