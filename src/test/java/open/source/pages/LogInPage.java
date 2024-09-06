package open.source.pages;

import open.source.page.elements.LogInPageElements;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LogInPage {

	private WebDriver driver;

	public LogInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = LogInPageElements.USER_NAME_TEXT_FIELD)
	WebElement userNameTextField;

	@FindBy(xpath = LogInPageElements.PASSWORD_TEXT_FIELD)
	WebElement passwordTextField;

	@FindBy(xpath = LogInPageElements.LOGIN_BUTTON)
	WebElement logInButton;

	@FindBy(xpath = LogInPageElements.ADMIN_DASHBOARD)
	WebElement adminDashBoardPage;

	/**
	 * The below method is used to log into the application.
	 * 
	 * @param properties
	 */
	public void logIntoApplication(Properties properties) {
		driver.get(properties.getProperty("url"));

		userNameTextField.sendKeys(properties.getProperty("userName"));
		passwordTextField.sendKeys(properties.getProperty("password"));
		logInButton.click();

		Assert.assertEquals(true, adminDashBoardPage.isDisplayed());
	}

}
