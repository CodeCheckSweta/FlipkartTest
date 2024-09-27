package com.flipkart.tests;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import com.flipkart.driver.DriverManager;
import com.flipkart.pages.HomePage;
import com.flipkart.pages.TravelPage;

public final class FlightTest extends BaseTest {

	private FlightTest() {
	}

	/*
	 * To check the price of cheapest flight between Kolkata and Bangalore on 10th
	 * Oct from Flipkart page
	 */
	@Test(description = "To check the price of cheapest flight between Kolkata and Bangalore on 10th Oct from Flipkart page")
	public void checkCheapestPrice() {
		HomePage hp = new HomePage(DriverManager.getDriver());
		TravelPage tp = hp.navigateToTravelPage("Flight Bookings");
		tp.waitForPageLoad();
		Assertions.assertThat(tp.getTitle()).isEqualTo("Flight bookings, Cheap flights, Lowest Air tickets at Flipkart.com");
		
		tp.searchFlights("Kolkata", "Bangalore", "October 2024", "10");
		tp.waitForPageLoad();
		double cheapestPrice = tp.getCheapestPrice();
		Assertions.assertThat(cheapestPrice).isPositive();
	}
}
