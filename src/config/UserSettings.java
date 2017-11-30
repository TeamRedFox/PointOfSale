package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class UserSettings
{
	private final String configFilePath = "config.properties";

	private Properties properties; 

	public UserSettings()
	{
		//Check if config File exists in the given location
		File configFile = new File(configFilePath);
		if (configFile.exists())	//If so, read from the file
			properties = readFromProperties(configFilePath);
		else
		{
			//If not, create a new Properties instance and write it to a file at the filePath
			properties = new Properties();
			writeProperties(properties, configFilePath);
		}
	}
	
	//Returns a properties instance read from the file at the given path
	static Properties readFromProperties(String filePath)
	{
		//Initiate properties and inputStream 
		Properties properties = new Properties();
		InputStream inputStream = null;
		
		try
		{
			//Load properties with the data from the config file
			inputStream = new FileInputStream(filePath);
			properties.load(inputStream);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			//Close inputStream if it was ever successfully created
			if (inputStream != null)
			{
				try
				{
					inputStream.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		return properties;
	}
	
	//Write the given properties instance to the config file
	static void writeProperties(Properties properties, String filePath)
	{
		//Initiate outputStream
		OutputStream outputStream = null;

		try
		{
			//Store data from properties instance into a file at filePath
			outputStream = new FileOutputStream(filePath);
			properties.store(outputStream, null);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			//Close outputStream if it was ever successfully created
			if (outputStream != null)
			{
				try
				{
					outputStream.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	/**Adds or updates the property with the given key to be the given value, updates the config file*/
	public void setProperty(String key, String value)
	{
		properties.setProperty(key, value);
		writeProperties(properties, configFilePath);
	}

	/**Returns the property with the given key from the config*/
	public String getProperty(String key)
	{
		return properties.getProperty(key);
	}
	
	
	/**Returns whether the config contains a propery with the given key*/
	public boolean containsProperty(String key)
	{
		return properties.containsKey(key);
	}
	
	/**Removes the property with the given key, updates the config file*/
	public void removeProperty(String key)
	{
		properties.remove(key);
		writeProperties(properties, configFilePath);
	}
}
