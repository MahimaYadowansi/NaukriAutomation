package testCases;

import org.testng.annotations.Test;

import pages.LogOutPage;
import pages.LoginPage;

public class LogOutTest extends BaseTest {
	LoginPage login;
	LogOutPage logOut;
	@Test
	public void testLogOut() throws InterruptedException
	{
       
		
		logOut = new LogOutPage(driver);
        logOut.LogOut();
        Thread.sleep(2000);
        
		
	}
	@Test
	public void logoutLOGINtest() throws InterruptedException
	{
		 
		LoginTest loginTest = new LoginTest(); // Creating object of LoginTest
		loginTest.driver = this.driver; // Assign the same driver instance
		loginTest.login(); 
		Thread.sleep(5000);
		logOut=new LogOutPage(driver);
	     logOut.LogOut();
	    
	
	}

}
