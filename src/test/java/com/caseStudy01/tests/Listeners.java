package com.caseStudy01.tests;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.caseStudy1.utils.Base;
import com.caseStudy1.utils.DriverManager;
import com.caseStudy1.utils.ExtentReportClass;

public class Listeners extends Base implements ITestListener {

	ExtentTest test;
	ExtentReports extent = ExtentReportClass.getExtentReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "TestCase Passed");
		String testMethodName = result.getMethod().getMethodName();
		extentTest.get().addScreenCaptureFromPath(getScreenShotPathUsingRobotClass(testMethodName,DriverManager.getDriver()), testMethodName);
	}

	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		String testMethodName = result.getMethod().getMethodName();
		String path = getScreenShotPath(testMethodName,DriverManager.getDriver());
		extentTest.get().addScreenCaptureFromPath(path, testMethodName);
	}

	public void onTestSkipped(ITestResult result) {
		extentTest.get().log(Status.SKIP, "TestCase Skipped");
	}

	public void onStart(ITestContext context) {
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
