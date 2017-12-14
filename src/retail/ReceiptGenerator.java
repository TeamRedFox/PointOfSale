package retail;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class ReceiptGenerator{

	//private ShoppingCartPanel cart = new ShoppingCartPanel(null);
	protected static String cart = "";
	
	public ReceiptGenerator(Cart info) {
		
		try{
				DecimalFormat money = new DecimalFormat("0.00");
				PrintWriter writer = new PrintWriter("Receipt.txt", "UTF-8");
				writer.println("Red Fox POS");
			//	System.out.println(cart.listModel.get(0));
				/*for(int i = 0; i > cart.listModel.getSize(); i++) {
					writer.print(cart.listModel.getElementAt(i));
					System.out.println(cart.listModel.getElementAt(i).toString());
					
				} 
				*/
			
				
				writer.println("Items");
				
				for(Item i : Cart.list){
					writer.println(i.toString());
				}
				/*for(int i = 0; i  < Cart.listModel.size(); i++) {
					cart = Cart.listModel.getElementAt(i).toString();
					writer.println(cart);	
				}
				*/
				writer.println(" "); 
				writer.println("Sub-Total: $" + money.format(((double) info.subtotal)/100));
				writer.println("Tax: $" + money.format(((double) info.totalTax)/100));
				writer.println("Total: $" + money.format(((double) info.total)/100));
				writer.println("Change Due: $" + RetailHelper.getCashString(CheckoutFrame.change)); 
				writer.println(" "); 
				writer.println("Thank You, Come again soon!");
				writer.close();
				
				
				
				Desktop.getDesktop().open(new File("Receipt.txt"));

			
			}
			catch (IOException ex) {
		        System.out.println("Error Generating Receipt");
		   }
		
	}

	
	
}