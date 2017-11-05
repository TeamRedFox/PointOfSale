package database_comm;
import java.sql.*;
import com.microsoft.sqlserver.jdbc.*;

public class DatabaseComm
{
	static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static final String DB_URL = "jdbc:mysql://localhost/EMP";

	static final String USER = "sa";
	static final String PASS = "brianbrian";
	
	//Debug localhost string
	static final String connectionUrl = "jdbc:sqlserver://150.250.147.167:1433;DatabaseName=REDFOX;user=sa;password=brianbrian;";

	//Debug RBMS string
	//String connectionUrl = "jdbc:sqlserver://rbmsdemo.dyndns.org:1433;DatabaseName=REDFOX;user=Brian;password=brianbrian;";
	
	//Debug item class for SQL connection
	//TODO remove
	private static class Item
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

	//TODO make not main
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		Item retrievedItem = getItemFromBarcode("12345678");
		System.out.println(retrievedItem);
	}
	
	public static Item getItemFromBarcode(String barcode) throws ClassNotFoundException, SQLException
	{
			//I don't know what this line really does
			//No touch
			Class.forName(JDBC_DRIVER);

			System.out.println("Connecting to database...");
			Connection con = DriverManager.getConnection(connectionUrl);

			//Create statement
			Statement stmt = con.createStatement();

			//Create and execute query to find items
			String query = "SELECT * FROM ITEMS WHERE BARCODE = '" + barcode + "'";
			ResultSet rs = stmt.executeQuery(query);
			
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
}
