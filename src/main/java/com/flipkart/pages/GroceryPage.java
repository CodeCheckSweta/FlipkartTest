package com.flipkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
	
	public void searchGrocery(String grocery) {
		clearAndSendKeys(searchBox, grocery, WaitStrategy.CLICKABLE, "Grocery Search Box");
		click(searchButton, WaitStrategy.CLICKABLE, "Search Button");
	}
}
