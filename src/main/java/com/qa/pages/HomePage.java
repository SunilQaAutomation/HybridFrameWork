package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;
import com.qa.util.TestUtil;

public class HomePage extends TestBase {

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//table[@class='layout']/tbody//tr//td[@style='color: green']")
	WebElement managerID;

	@FindBy(linkText = "New Customer")
	WebElement newCustomer;

	@FindBy(linkText = "New Account")
	WebElement newAccount;

	public String verifyManager() {
		return managerID.getText();

	}

	public String getManagerTitle() {
		TestUtil.waitforElemenToBeVisiable(managerID, verifyManager());
		return verifyManager();
	}

	public void clickOnNewCustomer() {
		newCustomer.click();
	}

	public void clickOnNewAccount() {
		newAccount.click();
	}
}
