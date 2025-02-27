package utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverManager {
	
	private static WebDriver driver;
	private  static WebDriverWait wait;
	
	
	//Method to get webdriver instance
public static WebDriver getDriver()
{
	if(driver==null)
	{
		String browser=ConfigReader.getProperty("browser");
		switch(browser.toLowerCase())
		{
		case "chrome" :
			driver=new ChromeDriver();
			break;
		case "firefox":
			driver=new FirefoxDriver();
			break;
		case "edge":
			driver=new EdgeDriver();
			break;
			default:
				throw new IllegalArgumentException("Invalid browser: "+browser);
		}
		driver.manage().window().maximize();
		wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		
	}
	return driver;
}

//Method to close WebDriver Instance
public static void closeDriver()
{
	if(driver!=null)
	{
		driver.quit();
		driver=null;  //Setting driver null
	}
}

}
