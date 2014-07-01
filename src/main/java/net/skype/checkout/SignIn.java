package net.skype.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import com.skype.helper.WaitForLoad;

public class SignIn {
	private WebDriver driver;

	@FindBy(how = How.ID, using = "skypeLogin")
	public static WebElement SignInWithSkypeAccount;

	@FindBy(how = How.CLASS_NAME, using = "bottom")
	public static WebElement FooterOfThePage;

	@FindBy(how = How.ID, using = "username")
	public static WebElement Username;

	@FindBy(how = How.ID, using = "password")
	public static WebElement Password;

	@FindBy(how = How.ID, using = "signIn")
	public static WebElement SignIn;


	public SignIn(WebDriver driver) {
	    this.driver = driver;
	    ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,
	            120);
	    PageFactory.initElements(finder, this);
	}

	public void clickSignInWithSkypeAccount(String testData_Homepage) {
		WaitForLoad.WaitForPageToLoad(FooterOfThePage);
		if(driver.findElement(By.className("isNotAuthenticated")).isDisplayed()){
		    WaitForLoad.WaitForElement(SignInWithSkypeAccount);
			SignInWithSkypeAccount.click();
		}
//		if(driver.findElement(By.className("isAuthenticated")).isDisplayed()){
//			driver.get(testData_Homepage);
//		}
	}


	public void SingInWithSkypeAccount(String testData_UserName, String testData_SkypeCreditPage) {
	    WaitForLoad.WaitForElement(Username);
	    Username.click();
	    Username.sendKeys(testData_UserName);
	    Password.click();
	    Password.sendKeys(testData_UserName);
	    SignIn.click();

	    //verify sign success
		WaitForLoad.WaitForPageToLoad(FooterOfThePage);
		if(driver.findElement(By.className("isAuthenticated")).isDisplayed()){
			driver.get(testData_SkypeCreditPage);
		}
	}
}
