package pages;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResumeUploadPage extends BasePage{

	public ResumeUploadPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	//Locators
	@FindBy(xpath="//a[normalize-space()='View profile']")
	private WebElement viewprofileBtn;
	
	@FindBy(xpath="//input[@id='attachCV']")
	private WebElement updateResumeInput;
	
	
	 @FindBy(xpath = "//span[@id='attachCVMsgBox']")
	    private WebElement successMessage;
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		public void updateNaukriResume() throws InterruptedException {
	        try {
	            // Wait for and click 'View Profile' button
	            wait.until(ExpectedConditions.elementToBeClickable(viewprofileBtn));
	            clickElement(viewprofileBtn);
	            System.out.println("Clicked on View profile button");

	            // Wait for the thank you message to disappear
	            waitForThankYouMessageToDisappear();

	         // Wait for resume upload input field
	            WebElement updateResumeBtn = wait.until(ExpectedConditions.visibilityOf(updateResumeInput));

	            // Ensure element is stable before interacting
	            waitUntilElementStopsMoving(updateResumeBtn);

	            // Debugging: Check if the input field is correct
	            System.out.println("Tag Name: " + updateResumeBtn.getTagName());
	            System.out.println("Type Attribute: " + updateResumeBtn.getAttribute("type"));

	            // Ensure it's an actual file input field
	            if (!"file".equals(updateResumeBtn.getAttribute("type"))) {
	                throw new IllegalStateException("The located element is not an <input type='file'>. Check your XPath.");
	            }

	            // File path correction
	            String resumePath = new File("src/test/resources/resume/MAHIMA-TEST Engineer.pdf").getAbsolutePath();
	            System.out.println("Resume Path: " + resumePath);

	            // Scroll to the file input field to avoid hidden element issues
	            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", updateResumeBtn);
	            Thread.sleep(1000); // Allow time for scrolling effect

	            // If file input is hidden, make it visible using JavaScript
	            ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='block';", updateResumeBtn);

	            // Upload Resume using sendKeys
	            enterText(updateResumeBtn, resumePath);

	            System.out.println("Resume updated successfully!");

	            // Wait and capture success message
	            Thread.sleep(2000);
	            String successMessageText = getSuccessMessage("//span[@id='attachCVMsgBox']");
	            if (!successMessageText.isEmpty()) {
	                System.out.println("Success Message: " + successMessageText);
	            } else {
	                System.out.println("Success message not found.");
	            }

	        } catch (Exception e) {
	            System.out.println("Unable to update resume: " + e);
	        }
	    }

	    // Wait for the 'Thank You' message to disappear before proceeding
	    private void waitForThankYouMessageToDisappear() {
	        try {
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Thank you for sharing your information')]")));
	            System.out.println("Thank You message disappeared.");
	        } catch (TimeoutException e) {
	            System.out.println("Thank You message did not disappear in time, proceeding anyway.");
	        }
	    }

	    // Wait until an element stops moving before interacting
	    public void waitUntilElementStopsMoving(WebElement element) throws InterruptedException {
	        Point lastLocation = null;
	        int stableCount = 0;

	        for (int i = 0; i < 10; i++) { // Try for up to 10 seconds
	            Point currentLocation = element.getLocation();

	            if (currentLocation.equals(lastLocation)) {
	                stableCount++;
	            } else {
	                stableCount = 0; // Reset if it moved again
	            }

	            if (stableCount >= 3) {
	                System.out.println("Element is now stable.");
	                return;
	            }

	            lastLocation = currentLocation;
	            Thread.sleep(500); // Check position every 500ms
	        }

	        System.out.println("Warning: Element might still be moving.");
	    }

	    // Capture success message safely using JavaScript
	    private String getSuccessMessage(String xpath) {
	        try {
	            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

	            // Use JavaScript to capture the message BEFORE it disappears
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            String message = (String) js.executeScript(
	                "var target = arguments[0];" +
	                "return target.innerText;", element);

	            System.out.println("Success message: Resume updated successfully !!!");
	            return message.trim();

	        } catch (TimeoutException e) {
	            System.out.println("Success message disappeared before capture.");
	            return "";
	        }
	    }
	}	