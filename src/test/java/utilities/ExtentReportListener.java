package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.*;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class ExtentReportListener implements ITestListener {

    private static ExtentReports extent;
    private static ExtentTest test;

    // Create and configure the ExtentReports instance
    public void onStart(ITestContext context) {
        String path = System.getProperty("user.dir")+"//reports//index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        clearScreenshotDirectory();
    }

    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test passed: " + result.getMethod().getMethodName());
    }

    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverManager.getDriver();
        String testName = result.getName();

        // Capture the screenshot on failure
        String screenshotPath = BaseTest.captureScreenshot(driver, testName);
        System.out.println("Screenshot captured for failed test case: " + testName);
        test.log(Status.FAIL, "Test failed: " + result.getMethod().getMethodName());
        test.log(Status.FAIL, result.getThrowable());
        test.log(Status.FAIL, "Test failed: " + testName,
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
    }

    private void clearScreenshotDirectory() {
        File screenshotDir = new File("screenshots");
        if (screenshotDir.exists()) {
            File[] files = screenshotDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
        }
    }

    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test skipped: " + result.getMethod().getMethodName());
    }

    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
