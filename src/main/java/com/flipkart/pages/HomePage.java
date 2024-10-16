package com.flipkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.enums.WaitStrategy;

public final class HomePage extends BasePage {
	
	public HomePage() {
		super();
		PageFactory.initElements(driver, this);
	}

	private final By groceryLink = By.xpath("//span[text()='Grocery']");
	private final By mobileLink = By.xpath("//span[text()='Mobiles & Tablets']");
	private final By travelLink = By.xpath("//span[text()='Travel']");

	public GroceryPage navigateToGroceryPage() {
		click(groceryLink, WaitStrategy.CLICKABLE, "Grocery Page Link");
		return new GroceryPage(); // method chaining
	}

	public MobilePage navigateToMobilePage() {
		click(mobileLink, WaitStrategy.CLICKABLE, "Mobile Page Link");
		return new MobilePage(); // method chaining
	}

	public TravelPage navigateToTravelPage() {
		click(travelLink, WaitStrategy.CLICKABLE, "Travel Page Link");
		return new TravelPage(); // method chaining
	}

}
