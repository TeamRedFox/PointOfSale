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
	//static final String connectionUrl = "jdbc:sqlserver://150.250.147.167:1433;DatabaseName=REDFOX;user=sa;password=brianbrian;";

	//Debug online string
	String connectionUrl = "jdbc:sqlserver://rbmsdemo.dyndns.org:1433;DatabaseName=REDFOX;user=brian;password=brianbrian;";

	private Connection connection;
	private Statement statement;

	public DatabaseConnection() throws SQLException
	{
		try
		{
			//I don't know what this line really does
			//No touch
			Class.forName(JDBC_DRIVER);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}

		System.out.println("Connecting to database...");
		connection = DriverManager.getConnection(connectionUrl);
		statement = connection.createStatement();

	}

	//Executes and returns results from a given query
	public ResultSet executeQuery(String query) throws SQLException
	{
		return statement.executeQuery(query);
	}


	//Executes a SQL statement, returns true it successful
	public boolean execute(String sql) throws SQLException
	{
		return statement.execute(sql);
	}

	//Close all connections in this object
	public void close() throws SQLException
	{
		statement.close();
		connection.close();
	}

}
