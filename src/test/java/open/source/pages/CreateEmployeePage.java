package open.source.pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import open.source.page.elements.CreateEmployeePageElements;
import open.source.utils.AbstractTest;
import open.source.utils.POJO;

public class CreateEmployeePage {

	private WebDriver driver;

	public static POJO pojo = new POJO();
	public static AbstractTest abstractTest = new AbstractTest();

	public CreateEmployeePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = CreateEmployeePageElements.ADMIN_BUTTON)
	WebElement adminButton;

	@FindBy(xpath = CreateEmployeePageElements.ADD_BUTTON)
	WebElement addButton;

	@FindBy(xpath = CreateEmployeePageElements.USER_ROLE_DROP_DOWN_BUTTON)
	WebElement userRoleDropDownButton;

	@FindBy(xpath = CreateEmployeePageElements.USER_ROLE_DROP_DOWN_LIST)
	List<WebElement> userRoleList;

	@FindBy(xpath = CreateEmployeePageElements.STATUS_DROP_DOWN_BUTTON)
	WebElement statusDropDownButton;

	@FindBy(xpath = CreateEmployeePageElements.STATUS_DROP_DOWN_LIST)
	List<WebElement> statusList;

	@FindBy(xpath = CreateEmployeePageElements.EMPLOYEE_NAME_TEXT_FIELD)
	WebElement employeeNameTextField;

	@FindBy(xpath = CreateEmployeePageElements.SECOND_EMPLOYEE)
	WebElement secondEmployeeName;

	@FindBy(xpath = CreateEmployeePageElements.EMPLOYEE_USER_NAME_TEXT_FIELD)
	WebElement employeeUserNameTextField;

	@FindBy(xpath = CreateEmployeePageElements.EMPLOYEE_PASSWORD_TEXT_FIELD)
	WebElement employeePasswordTextField;

	@FindBy(xpath = CreateEmployeePageElements.EMPLOYEE_CONFIRM_PASSWORD_TEXT_FIELD)
	WebElement employeeConfirmPasswordTextField;

	@FindBy(xpath = CreateEmployeePageElements.SAVE_BUTTON)
	WebElement saveButton;

	@FindBy(xpath = CreateEmployeePageElements.SUCCESS_MESSAGE_LABEL)
	WebElement successMessageLabel;

	@FindBy(xpath = CreateEmployeePageElements.SEARCH_BUTTON)
	WebElement searchButton;

	@FindBy(xpath = CreateEmployeePageElements.ONE_RECORD_FOUND_TEXT_LABEL)
	WebElement oneRecordFoundTextLabel;

	public WebElement fullEmployeeName(String employeeName) {
		return driver.findElement(By.xpath("//span[text()='" + employeeName + "']"));
	}

	/**
	 * The below method is used to create an employee.
	 * 
	 * @param properties
	 */
	public void createAnEmployee(Properties properties) {
		adminButton.click();
		addButton.click();
		userRoleDropDownButton.click();

		abstractTest.selectValueFromDropDown(driver, userRoleList, properties.getProperty("userRole"));
		statusDropDownButton.click();
		abstractTest.selectValueFromDropDown(driver, statusList, properties.getProperty("userStatus"));
		employeeNameTextField.sendKeys("R");
		abstractTest.waitUntilElementIsClickable(driver, secondEmployeeName);
		secondEmployeeName.click();

		pojo.setEmployeeUserName(employeeNameTextField.getAttribute("value"));
		pojo.setRandomUserName(abstractTest.generateRandomUsername(6));
		pojo.setRandomPassword(abstractTest.generateRandomPassword(9));
		employeeUserNameTextField.sendKeys(pojo.getRandomUserName());
		employeePasswordTextField.sendKeys(pojo.getRandomPassword());
		employeeConfirmPasswordTextField.sendKeys(pojo.getRandomPassword());
		saveButton.click();

		abstractTest.validateSuccessMessage(driver, By.xpath(CreateEmployeePageElements.SUCCESS_MESSAGE_LABEL));

	}

	/**
	 * The below method is used to validate the details of an employee which is
	 * created.
	 * 
	 * @param properties
	 */
	public void validateCreatedEmployeeDetails(Properties properties) {
		abstractTest.waitUntilPageLoads();
		employeeUserNameTextField.sendKeys(pojo.getRandomUserName());

		userRoleDropDownButton.click();
		abstractTest.selectValueFromDropDown(driver, userRoleList, properties.getProperty("userRole"));
		employeeNameTextField.sendKeys(pojo.getEmployeeUserName());
		abstractTest.waitUntilElementIsClickable(driver, fullEmployeeName(pojo.getEmployeeUserName()));
		fullEmployeeName(pojo.getEmployeeUserName()).click();

		statusDropDownButton.click();
		abstractTest.selectValueFromDropDown(driver, statusList, properties.getProperty("userStatus"));
		abstractTest.waitUntilElementIsClickable(driver, searchButton);
		searchButton.click();
		boolean isEmployeeRecordDisplayed = oneRecordFoundTextLabel.isDisplayed();

		Assert.assertEquals(true, isEmployeeRecordDisplayed);
	}

}
