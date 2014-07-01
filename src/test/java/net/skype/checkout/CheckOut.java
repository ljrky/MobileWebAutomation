package net.skype.checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.skype.helper.WaitForLoad;
import net.skype.checkout.SignIn;
import com.skype.wallet.HomePage;
import com.skype.wallet.IndexPage;

public class CheckOut {

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

	@Parameters({ "homePage", "skypeName", "skypeCreditPage"})
	@BeforeMethod
	public void beforeMethod(String homePage, String skypeName, String skypeCreditPage){
		//Login Function
		SignIn signIn = new SignIn(driver);
		signIn.clickSignInWithSkypeAccount(homePage);
		signIn.SingInWithSkypeAccount(skypeName, skypeCreditPage);
		SkypeCreditPage skypecreditPage = new SkypeCreditPage(driver);
		skypecreditPage.clickContinue();
	}


	@Parameters({ "cardNumber", "nameOnCard", "ExpiryMonth", "ExpiryYear","cardSecurityCode"})
	@Test()
	public void HomePage(String cardNumber, String nameOnCard, String ExpiryMonth, String ExpiryYear, String cardSecurityCode) {

		CheckoutPage checkoutPage = new CheckoutPage(driver);
		checkoutPage.clickTOS();

//	    Reporter.log("Test start\n", 1);
//	    Reporter.log("1) Navigate to Homepage; \n", 1);
//		IndexPage indexpage = new IndexPage(driver);
//	    WaitForLoad.WaitForElement(com.skype.wallet.IndexPage.link25USDCreditATU,5000, 5);
//	    indexpage.clicklink25USDCreditATU();
//
//		HomePage homepage = new HomePage(driver);
//		Reporter.log("2) Filling Card information; \n", 1);
//		String[] CardInformation = {cardNumber, nameOnCard, ExpiryMonth, ExpiryYear, cardSecurityCode};
//		homepage.fillCreditCardForm(CardInformation);
//
//	    Reporter.log("3) Click Pay now; \n", 1);
//	    homepage.clickPayNow();


	}

}
