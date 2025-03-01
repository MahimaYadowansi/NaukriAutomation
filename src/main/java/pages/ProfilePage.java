package pages;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasePage {

	public ProfilePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// Locators
	@FindBy(xpath = "//div[text()='Jobs']")
	private WebElement jobPage;
	@FindBy(xpath = "//div[text()='Companies']")
	private WebElement companyPage;
	@FindBy(xpath = "//div[text()='Services']")
	private WebElement servicePage;
	@FindBy(xpath = "//img[@alt='naukri360-pill']")
	private WebElement naukri360Page;
	@FindBy(xpath = "//span[contains(@class, 'ni-gnb-icn-bell')]")
	private WebElement notificationPage;
	
	@FindBy(xpath="//a[@class='close']")
	private WebElement closeNotification;
	
	@FindBy(xpath="//img[@alt='Naukri Logo']")
	private WebElement logoClick;
	
	@FindBy(xpath = "//a[normalize-space()='View profile']")
	private WebElement viewProfilePage;
	@FindBy(xpath = "//div[contains(@class ,'nI-gNb-drawer__bars')]")
	private WebElement drawerBarLinePage;

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

public void testNavigation()
{    wait.until(ExpectedConditions.visibilityOf(jobPage));
	clickElement(jobPage);
	System.out.println("The job page navigated");
	wait.until(ExpectedConditions.visibilityOf(companyPage));
	clickElement(companyPage);
	System.out.println("The companyPage navigated");
	wait.until(ExpectedConditions.visibilityOf(naukri360Page));
	clickElement(naukri360Page);
	System.out.println("The naukri360Page navigated");
	
	wait.until(ExpectedConditions.visibilityOf(notificationPage));
	clickElement(notificationPage);
	System.out.println("The notificationPage  navigated");
	wait.until(ExpectedConditions.visibilityOf(closeNotification));
	clickElement(closeNotification);
	System.out.println("The closeNotification page navigated");
	
	
	wait.until(ExpectedConditions.visibilityOf(logoClick));
	clickElement(logoClick);
	System.out.println("The logo homepage navigated");
	wait.until(ExpectedConditions.visibilityOf(viewProfilePage));
	clickElement(viewProfilePage);
	System.out.println("The viewProfilePage navigated");
	String ParentWindow = driver.getWindowHandle();

	
	wait.until(ExpectedConditions.visibilityOf(servicePage));
	clickElement(servicePage);
	
	Set<String> windowsOpened = driver.getWindowHandles();
	Iterator<String> itr = windowsOpened.iterator();

	while (itr.hasNext()) {
		String jobApplyWindow = itr.next();
		if (!ParentWindow.equals(jobApplyWindow)) {
			driver.switchTo().window(jobApplyWindow);
			System.out.println(driver.getTitle());
	
	
}

		
	}
	driver.switchTo().defaultContent();
	
}
}

