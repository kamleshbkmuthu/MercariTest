package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.DriverManager;

import java.util.List;
import java.util.stream.Collectors;

public class SearchPage extends BasePage{
    public SearchPage() {
        PageFactory.initElements(DriverManager.getDriver(),this);
    }
    @FindBy(xpath = "//input[@placeholder='なにをお探しですか？']")
    private WebElement searchBar;
    @FindAll(@FindBy(xpath = "//select[@class='merInputNode select__da4764db medium__da4764db']"))
    private List<WebElement> filterDropdown ;
    @FindBy(xpath = "//input[@aria-checked='true' and @name = 'category_id']//..//span")
    private WebElement selectedFilterElement;
    @FindBy(xpath = "//h3[contains(text(),'絞り込み')]")
    private WebElement filterText;
    @FindAll(@FindBy(xpath = "//section[@data-testid='search-history']//div[@role='list']/div[@role='listitem']"))
    private List<WebElement> searchHistoryList;
    @FindAll(@FindBy(xpath = "//section[@data-testid='search-history']//div[@role='list']/div[@role='listitem']//p"))
    private List<WebElement> searchHistoryListText;
    @FindBy(xpath = "//input[@aria-label='検索キーワードを入力']")
    private WebElement searchWithFilter;
    @FindBy(xpath = "//button[@aria-label='close']//div[@class='merIcon']//*[name()='svg']")
    private WebElement clearCategories;
    @FindBy(xpath = "//div[@data-testid='mercari-logo']//*[name()='svg']")
    private WebElement mercariLogo;
    String categoryString = "//p[contains(text(),'%s')]";
    public void clickSearchBar() {
        waitUntilElementVisibleForSTale(By.xpath("//input[@placeholder='なにをお探しですか？']"));
        clickElement(searchBar);
    }

    public void searchCategory(String category) {
        clickElement(searchBar);
        sendKeys(searchWithFilter,category);
        searchWithFilter.sendKeys(Keys.ENTER);
    }

    public CategoriesPage selectCategory(String category) {
        WebElement element = findElement(By.xpath(String.format(categoryString,category)));
        clickElement(element);
        return new CategoriesPage();
    }
    public void checkSearchPage(){
        waitUntilElementVisible(searchBar);
    }
    public String getSelectedFilterOnIndex(int index){
        // retrying for 5 iterations until the category filter select dropdown elements are loaded
        List<WebElement> filterDropdownWebElements =
                retryUntilCondition(By.xpath("//select[@class='merInputNode select__da4764db medium__da4764db']"),
                        5, 2);
        // accessing based on index as there are two dropdown elements for sub categories
        WebElement filterElement = filterDropdownWebElements.get(index);
        Select selectCategory = new Select(filterElement);
        // Get the currently selected option
        WebElement selectedOption = selectCategory.getFirstSelectedOption();
        //returning the selected value
        return selectedOption.getText();
    }
    public String getSelectedFilterAttributeValue(){
        return getText(selectedFilterElement);
    }
    public void clickCloseCategoryOnSearch() {
        if(waitForElementVisible(clearCategories,10))
            clickElement(clearCategories);
    }
    public int getSearchHistoryCount(){
        clickSearchBar();
        return searchHistoryList.size();
    }
    public List<String> getSearchHistoryCategories(){
        clickSearchBar();
        return searchHistoryListText.stream().map(WebElement::getText).collect(Collectors.toList());
    }
    public SearchPage clickLatestSearchHistory(){
        clickSearchBar();
        clickElement(searchHistoryList.get(0));
        return this;
    }
}
