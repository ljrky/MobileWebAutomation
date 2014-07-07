package net.skype.mock;



import java.net.URL;

import io.selendroid.SelendroidCapabilities;
import io.selendroid.SelendroidConfiguration;
import io.selendroid.SelendroidDriver;
import io.selendroid.SelendroidLauncher;
import net.skype.mock.HomePage;
import net.skype.mock.IndexPage;

import com.skype.helper.WaitForLoad;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.net.Urls;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.uiautomation.ios.IOSCapabilities;
import org.uiautomation.ios.client.uiamodels.impl.RemoteIOSDriver;
import org.uiautomation.ios.client.uiamodels.impl.augmenter.IOSDriverAugmenter;


public class HomePageTest {

	  private SelendroidLauncher selendroidServer = null;
	  private WebDriver driver = null;

	@Parameters({ "homePage", "driverPath", "browserType" })
	@BeforeClass()
	public void beforeClass(String homePage, String driverPath, String browserType) throws Exception {
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
		case "Android":
		    if (selendroidServer != null) {
		        selendroidServer.stopSelendroid();
		      }
		      SelendroidConfiguration config = new SelendroidConfiguration();

		      selendroidServer = new SelendroidLauncher(config);
		      selendroidServer.lauchSelendroid();

		      DesiredCapabilities caps = SelendroidCapabilities.android();

		      driver = new SelendroidDriver(caps);
			break;
		case "WP":
			driver = new RemoteWebDriver(new URL("http://192.168.11.111:8080"), DesiredCapabilities.internetExplorer());
			break;
		case "iOS":
//			driver = IOSDriverAugmenter.getIOSDriver(new RemoteWebDriver(new URL("http://localhost:5555/wd/hub"), DesiredCapabilities.iphone()));
//			driver = new RemoteWebDriver(new URL("http://192.168.11.144:3001/wd/hub"), DesiredCapabilities.iphone());

			//1st solution
//			DesiredCapabilities safari = IOSCapabilities.iphone("safari");
//			driver = new RemoteWebDriver(new URL("http://localhost:5555/wd/hub"), safari);

			//2nd solution
			driver = IOSDriverAugmenter.getIOSDriver(new RemoteWebDriver(new URL("http://localhost:5555/wd/hub"), DesiredCapabilities.iphone()));

			break;
		}
		driver.get(homePage);
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	@Parameters({ "cardNumber", "nameOnCard", "ExpiryMonth", "ExpiryYear","cardSecurityCode"})
	@Test()
	public void HomePage(String cardNumber, String nameOnCard, String ExpiryMonth, String ExpiryYear, String cardSecurityCode) {
	    Reporter.log("Test start\n", 1);
	    Reporter.log("1) Navigate to Homepage; \n", 1);
		IndexPage indexpage = new IndexPage(driver);
	    WaitForLoad.WaitForElement(net.skype.mock.IndexPage.link25USDCreditATU,5000, 5);
	    indexpage.clicklink25USDCreditATU();

		HomePage homepage = new HomePage(driver);
		Reporter.log("2) Filling Card information; \n", 1);
		String[] CardInformation = {cardNumber, nameOnCard, ExpiryMonth, ExpiryYear, cardSecurityCode};
	    WaitForLoad.WaitForPageToLoad(net.skype.mock.HomePage.iFrame);
		homepage.fillCreditCardForm(CardInformation);

	    Reporter.log("3) Click Pay now; \n", 1);
	    homepage.clickPayNow();
	}

}
