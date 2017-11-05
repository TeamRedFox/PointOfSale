package database_comm;
import java.sql.*;
import com.microsoft.sqlserver.jdbc.*;

public class DatabaseComm
{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/EMP";

	static final String USER = "sa";
	static final String PASS = "brianbrian";

	//TODO make not main
	public static void main(String[] args) throws SQLException
	{
		System.out.println("starting");
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			//Debug localhost string
			String connectionUrl = "jdbc:sqlserver://150.250.147.167:1433;DatabaseName=REDFOX;user=sa;password=brianbrian;";
			
			//Debug RBMS string
			//String connectionUrl = "jdbc:sqlserver://rbmsdemo.dyndns.org:1433;DatabaseName=REDFOX;user=Brian;password=brianbrian;";

			Connection con = DriverManager.getConnection(connectionUrl);
			System.out.println("done");
			System.out.println(con);

		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
