package com.extentReports4Features.ExtentReports4Features;

import java.io.File;
import java.io.IOException;

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
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

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
		extentHtmlReporter = configureExtentHtmlReporter(extentHtmlReporter);
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentHtmlReporter);
		setSystemInfoInExtentReports(extentReports);
	}

	@BeforeMethod
	public void setUp() {

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
			extentTest.info("Launched the Google URL", buildMediaEntityToCreateScreenCaptureFromPath());
			driver.manage().window().maximize();
			extentTest.info("Maximized the browser window", buildMediaEntityToCreateScreenCaptureFromPath());
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
			extentTest.info("Launched the LinkedIn URL", buildMediaEntityToCreateScreenCaptureFromPath());
			driver.manage().window().maximize();
			extentTest.info("Maximizing the browser window", buildMediaEntityToCreateScreenCaptureFromPath());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MediaEntityModelProvider buildMediaEntityToCreateScreenCaptureFromPath() {
		try {
			return MediaEntityBuilder.createScreenCaptureFromPath(screenshotController.addScreenshotToReport(driver))
					.build();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ExtentHtmlReporter configureExtentHtmlReporter(ExtentHtmlReporter extentHtmlReporter) {
		extentHtmlReporter.config().setDocumentTitle("FIMAutomation");
		extentHtmlReporter.config().setReportName("FIMAutomation Extent Report");
		extentHtmlReporter.config().setTheme(Theme.DARK);
		extentHtmlReporter.config().setTimeStampFormat("dd-MMM-yyyy_hh-mm-ss_aa");
		return extentHtmlReporter;
	}

	public void setSystemInfoInExtentReports(ExtentReports extentReports) {
		extentReports.setSystemInfo("Name", "SampleTestSuite");
		extentReports.setSystemInfo("Browser", "Chrome");
		extentReports.setSystemInfo("Environment", "Test");
	}

	@AfterMethod
	public void tearDown(ITestResult iTestResult) {
		try {
			screenshotController.setTestName(iTestResult.getMethod().getMethodName());
			screenshotController.setScreenShotParentFolderPath(parentReportsFolderPath);
			if (iTestResult.getStatus() == ITestResult.FAILURE) {
				extentTest.fail("Overall Test Status: Failed", buildMediaEntityToCreateScreenCaptureFromPath());
			} else if (iTestResult.getStatus() == ITestResult.SUCCESS) {
				this.extentTest.pass("Overall Test Status: Passed", buildMediaEntityToCreateScreenCaptureFromPath());
			} else if (iTestResult.getStatus() == ITestResult.SKIP) {
				this.extentTest.pass("Overall Test Status: Skipped", buildMediaEntityToCreateScreenCaptureFromPath());
			}
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void tearDownSampleTestSuite() {
		extentReports.flush();
	}

}
