package com.skype.wallet;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import com.skype.helper.WaitForLoad;

public class HomePage {
	private WebDriver driver;

	@FindBy(how = How.ID, using = "cardNumber")
	public static WebElement cardNumber;

	@FindBy(how = How.ID, using = "nameOnCard")
	public static WebElement nameOnCard;

	@FindBy(how = How.NAME, using = "expiry_date_month")
	public static WebElement expiryDateMonth;

	@FindBy(how = How.NAME, using = "expiry_date_year")
	public static WebElement expiryDateYear;

	@FindBy(how = How.ID, using = "cvcInput")
	public static WebElement cardSecurityCode;

	@FindBy(how = How.ID, using = "continueButton")
	public static WebElement payNow;

	@FindBy(how = How.ID, using = "cancelFlow")
	public static WebElement cancel;

	@FindBy(how = How.TAG_NAME, using = "iframe")
	public static WebElement iFrame;


	public HomePage(WebDriver driver) {
	    this.driver = driver;
	    ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,
	            120);
	    PageFactory.initElements(finder, this);
	}

	public void enterCardNumber(String testData_CardNumber) {
		cardNumber.click();
		cardNumber.sendKeys(testData_CardNumber);
	}

	public void enterNameOnCard(String testData_NameOnCard) {
		nameOnCard.click();
		nameOnCard.sendKeys(testData_NameOnCard);
	}

	public void selectExpiryMonth(String testData_ExpiryMonth) {
		expiryDateMonth.sendKeys(testData_ExpiryMonth);
	}

	public void selectExpiryYear(String testData_ExpiryYear) {
		expiryDateYear.sendKeys(testData_ExpiryYear);
	}

	public void enterCardSecurityCode(String testData_CardSecurityCode) {
		cardSecurityCode.click();
		cardSecurityCode.sendKeys(testData_CardSecurityCode);
	}

	public void clickPayNow() {
		payNow.click();
	}

	public void selectIframe() {
		driver.switchTo().frame(iFrame);
	}

	public void deselectIframe() {
		driver.switchTo().frame(iFrame);
	}

	public void clickTOS() {
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


	public void fillCreditCardForm(String[] CardInformation) {
		clickTOS();
		selectIframe();
	    selectExpiryMonth(CardInformation[2]);
	    selectExpiryYear(CardInformation[3]);
	    enterCardNumber(CardInformation[0]);
	    enterNameOnCard(CardInformation[1]);
	    enterCardSecurityCode(CardInformation[4]);
		nameOnCard.click();
		deselectIframe();
	}



}
