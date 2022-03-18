package com.orangehrm.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.orangehrm.reports.Report;
import com.orangehrm.utils.Util;

public class LoginPage {
	WebDriver driver;
	String title = "OrangeHRM";
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="txtUsername")
	private WebElement input_username;
	
	@FindBy(id="txtPassword")
	private WebElement input_password;
	
	@FindBy(id="btnLogin")
	private WebElement  btn_Login;
	
	
	
	
	public void isPageOpened() {
		try {
			Assert.assertEquals(driver.getTitle(), title);
			Report.passTest(driver, "Login page succesfully launched");
		}catch (Exception e) {
			Report.failTest(driver, "Application launch failed!!!!!!!!!!!");
		}
	}
	
	
	public void logInToApplication() {
		Util.sendKeys(driver, input_username, Util.getProperty("username"), "User name");
		Util.sendKeys(driver, input_password, Util.getProperty("password"), "Password");
		Util.click(driver, btn_Login, "Login");
	}
	
	
	

}
