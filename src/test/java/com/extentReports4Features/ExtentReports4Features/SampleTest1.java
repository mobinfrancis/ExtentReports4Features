package com.extentReports4Features.ExtentReports4Features;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class SampleTest1 {

	private String parentReportsFolderPath;
	private ExtentReporterNG extentReporterNG;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@BeforeSuite
	public void setUpSampleTestSuite(ITestContext iTestContext) {
		String testSuiteName = iTestContext.getSuite().getName();
		parentReportsFolderPath = System.getProperty("user.dir") + File.separator + "reports" + File.separator + "Run_"
				+ DateTimeUtility.getFormattedCurrentDateTime("dd-MMM-yyyy_hh-mm-ss_aa") + File.separator;
		String extentReportsFolderPath = parentReportsFolderPath + "HTML Results";
		String extentReportsFilePath = extentReportsFolderPath + File.separator + testSuiteName + ".html";
		extentReporterNG = new ExtentReporterNG();
		extentReporterNG.setExtentReports(extentReportsFilePath);
	}

	@Test
	public void test1(ITestContext iTestContext) {
		try {
			String testSuiteName = iTestContext.getSuite().getName();
			ScreenshotController screenshotController = new ScreenshotController();
			screenshotController.setTestSuiteName(testSuiteName);
			System.out.println("Inside test 1");
			extentTest.set(extentReporterNG.getExtentReports().createTest("Test1"));
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.navigate().to("https://www.google.com");
			screenshotController.setTestName(new Object() {
			}.getClass().getEnclosingMethod().getName());
			screenshotController.setScreenShotParentFolderPath(parentReportsFolderPath);
			extentTest.get().info("Launched the Google URL", MediaEntityBuilder
					.createScreenCaptureFromPath(screenshotController.addScreenshotToReport(driver)).build());
			driver.manage().window().maximize();
			extentTest.get().info("Maximized the browser window for Google", MediaEntityBuilder
					.createScreenCaptureFromPath(screenshotController.addScreenshotToReport(driver)).build());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			driver.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test2(ITestContext iTestContext) {
		try {
			String testSuiteName = iTestContext.getSuite().getName();
			ScreenshotController screenshotController = new ScreenshotController();
			screenshotController.setTestSuiteName(testSuiteName);
			System.out.println("Inside test 2");
			extentTest.set(extentReporterNG.getExtentReports().createTest("Test2"));
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.navigate().to("https://www.linkedin.com");
			screenshotController.setTestName(new Object() {
			}.getClass().getEnclosingMethod().getName());
			screenshotController.setScreenShotParentFolderPath(parentReportsFolderPath);
			extentTest.get().info("Launched the LinkedIn URL", MediaEntityBuilder
					.createScreenCaptureFromPath(screenshotController.addScreenshotToReport(driver)).build());
			driver.manage().window().maximize();
			extentTest.get().info("Maximizing the browser window for LinkedIn", MediaEntityBuilder
					.createScreenCaptureFromPath(screenshotController.addScreenshotToReport(driver)).build());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			driver.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test3(ITestContext iTestContext) {
		try {
			String testSuiteName = iTestContext.getSuite().getName();
			ScreenshotController screenshotController = new ScreenshotController();
			screenshotController.setTestSuiteName(testSuiteName);
			System.out.println("Inside test 3");
			extentTest.set(extentReporterNG.getExtentReports().createTest("Test3"));
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.navigate().to("https://www.twitter.com");
			screenshotController.setTestName(new Object() {
			}.getClass().getEnclosingMethod().getName());
			screenshotController.setScreenShotParentFolderPath(parentReportsFolderPath);
			extentTest.get().info("Launched the Twitter URL", MediaEntityBuilder
					.createScreenCaptureFromPath(screenshotController.addScreenshotToReport(driver)).build());
			driver.manage().window().maximize();
			extentTest.get().info("Maximizing the browser window for Twitter", MediaEntityBuilder
					.createScreenCaptureFromPath(screenshotController.addScreenshotToReport(driver)).build());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			driver.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void tearDown(ITestResult iTestResult, ITestContext iTestContext) {
		try {
			String testSuiteName = iTestContext.getSuite().getName();
			ScreenshotController screenshotController = new ScreenshotController();
			screenshotController.setTestSuiteName(testSuiteName);
			screenshotController.setTestName(iTestResult.getMethod().getMethodName());
			screenshotController.setScreenShotParentFolderPath(parentReportsFolderPath);
			if (iTestResult.getStatus() == ITestResult.FAILURE) {
				extentTest.get().fail("Overall Test Status: Failed");
			} else if (iTestResult.getStatus() == ITestResult.SUCCESS) {
				extentTest.get().pass("Overall Test Status: Passed");
			} else if (iTestResult.getStatus() == ITestResult.SKIP) {
				extentTest.get().pass("Overall Test Status: Skipped");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void tearDownSampleTestSuite() {
		extentReporterNG.getExtentReports().flush();
	}

}
