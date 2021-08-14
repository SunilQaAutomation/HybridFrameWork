package com.qa.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.pages.NewAccountPage;

public class NewAccountTest extends TestBase {

	LoginPage loginpage;
	NewAccountPage newaccountpage;
	HomePage homepage;

	NewAccountTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initilization();
		loginpage = new LoginPage();
		newaccountpage = new NewAccountPage();
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void createNewAccount() {
		homepage.clickOnNewAccount();
		Assert.assertEquals(newaccountpage.getNewAccountTitle(), "Add new account form");
		newaccountpage.createAccount("82776", "10000", "Current");
		Assert.assertEquals(newaccountpage.getTileOfAccountCreated(), "Account Generated Successfully!!!");
		String custId = newaccountpage.getCustomerID();
		String accountId = newaccountpage.geAccountID();

		System.out.println("Customer id is" + custId);
		System.out.println("Account id is" + accountId);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
}
