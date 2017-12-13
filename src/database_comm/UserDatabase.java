package database_comm;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import login.User;

public class UserDatabase
{
	/**Returns an user with the given credentials from the database. null if not found*/
	public static User getUserFromLogin(String username, String passwordHash) throws SQLException
	{

		//Create query to find user with matching credentials
		DatabaseConnection connection = new DatabaseConnection();
		String query = "SELECT * FROM USERS WHERE USERNAME = '" + username + "' AND PASS_HASH = '" +  passwordHash + "'";
		
		//Set up our return user with null by default
		User returnUser = null;
		
		try
		{
			//Execute our query
			ResultSet rs = connection.executeQuery(query);
			
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
		String query = "INSERT INTO USERS (USERNAME, PASS_HASH, FIRST, LAST, IS_ADMIN) VALUES ("
		+ DatabaseHelper.formatStringField(user.getUsername()) + ", "
		+ DatabaseHelper.formatStringField(user.getPaswordHash()) + ", "
		+ DatabaseHelper.formatStringField(user.getFirstName()) + ", "
		+ DatabaseHelper.formatStringField(user.getLastName()) + ", "
		+ DatabaseHelper.formatStringField((user.isAdmin() ? "Y" : "N")) + ")";

		boolean successful = false;
		//System.out.println(query);
		try
		{
			//Attempt to execute query, storing the user in the database if successful
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


	/**Modifies the user in the database to match the given user, returns true if successful.
	 * This CANNOT modify the username as of right now
	 * @throws SQLException */
	public static boolean updateUser(User user) throws SQLException
	{
		//Create database connection
		DatabaseConnection connection = new DatabaseConnection();
		
		//Create SQL query to update all fields of a user
		//TODO return false or throw exception if user to update cannot be found
		String query = "UPDATE USERS SET "
		+ "USERNAME = " + DatabaseHelper.formatStringField(user.getUsername())
		+ ", PASS_HASH = " + DatabaseHelper.formatStringField(user.getPaswordHash())
		+ ", FIRST = " + DatabaseHelper.formatStringField(user.getFirstName())
		+ ", LAST = " + DatabaseHelper.formatStringField(user.getLastName())
		+ ", IS_ADMIN = " + DatabaseHelper.formatStringField((user.isAdmin() ? "Y" : "N"))
		+ " WHERE USERNAME = " + DatabaseHelper.formatStringField(user.getUsername());
		
		//System.out.println(query);
		try
		{
			//Attempt to execute query, storing the user in the database if successful
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


	/**Removes the user matching the given barcode from the database, returns true if successful
	 * @throws SQLException */
	public static boolean removeUser(String username) throws SQLException
	{
		//Create database connection
		DatabaseConnection connection = new DatabaseConnection();

		//Set up query to remove a user from the USERS table and execute it
		String query = "DELETE FROM USERS WHERE USERNAME = " + DatabaseHelper.formatStringField(username);
		
		boolean successful = false;
		//System.out.println(query);
		try
		{
			//Attempt to execute query, storing the user in the database if successful
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
	
	/**Returns a list of all usernames in the database
	 * @throws SQLException */
	public static String[] getAllUsernames() throws SQLException
	{
		//Create query to fetch all users
		DatabaseConnection connection = new DatabaseConnection();
		String query = "SELECT * FROM USERS";
		
		//Create list to store usernames
		ArrayList<String> list = new ArrayList<String>();
		
		try
		{
			//Execute our query
			ResultSet rs = connection.executeQuery(query);
			ResultSetMetaData metaData = rs.getMetaData();
			
			//Display all results from our query
			while (rs.next())
			{ 
				for(int i = 1; i <= metaData.getColumnCount(); i++)
				{
					if (metaData.getColumnName(i).equals("USERNAME"))
					{
						list.add(rs.getString(i));
					}
				}
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
		return (String[])list.toArray(new String[0]);
	}
	
	/**FOR DEBUG PURPOSES
	 * Lists all users in the database
	 * @throws SQLException */
	public static void printAllUsers() throws SQLException
	{
		//Create query to fetch all users
		DatabaseConnection connection = new DatabaseConnection();
		String query = "SELECT * FROM USERS";
		
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
