package testCases;

import org.testng.annotations.Test;

import pages.JobApplyPage;
import pages.JobSearchPage;
import pages.LoginPage;

public class JobApplyTest extends BaseTest {
	LoginPage login;
	JobSearchPage jobSearch;
	JobApplyPage jobApply;
public void applyJob() throws InterruptedException
{
	jobApply =new JobApplyPage(driver);
	jobApply.selectJobFreshness();
	jobApply.selectJobToApply();
			
}

@Test
public void testJobApply() throws InterruptedException
{
	LoginTest loginTest=new LoginTest();
	loginTest.driver=this.driver;
	loginTest.login();
	
	JobSearchTest jobSearchTest=new JobSearchTest();
	jobSearchTest.driver=this.driver;
	jobSearchTest.jobSearch();
	Thread.sleep(5000);
	applyJob();
	
	
	
	
	
}
}
