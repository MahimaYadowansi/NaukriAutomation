package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JobSearchPage extends BasePage {

	public JobSearchPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// Locators
	@FindBy(xpath = "//span[text()='Search jobs here']")
	private WebElement searchField;

	@FindBy(xpath = "//input[@placeholder='Enter keyword / designation / companies']")
	private WebElement searchjob;

	@FindBy(id = "experienceDD")
	private WebElement searchExp;

	@FindBy(xpath = "//ul[@class='dropdown ']//li")
	private List<WebElement> searchExpDropDownList;

	@FindBy(xpath = "//input[@placeholder='Enter location']")
	private WebElement searchLocation;

	@FindBy(xpath = "//span[text()='Search']")
	private WebElement searchBtn;

	// method

	public void clickSearchBar() {
		clickElement(searchField);
	}

	public void enterJob(String jobName) {
		enterText(searchjob, jobName);
	}

	public void clickExperience()  {
		clickElement(searchExp);

		System.out.println("exp drop is clicked");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		List<WebElement> experienceOptions = wait
				.until(ExpectedConditions.visibilityOfAllElements(searchExpDropDownList));

		for (WebElement options : experienceOptions) {
			if (options.getText().trim().equalsIgnoreCase("1 year")) {
				options.click();
				System.out.println("1 year option is selected");
				break;
			}
		}

	}

	public void enterLocation(String location) {
		clickElement(searchLocation);
		enterText(searchLocation, location);
	}

	public void clickSearchBtn() {
		clickElement(searchBtn);
	}
}
