package pages;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JobApplyPage extends BasePage {

	public JobApplyPage(WebDriver driver) {
		super(driver);
		// this.driver=driver;
		PageFactory.initElements(driver, this);

	}
	// Locators

	@FindBy(id = "filter-freshness")
	private WebElement jobFreshnessDropdown;

	@FindBy(xpath = "//ul[@data-filter-id='freshness']//span[text()='Last 7 days']")
	private WebElement jobFreshnessList;

	@FindBy(xpath = "//div[@id='listContainer']")
	private List<WebElement> jobList;

	@FindBy(xpath = "//button[text()='Apply']")
	private WebElement jobApplyBtn;

	@FindBy(xpath = "//button[text()='Apply on company site']")
	private WebElement jobApplyByCompanyBtn;
	
	
	@FindBy(xpath="//button[text()='I am interested']")
	private WebElement interestedBtn;
	

	@FindBy(xpath = "//span[contains(normalize-space(), 'You have successfully applied to')]")
	private WebElement successMessage;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

//method
	public void selectJobFreshness() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", jobFreshnessDropdown);
		clickElement(jobFreshnessDropdown);
		System.out.println("Job Freshness Dropdown is clicked");
		System.out.println("The job freshness choosen: " + jobFreshnessList.getText());
		clickElement(jobFreshnessList);
		Thread.sleep(5000);

		// WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		/*
		 * List <WebElement> freshnessOption= (List<WebElement>)
		 * wait.until(ExpectedConditions.visibilityOfAllElements(jobFreshnessList));
		 * 
		 * 
		 * for(WebElement options:freshnessOption) {
		 * if(options.getText().trim().toLowerCase().equals("Last 7 days")) {
		 * System.out.println("The selected job freshness is: "+options.getText());
		 * clickElement(options); break; }
		 * 
		 * 
		 * }
		 */

	}

	// Method to select and apply for a job
	public String selectJobToApply() throws InterruptedException {
		String parentWindow = driver.getWindowHandle();
		System.out.println("The parent URL is: " + parentWindow);
		// Again scroll back
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", jobList.get(0));
		clickElement(jobList.get(0));
		System.out.println("Job is clicked");
		Thread.sleep(5000);
		
		Set<String> windowsOpened = driver.getWindowHandles();
		Iterator<String> itr = windowsOpened.iterator();

		while (itr.hasNext()) {
			String jobApplyWindow = itr.next();
			if (!parentWindow.equals(jobApplyWindow)) {
				driver.switchTo().window(jobApplyWindow);
				System.out.println(driver.getTitle());

				 // Wait for any of the three buttons to appear
	            WebElement applyButton = null;
	            try {
	                applyButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Apply']")));
	            } catch (TimeoutException e1) {
	                try {
	                    applyButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Apply on company site']")));
	                } catch (TimeoutException e2) {
	                    try {
	                        applyButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='I am interested']")));
	                    } catch (TimeoutException e3) {
	                    	// Instead of failing the test, log a message and return
	                        System.out.println("No application button found on the job page. Please apply manually.");
	                        return "Manual application required"; // Return a message instead of failing
	                    }
	                }
	            }
				
				
				// Click the available button
	            if (applyButton != null) {
	                clickElement(applyButton);
	                System.out.println("Clicked on: " + applyButton.getText());
	                Thread.sleep(5000);
	            }
				
				
				System.out.println("Clicked on job apply btn");
				Thread.sleep(5000);

				String successMessageText = getSuccessMessage(successMessage);

				if (!successMessageText.isEmpty()) {
					System.out.println(" Success: " + successMessageText);
					return successMessageText; // Return the success message if found
				} else {
					System.out.println(" Job application failed - success message not found.");
					throw new AssertionError("Job application failed: Success message not found.");
				}
			}
		}
		throw new AssertionError("No new job application window found."); // Fail if no job window opens
	}

	// Method to capture the success message
	private String getSuccessMessage(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
			WebElement visibleElement = wait.until(ExpectedConditions.visibilityOf(successMessage));

			// Use JavaScript to capture the message BEFORE it disappears
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String message = (String) js.executeScript("return arguments[0].innerText;", visibleElement);

			return message.trim();
		} catch (TimeoutException e) {
			System.out.println("Success message not displayed.");
			return "";
		}
	}
}