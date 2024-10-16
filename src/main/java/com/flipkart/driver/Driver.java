package com.flipkart.driver;

import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.flipkart.pages.BasePage;
import com.flipkart.utils.PropertyUtils;

public final class Driver extends BasePage{
	private Driver() {}

	public static WebDriver initDriver() throws Exception {
		if(Objects.isNull(driver)) {
			DriverManager.setDriver(new ChromeDriver());
			DriverManager.getDriver().manage().window().maximize();
			DriverManager.getDriver().get(PropertyUtils.get("baseUrl"));
		}
		return DriverManager.getDriver();
	}
	
	public static void quitDriver() {
		if(Objects.nonNull(driver)) {
			DriverManager.getDriver().quit();
			DriverManager.unload();
		}
	}
}
