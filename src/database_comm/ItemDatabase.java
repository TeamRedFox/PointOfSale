package database_comm;
import java.sql.ResultSet;
import java.sql.SQLException;

import retail.RetailHelper;
import retail.Item;

public class ItemDatabase
{

	/**Returns an item with the given barcode from the database. null if not found
	 * @throws SQLException */
	public static Item getItemFromBarcode(String barcode) throws SQLException
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
				
				System.out.println("Item retrieved successfully");
			}
			else
			{
				//If not, print that the item was not found
				System.out.println("Item with barcode " + barcode + " not found");
			}
		}
		catch (SQLException e)
		{
			//Throw a SQL exception if we run into one
			throw e;
		}
		finally
		{
			//Close the connection regardless of whether we encountered an exception
			connection.close();
		}
		
		//Return our results
		return returnItem;
	}


	/**Adds the given item to the database, returns true if successful
	 * @throws SQLException */
	public static boolean addItem(Item item) throws SQLException
	{
		//Create database connection
		DatabaseConnection connection = new DatabaseConnection();

		//Set up query to insert into ITEMS table and execute it
		//TODO deal with placeholder ITEM_NO and COST values
		String query = "INSERT INTO ITEMS (ITEM_NO, BARCODE, DESCR, PRICE, COST, IS_TAXABLE) "
				+ "VALUES ('" + item.getBarcode() + "', '" + item.getBarcode() + "', '" + item.getDescription()
				+ "', " + item.getPriceString() + ", " + item.getPriceString() + ", '"+ (item.isTaxable() ? "Y" : "N") +"')";
		System.out.println(query);

		boolean successful = false;
		//System.out.println(query);
		try
		{
			//Attempt to execute query, storing the item in the database if successful
			successful = connection.execute(query);
		}
		catch(SQLException e)
		{
			//Throw a SQL exception if we run into one
			throw e;
		}
		finally
		{
			//Close the connection regardless of whether we encountered an exception
			connection.close();
		}

		return successful;
	}

}
