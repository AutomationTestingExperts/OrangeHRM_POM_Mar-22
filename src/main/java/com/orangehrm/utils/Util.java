package com.orangehrm.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangehrm.reports.Report;

import org.apache.commons.io.FileUtils;

public class Util {
	
	public static String getProperty(String key) {
		Properties prop = null;
		try {
			File file = new File(System.getProperty("user.dir") + "\\config.properties");
			FileInputStream fi = new FileInputStream(file);
			prop = new Properties();
			prop.load(fi);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (String) prop.get(key);
	}
	
	
	public static String captureScreenShot(WebDriver driver) {
		String dest = "";
		try {
			TakesScreenshot screen = (TakesScreenshot) driver;
			File src = screen.getScreenshotAs(OutputType.FILE);
			dest = System.getProperty("user.dir") + "\\Reports\\screenshots.png";
			File target = new File(dest);
			FileUtils.copyFile(src, target);
			return dest;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dest;
	}
	
	public static void sendKeys(WebDriver driver, WebElement element, String text, String desc) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(element));
			element.sendKeys(text);
			Report.passTest("User entered " + text + " into " + desc);
		}catch (Exception e) {
			Report.failTest(driver, "User unable to enter text!!! "+e.getMessage());
		}
	}
	
	public static void click(WebDriver driver, WebElement element, String desc) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			Report.passTest("User clicked on " + desc);
		}catch (Exception e) {
			Report.failTest(driver, "User unable click on element!!! "+e.getMessage());
		}
	}
	
	

}
