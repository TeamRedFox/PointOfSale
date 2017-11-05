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

	public static Item getItemFromBarcode(String barcode)
	{
		//Create and execute query to find item with matching barcode
		DatabaseConnection connection = new DatabaseConnection();
		String query = "SELECT * FROM ITEMS WHERE BARCODE = '" + barcode + "'";
		ResultSet rs = connection.executeQuery(query);
		
		try
		{
			//Check if our query got any results
			if (rs.next())
			{
				//If so, return item with variables assigned
				Item returnItem = new Item();
				returnItem.barcode = barcode;
				returnItem.description = rs.getString("DESCR");
				returnItem.price = (int)(rs.getFloat("PRICE") * 100);
				return returnItem;
			}
			else
			{
				//If not, return null
				System.out.println("Item with barcode " + barcode + " not found");
				return null;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}

	}

}
