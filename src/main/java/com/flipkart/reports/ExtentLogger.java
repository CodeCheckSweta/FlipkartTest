package com.flipkart.reports;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.Markup;

public final class ExtentLogger {
	private ExtentLogger() {}	//Private constructor to avoid external instantiation
	
	// Log a pass message
    public static void pass(String message) {
        ExtentTest test = ExtentManager.getExtentTest();
        if (test != null) {
            test.pass(message);
        } else {
            System.out.println("ExtentTest is not initialized. Cannot log pass message.");
        }
    }
	
    // Log a pass message with Markup
	public static void pass(Markup message) {
		ExtentTest test = ExtentManager.getExtentTest();
        if (test != null) {
            test.pass(message);
        } else {
            System.out.println("ExtentTest is not initialized. Cannot log pass message.");
        }
	}
	
	// Log a fail message
    public static void fail(String message) {
        ExtentTest test = ExtentManager.getExtentTest();
        if (test != null) {
            test.fail(message);
        } else {
            System.out.println("ExtentTest is not initialized. Cannot log fail message.");
        }
    }

    // Log a fail message with Markup
    public static void fail(Markup message) {
        ExtentTest test = ExtentManager.getExtentTest();
        if (test != null) {
            test.fail(message);
        } else {
            System.out.println("ExtentTest is not initialized. Cannot log fail message.");
        }
    }

    // Log a skip message
    public static void skip(String message) {
        ExtentTest test = ExtentManager.getExtentTest();
        if (test != null) {
            test.skip(message);
        } else {
            System.out.println("ExtentTest is not initialized. Cannot log skip message.");
        }
    }

    // Log a skip message with Markup
    public static void skip(Markup message) {
        ExtentTest test = ExtentManager.getExtentTest();
        if (test != null) {
            test.skip(message);
        } else {
            System.out.println("ExtentTest is not initialized. Cannot log skip message.");
        }
    }

    // Log an info message
    public static void info(String message) {
        ExtentTest test = ExtentManager.getExtentTest();
        if (test != null) {
            test.info(message);
        } else {
            System.out.println("ExtentTest is not initialized. Cannot log info message.");
        }
    }

    // Log an info message with Markup
    public static void info(Markup message) {
        ExtentTest test = ExtentManager.getExtentTest();
        if (test != null) {
            test.info(message);
        } else {
            System.out.println("ExtentTest is not initialized. Cannot log info message.");
        }
    }
}