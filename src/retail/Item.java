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
	
	//TODO better toString representation
	public String toString()
	{
		return barcode + ", " + description + ", $" + RetailHelper.getCashString(price) + (taxable ? ", taxable" : ", not taxable"); 
	}

}
