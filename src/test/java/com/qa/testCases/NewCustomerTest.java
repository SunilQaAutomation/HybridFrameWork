package com.qa.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.pages.NewCustomerPage;

public class NewCustomerTest extends TestBase {

	LoginPage loginpage;
	NewCustomerPage newCustomePage;
	HomePage homepage;

	public NewCustomerTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {

		initilization();
		loginpage = new LoginPage();
		homepage = new HomePage();
		newCustomePage = new NewCustomerPage();
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void createNewCustomerTest() {
		homepage.clickOnNewCustomer();
		Assert.assertEquals(newCustomePage.getNewCustomerTitle(), "Add New Customer");
		try {
			newCustomePage.newCustomer("Ramesh", "female", "07/07/2021", "PUNE", "MUMBAI", "MH", "503456",
					"0123456789", "def.rtyu@gmail.com", "password123$");
		} catch (Exception e) {
			System.out.println(e);
		}
		Assert.assertEquals(newCustomePage.getTileOfRegisteredCustomer(), "Customer Registered Successfully!!!");

		String custid = newCustomePage.getCustomerID();
		System.out.println("Customer id: " + custid);
	}

	 @AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
