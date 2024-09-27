package com.flipkart.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.flipkart.reports.ExtentLogger;
import com.flipkart.reports.ExtentReport;

public class ListenerClass implements ITestListener, ISuiteListener {

	@Override
	public void onStart(ISuite suite) {
		ExtentReport.initReports();
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentReport.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) { 
		String logText = "<b>" + result.getMethod().getMethodName() + " is passed.</b>";
		Markup markupMessage = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		ExtentLogger.pass(markupMessage);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String logText = "<b>" + result.getMethod().getMethodName() + " is failed.</b>";
		Markup markupMessage = MarkupHelper.createLabel(logText, ExtentColor.RED);
		ExtentLogger.fail(markupMessage);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String logText = "<b>" + result.getMethod().getMethodName() + " is skipped.</b>";
		Markup markupMessage = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		ExtentLogger.skip(markupMessage);
	}

	@Override
	public void onFinish(ISuite suite) {
		ExtentReport.flushReports();
	}
}
