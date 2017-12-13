package retail;

public class RetailHelper
{
	
	/**Returns cash string formatted X.XX for a given price in cents*/
	public static String getCashString(int amount)
	{	System.out.println(amount);
		int cents = amount % 100;
		int dollars = amount - cents;
		dollars /= 100;
		return dollars + "." + cents;
	}
}
