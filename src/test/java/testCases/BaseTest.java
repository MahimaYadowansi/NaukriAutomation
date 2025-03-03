package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import utilities.ConfigReader;
import utilities.WebDriverManager;

public class BaseTest {
	 public WebDriver driver;
	 @BeforeTest
	    public void setup() {
	        driver = WebDriverManager.getDriver(); // Get WebDriver instance from WebDriverManager
	        driver.manage().window().maximize();
	        driver.get(ConfigReader.getProperty("url")); // Read URL from config file
	    }

	    @AfterTest
	    public void tearDown() {
	        WebDriverManager.closeDriver(); // Close browser
	    }
}
