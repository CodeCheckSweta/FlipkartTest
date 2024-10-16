package com.flipkart.factories;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.flipkart.driver.DriverManager;
import com.flipkart.enums.WaitStrategy;
import com.flipkart.pages.BasePage;

public final class ExplicitWaitFactory extends BasePage {

	private ExplicitWaitFactory() {
	}

	public static WebElement  performExplicitWait(WaitStrategy waitstrategy, By by) {
		WebElement element = null;
		if(waitstrategy == WaitStrategy.CLICKABLE) {
			element = new WebDriverWait(driver, Duration.ofSeconds(20))
					.until(ExpectedConditions.elementToBeClickable(by));
		} else if(waitstrategy == WaitStrategy.PRESENCE) {
			element = new WebDriverWait(driver, Duration.ofSeconds(20))
					.until(ExpectedConditions.presenceOfElementLocated(by));
		} else if(waitstrategy == WaitStrategy.VISIBLE) {
			element = new WebDriverWait(driver, Duration.ofSeconds(20))
					.until(ExpectedConditions.visibilityOfElementLocated(by));
		} else if(waitstrategy == WaitStrategy.NONE) {
			System.out.println("No Wait strategy defined");
			element = DriverManager.getDriver().findElement(by);
		}  
		return element;
	}
}
