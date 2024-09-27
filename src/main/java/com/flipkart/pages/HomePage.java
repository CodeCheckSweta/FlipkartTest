package com.flipkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.enums.WaitStrategy;

public final class HomePage extends BasePage {
	
	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private final By groceryLink = By.xpath("//span[text()='Grocery']");
	private final By mobileLink = By.xpath("//span[text()='Mobiles & Tablets']");
	private final By travelLink = By.xpath("//span[text()='Travel']");

	public GroceryPage navigateToGroceryPage() {
		click(groceryLink, WaitStrategy.CLICKABLE, "Grocery Page Link");
		return new GroceryPage(driver); // method chaining
	}

	public MobilePage navigateToMobilePage(String value) {
		click(mobileLink, WaitStrategy.CLICKABLE, "Mobile Page Link");
		return new MobilePage(driver); // method chaining
	}

	public TravelPage navigateToTravelPage(String value) {
		click(travelLink, WaitStrategy.VISIBLE, "Travel Page Link");
		return new TravelPage(driver); // method chaining
	}

}
