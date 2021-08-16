package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qa.base.TestBase;


/**
 * @author sunil.kumar.battula
 * 
 *         This class store the Web Elements and its method
 *
 */

public class LoginPage extends TestBase {

	// Initializing the Page Objects:
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	// Page Factory -- OR-- Identifying the Web Elements

	@FindBy(xpath = "//button[@id='details-button']")
	WebElement btnAdvanced;

	@FindBy(linkText = "Proceed to demo.guru99.com (unsafe)")
	WebElement proceedlink;

	@FindBy(name = "uid")
	WebElement userId;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(name = "btnLogin")
	WebElement loginButton;

	@FindBy(name = "btnReset")
	WebElement resetButton;
	
	@FindBy(xpath = "//table[@class='layout']/tbody//tr//td[@style='color: green']")
	WebElement managerID;
	
	
	
	
	public void clickOnAdvanced() {
		btnAdvanced.click();
	}
	
	public void clickOnProceed() {
		proceedlink.click();
	}
	
	public void enterUsreid(String name) {
		userId.sendKeys(name);
	}
	
	public void enterPassword(String pwd) {
		password.sendKeys(pwd);
	}
	
	public void clickOnLogin() {
		loginButton.click();
	}
	
	public HomePage login(String name, String password) {
	//	test.info("Clicking on the advanced Button");
		clickOnAdvanced();
	//	test.info("Clicking on the proceed Button");
		clickOnProceed();
	//	test.info("Entering username");
		enterUsreid(name);
//		test.info("entering password");
		enterPassword(password);
	//	test.info("Clicking on the login Button");
		clickOnLogin();
		return new HomePage();
				
		}
	

}
