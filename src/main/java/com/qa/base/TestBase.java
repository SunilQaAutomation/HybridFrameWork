package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.util.TestUtil;
import com.qa.util.WebEventListener;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static Properties prop;
	public static WebDriver driver;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventlistener;

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	@BeforeTest
	public void setExtentReport() {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/ExtentReport.html");

		htmlReporter.config().setDocumentTitle("Automaton Report");
		htmlReporter.config().setReportName("Functionl Report");
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("HostNmae", "Local");
		extent.setSystemInfo("Operating System", "Winodw 10");
		extent.setSystemInfo("Tester", "Sunny");
		extent.setSystemInfo("Browser", "Chrome");

	}

	@AfterTest
	public void endReport() {
		extent.flush();

	}
	
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "TEST CASE FAILED IS : " + result.getName());
			test.log(Status.FAIL, "TEST CASE FAILED WITH THE ASSERTION : " + result.getThrowable());
			test.log(Status.FAIL, "TEST CASE FAILED IN THE CLASS : " + result.getTestClass());
			
			
			String screenShotPath = TestUtil.takeScreenshotAtEndOfTest(driver, result.getName());
			test.addScreenCaptureFromPath(screenShotPath);

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "TEST CASE PASSED IS : " + result.getName());
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "TEST CASE SKIIPED IS : " + result.getName());
		}

		driver.quit();
	}



	public TestBase() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(
					"C:\\Selenium_WorkSpace\\com.qa.HybridFrameWORK\\src\\main\\java\\com\\qa\\config\\config.properties");
			try {
				prop.load(ip);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void initilization() {

		String browserName = prop.getProperty("browser");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		e_driver = new EventFiringWebDriver(driver);
		try {
			eventlistener = new WebEventListener();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		e_driver.register(eventlistener);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
	}

}
