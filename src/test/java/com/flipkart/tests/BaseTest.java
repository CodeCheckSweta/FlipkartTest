package com.flipkart.tests;

import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.flipkart.driver.Driver;
import com.flipkart.reports.ExtentReport;

public class BaseTest {
	protected BaseTest() {}
	
	@BeforeSuite
	public void initialize() {
		ExtentReport.initReports();
	}
	
	@AfterSuite
	public void flush() {
		ExtentReport.flushReports();
	}
	
	@BeforeMethod
	protected void setUp(Method method) throws Exception {
		ExtentReport.createTest(method.getName());
		Driver.initDriver();
	}
	
	@AfterMethod
	protected void tearDown() {
		Driver.quitDriver();
	}
	
}
