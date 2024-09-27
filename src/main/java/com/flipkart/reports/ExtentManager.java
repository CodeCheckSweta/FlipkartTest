package com.flipkart.reports;

import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {
	private ExtentManager() {}		//Private constructor to avoid external instantiation
	private static ThreadLocal<ExtentTest> exTest = new ThreadLocal<>();
	
	static ExtentTest getExtentTest() {	//deafult -->it can only be accessed within package -->private packages
		return exTest.get();
	}
	
	static void setExtentTest(ExtentTest test) {
		exTest.set(test);
	}
	
	static void unload() {
		exTest.remove();
	}
}
