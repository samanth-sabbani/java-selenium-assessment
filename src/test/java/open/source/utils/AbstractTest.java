package open.source.utils;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AbstractTest extends BaseTest {

	public String generateRandomUsername(int length) {

		String alphabets = "abcdefghijklmnop";
		StringBuffer randomString = new StringBuffer(length);
		Random random = new Random();

		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(alphabets.length());
			char randomChar = alphabets.charAt(randomIndex);
			randomString.append(randomChar);
		}
		return randomString.toString();
	}

	public String generateRandomPassword(int length) {

		String alphabets = "abcdefghijklmnop1234567890";
		StringBuffer randomString = new StringBuffer(length);
		Random random = new Random();

		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(alphabets.length());
			char randomChar = alphabets.charAt(randomIndex);
			randomString.append(randomChar);
		}
		return randomString.toString();
	}

	public void waitUntilElementIsClickable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void selectValueFromDropDown(WebDriver driver, List<WebElement> elements, String value) {
		waitUntilElementIsClickable(driver, elements.get(1));
		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i).getText().equalsIgnoreCase(value)) {
				elements.get(i).click();
				break;
			}
		}
	}

	public void validateSuccessMessage(WebDriver driver, By element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));

		boolean isSuccessMessageDisplayed = driver.findElement(element).isDisplayed();

		Assert.assertEquals(true, isSuccessMessageDisplayed);
	}

	public void waitUntilPageLoads() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void scrollToTopOfThepage(WebDriver driver) {
		JavascriptExecutor je = (JavascriptExecutor) driver;

		je.executeScript("window.scrollTo(0, 0);");
	}
}
