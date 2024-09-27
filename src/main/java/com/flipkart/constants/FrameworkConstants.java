package com.flipkart.constants;

import java.time.Duration;
import java.util.Date;

public final class FrameworkConstants {
	private FrameworkConstants() {}

	private static final String PROJECT_PATH = System.getProperty("user.dir");
	private static final String CONFIGFILEPATH= PROJECT_PATH + "/src/test/resources/config/config.properties";
	private static final Duration EXPLICIT_WAIT = Duration.ofSeconds(20);
	public static final String EXTENT_REPORT_FOLDER_PATH = PROJECT_PATH + "/ExtentReports/";
	static Date d = new Date();
	public static final String EXTENT_REPORT_NAME = "AutomationReport_"
			+ d.toString().replace(":", "_").replace(" ", "_") + ".html";
	private static String extentReportFilePath = "";
	
	public static String getConfigfilepath() {
		return CONFIGFILEPATH;
	}

	public static Duration getExplicitWait() {
		return EXPLICIT_WAIT;
	}
	
	public static String getExtentReportFilePath() {
		if (extentReportFilePath.isEmpty()) {
			extentReportFilePath = createExtentReportPath();
		}
		return extentReportFilePath;
	}
	
	public static String createExtentReportPath() {
		return EXTENT_REPORT_FOLDER_PATH + EXTENT_REPORT_NAME;
	}
}
