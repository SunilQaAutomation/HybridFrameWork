package com.qa.testCases;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import com.qa.base.TestBase;
import com.qa.pages.LoginPage;


public class LoginTest extends TestBase {

	public LoginPage loginpage;

	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initilization();
		loginpage = new LoginPage();

	}

	@Test // passing the username and Password thru the properties file
	public void loginVerify() {

		test = extent.createTest("loginVerify");
		loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test // passing the username and Password thru the properties file
	public void loginVerif2() {

		test = extent.createTest("loginVerify2");
		loginpage.login(prop.getProperty("username1"), prop.getProperty("password1"));
	}

	// @Parameters({"USERNAME","PASSWORD"})
	// @Test // passing the username and Password thru as a parameter thruogh
	// testng.xml file

	

}
