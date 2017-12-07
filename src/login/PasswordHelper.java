package login;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHelper
{
	public static String generatePasswordHash(String password)
	{
		//Create instances of digest and password char array
		MessageDigest passDigest;
		byte[] passArray;

		try
		{
			//Create instance of MessageDigest we can use to  
			passDigest = MessageDigest.getInstance("MD5");

			//Convert password to byte array 
			passArray = password.getBytes("UTF-8");
		}
		catch (NoSuchAlgorithmException e)
		{
			//Hardcoded algorithm type, this exception shouldn't be called
			e.printStackTrace();
			return "";
		}
		catch (UnsupportedEncodingException e)
		{
			//Hardcoded encoding type, this exception shouldn't be called
			e.printStackTrace();
			return "";
		}

		//Use digest to get an array of chars as a hash and return it as a string
		byte[] hashArray = passDigest.digest(passArray);
		return new String(hashArray);
	}

}
