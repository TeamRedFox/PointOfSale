package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import view.POS_GUI;

public class Driver {
//We all know what this class does 
	// change the users filename to the correct filepath
	private static final String USERS_FILENAME = "C://workspace//Point of Sale//POS_Users.csv";
	
	public static void main(String[] args) throws IOException, FileNotFoundException {
		POS website = new POS();
		createUsers(USERS_FILENAME, 1, website);
		new POS_GUI(website);
	}

	public static void createUsers(String filename, int headerRows, POS website) {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			int lineNumber = 1;
			while ((line = br.readLine()) != null) {
				if (lineNumber > headerRows) {
					String delims = "[,]+";
					String[] userInfo = line.split(delims);
					
					website.addUser(userInfo[0], userInfo[1], userInfo[2], userInfo[3], userInfo[4], userInfo[5]);
				}
				lineNumber++;
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
}
