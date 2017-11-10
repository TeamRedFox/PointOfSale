package Driver;
import java.io.FileNotFoundException;
import java.io.IOException;	

	
	
	public class POS_Driver {
		
		public static void main(String[] args) throws IOException, FileNotFoundException {
			POS_Driver register = new POS_Driver();
			new POS_GUI_Controller(register);
		}
		

	}


