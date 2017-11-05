package database_comm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection
{

	static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	static final String USER = "sa";
	static final String PASS = "brianbrian";
	
	//Debug localhost string
	static final String connectionUrl = "jdbc:sqlserver://150.250.147.167:1433;DatabaseName=REDFOX;user=sa;password=brianbrian;";
	//Debug RBMS string
	//String connectionUrl = "jdbc:sqlserver://rbmsdemo.dyndns.org:1433;DatabaseName=REDFOX;user=Brian;password=brianbrian;";
	
	private Connection connection;
	private Statement statement;
	
	public DatabaseConnection()
	{
		try
		{
			//I don't know what this line really does
			//No touch
			Class.forName(JDBC_DRIVER);

			System.out.println("Connecting to database...");
			connection = DriverManager.getConnection(connectionUrl);
			statement = connection.createStatement();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	//Executes and returns results from a given query
	public ResultSet executeQuery(String query)
	{
		try
		{
			return statement.executeQuery(query);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	//Close all connections in this object
	public void close()
	{
		try
		{
			statement.close();
			connection.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

}
