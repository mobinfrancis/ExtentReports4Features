package com.extentReports4Features.ExtentReports4Features;

import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class SampleTest1 {

	private ExtentTest extentTest;
	private ExtentReports extentReports;
	private ScreenshotController screenshotController;
	private String parentReportsFolderPath;
	private WebDriver driver;

	@BeforeSuite
	public void setUpSampleTestSuite(ITestContext testSuiteName) {
		System.setProperty("org.freemarker.loggerLibrary", "none");
		screenshotController = new ScreenshotController();
		screenshotController.setTestSuiteName(testSuiteName.getSuite().getName());
		parentReportsFolderPath = System.getProperty("user.dir") + File.separator + "reports" + File.separator + "Run_"
				+ DateTimeUtility.getFormattedCurrentDateTime("dd-MMM-yyyy_hh-mm-ss_aa") + File.separator;
		String extentReportsFolderPath = parentReportsFolderPath + "HTML Results";
		String extentReportsFilePath = extentReportsFolderPath + File.separator + testSuiteName.getSuite().getName()
				+ ".html";
		File extentReportFile = new File(extentReportsFilePath);
		ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(extentReportFile);
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentHtmlReporter);
	}

	@AfterSuite
	public void tearDownSampleTestSuite() {
		extentReports.flush();
	}

	@BeforeMethod
	public void setUp() {

	}

	@AfterMethod
	public void tearDown(ITestResult iTestResult) {
		try {
			screenshotController.setTestName(iTestResult.getMethod().getMethodName());
			screenshotController.setScreenShotParentFolderPath(parentReportsFolderPath);
			if (iTestResult.getStatus() == ITestResult.FAILURE) {
				extentTest.fail("Overall Test Status: Failed", MediaEntityBuilder
						.createScreenCaptureFromPath(screenshotController.addScreenshotToReport(driver)).build());
			} else if (iTestResult.getStatus() == ITestResult.SUCCESS) {
				this.extentTest.pass("Overall Test Status: Passed", MediaEntityBuilder
						.createScreenCaptureFromPath(screenshotController.addScreenshotToReport(driver)).build());
			} else if (iTestResult.getStatus() == ITestResult.SKIP) {
				this.extentTest.pass("Overall Test Status: Skipped", MediaEntityBuilder
						.createScreenCaptureFromPath(screenshotController.addScreenshotToReport(driver)).build());
			}
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test1() {
		try {
			System.out.println("Inside test 1");
			extentTest = extentReports.createTest("Test1");
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "chromedriver.exe");
			driver = new ChromeDriver();
			driver.navigate().to("https://www.google.com");
			screenshotController.setTestName(new Object() {
			}.getClass().getEnclosingMethod().getName());
			screenshotController.setScreenShotParentFolderPath(parentReportsFolderPath);
			extentTest.info("Launched the Google URL", MediaEntityBuilder
					.createScreenCaptureFromPath(screenshotController.addScreenshotToReport(driver)).build());
			driver.manage().window().maximize();
			extentTest.info("Maximized the browser window", MediaEntityBuilder
					.createScreenCaptureFromPath(screenshotController.addScreenshotToReport(driver)).build());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test2() {
		try {
			System.out.println("Inside test 2");
			extentTest = extentReports.createTest("Test2");
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "chromedriver.exe");
			driver = new ChromeDriver();
			driver.navigate().to("https://www.linkedin.com");
			screenshotController.setTestName(new Object() {
			}.getClass().getEnclosingMethod().getName());
			screenshotController.setScreenShotParentFolderPath(parentReportsFolderPath);
			extentTest.info("Launched the LinkedIn URL", MediaEntityBuilder
					.createScreenCaptureFromPath(screenshotController.addScreenshotToReport(driver)).build());
			driver.manage().window().maximize();
			extentTest.info("Maximizing the browser window", MediaEntityBuilder
					.createScreenCaptureFromPath(screenshotController.addScreenshotToReport(driver)).build());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
