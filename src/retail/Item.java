package retail;

public class Item
{
	private String barcode;
	private String description;
	private int price;
	private boolean taxable;
	
	public Item(String barcode)
	{
		this.setBarcode(barcode);
	}
	
	public Item(Item newItem) {
		this.setBarcode(newItem.getBarcode());
		this.setDescription(newItem.getDescription());
		this.setPrice(newItem.getPrice());
		this.setTaxable(newItem.isTaxable());
	}

	public String getBarcode()
	{
		return barcode;
	}

	public void setBarcode(String barcode)
	{
		this.barcode = barcode;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price)
	{
		this.price = price;
	}

	public boolean isTaxable()
	{
		return taxable;
	}

	public void setTaxable(boolean taxable)
	{
		this.taxable = taxable;
	}
	
	/**Returns price string for this item's price, formatted with RetailHelper.getCashString()*/
	public String getPriceString()
	{
		return RetailHelper.getCashString(price);
	}
	
	//TODO better toString representation
	public String toString()
	{
		return barcode + ", " + description + ", $" + RetailHelper.getCashString(price); 
	}

}
