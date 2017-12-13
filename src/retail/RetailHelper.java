package retail;

public class RetailHelper
{
	
	/**Returns cash string formatted X.XX for a given price in cents*/
	public static String getCashString(int amount)
	{	System.out.println(amount);
		int cents = amount % 100;
		int dollars = amount - cents;
		dollars /= 100;
		return dollars + "." + ((cents < 10 ) ? "0" : "") + cents;
	}

	/**Returns price in cents from given cash string formatted X.XX*/
	public static int getCentsFromCashString(String cashString)
	{
		String[] parts = cashString.split(".");
		return (Integer.parseInt(parts[0]) * 100) + Integer.parseInt(parts[1]);
	}
}
