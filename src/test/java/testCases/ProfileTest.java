package testCases;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.ProfilePage;

public class ProfileTest extends BaseTest {
	
	ProfilePage profile;
	LoginPage login;
	@Test
	public void testNaukriNavigation() throws InterruptedException
	{
		
		LoginTest loginTest = new LoginTest(); // Creating object of LoginTest
		loginTest.driver = this.driver; // Assign the same driver instance
		loginTest.login();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5))	;
		profile= new ProfilePage(driver);
		profile.testNavigation();
	}

}
