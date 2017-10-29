
package pos;
import java.util.Scanner;

public class saleTax {
    
   public static void main(String[] args) {

      Scanner input = new Scanner(System.in);
      
      System.out.print("Enter the amount of your all purchases: ");
      
      double purchaseAmount = input.nextDouble();
     
      System.out.println();
      
      System.out.println("Your purchase amount is: $" + purchaseAmount);
      
      System.out.println();
       
      double saleTax = purchaseAmount * 0.07;

      System.out.println("The amount of tax for your purchase is: $" + (int)(saleTax * 100)/100.0);

      System.out.println();
       
      double totalAmount = saleTax + purchaseAmount;
      
      System.out.println("The total amount of your all purchases is: $" + String.format("%.2f",totalAmount));

   }
 }


