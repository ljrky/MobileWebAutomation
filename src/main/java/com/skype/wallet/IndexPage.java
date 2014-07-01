package com.skype.wallet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

public class IndexPage {

	private WebDriver driver;

	@FindBy(how = How.LINK_TEXT, using = "25USD credit + ATU")
	public static WebElement link25USDCreditATU;

	public IndexPage(WebDriver driver) {
	    this.driver = driver;
	    ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,
	            120);
	    PageFactory.initElements(finder, this);

	}

	public void clicklink25USDCreditATU() {
		link25USDCreditATU.click();
	}

}
