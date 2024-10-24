package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.DriverManager;

public class CategoriesPage extends BasePage{
    public CategoriesPage() {
        PageFactory.initElements(DriverManager.getDriver(),this);
    }
    @FindBy(xpath = "//h1[contains(text(),'カテゴリー')]")
    private WebElement filterPageHeader;
    String filterCategory = "//a[contains(text(),'%s')]";
    String category2FilterHeader = "//h1[contains(text(),'%s')]";
    String filterCategory2 = "//a[contains(text(),'%s') and contains(@data-location,'category_list')]";
    // this method is used to select the first category filter
    public CategoriesPage selectCategory(String category){
        waitUntilElementVisible(filterPageHeader);
        WebElement element = waitUntilElementClickable(By.xpath(String.format(filterCategory,category)));
        clickUsingAction(element);
        return this;
    }
    // this method is used to select the sub category filters
    public CategoriesPage selectSubCategory(String category, String previousCategory){
        waitUntilElementVisible(By.xpath(String.format(category2FilterHeader,previousCategory)));
        WebElement element = waitUntilElementClickable(By.xpath(String.format(filterCategory2,category)));
        clickElement(element);
        return this;
    }
}
