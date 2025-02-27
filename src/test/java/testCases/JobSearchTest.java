package testCases;

import org.testng.annotations.Test;

import pages.JobSearchPage;
import pages.LoginPage;

public class JobSearchTest extends BaseTest {
	JobSearchPage jobSearch;
	LoginPage login;

	@Test(priority = 2)
	public void testJobSearch() throws InterruptedException {
		
		LoginTest loginTest = new LoginTest(); // Creating object of LoginTest
		loginTest.driver = this.driver; // Assign the same driver instance
		loginTest.login(); // Calling login method from LoginTest
		
		
		jobSearch = new JobSearchPage(driver);
		jobSearch.clickSearchBar();
		jobSearch.enterJob("Software Tester");
		jobSearch.clickExperience();
		jobSearch.enterLocation("India");
		jobSearch.clickSearchBtn();
	}
}
