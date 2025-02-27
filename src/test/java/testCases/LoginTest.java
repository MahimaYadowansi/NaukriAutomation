package testCases;

import org.testng.annotations.Test;

import pages.LoginPage;

public class LoginTest extends BaseTest {
	LoginPage login;
	
	public void login()
	{
		String emailENV=System.getenv("NaukriLOGIN_EMAIL");
		String passwordENV=System.getenv("NaukriLOGIN_PASSWORD");
		login=new LoginPage(driver);
		login.baseLoginBtn();
		login.enterEmail(emailENV);
        login.enterPassword(passwordENV);
        login.clickLogin();
	}
	@Test(priority = 1)
	public void testvalidLogin()
	{
		login();
		
        try {
            Thread.sleep(5000); // Replace with WebDriverWait for better reliability
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	}
	
	


