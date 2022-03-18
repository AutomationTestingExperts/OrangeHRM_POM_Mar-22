package com.orangehrm.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.orangehrm.reports.Report;
import com.orangehrm.utils.Util;

public class HomePage {
	
	
	WebDriver driver;
	String header = "Dashboard";
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h1")
	private WebElement txt_header;
	
	@FindBy(id="welcome")
	private WebElement lnk_welcome;
	
	@FindBy(linkText="Logout")
	private WebElement lnk_Logout;
	
	public void isPageOpened() {
		try {
			
			Assert.assertEquals(txt_header.getText(), header);
			Report.passTest(driver, "User succesfully logged in");
		}catch (Exception e) {
			Report.failTest(driver, "Login failed!!!!!!!!!!!");
		}
	}
	
	public void logOutOfApplication() {
		Util.click(driver, lnk_welcome, "Welcome");
		Util.click(driver, lnk_Logout, "Logout");
		
	}

}
