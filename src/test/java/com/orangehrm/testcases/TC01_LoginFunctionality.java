package com.orangehrm.testcases;

import org.testng.annotations.Test;

import com.orangehrm.config.BaseClass;
import com.orangehrm.pom.HomePage;
import com.orangehrm.pom.LoginPage;

public class TC01_LoginFunctionality extends BaseClass {
	
	@Test
	public void validateLoginFunctionality() {
		LoginPage log = new LoginPage(driver);
		log.isPageOpened();
		log.logInToApplication();
		HomePage home= new HomePage(driver);
		home.isPageOpened();
		home.logOutOfApplication();
		log.isPageOpened();
	}

}
