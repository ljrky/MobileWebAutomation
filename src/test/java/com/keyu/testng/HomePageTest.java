package com.keyu.testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class HomePageTest {

	protected static WebDriver driver;

	@Parameters({ "homePage", "driverPath", "browserType" })
	@BeforeClass()
	public void beforeClass(String homePage, String driverPath, String browserType) {
//		System.setProperty("webdriver.chrome.driver", driverPath);
//		System.setProperty("webdriver.ie.driver", driverPath);
//	    driver = new InternetExplorerDriver();
//	    driver.get(homePage);

		switch(browserType){
		case "InternetExplorerDriver" :
			System.setProperty("webdriver.ie.driver", driverPath);
			driver = new InternetExplorerDriver();
			break;
		case "ChromeDriver":
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver = new ChromeDriver();
			break;
		}
		driver.get(homePage);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}


	@Parameters({ "email", "password" })
	@Test()
	public void HomePage(String email, String password) {
	    Reporter.log("Podio login test \n", 1);
	    Reporter.log("1) Navigate to http://podio.com/login page; \n", 1);
		HomePage homepage = new HomePage(driver);
		Reporter.log("2) Enter valid login and password (shouldn't be hardcoded); \n", 1);
	    WaitForLoad.WaitForElement(com.keyu.testng.HomePage.submitButton,5000, 5);
	    homepage.enterEmail(email);
	    homepage.enterPassword(password);
	    Reporter.log("3) Submit the form; \n", 1);
	    homepage.submitButton();
	    Reporter.log("4) Verify that you're successfully logged in; \n", 1);
	    WaitForLoad.WaitForElement(com.keyu.testng.HomePage.homeStream,5000, 5);
	    homepage.homeStreamIsDisplayed();
	    Reporter.log("5) Verify that posts in Activity stream block are loaded. \n", 1);
	}
}
