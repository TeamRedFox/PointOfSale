package retail;
import java.util.*;

public class cart {
	
	public Map<Integer,Item> cart;
	public int size;
	
	//Constructor to create hash map for cart
	public cart() {
		cart = new HashMap<Integer,Item>();
		size = 0;
	}
	
	//adds and item to our cart
	public void addItem(Item item){
		size = size + 1;
		cart.put(size, item);
	}
	
	//iterate through the cart and print out cart
	public void cartList() {
		for(Map.Entry<Integer, Item> entry :cart.entrySet()){
			Integer key = entry.getKey();
			Item value = entry.getValue();
		}
	}

	
	//remove chosen item
	public void removeItem(int cartNum){
		Iterator<Map.Entry<Integer, Item>> entries = cart.entrySet().iterator();
		while(entries.hasNext()){
			Map.Entry<Integer,Item> entry = entries.next();
		}
	}
	
	//returns total price
	public int getTotalPrice(){
		int total = 0;
		for(Map.Entry<Integer, Item> entry :cart.entrySet()){
			total += entry.getValue().getPrice();
		}
		return total;
	}
	
	
	
}