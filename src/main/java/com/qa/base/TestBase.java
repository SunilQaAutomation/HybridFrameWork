package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
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
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
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
	//public static String browserName;
	

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	// This method is used to create the HTML Extent Report and seting the
	// Environment Details
	
	@BeforeTest
	public void setExtentReport() {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/ExtentReport.html");

		htmlReporter.config().setEncoding("utf-8");
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

	// This method is used to flush the report at the end of test

	@AfterTest
	public void endReport() {
		extent.flush();

	}

	// This method is used to update the stauts at the end of the test in the Extent
	// Report
	
   @AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		String methodName = result.getMethod().getMethodName();
		String className = result.getTestClass().getName();

		if (result.getStatus() == ITestResult.FAILURE) {

			String errorMessage = result.getThrowable().getMessage();
			String exceptoinMessage = Arrays.toString(result.getThrowable().getStackTrace());

			test.fail("<details><summary><b><font color=red><u>Exception Occured, Click on to see details:"
					+ "</u></font></b></summary>+" + errorMessage.replaceAll(",", "<br>") + "\n"
					+ exceptoinMessage.replaceAll(",", "<br>") + "</details> \n");

			String screenShotPath = TestUtil.takeScreenshotAtEndOfTest(driver, result.getName());
			try {
				test.fail("<b><font color=red>" + "Screen shot of failure " + "</font></b>",
						MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build());
			} catch (IOException e) {
				test.fail("Test Failed, Cannot attach the Screenshot");
			}

			// test.addScreenCaptureFromPath(screenShotPath);

			String logText = "<b>Test Method " + methodName + " is Failed form the class " + className + "</b>";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
			test.log(Status.FAIL, m);

		} else if (result.getStatus() == ITestResult.SUCCESS) {

			String logText = "<b>Test Method " + methodName + " is Successful form the class " + className + "</b>";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
			test.log(Status.PASS, m);

		} else if (result.getStatus() == ITestResult.SKIP) {
			String logText = "<b>Test Method " + methodName + " is Skipped form the class " + className + "</b>";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.ORANGE);
			test.log(Status.SKIP, m);
		}

		driver.quit();
	}

	// This Constructor helps to initilise the properties file
	public TestBase() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(
					"C:\\Selenium_WorkSpace\\HybridFrameWork\\src\\main\\java\\com\\qa\\config\\config.properties");
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

	// This Method helps to initiase the Browser and also create the WebDriver
	// Listeners
	public static void initilization() {

		String  browserName = prop.getProperty("browser");

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
