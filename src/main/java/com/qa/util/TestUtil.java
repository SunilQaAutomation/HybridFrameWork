package com.qa.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.qa.base.TestBase;

public class TestUtil extends TestBase {

	public static String takeScreenshotAtEndOfTest(WebDriver driver, String screenshotName) throws IOException {

		String datename = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + "/screenShots/" + screenshotName +"_"+ datename + ".png";
		
	
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;

	}

	public static void waitforElemenToBeClickabble(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitforElemenToVisiable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitforElemenToBeVisiable(WebElement element, String text) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.textToBePresentInElement(element, text));

	}

	public static void selecrRadiobtnByisSelected(String text, List<WebElement> element) {
		Iterator<WebElement> it = element.iterator();
		while (it.hasNext()) {
			WebElement ele = it.next();
			if (!(ele.isSelected())) {
				ele.click();
				break;
			}
		}
	}

	public static void selecrRadiobtnBygetText(String text, List<WebElement> element) {
		Iterator<WebElement> it = element.iterator();
		while (it.hasNext()) {
			WebElement ele = it.next();
			System.out.println("text of the radio button" + ele.getText());
			if (ele.getText().equalsIgnoreCase("female")) {
				ele.click();
				break;
			}
		}
	}

	public static void selectDropDownByValue(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

}