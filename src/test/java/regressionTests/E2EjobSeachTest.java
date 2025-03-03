package regressionTests;

import org.testng.annotations.Test;

import pages.JobApplyPage;
import pages.JobSearchPage;
import pages.LogOutPage;
import pages.LoginPage;
import testCases.BaseTest;
import testCases.JobApplyTest;
import testCases.JobSearchTest;
import testCases.LogOutTest;
import testCases.LoginTest;

public class E2EjobSeachTest extends BaseTest {
	
	
	JobApplyPage jobApply;
	LogOutPage logout;
	@Test
	public void jobSearchE2E() throws InterruptedException
	{
		
		JobApplyTest jobApplyTest = new JobApplyTest();
        jobApplyTest.driver = this.driver;
        jobApplyTest.testJobApply();  // Calls the job application flow
        Thread.sleep(5000);
        LogOutTest logOutTest = new LogOutTest();
        logOutTest.driver = this.driver;
        logOutTest.testLogOut();  // Calls logout function
		
		
		
	}

}
