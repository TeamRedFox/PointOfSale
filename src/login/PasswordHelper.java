package login;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

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

		//Use digest to get hash as an array of chars and return it as a hex string
		byte[] hashArray = passDigest.digest(passArray);
		return DatatypeConverter.printHexBinary(hashArray);
	}

}
