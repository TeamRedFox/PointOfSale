package login;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHelper
{
	private static String hashAlgorithm = "MD5";
	private static String stringEncodingFormat = "UTF-8"; 
	
	public static String generatePasswordHash(String password)
	{
		//Create instances of digest and password char array
		MessageDigest passDigest;
		byte[] passArray;

		try
		{
			//Create instance of MessageDigest we can use to  
			passDigest = MessageDigest.getInstance(hashAlgorithm);

			//Convert password to byte array 
			passArray = password.getBytes(stringEncodingFormat);
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
			return "";
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
			return "";
		}

		//Use digest to get an array of chars as a hash and return it as a string
		byte[] hashArray = passDigest.digest(passArray);
		return new String(hashArray);
	}

}
