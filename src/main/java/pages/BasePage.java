package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	WebDriver driver;
	
	public BasePage(WebDriver driver)
	{
		this.driver=driver;
	}
//wait for element to visible
	
	public void waitForElement(WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	
	//Click on element
	public void clickElement(WebElement element)
	{
		waitForElement(element);
		element.click();
	}
	
	
	//Enter Text
	public void enterText(WebElement element, String text)
	{
		waitForElement(element);
		element.sendKeys(text);
	}
}
