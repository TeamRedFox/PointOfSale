package retail;

public class RetailHelper
{
	
	/**Returns cash string formatted X.XX for a given price in cents*/
	public static String getCashString(int amount)
	{
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

	private static final int maxBarcodeLength = 15;
	private static final int maxDescriptionLength = 30;
	private static final int maxPriceLength = 10;
	
	/**Returns a string with barcode, price, and description with even spacing, and "..." as needed*/
	public static String getRegisterItemString(Item item)
	{
		return forceStringLength(item.getBarcode(), maxBarcodeLength)
				+ forceStringLength(item.getDescription(), maxDescriptionLength)
				+ "$" + item.getPriceString();
	}
	
	private static String forceStringLength(String string, int fitLength)
	{
		if (string.length() > fitLength - 1)
		{
			string = string.substring(0, fitLength - 4);
			string += "...";
		}
		
		while (string.length() < fitLength)
		{
			string += " ";
		}
		
		return string;
	}
}
