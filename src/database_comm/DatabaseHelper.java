package database_comm;

public class DatabaseHelper
{
	/**Formats and sanitizes a string (varchar) value to be put into the database without errors*/
	public static String formatStringField(String input)
	{
		//Single quotes are interpreted as quotations in SQL, replace them with their literal (double quotes)
		input = input.replace("'", "''");
		
		//Auto-add the single quotes in this method for simplification 
		return "'" + input + "'";
	}

}
