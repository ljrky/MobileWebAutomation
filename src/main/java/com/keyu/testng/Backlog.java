package com.keyu.testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

public class Backlog {

	private WebDriver driver;

	@FindBy(how = How.NAME, using = "title")
	public static WebElement title;

	@FindBy(how = How.NAME, using = "description")
	public static WebElement description;


	@FindBy(how = How.NAME, using = "submit")
	public static WebElement submit;

	public Backlog(WebDriver driver) {
	    this.driver = driver;
	    ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,
	            120);
	    PageFactory.initElements(finder, this);

	}

	public void enterTitle(String Title) {
		title.clear();
		title.sendKeys(Title);
	}

	public void enterDescription(String Description) {
		description.clear();
		description.sendKeys(Description);
	}

	public void submitButton() {
		submit.click();
	}
}
