package retail;
import java.util.*;

public class Cart {
	
	public ArrayList<Item> list;
	public int size;
	
	//constructor to create an arraylist for cart
	public Cart() {
		
		list = new ArrayList<Item> (10);
		this.setSize(0);
		
	}
	
	//adds an item to our cart
	public void addItem(Item newItem){
		
		list.add(newItem);
		this.setSize(this.getSize() + 1);
		
	}
	
	//remove item at specified index
	public void removeItem(int cartNum){
		
		list.remove(cartNum);
		this.setSize(this.getSize() - 1);
		
	}
	
	//returns total price
	public int getTotalPrice(boolean tax){
		
		int total = 0;
		
		for (int i = 0; i < this.getSize(); i++) {
			
			total += list.get(i).getPrice();
			
			if (tax = true && list.get(i).isTaxable()) {
				
				total += list.get(i).getPrice() * .07;
				
			}
			
		}
		
		return total;
		
	}
	
	public int getSize() {
		
		return this.size;
		
	}
	
	public void setSize(int newSize) {
		
		this.size = newSize;
		
	}
	
}