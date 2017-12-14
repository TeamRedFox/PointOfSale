package login;

import java.time.LocalDate;
import java.time.LocalTime;

//check this exception - are inputs okay?
//should inputs be variables?

//mostly complete as of 4/20/17
public class InvalidUsernameOrPasswordException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String invalidField;
	private LocalTime timeThrown;

	public InvalidUsernameOrPasswordException(String invalidField) {
		this.invalidField = invalidField;
		this.timeThrown = LocalTime.now();
	}

	public String getErrorMessage() {
		String message;
		
		if (invalidField.equals("invalid")){
			message = "Invalid credentials. Please try again.";
		}
		else if (invalidField.equals("username")) {
			message = "The inputted username does not match any in our system.";
		}
		else if (invalidField.equals("password")) {
			message = "Incorrect password entered.";
		}
		else {
			message = "One or more login fields are empty.";
		}
		
		return message;// + " " + LocalDate.now() + " " + LocalTime.now(); // adds timestamp when error  occured, remove if needed
	}
	
}
