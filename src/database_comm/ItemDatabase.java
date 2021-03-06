package database_comm;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import retail.Item;

public class ItemDatabase
{

	/**Returns an item with the given barcode from the database. null if not found
	 * @throws SQLException */
	public static Item getItemFromBarcode(String barcode) throws SQLException
	{
		//Create query to find item with matching barcode
		DatabaseConnection connection = new DatabaseConnection();
		String query = "SELECT * FROM ITEMS WHERE BARCODE = '" + barcode + "'";

		//Set up our return item with null by default
		Item returnItem = null;

		try
		{
			//Execute our query
			ResultSet rs = connection.executeQuery(query);
			
			//Check if our query got any results
			if (rs.next())
			{
				//If so, create item instance from result data
				returnItem = new Item(barcode);
				returnItem.setDescription(rs.getString("DESCR"));
				returnItem.setPrice((int)(rs.getDouble("PRICE") * (double)100));
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
		String query = "INSERT INTO ITEMS (ITEM_NO, BARCODE, DESCR, PRICE, COST, IS_TAXABLE) VALUES ("
		+ DatabaseHelper.formatStringField(item.getBarcode()) + ", "
		+ DatabaseHelper.formatStringField(item.getBarcode()) + ", "
		+ DatabaseHelper.formatStringField(item.getDescription()) + ", "
		+ item.getPriceString() + ", "
		+ item.getPriceString() + ", "
		+ DatabaseHelper.formatStringField((item.isTaxable() ? "Y" : "N")) + ")";
		
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


	/**Modifies the item in the database to match the given item, returns true if successful.
	 * This CANNOT modify the barcode as of right now
	 * @throws SQLException */
	public static boolean updateItem(Item item) throws SQLException
	{
		//Create database connection
		DatabaseConnection connection = new DatabaseConnection();
		
		//Create SQL query to update all fields of an item
		//TODO deal with placeholder ITEM_NO and COST values
		//TODO return false or throw exception if item to update cannot be found
		String query = "UPDATE ITEMS SET "
		+ "ITEM_NO = " + DatabaseHelper.formatStringField(item.getBarcode())
		+ ", BARCODE = " + DatabaseHelper.formatStringField(item.getBarcode())
		+ ", DESCR = " + DatabaseHelper.formatStringField(item.getDescription())
		+ ", PRICE = " + item.getPriceString()
		+ ", COST = " + item.getPriceString()
		+ ", IS_TAXABLE = " + DatabaseHelper.formatStringField((item.isTaxable() ? "Y" : "N"))
		+ " WHERE BARCODE = " + DatabaseHelper.formatStringField(item.getBarcode());
		
		//System.out.println(query);
		try
		{
			
			//Attempt to execute query, storing the item in the database if successful
			connection.execute(query);
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

		return true;
	}


	/**Removes the item matching the given barcode from the database, returns true if successful
	 * @throws SQLException */
	public static boolean removeItem(String barcode) throws SQLException
	{
		//Create database connection
		DatabaseConnection connection = new DatabaseConnection();

		//Set up query to remove an item from the ITEMS table and execute it
		//TODO deal with placeholder ITEM_NO and COST values
		String query = "DELETE FROM ITEMS WHERE BARCODE = " + DatabaseHelper.formatStringField(barcode);
		
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
	
	/**FOR DEBUG PURPOSES
	 * Lists all items in the database
	 * @throws SQLException */
	public static void printAllItems() throws SQLException
	{
		//Create a query to fetch all items
		DatabaseConnection connection = new DatabaseConnection();
		String query = "SELECT * FROM ITEMS";

		try
		{
			//Execute our query
			ResultSet rs = connection.executeQuery(query);
			ResultSetMetaData metaData = rs.getMetaData();
			
			//Display all results from our query 
			while (rs.next())
			{
				String row = ""; 
				for(int i = 1; i <= metaData.getColumnCount(); i++)
				{
					row += metaData.getColumnName(i) + ": ";
					
					row += rs.getString(i);
					if (i < metaData.getColumnCount())
						row += ", ";
				}
				System.out.println(row);
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
	}

}
