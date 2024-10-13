package com.flipkart.driver;

import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.flipkart.utils.PropertyUtils;

public final class Driver {
	private Driver() {}

	public static WebDriver initDriver() throws Exception {
		if(Objects.isNull(DriverManager.getDriver())) {
			DriverManager.setDriver(new ChromeDriver());
			DriverManager.getDriver().manage().window().maximize();
			DriverManager.getDriver().get(PropertyUtils.get("baseUrl"));
		}
		return DriverManager.getDriver();
	}
	
	public static void quitDriver() {
		if(Objects.nonNull(DriverManager.getDriver())) {
			DriverManager.getDriver().quit();
			DriverManager.unload();
		}
	}
}
