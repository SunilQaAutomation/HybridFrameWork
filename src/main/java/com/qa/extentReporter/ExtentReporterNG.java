package com.qa.extentReporter;

import java.io.IOException;
import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
/*
 *	This class implementes the ITestListener and used to implements extent reports
 *	using the Listeners.
 *  We can generate the Extnet Report either throw the Lister or through the custom generation(which is done in TestBase)
 *  
 */

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.base.TestBase;
import com.qa.base.TestBase2;
import com.qa.util.TestUtil;

public class ExtentReporterNG extends TestBase2 implements ITestListener {
	
	private static ExtentReports extent;

	public static ExtentReports createInstance() {

		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "/Reports/ExtentReport.html");

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
		return extent;

	}
		
	public static ExtentReports extentR=	ExtentManger.createInstance();
	public static ThreadLocal<ExtentTest> extentTest= new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest test = extentR.createTest(result.getTestClass().getName() + " :: " +
		 result.getMethod().getMethodName());
		 extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String logText = "<b>Test Method " + result.getMethod().getMethodName() + " is Successful form the class "
				+ result.getTestClass().getName() + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		extentTest.get().log(Status.PASS, m);

	}

	@Override
	public void onTestFailure(ITestResult result) {
		String errorMessage = result.getThrowable().getMessage();
		String exceptoinMessage = Arrays.toString(result.getThrowable().getStackTrace());
	

		extentTest.get().fail("<details><summary><b><font color=red><u>Exception Occured, Click on to see details:"
				+ "</u></font></b></summary>+" + errorMessage.replaceAll(",", "<br>") + "\n"
				+ exceptoinMessage.replaceAll(",", "<br>") + "</details> \n");
		
			 
		String screenShotPath = TestUtil.takeScreenshotAtEndOfTest(driver, result.getName());
		try {
			extentTest.get().fail("<b><font color=red>" + "Screen shot of failure " + "</font></b>",
					MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build());
		} catch (IOException e) {
			extentTest.get().fail("Test Failed, Cannot attach the Screenshot");
		}

		String logText = "<b>Test Method " + result.getMethod().getMethodName() + " is Failed form the class "
				+ result.getTestClass().getName() + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		extentTest.get().log(Status.FAIL, m);

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String logText = "<b>Test Method " + result.getMethod().getMethodName() + " is Skipped form the class "
				+ result.getTestClass().getName() + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.ORANGE);
		extentTest.get().log(Status.SKIP, m);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		if (extent != null) {
			extent.flush();
			driver.quit();
		}

	}

}
