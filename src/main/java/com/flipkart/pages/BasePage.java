package com.flipkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.flipkart.constants.FrameworkConstants;
import com.flipkart.driver.DriverManager;
import com.flipkart.enums.WaitStrategy;
import com.flipkart.factories.ExplicitWaitFactory;
import com.flipkart.reports.ExtentLogger;

public class BasePage {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, FrameworkConstants.getExplicitWait());
	}

	protected String getPageTitle() {
		return DriverManager.getDriver().getTitle();
	}
	
	public void waitForPageLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
			}
		};

		wait.until(pageLoadCondition);
	}

	protected void click(By by, WaitStrategy waitstrategy ,String elementName) {		
		WebElement element = ExplicitWaitFactory.performExplicitWait(waitstrategy, by);
		element.click();
		ExtentLogger.pass("<b>" + elementName + "</b> is clicked");
	}
	
	protected void clear(By by, WaitStrategy waitStrategy, String elementName) {
		ExplicitWaitFactory.performExplicitWait(waitStrategy, by).clear();
		ExtentLogger.info("Clearing the field  <b>" + elementName + "</b>");
	}
	
	protected void sendKeys(By by, String value, WaitStrategy waitstrategy, String elementName) {
		WebElement element = ExplicitWaitFactory.performExplicitWait(waitstrategy, by);
		element.sendKeys(value);
		ExtentLogger.pass("<b>" + value + "</b> is entered successfully in <b>" + elementName + "</b>");
	}

	protected void clearAndSendKeys(By by, String value, WaitStrategy waitstrategy, String elementName) {
		WebElement element = ExplicitWaitFactory.performExplicitWait(waitstrategy, by);
		element.clear();
		element.sendKeys(value);
		ExtentLogger.pass("<b>" + value + "</b> is entered successfully in <b>" + elementName + "</b>");
	}
	
	protected static String getElementText(By by, WaitStrategy waitStrategy) {
		WebElement element = ExplicitWaitFactory.performExplicitWait(waitStrategy, by);
		ExtentLogger.info("Element Text: " + element.getText());
		return element.getText();
	}
	
	protected static WebElement getElement(By by, WaitStrategy waitStrategy) {
		return ExplicitWaitFactory.performExplicitWait(waitStrategy, by);
	}

}
