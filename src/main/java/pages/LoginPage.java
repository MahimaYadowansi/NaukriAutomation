package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//locators
	@FindBy(xpath="//a[@title='Jobseeker Login']")
	private WebElement baseLoginBtn;
	
	@FindBy(xpath="//input[@placeholder='Enter your active Email ID / Username']")
	private WebElement emailField;
	
	@FindBy(xpath="//input[@placeholder='Enter your password']")
	private WebElement passwordField;
	
	@FindBy(xpath="//button[text()='Login']")
	private WebElement loginBtn;
	

	
	//Methods
	public void baseLoginBtn()
	{
		clickElement(baseLoginBtn);
	}
	
	public void enterEmail(String username)
	{
		enterText(emailField, username);
	}
	
	public void enterPassword(String password) {
        enterText(passwordField, password);
    }

    public void clickLogin() {
    	clickElement(loginBtn);
    }
	
}
