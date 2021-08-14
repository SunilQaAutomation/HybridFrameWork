package com.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;
import com.qa.util.TestUtil;

public class NewCustomerPage extends TestBase {

	HomePage homepage = new HomePage();
		
	public NewCustomerPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//table[@class='layout']//tr//p")
	WebElement newCustomerTitle;
	
	@FindBy(name = "name")
	WebElement customerName;

	@FindBy(xpath = "//input[@name='rad1']")
	List<WebElement> gender;

	@FindBy(xpath = "//input[@name='dob']")
	WebElement dob;

	@FindBy(xpath = "//textarea[@name='addr']")
	WebElement address;

	@FindBy(xpath = "//input[@name='city']")
	WebElement city;

	@FindBy(xpath = "//input[@name='state']")
	WebElement state;

	@FindBy(xpath = "//input[@name='pinno']")
	WebElement pin;

	@FindBy(xpath = "//input[@name='telephoneno']")
	WebElement telephoneno;

	@FindBy(xpath = "//input[@name='emailid']")
	WebElement emailid;

	@FindBy(xpath = "//input[@name='password']")
	WebElement password;

	@FindBy(xpath = "//input[@name='sub']")
	WebElement btnSubmit;

	@FindBy(xpath = "//table[@id='customer']/tbody/tr[1]/td/p")
	WebElement txtRegisteredCustomer;

	@FindBy(xpath = "//table[@id='customer']/tbody/tr[4]/td[2]")
	WebElement customerID;
	
	public String getNewCustomerTitle() {
		TestUtil.waitforElemenToVisiable(newCustomerTitle);
		return newCustomerTitle.getText();
	}

	public void enterCustomerName(String name) {
		customerName.sendKeys(name);
	}

	public void enterDob(String date) {
		dob.sendKeys(date);
	}

	public void enterAddress(String add) {
		address.sendKeys(add);
	}

	public void enterCity(String cit) {
		city.sendKeys(cit);
	}

	public void enterState(String stat) {
		state.sendKeys(stat);
	}

	public void enterPin(String pincode) {
		pin.sendKeys(pincode);
	}

	public void enterPhone(String telephone) {
		telephoneno.sendKeys(telephone);
	}

	public void enteMail(String mail) {
		emailid.sendKeys(mail);
	}

	public void enterPassword(String pwd) {
		password.sendKeys(pwd);
	}

	public void clickOnSubmit() {
		btnSubmit.click();
	}

	public String getTileOfRegisteredCustomer() {
		return txtRegisteredCustomer.getText();
	}

	public String getCustomerID() {
			return customerID.getText();
	}


	public void newCustomer(String cName, String sex, String date, String add, String city, String state,
			String pincode, String telephone, String mail, String password) {

		homepage.clickOnNewCustomer();
		TestUtil.waitforElemenToVisiable(newCustomerTitle);
		enterCustomerName(cName);
		TestUtil.selecrRadiobtnByisSelected(sex, gender);
		enterDob(date);
		enterAddress(add);
		enterCity(city);
		enterState(state);
		enterPin(pincode);
		enterPhone(telephone);
		enteMail(mail);
		enterPassword(password);
		clickOnSubmit();
		TestUtil.waitforElemenToBeVisiable(txtRegisteredCustomer, getTileOfRegisteredCustomer());
	

	}

}
