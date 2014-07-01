package com.keyu.testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AddANewBug {

	protected static WebDriver driver;

	@Parameters({ "homePage", "driverPath", "browserType" })
	@BeforeClass()
	public void beforeClass(String homePage, String driverPath, String browserType) {

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


	@Parameters({ "title", "description" })
	@Test()
	public void HomePage(String title, String description) {;
	    Reporter.log("1) Navigate to homepage; \n", 1);
		BugIndex bugIndex = new BugIndex(driver);

		Reporter.log("2) Enter description and title ; \n", 1);
	    WaitForLoad.WaitForElement(com.keyu.testng.BugIndex.title,5000, 5);

	    bugIndex.enterTitle(title);
	    bugIndex.enterDescription(description);

	    Reporter.log("3) Submit the form; \n", 1);
	    bugIndex.submitButton();
	}
}
