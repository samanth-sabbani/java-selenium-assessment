package open.source.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

	private WebDriver driver;

	public Properties properties;

	@BeforeTest
	public void launchBrowser() {

		String pathToProject = System.getProperty("user.dir");

		System.setProperty("webdriver.chrome.driver", pathToProject + "\\chromedriver-win64\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(260));
		setDriver(driver);

		try {
			properties = new Properties();
			FileInputStream fileInputStream = new FileInputStream(pathToProject + "\\configs\\environment.properties");
			properties.load(fileInputStream);
			fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@AfterTest
	public void closeBrowserSession() {
		 getDriver().quit();
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

}
