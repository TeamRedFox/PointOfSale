package database_comm;
import java.sql.ResultSet;
import java.sql.SQLException;

import retail.RetailHelper;
import retail.Item;

public class ItemDatabase
{

	//Returns an item with the given barcode from the database. null if not found
	public static Item getItemFromBarcode(String barcode)
	{
		//Create and execute query to find item with matching barcode
		DatabaseConnection connection = new DatabaseConnection();
		String query = "SELECT * FROM ITEMS WHERE BARCODE = '" + barcode + "'";
		ResultSet rs = connection.executeQuery(query);

		//Set up our return item with null by default
		Item returnItem = null;
		
		try
		{
			//Check if our query got any results
			if (rs.next())
			{
				//If so, create item instance from result data
				returnItem = new Item(barcode);
				returnItem.setDescription(rs.getString("DESCR"));
				returnItem.setPrice((int)(rs.getFloat("PRICE") * 100));
				returnItem.setTaxable(rs.getString("IS_TAXABLE").equals("Y"));
			}
			else
			{
				//If not, print that the item was not found
				System.out.println("Item with barcode " + barcode + " not found");
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		//Close the connection and return our results
		connection.close();		
		return returnItem;

	}
	
	
	//Adds the given item to the database, returns true if successful
	public static boolean addItem(Item item)
	{
		//Create database connection
		DatabaseConnection connection = new DatabaseConnection();
		
		//Set up query to insert into ITEMS table and execute it
		//TODO deal with placeholder ITEM_NO and COST values
		String query = "INSERT INTO ITEMS (ITEM_NO, BARCODE, DESCR, PRICE, COST, IS_TAXABLE) "
				+ "VALUES ('" + item.getBarcode() + "', '" + item.getBarcode() + "', '" + item.getDescription()
				+ "', " + item.getPriceString() + ", " + item.getPriceString() + ", '"+ (item.isTaxable() ? "Y" : "N") +"')";
		System.out.println(query);
		boolean successful = connection.execute(query);
		
		connection.close();
		return successful;
	}

}
