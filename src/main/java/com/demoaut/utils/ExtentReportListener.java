package com.demoaut.utils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.demoaut.base.TestBase;

public class ExtentReportListener extends TestBase implements ITestListener {

	ExtentReports extentReport = TestUtil.createInstance();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@Override
	public void onFinish(ITestContext arg0) {
		if(extentReport != null){
			extentReport.flush();
		}

	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		String exceptionMessage = Arrays.toString(arg0.getThrowable().getStackTrace());
		extentTest.get().fail("<details><summary><b><font color=red>Exception Occured, click to see details: "
				+ "</font></b></summary>" + exceptionMessage.replace(",", "<br>") + "</details> \n");
		String path = takeScreenshot(arg0.getMethod().getMethodName());
		try {
			extentTest.get().fail("Screenshot of failure",
					MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		} catch (IOException e) {
			extentTest.get().fail("Test Failed, Cannot attach screenshot");
		}
		String logtext = "Test Method " + arg0.getMethod().getMethodName() + " Failed ";
		Markup m = MarkupHelper.createLabel(logtext, ExtentColor.RED);
		extentTest.get().log(Status.FAIL, m);
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		String logtext = "Test Method " + arg0.getMethod().getMethodName() + " Skipped ";
		Markup m = MarkupHelper.createLabel(logtext, ExtentColor.YELLOW);
		extentTest.get().log(Status.SKIP, m);
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		ExtentTest test = extentReport.createTest(arg0.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		String logtext = "Test Method " + arg0.getMethod().getMethodName() + " Successful ";
		Markup m = MarkupHelper.createLabel(logtext, ExtentColor.GREEN);
		extentTest.get().log(Status.PASS, m);
	}

	public String takeScreenshot(String methodName) {
		String filename = getScreenshotName(methodName);
		String directory = System.getProperty("user.dir") + "/Screenshots/";
		new File(directory).mkdirs();
		String path = directory + filename;
		try {
			TakesScreenshot scr = ((TakesScreenshot) driver);
			File srcFile = scr.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	public static String getScreenshotName(String methodName) {
		Date d = new Date();
		String FileName = methodName + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".png";
		return FileName;
	}

}
