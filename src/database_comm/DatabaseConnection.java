package database_comm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.scenario.Settings;

import config.ProgramSettings;

public class DatabaseConnection
{

	static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	static final String USER = "sa";
	static final String PASS = "brianbrian";

	//Debug localhost string
	//static final String connectionUrl = "jdbc:sqlserver://150.250.147.167:1433;DatabaseName=REDFOX;user=sa;password=brianbrian;";

	//Debug online string
	//String connectionUrl = "jdbc:sqlserver://rbmsdemo.dyndns.org:1433;DatabaseName=REDFOX;user=brian;password=brianbrian;";

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
		connection = DriverManager.getConnection(getConnectionUrl());
		statement = connection.createStatement();

	}
	
	private String getConnectionUrl()
	{
		return "jdbc:sqlserver://"
				+ ProgramSettings.getProperty("database_server_name") + ":"
				+ ProgramSettings.getProperty("database_port") + ";DatabaseName="
				+ ProgramSettings.getProperty("database_name") + ";user="
				+ ProgramSettings.getProperty("database_username") + ";password="
				+ ProgramSettings.getProperty("database_password") + ";";
		//String s = "jdbc:sqlserver://rbmsdemo.dyndns.org:1433;DatabaseName=REDFOX;user=brian;password=brianbrian;";		
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
