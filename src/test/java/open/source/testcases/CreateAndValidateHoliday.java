package open.source.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import open.source.pages.HolidaysPage;
import open.source.pages.LogInPage;
import open.source.utils.AbstractTest;

public class CreateAndValidateHoliday extends AbstractTest {

	@BeforeTest
	public void logIn() {
		
		LogInPage logInPage = new LogInPage(getDriver());

		logInPage.logIntoApplication(properties);
	}

	@Test
	public void createAHoliday() {
		HolidaysPage HolidaysPage = new HolidaysPage(getDriver());

		HolidaysPage.createAHoliday(properties);

	}

	@Test(dependsOnMethods = { "createAHoliday" })
	public void validateCreatedHolidayDetails() {
		HolidaysPage HolidaysPage = new HolidaysPage(getDriver());

		HolidaysPage.validateCreatedHolidayDetails(properties);

	}

}
