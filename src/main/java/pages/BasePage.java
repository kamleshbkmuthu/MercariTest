package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.DriverManager;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BasePage {
    WebDriverWait wait;
    public BasePage() {
        this.wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30));
    }

    public void clickElement(WebElement element) {
        waitUntilElementClickable(element).click();
    }

    public void clickUsingAction(WebElement element){
        Actions actions = new Actions(DriverManager.getDriver());
        actions.moveToElement(element).click().build().perform();
    }

    // Send keys method
    public void sendKeys(WebElement element, String text) {
        waitUntilElementClickable(element).sendKeys(text);
    }

    // Get text from element
    public String getText(WebElement element) {
        return waitUntilElementClickable(element).getText();
    }

    // Wait until element is clickable by taking in locator as argument
    public WebElement waitUntilElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    // Wait until element is clickable by taking in Web element as argument
    public WebElement waitUntilElementClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Wait until element is visible by taking in locator as argument
    public WebElement waitUntilElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    // Wait until element is visible by taking in Web element as argument
    public WebElement waitUntilElementVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    // Wait until element is clickable by taking in Web element as argument and timeout duration as seconds
    public boolean waitForElementVisible(WebElement element, int seconds) {
        WebDriverWait webDriverWait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(seconds));
        return webDriverWait.until(ExpectedConditions.elementToBeClickable(element)).isDisplayed();
    }
    //retry code for page loading check
    public List<WebElement> retryUntilCondition(By locator, int maxRetries, int delayInSeconds) {
        int attempts = 0;
        List<WebElement> filterDropdown =null;
        for (int attempt = 0; attempt < maxRetries; attempt++) {
            filterDropdown = DriverManager.getDriver().findElements(locator);
            // Check if the dropdown is populated
            if (filterDropdown != null && !filterDropdown.isEmpty()) {
                return filterDropdown;
            }

            // Wait before the next retry
            try {
                TimeUnit.SECONDS.sleep(delayInSeconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Retrying... Attempt " + (attempt + 1));
        }

        // If after all retries, the dropdown is still not populated
        if (filterDropdown == null || filterDropdown.isEmpty()) {
            System.out.println("Dropdown is still empty after " + maxRetries + " retries.");
        }

        return filterDropdown;
    }

    // Wait until element is clickable by taking in locator as argument for stale

    public WebElement waitUntilElementVisibleForSTale(By locator) {
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)));
    }

    //find element method for common usage
    public WebElement findElement(By locator){

        return DriverManager.getDriver().findElement(locator);
    }
}
