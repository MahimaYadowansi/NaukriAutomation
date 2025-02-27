package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
	public static Properties properties;
	static {
		try
		{
			FileInputStream file=new FileInputStream("src/test/java/utilities/config.properties");
			properties =new Properties();
			properties.load(file);
			file.close();
		}catch(Exception e)
		{
			System.out.println("Error loading config file: "+e);
		}
	}
//get property value by key
	public static String getProperty(String Key)
	
	{
		return properties.getProperty(Key);
	}
}
