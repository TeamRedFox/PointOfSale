
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import database_comm.UserDatabase;	

	
	
	public class POS_Driver {
		
		public static void main(String[] args) throws IOException, FileNotFoundException, SQLException {
			POS_Driver register = new POS_Driver();
			new POS_GUI_Controller(register);
			// test - Lists all users in user db
			UserDatabase.printAllUsers();
		}
		

	}


