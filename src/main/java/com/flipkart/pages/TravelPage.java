package com.flipkart.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.flipkart.enums.WaitStrategy;
import com.flipkart.reports.ExtentLogger;

public class TravelPage extends BasePage {

	public TravelPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public String getTitle() {
		return getPageTitle();
	}

	private final By departureCity = By.name("0-departcity");
	private final By arrivalCity = By.name("0-arrivalcity");
	private final By departureDate = By.name("0-datefrom");
	private final By nextMonthBtn = By.xpath("//div[@class='au1mSN']//button[@class='R0r93E']");
	private final By searchBtn = By.xpath("//span[text()='SEARCH']");

	public TravelPage searchFlights(String source, String dest, String month_year, String day) {
		clearAndSendKeys(departureCity, source, WaitStrategy.VISIBLE, "Departure City");
		selectCityFromDropdown(source);
		clearAndSendKeys(arrivalCity, dest, WaitStrategy.VISIBLE, "Arrival City");
		selectCityFromDropdown(dest);
		selectDate(month_year, day);
		click(searchBtn, WaitStrategy.CLICKABLE, "Search Button");
		return this;
	}

	public void selectCityFromDropdown(String cityName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement cityOption =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'"+cityName+"')]")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", cityOption);
	}

	public void selectDate(String month_year, String day) {
		click(departureDate, WaitStrategy.CLICKABLE, "Departure Date");

		// Loop until the correct month is found
	    while(!isMonthVisible(month_year)) {
	        click(nextMonthBtn, WaitStrategy.CLICKABLE, "Next Month Button");
	    }

	    // Select the day once the correct month is visible
	    selectDay(day);
	}
	
	private boolean isMonthVisible(String targetMonthYear) {
	    List<WebElement> visibleMonths = driver.findElements(By.xpath("//th[@colspan='5']/div[@class='_1w7bXX']"));
	    
	    for (WebElement visibleMonth : visibleMonths) {
	        if (visibleMonth.getText().equals(targetMonthYear)) {
	            return true;
	        }
	    }
	    return false;
	}

	private void selectDay(String day) {
	    List<WebElement> days = driver.findElements(By.xpath("//button[@class='pl8ttv' and not(@disabled)]"));

	    for (WebElement dayElement : days) {
	        if (dayElement.getText().equals(day)) {
	            dayElement.click();
	            ExtentLogger.info(dayElement.getText()+" is clicked");
	            return;
	        }
	    }
	}

	public double getCheapestPrice() {
		List<WebElement> priceElements = driver.findElements(By.xpath("//div[@class='O+irE2']"));
		
		List<Double> prices = new ArrayList<>();
		for(WebElement priceElement:priceElements) {
			String priceText = priceElement.getText();
			if(!priceText.isEmpty()) {
				prices.add(Double.parseDouble(priceText));
			}
		}
		
		if(!prices.isEmpty()) {
			return Collections.min(prices);
		}else {
			throw new RuntimeException("No flight prices found");
		}
	}
}
