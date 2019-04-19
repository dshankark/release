package factory;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class extRep implements ITestListener{
	ExtentReports report;
	ExtentHtmlReporter htm;
	ExtentTest test;
	WebDriver driver;
	@Test

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Pass value from report");
		test=report.createTest(result.getMethod().getDescription());
		test=report.createTest(result.getMethod().getMethodName());

		test.log(Status.PASS, result.getMethod().getMethodName()+"   "+ result.getMethod().getDescription());

	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		test =report.createTest(result.getMethod().getDescription());
		test.log(Status.FAIL, result.getMethod().getMethodName()+" Test Failed");
		test.log(Status.ERROR, result.getThrowable().getMessage()+" Test error");
		test.log(Status.FAIL, result.getMethod().getDescription()+" Test case name");
		TakesScreenshot ss =(TakesScreenshot)Browser.driver;
		File src= ss.getScreenshotAs(OutputType.FILE);
		File dest= new File ("C://ss//"+result.getMethod().getMethodName()+".png");
		try {
			test.log(Status.FAIL, result.getMethod().getDescription(), MediaEntityBuilder.createScreenCaptureFromPath("C://ss//"+result.getMethod().getMethodName()+".png").build());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		System.out.println("Reporting started");
		report =new ExtentReports();
		htm = new ExtentHtmlReporter("./test-output/extent.html");

		report.attachReporter(htm);
		htm.config().setDocumentTitle("Sankar Kumar Report");
		htm.config().setReportName("Full Page Report");
		report.setSystemInfo("Release 1.0", "Sprint 2");

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println(context.getPassedTests()+"Execution Ended");
		report.removeTest(test);
		report.flush();
	}
}
