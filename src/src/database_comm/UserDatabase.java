package database_comm;
import java.sql.ResultSet;
import java.sql.SQLException;

import login.User;
import login.InvalidUsernameOrPasswordException;

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

			if (username.isEmpty()) {
				throw new InvalidUsernameOrPasswordException("empty");
			}
			else if (passwordHash.isEmpty()) {
				throw new InvalidUsernameOrPasswordException("empty");
			}
			else {
				System.out.println("User with the given credentials not found.");
				throw new InvalidUsernameOrPasswordException("invalid");
			}
			//			else if (!usernameInUse(username)) {
			//				throw new InvalidUsernameOrPasswordException("username");
			//			}
			//			else if (usernameInUse(username) & !users.get(username).getPassword().equals(password)) {
			//				throw new InvalidUsernameOrPasswordException("password");
			//			}
			//			else if (usernameInUse(username) & users.get(username).getPassword().equals(password)) {
			//				currentUser = users.get(username);
			//			}
		}

		//Close the connection and return our results
		connection.close();		
		return returnUser;
	}

	/**Adds the given user to the database, returns true if successful*/
	public static boolean addUser(User user) throws SQLException
	{
		//Create database connection
		DatabaseConnection connection = new DatabaseConnection();

		//Set up query to insert into USERS table and execute it
		String query = "INSERT INTO USERS (USERNAME, PASS_HASH, FIRST, LAST, IS_ADMIN) "
				+ "VALUES ('" + user.getUsername() + "', '" + user.getPaswordHash() + "', '"
				+ user.getFirstName() + "', '" + user.getLastName() +
				"', '" + (user.isAdmin() ? "Y" : "N" ) + "')";
		System.out.println(query);
		boolean successful = connection.execute(query);

		connection.close();
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
