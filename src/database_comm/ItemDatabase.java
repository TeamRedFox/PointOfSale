package database_comm;
import java.sql.ResultSet;
import java.sql.SQLException;

import retail.RetailHelper;

public class ItemDatabase
{

	//Debug item class for SQL connection
	//TODO remove
	public static class Item
	{
		public String barcode;
		public String description;
		public int price;
		//public int cost;

		public String toString()
		{
			return barcode + ", " + description + ", " + price;
		}
	}

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
				returnItem = new Item();
				returnItem.barcode = barcode;
				returnItem.description = rs.getString("DESCR");
				returnItem.price = (int)(rs.getFloat("PRICE") * 100);
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
	public static boolean addItemToDatabase(Item item)
	{
		//Create database connection
		DatabaseConnection connection = new DatabaseConnection();
		
		//Set up query to insert into ITEMS table and execute it
		//TODO deal with placeholder ITEM_NO and COST values
		String query = "INSERT INTO ITEMS (ITEM_NO, BARCODE, DESCR, PRICE, COST) "
				+ "VALUES ('" + item.barcode + "', '" + item.barcode + "', '" + item.description + "', " + getCashString(item.price) + ", '1.00')";
		//System.out.println(query);
		connection.execute(query);
		
		
		return false;
	}
	
	//Placeholder get cash string
	//TODO remove
	public static String getCashString(int amount)
	{
		int cents = amount % 100;
		int dollars = amount - cents;
		dollars /= 100;
		return dollars + "." + cents;
	}

}
