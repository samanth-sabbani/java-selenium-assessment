package open.source.pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import open.source.page.elements.CreateEmployeePageElements;
import open.source.page.elements.HolidaysPageElements;
import open.source.utils.AbstractTest;

public class HolidaysPage {

	private WebDriver driver;

	public static AbstractTest abstractTest = new AbstractTest();

	public HolidaysPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = HolidaysPageElements.LEAVE_BUTTON)
	WebElement leaveButton;

	@FindBy(xpath = HolidaysPageElements.CONFIGURE_BUTTON)
	WebElement configureButton;

	@FindBy(xpath = HolidaysPageElements.CONFIGURE_ITEMS_LIST)
	List<WebElement> configureItemsList;

	@FindBy(xpath = HolidaysPageElements.GANESH_CHATHURTHI_HOLIDAY_LIST_ITEM)
	List<WebElement> ganeshChathurthiHoliday;

	@FindBy(xpath = HolidaysPageElements.GANESH_CHATHURTHI_HOLIDAY_DELETE_BUTTON)
	WebElement ganeshChathurthiHolidayDeleteIcon;

	@FindBy(xpath = HolidaysPageElements.YES_DELETE_BUTTON)
	WebElement yesDeleteButton;

	@FindBy(xpath = HolidaysPageElements.ADD_BUTTON)
	WebElement addButton;

	@FindBy(xpath = HolidaysPageElements.HOLIDAY_DATE_TEXT_FIELD)
	WebElement holidayDateTextField;

	@FindBy(xpath = HolidaysPageElements.DAY_DROP_DOWN_BUTTON)
	WebElement dayDropDownButton;

	@FindBy(xpath = HolidaysPageElements.DAY_DROP_DOWN_LIST)
	List<WebElement> dayDropDownList;

	@FindBy(xpath = HolidaysPageElements.HOLIDAY_NAME_TEXT_FIELD)
	WebElement holidayNameTextField;

	@FindBy(xpath = HolidaysPageElements.REPEATS_ANUALLY_RADIO_BUTTON)
	WebElement repeatsAnuallyRadioButton;

	@FindBy(xpath = HolidaysPageElements.SAVE_BUTTON)
	WebElement saveButton;

	@FindBy(xpath = HolidaysPageElements.FROM_DATE_TEXT_FIELD)
	WebElement fromDateTextField;

	@FindBy(xpath = HolidaysPageElements.TO_DATE_TEXT_FIELD)
	WebElement toDateTextField;

	@FindBy(xpath = HolidaysPageElements.SEARCH_BUTTON)
	WebElement searchButton;

	@FindBy(xpath = HolidaysPageElements.ONE_RECORD_FOUND_TEXT_LABEL)
	WebElement oneRecordFoundTextLabel;

	/**
	 * The below method is used to create a holiday.
	 * 
	 * @param properties
	 */
	public void createAHoliday(Properties properties) {
		leaveButton.click();
		configureButton.click();
		abstractTest.selectValueFromDropDown(driver, configureItemsList, properties.getProperty("configureItem"));

		if (ganeshChathurthiHoliday.size() > 0) {
			ganeshChathurthiHolidayDeleteIcon.click();
			yesDeleteButton.click();
		}

		abstractTest.scrollToTopOfThepage(driver);
		addButton.click();

		abstractTest.waitUntilPageLoads();

		dayDropDownButton.click();
		abstractTest.selectValueFromDropDown(driver, dayDropDownList, properties.getProperty("holidayType"));
		holidayNameTextField.sendKeys(properties.getProperty("holidayName"));

		holidayDateTextField.sendKeys(properties.getProperty("holidayDate"));

		try {
			repeatsAnuallyRadioButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", repeatsAnuallyRadioButton);
		}

		saveButton.click();

		abstractTest.validateSuccessMessage(driver, By.xpath(CreateEmployeePageElements.SUCCESS_MESSAGE_LABEL));

	}

	/**
	 * The below method is used to validated the holiday details of holiday which is
	 * created.
	 * 
	 * @param properties
	 */
	public void validateCreatedHolidayDetails(Properties properties) {
		abstractTest.waitUntilPageLoads();

		fromDateTextField.sendKeys(Keys.CONTROL + "a");
		fromDateTextField.sendKeys(Keys.BACK_SPACE);

		toDateTextField.sendKeys(Keys.CONTROL + "a");
		toDateTextField.sendKeys(Keys.BACK_SPACE);

		fromDateTextField.sendKeys(properties.getProperty("holidayDate"));

		toDateTextField.sendKeys(properties.getProperty("holidayDate"));

		searchButton.click();

		searchButton.click();

		Assert.assertEquals(true, oneRecordFoundTextLabel.isDisplayed());

	}

}
