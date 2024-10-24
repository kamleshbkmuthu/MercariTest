package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DriverManager {

    private static WebDriver driver;
    public static Properties properties = new Properties();

    // Load properties from config file
    static {
        try {
            FileInputStream file = new FileInputStream("src/main/resources/application.properties");
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Singleton pattern to get a single WebDriver instance
    public static WebDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }

    // Initialize WebDriver based on the browser specified in properties
    private static void initializeDriver() {
        String browser = properties.getProperty("browser");
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();// for chromedriver.exe setup
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();// for firefox driver.exe setup
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Browser not supported: " + browser);
        }
        driver.manage().window().maximize();
    }

    // Close the WebDriver
    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
