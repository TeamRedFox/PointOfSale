package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

//User for the Matchmaker program
public class User implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String role;
	/* arraylist for items to put in register?
	private ArrayList<Item> ItemList;
    */
	
	// TODO serializable
	public User(String username, String password, String firstName, String lastName, String email, String role) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.setRole(role);
	}

	
   // Default constructor
	public User() {
	}



	public String getUsername() {
		return username;
	}

	protected void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	protected void setPassword(String password) {
		this.password = password;
	}


	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}


	public String toString() {
		return firstName + " " + lastName + " " + role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
