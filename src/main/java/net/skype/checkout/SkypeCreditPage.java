package net.skype.checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import com.skype.helper.WaitForLoad;

public class SkypeCreditPage {

	private WebDriver driver;

	@FindBy(how = How.ID, using = "productSelection")
	public static WebElement Continue;

	@FindBy(how = How.CLASS_NAME, using = "cancelFlow")
	public static WebElement Cancel;

	@FindBy(how = How.CLASS_NAME, using = "bottom")
	public static WebElement FooterOfThePage;

	public SkypeCreditPage(WebDriver driver) {
	    this.driver = driver;
	    ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,
	            120);
	    PageFactory.initElements(finder, this);
	}

	public void clickContinue() {
		WaitForLoad.WaitForPageToLoad(FooterOfThePage);
		WaitForLoad.WaitForElement(Continue);
		Continue.click();
	}

}
