package database_comm;
import java.sql.ResultSet;
import java.sql.SQLException;

import login.User;

public class UserDatabase
{
	/**Returns an user with the given credentials from the database. null if not found*/
	public static User getUserFromLogin(String username, String passwordHash) throws SQLException
	{

		//Create and execute query to find user with matching credentials
		DatabaseConnection connection = new DatabaseConnection();
		String query = "SELECT * FROM USERS WHERE USERNAME = '" + username + "' AND PASS_HASH = '" +  passwordHash + "'";
		ResultSet rs = connection.executeQuery(query);
		
		//Set up our return user with null by default
		User returnUser = null;
		
		try
		{
			//Check if our query got any results
			if (rs.next())
			{
				//If so, create user instance from result data
				returnUser = new User(username);
				returnUser.setPaswordHash(passwordHash);
				returnUser.setFirstName(rs.getString("FIRST"));
				returnUser.setLastName(rs.getString("LAST"));
				returnUser.setAdmin(rs.getString("IS_ADMIN").equals("Y"));

				System.out.println("User retrieved successfully");
			}
			else
			{
				//If not, print that the user was not found
				System.out.println("User with the given credentials not found");
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
		return returnUser;
	}
	
	/**Adds the given user to the database, returns true if successful
	 * @throws SQLException */
	public static boolean addUser(User user) throws SQLException
	{
		//Create database connection
		DatabaseConnection connection = new DatabaseConnection();

		//Set up query to insert into USERS table
		String query = "INSERT INTO USERS (USERNAME, PASS_HASH, FIRST, LAST, IS_ADMIN) "
				+ "VALUES ('" + user.getUsername() + "', '" + user.getPaswordHash() + "', '"
				+ user.getFirstName() + "', '" + user.getLastName() +
				"', '" + (user.isAdmin() ? "Y" : "N" ) + "')";

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
