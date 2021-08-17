package com.qa.testCases;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ParallelTest {

	public WebDriver driver;

	@BeforeMethod
	public void initilization() {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.get("https://www.makemytrip.com/");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test1() {
		System.out.println("thread name is: " + Thread.currentThread().getId());
		driver.findElement(By.xpath("//li[@class='menu_Hotels']//span[2]")).click();
	}

	@Test
	public void test2() {
		System.out.println("thread name is: " + Thread.currentThread().getId());

		driver.findElement(By.xpath("//span[normalize-space()='Flights']")).click();
	}

	@Test
	public void test3() {
		System.out.println("thread name is: " + Thread.currentThread().getId());
		driver.findElement(By.xpath("//span[normalize-space()='Villas & Apts']")).click();
	}

	@Test
	public void test4() {
		System.out.println("thread name is: " + Thread.currentThread().getId());

		driver.findElement(By.xpath("//span[normalize-space()='Holiday Packages']")).click();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
