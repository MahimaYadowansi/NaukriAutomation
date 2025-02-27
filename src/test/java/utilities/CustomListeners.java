package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
//import org.apache.logging.log4j.core.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.commons.io.FileUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import pages.BasePage;

public class CustomListeners extends BasePage implements ITestListener{
	public CustomListeners(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}


	private WebDriver driver;
	 public ExtentReports extent = ExtentManager.getInstance();
		//public static ExtentTest test;
	// Initialize Logger
	    private static final Logger logger = (Logger) LogManager.getLogger(CustomListeners.class);
	    @Override
	    public void onTestStart(ITestResult result) {
	    	
	            // Create a new test instance when a test starts
	            String testName = result.getName();
	            String description = result.getMethod().getDescription();
	            // Creating a test in Extent Reports
	            ExtentManager.createTest(testName, description);

	            // Log the start of the test
	            logger.info("Test start: " + testName);
	            ExtentManager.getTest().log(Status.INFO, "Test started: " + testName);
	    }
	    @Override
	    public void onTestSuccess(ITestResult result) {
	        logger.info("Test passed: " + result.getName());
	        ExtentManager.getTest().log(Status.PASS, "Test Passed Successfully");
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	    	logger.error("Test failed: " + result.getName(), result.getThrowable());
	        ExtentManager.getTest().log(Status.FAIL, "Test Failed: " + result.getThrowable());

	        // Retrieve WebDriver instance
	        this.driver = WebDriverManager.getDriver();

	        if (driver != null) {
	            String screenshotPath = takeScreenshot(result.getName());
	            ExtentManager.getTest().fail("Screenshot of failure",
				        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	        } else {
	            logger.warn("WebDriver instance is null. Screenshot could not be taken.");
	        }
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	    	 logger.info("Test skipped: " + result.getName());
	    	 ExtentManager.getTest().log(Status.SKIP, "Test Skipped: " + result.getThrowable());
	    }

	    @Override
	    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	        // This method can be used if you care about tests failing within a success percentage
	    }

	    @Override
	    public void onStart(ITestContext context) {
	    	 logger.info("Test suite started: " + context.getName());
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	    	 logger.info("Test suite finished: " + context.getName());
	    	 ExtentManager.getInstance().flush();
	    }
	    
	  
	    public String takeScreenshot(String testName) {
	    	 File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	         String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + testName + ".png";

	         try {
	             FileUtils.copyFile(srcFile, new File(screenshotPath));
	             logger.info("Screenshot saved: " + screenshotPath);
	         } catch (IOException e) {
	             logger.error("Error saving screenshot", e);
	         }
	         return screenshotPath;
	    }
	

	    
}

