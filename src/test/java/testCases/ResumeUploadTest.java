package testCases;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.ResumeUploadPage;

public class ResumeUploadTest extends BaseTest{
	ResumeUploadPage resumeUpload;
	LoginPage login;
	@Test
	public void testResumeUpload() throws InterruptedException
	{
		LoginTest loginTest = new LoginTest(); // Creating object of LoginTest
		loginTest.driver = this.driver; // Assign the same driver instance
		loginTest.login();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5))	;
		resumeUpload= new ResumeUploadPage(driver);
		resumeUpload.updateNaukriResume();
	}

}
