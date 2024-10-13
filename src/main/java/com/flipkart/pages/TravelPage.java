package com.flipkart.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.enums.WaitStrategy;
import com.flipkart.reports.ExtentLogger;

public class TravelPage extends BasePage {

	public TravelPage() {
		super();
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

	public TravelPage searchFlights(String source, String dest, String month_year, String day) throws InterruptedException {
		clearAndSendKeys(departureCity, source, WaitStrategy.VISIBLE, "Departure City");
		By depart= By.xpath("//input[@name='0-departcity']//following::span[text()='"+source+"']");
		click(depart, WaitStrategy.CLICKABLE, "Departure Date");
		clearAndSendKeys(arrivalCity, dest, WaitStrategy.VISIBLE, "Arrival City");
		By arrival= By.xpath("//input[@name='0-arrivalcity']//following::span[text()='"+dest+"']");
		click(arrival, WaitStrategy.CLICKABLE, "Arrival Date");
		selectDate(month_year, day);
		click(searchBtn, WaitStrategy.CLICKABLE, "Search Button");
		Thread.sleep(5000);
		return this;
	}

	public void selectDate(String month_year, String day) {
		click(departureDate, WaitStrategy.CLICKABLE, "Departure Date");

		// Loop until the correct month is found
	    while(!isMonthVisible(month_year)) {
	        click(nextMonthBtn, WaitStrategy.CLICKABLE, "Next Month Button");
	    }

	    //Select the day once the correct month is visible
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
		System.out.println("//div[text()='October 2024']//following::td[.//button[text()='"+day+"']][1]");
		WebElement dayElement= driver.findElement(By.xpath("//div[text()='October 2024']//following::td[.//button[text()='"+day+"'] and not(@disabled)][1]"));
		String dateValue= dayElement.getText();
		dayElement.click();
		ExtentLogger.info(dateValue+" is clicked");
	}

	public double getCheapestPrice() {
		List<WebElement> priceElements = driver.findElements(By.xpath("//div[text()='Book']/preceding-sibling::div[1]/div[1]"));
		
		List<Double> prices = new ArrayList<>();
		for(WebElement priceElement:priceElements) {
			String priceText = priceElement.getText().replaceAll("[^\\d]", "");
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
