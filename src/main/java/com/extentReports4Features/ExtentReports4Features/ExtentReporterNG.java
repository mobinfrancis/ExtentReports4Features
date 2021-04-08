package com.extentReports4Features.ExtentReports4Features;

import java.io.File;

import org.testng.ITestContext;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterNG {

	private ExtentReports extentReports;

	public ExtentReports extentReportGenerator(ITestContext testSuiteName, String extentReportsFilePath) {
		System.setProperty("org.freemarker.loggerLibrary", "none");
		File extentReportFile = new File(extentReportsFilePath);
		ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(extentReportFile);
		extentHtmlReporter = configureExtentHtmlReporter(extentHtmlReporter);
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentHtmlReporter);
		setSystemInfoInExtentReports(extentReports);
		return extentReports;
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

}
