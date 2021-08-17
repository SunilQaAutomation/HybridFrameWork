package com.qa.extentReporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentManger  {
	
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
}
