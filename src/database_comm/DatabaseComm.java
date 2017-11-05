package database_comm;
import java.sql.*;

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

	//Debug user class for SQL connection
	//TODO remove
	private static class User
	{
		public String username;
		public String passwordHash;
		public String firstName;
		public String lastName;
		
		public String toString()
		{
			return username + ", " + passwordHash + ", " + firstName + " " + lastName;
		}
	}

	//Debug item class for SQL connection
	//TODO remove

	//TODO make not main
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		Item retrievedItem = getItemFromBarcode("12345678");
		System.out.println(retrievedItem);
		
		User retrievedUser = getUserFromLogin("bintile", "123");
		System.out.println(retrievedUser);
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

			//Create and execute query to find item with matching barcode
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
	
	public static User getUserFromLogin(String username, String passwordHash) throws ClassNotFoundException, SQLException
	{

		//I don't know what this line really does
		//No touch
		Class.forName(JDBC_DRIVER);

		System.out.println("Connecting to database...");
		Connection con = DriverManager.getConnection(connectionUrl);

		//Create statement
		Statement stmt = con.createStatement();

		//Create and execute query to find item with matching name and password hash
		String query = "SELECT * FROM USERS WHERE USERNAME = '" + username + "' AND PASS_HASH = '" +  passwordHash + "'";
		ResultSet rs = stmt.executeQuery(query);
		
		//Check if our query got any results
		if (rs.next())
		{
			//If so, return item with variables assigned
			User returnUser = new User();
			returnUser.username = rs.getString("USERNAME");
			returnUser.passwordHash = rs.getString("PASS_HASH");
			returnUser.firstName = rs.getString("FIRST");
			returnUser.lastName = rs.getString("LAST");
			return returnUser;
		}
		else
		{
			//If not, return null
			System.out.println("User with the given credentials not found");
			return null;
		}
	}
	
	
}
