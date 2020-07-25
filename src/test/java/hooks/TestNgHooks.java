package hooks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
import utils.HTMLReporter;

public class TestNgHooks extends WebDriverServiceImpl{

	
	@BeforeSuite
	public void beforeSuite() {
		startReport();
	}

	@BeforeClass
	public void beforeClass() {
		startTestCase(testCaseName, testDescription);		
	}

	@BeforeMethod
	public void beforeMethod() throws FileNotFoundException, IOException {
		init("chrome");
	}
	
	@BeforeMethod()
	public void startLogin() {
		setLanguage("en");
		type(getDriver().findElement(By.id("username")), properties.getProperty("UserName"));
		type(getDriver().findElement(By.id("password")), properties.getProperty("Password"));
		click(getDriver().findElement(By.className("decorativeSubmit")));
	}
	

	@AfterMethod
	public void afterMethod() {
		closeActiveBrowser();
	}

	@AfterSuite
	public void afterSuite() {
		endResult();
	}

	@DataProvider(name="fetchData",parallel=true)
	public  Object[][] getData(){
		return DataInputProvider.getSheet(dataSheetName);		
	}	

	public void init(String browserName) throws FileNotFoundException, IOException {

		startTestcase(nodes);		
		
		if(browserName.contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			
			if(properties.getProperty("Headless").equalsIgnoreCase("true"))
				options.setHeadless(true);
			
			webdriver = new ChromeDriver(options);
		}else {
			WebDriverManager.firefoxdriver().setup();
			webdriver = new FirefoxDriver();
		}
				
		driver = new EventFiringWebDriver(webdriver);
		driver.register(this);

		tlDriver.set(driver);		
		getDriver().manage().window().maximize();

		getDriver().get(properties.getProperty("URL"));
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

}
