package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogOutPage extends BasePage{

	public LogOutPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	//locators
	@FindBy(xpath = "//div[contains(@class ,'nI-gNb-drawer__bars')]")
	private WebElement drawerBarLinePage;
	
	@FindBy(xpath="//a[@title='Logout']")
	private WebElement logOut;
	
public void LogOut()
{
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOf(drawerBarLinePage));
	clickElement(drawerBarLinePage);
	wait.until(ExpectedConditions.visibilityOf(logOut));
	clickElement(logOut);
	
}
}
