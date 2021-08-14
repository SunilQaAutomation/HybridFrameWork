package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;
import com.qa.util.TestUtil;

public class NewAccountPage extends TestBase {

	HomePage homepage = new HomePage();

	public NewAccountPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//table//tr//p")
	WebElement newAccountTitle;

	@FindBy(name = "cusid")
	WebElement custID;

	@FindBy(xpath = "//select[@name='selaccount']")
	WebElement acctType;

	@FindBy(name = "inideposit")
	WebElement initialDeposit;

	@FindBy(name = "button2")
	WebElement submitButton;

	@FindBy(xpath = "//table[@align='center']//tr//td//p")
	WebElement actPageTitle;

	@FindBy(xpath = "//table//tr//p")
	WebElement txtRegisteredAccount;

	@FindBy(xpath = "//table//tr[5]/td[2]")
	WebElement customerID;

	@FindBy(xpath = "//table//tr[4]/td[2]")
	WebElement accountID;

	public String getNewAccountTitle() {
		TestUtil.waitforElemenToVisiable(newAccountTitle);
		return newAccountTitle.getText();
	}

	public void enterCutomerID(String customerID) {
		custID.sendKeys(customerID);
	}

	public void enterDeposit(String amount) {
		initialDeposit.sendKeys(amount);
	}

	public void clickOnSubmitButton() {
		submitButton.click();
	}

	public String getTileOfAccountCreated() {
		return txtRegisteredAccount.getText();
	}

	public String getCustomerID() {
		return customerID.getText();
	}

	public String geAccountID() {
		return accountID.getText();
	}

	public void createAccount(String cstomerID, String deposit, String value) {
		homepage.clickOnNewAccount();
		enterCutomerID(cstomerID);
		enterDeposit(deposit);
		TestUtil.selectDropDownByValue(acctType, value);
		clickOnSubmitButton();
		TestUtil.waitforElemenToVisiable(txtRegisteredAccount);

	}
}
