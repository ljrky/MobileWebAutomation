package net.skype.checkout;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import com.thoughtworks.selenium.webdriven.commands.WaitForPageToLoad;

public class CheckoutPage {

	private WebDriver driver;

	@FindBy(how = How.ID, using = "continueButton")
	public static WebElement ContinueButton;

	@FindBy(how = How.ID, using = "cancelFlow")
	public static WebElement CancelButton;


	public CheckoutPage(WebDriver driver) {
	    this.driver = driver;
	    ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,
	            120);
	    PageFactory.initElements(finder, this);
	}


	public void clickTOS() {
//		WaitForPageToLoad
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		String executionScript = "return $(" + "\"input[name=agreeTos]:visible, input[name=termsOfService]:visible\"" + ").length;";
		Long numOfTOS = (Long) (executor.executeScript(executionScript));

		for(int i = 0; i < numOfTOS; i++){
			String isSelectScript = "return $(" + "\"input[name=agreeTos]:visible, input[name=termsOfService]:visible\"" + ")[" + i + "].checked";
			Boolean tosSelect = (Boolean)executor.executeScript(isSelectScript);

			if(tosSelect == false){
				String script = "$(" + "\"input[name=agreeTos]:visible, input[name=termsOfService]:visible\"" + ")[" + i + "].click();";
				executor.executeScript(script);
			}
		}
	}
}
