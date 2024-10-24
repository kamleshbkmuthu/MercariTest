package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.SearchPage;

import java.io.FileInputStream;
import java.io.IOException;
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
    // After all tests, close the browser (AfterClass)
    @AfterClass
    public void tearDown() {
        DriverManager.closeDriver();
    }
}
