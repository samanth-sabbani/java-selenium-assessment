package open.source.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import open.source.pages.CreateEmployeePage;
import open.source.pages.LogInPage;
import open.source.utils.AbstractTest;

public class EmployeeCreationAndValidationTest extends AbstractTest {

	@BeforeTest
	public void logIn() {
		LogInPage logInPage = new LogInPage(getDriver());

		logInPage.logIntoApplication(properties);
	}

	@Test
	public void createAnEmployee() {
		CreateEmployeePage createEmployeePage = new CreateEmployeePage(getDriver());

		createEmployeePage.createAnEmployee(properties);

	}

	@Test(dependsOnMethods = { "createAnEmployee" })
	public void validateCreatedEmployeeDetails() {
		CreateEmployeePage createEmployeePage = new CreateEmployeePage(getDriver());

		createEmployeePage.validateCreatedEmployeeDetails(properties);

	}

}
