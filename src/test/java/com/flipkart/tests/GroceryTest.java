package com.flipkart.tests;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import com.flipkart.pages.GroceryPage;
import com.flipkart.pages.HomePage;
import com.flipkart.reports.ExtentLogger;

public final class GroceryTest extends BaseTest{
	private GroceryTest() {}
	
	@Test(description = "To Search the Grocery page and identify the least price for 1kg Almonds.")
	public void searchLeastAlmondPrice() throws InterruptedException {
		HomePage hp = new HomePage();
		GroceryPage gp = hp.navigateToGroceryPage();
		gp.waitForPageLoad();
		Assertions.assertThat(gp.getTitle()).isEqualTo("Flipkart Grocery Store - Groceries Online & Get Rs.1 Deals at Flipkart.com");
		
		gp.setPinCode("122006");
		gp.searchGrocery("1kg almond");
		gp.waitForPageLoad();
		
		double cheapestPrice = gp.getCheapestAlmondPrice();
		ExtentLogger.info("Cheapest 1kg Almonds cost: "+cheapestPrice);
		Assertions.assertThat(cheapestPrice).isPositive();
	}
}
