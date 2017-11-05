package database_comm;
import java.sql.*;

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
				//If so, create user instance from result data
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

}
