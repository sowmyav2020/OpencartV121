package Utilities;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
//Listeners class used to generate extent reports
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class Reporting extends TestListenerAdapter{

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	public static Logger logger;
	String repName;
	
	public void onStart(ITestContext testContext)
	{
		String timestamp=new SimpleDateFormat("yyyy.mm.dd.HH.mm.ss").format(new Date());
		String repName="Test-Report-"+timestamp+".html";
		
		sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);
		

		
		sparkReporter.config().setDocumentTitle("opencart Test Project");
		sparkReporter.config().setReportName("Functional Test Report");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
	
		extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("SubModule", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "sowmya");
		
		String os=testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser=testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String>includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());;
		}
	}
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Passed");
		
	}
	public void onTestFailure(ITestResult result)
	{
   
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
	
		try {
			String imgPath=new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.SKIP, "Test skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
		String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport=new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}catch (IOException e) {
			e.printStackTrace();
		
		}
	}
}
