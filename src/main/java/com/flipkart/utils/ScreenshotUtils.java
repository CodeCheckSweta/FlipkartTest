package com.flipkart.utils;

import java.sql.DriverManager;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public final class ScreenshotUtils {
	private ScreenshotUtils() {}
	
	public static String getBase64Image() {
		return ((TakesScreenshot) DriverManager.getDrivers()).getScreenshotAs(OutputType.BASE64);
	}
}
