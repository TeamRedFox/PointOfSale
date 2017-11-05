package database_comm;
import java.sql.*;

public class UserDatabase
{
	//Debug user class for SQL connection
	//TODO remove
	public static class User
	{
		public String username;
		public String passwordHash;
		public String firstName;
		public String lastName;
		public boolean isAdmin;

		public String toString()
		{
			return username + ", " + passwordHash + ", " + firstName + " " + lastName + (isAdmin ? ", admin" : ", user");
		}
	}
	
	public static User getUserFromLogin(String username, String passwordHash)
	{

		//Create and execute query to find item with matching barcode
		DatabaseConnection connection = new DatabaseConnection();
		String query = "SELECT * FROM USERS WHERE USERNAME = '" + username + "' AND PASS_HASH = '" +  passwordHash + "'";
		ResultSet rs = connection.executeQuery(query);

		try
		{
			//Check if our query got any results
			if (rs.next())
			{
				//If so, return item with variables assigned
				User returnUser = new User();
				returnUser.username = rs.getString("USERNAME");
				returnUser.passwordHash = rs.getString("PASS_HASH");
				returnUser.firstName = rs.getString("FIRST");
				returnUser.lastName = rs.getString("LAST");
				returnUser.isAdmin = rs.getBoolean("IS_ADMIN");
				return returnUser;
			}
			else
			{
				//If not, return null
				System.out.println("User with the given credentials not found");
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
