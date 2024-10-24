package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.SearchPage;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class BaseTest {

    public static WebDriver driver;
    public static Properties properties = DriverManager.properties;
    protected SearchPage searchPage;
    // This method will run before any test method in the test class (BeforeClass)
    @BeforeClass(alwaysRun = true)
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get(properties.getProperty("baseURL"));
        searchPage = new SearchPage();
    }
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        // Create a timestamp to make the screenshot filename unique
        String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
        String screenshotPath = System.getProperty("user.dir")+"//screenshots/" + screenshotName + "_" + timestamp + ".png";
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            // Copy the file to the desired location
            FileUtils.copyFile(srcFile, new File(screenshotPath));
            System.out.println("Screenshot saved at: " + screenshotPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenshotPath; // Return the path of the screenshot
    }
    // After all tests, close the browser (AfterClass)
    @AfterClass(alwaysRun = true)
    public void tearDown() {
        DriverManager.closeDriver();
    }
}
