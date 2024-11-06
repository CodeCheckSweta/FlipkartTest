package com.flipkart.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.enums.WaitStrategy;

public class GroceryPage extends BasePage{

	public GroceryPage() {
		super();
		PageFactory.initElements(driver, this);
	}
	
	public String getTitle() {
		return getPageTitle();
	}
	
	private final By pincodeTextBox = By.xpath("//input[@title='Enter pincode']");
	private final By searchBox = By.xpath("//input[@title='Search grocery products']");
	private final By searchButton = By.xpath("//button[@type='submit']");
	
	public GroceryPage setPinCode(String pincode) {
		clearAndSendKeys(pincodeTextBox, pincode, WaitStrategy.CLICKABLE, "Pincode");
		driver.findElement(pincodeTextBox).sendKeys(Keys.ENTER);
		return this;
	}
	
	public GroceryPage searchGrocery(String grocery) throws InterruptedException {
		clearAndSendKeys(searchBox, grocery, WaitStrategy.CLICKABLE, "Grocery Search Box");
		click(searchButton, WaitStrategy.CLICKABLE, "Search Button");
		waitForPageLoad();
		Thread.sleep(5000);
		return this;
	}
	
	public double getCheapestAlmondPrice() {

		List<WebElement> priceElements = driver.findElements(By.xpath("(//span[text()='Add Item']/preceding::div[contains(text(),'1 kg') or contains(text(),'2x500 g')][2])/preceding::div[contains(text(),'₹')][2]"));
		System.out.println(priceElements);
		List<Double> prices = new ArrayList<>();
		for(WebElement priceElement:priceElements) {
			System.out.println(priceElement.getText());
			String priceText = priceElement.getText().replaceAll("[^\\d]", "");
			if(!priceText.isEmpty()) {
				prices.add(Double.parseDouble(priceText));
			}
		}
		
		if(!prices.isEmpty()) {
			return Collections.min(prices);
		}else {
			throw new RuntimeException("No almond prices found");
		}
	}
}
