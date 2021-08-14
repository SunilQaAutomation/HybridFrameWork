package com.qa.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.qa.base.TestBase;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.util.TestUtil;

public class HomePageTest extends TestBase {

	LoginPage longinpage;
	HomePage homepage;

	public HomePageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initilization();
		longinpage = new LoginPage();
		homepage = new HomePage();
		homepage= longinpage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void verifyManager1() {
		test = extent.createTest("verifyManager3");
		test.createNode("With the Valid inputs");
		test.info("Verifying login manger title");
		Assert.assertEquals(homepage.getManagerTitle(), "Manger Id : mngr347104");
		test.info("Manager is verified");
	}
	
	
	@Test
	public void verifyManager2() {
		test = extent.createTest("verifyManager2");
		test.createNode("with Invalid inputs");
		test.info("Verifying login manger title");
		Assert.assertEquals(homepage.getManagerTitle(), "Manger Id : mngr347105");
		test.info("Manager is verified");

	}
	

}
