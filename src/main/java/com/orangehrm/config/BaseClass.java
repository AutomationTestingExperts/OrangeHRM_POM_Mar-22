package com.orangehrm.config;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentTest;
import com.orangehrm.reports.Report;
import com.orangehrm.utils.Util;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;
	public static ExtentTest report;
	
	@BeforeSuite
	public void beforeSuite() {
		
	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		String browser = Util.getProperty("browser");
		String url = Util.getProperty("url");
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "D:\\Workspace_Java\\Workspace_OnlineClasses\\com.orangehrm.Mar21\\Drivers\\chromedriver.exe");
//			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}else if(browser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else {
			System.out.println("Invalid browser!!!!!!!!!!!!");
		}
		report = Report.startReport(System.getProperty("user.dir")+"\\Reports\\Report.html", method.getName(), "").assignCategory("Web").assignAuthor("QA xperts");
		report.info("<<<<<<<<<< Execution Started >>>>>>>>>>");
		report.pass(browser + " launched!!!!!!");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(url);
//		log.info(browser+ " launched.");
//		log.info(url+" opened.");
		report.pass(url+" opened.");
	}

	@AfterMethod
	public void afterMethod() {
		if(driver!=null) {
			driver.close();
		}
		Report.getReporter(System.getProperty("user.dir")+"\\Reports\\Report.html").flush();
	}

	@AfterSuite
	public void afterSuite() {
		
	}

}
