package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String reportName;
	
	public void onStart(ITestContext context) {
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		reportName = "Test-Report-"+timeStamp+".html";
		
		extent = new ExtentReports();
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+reportName);
		extent.attachReporter(sparkReporter);
		
		// Report configurations
		sparkReporter.config().setDocumentTitle("OpenCart Automation Project");
		sparkReporter.config().setReportName("OpenCart FUnctional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		// Setting up system information
		extent.setSystemInfo("Application", "OpenCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String OS = context.getCurrentXmlTest().getParameter("OS");
		extent.setSystemInfo("Operating System", OS);
		String broswer = context.getCurrentXmlTest().getParameter("Browser");
		extent.setSystemInfo("Browser", broswer);
		
		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty())
			extent.setSystemInfo("Groups", includedGroups.toString());
	}
	
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+" passed");
	}
	
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName()+" failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+" skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext context) {
		extent.flush();
		
		// this piece of code will open report automatically as soon as execution completes
		String extentReportPath = System.getProperty("user.dir")+"\\reports\\"+reportName;
		File extentReport = new File(extentReportPath);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
			Desktop.getDesktop().open(extentReport);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
