package tests;

import dataProviders.CategoriesDataProvider;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CategoriesPage;
import utilities.BaseTest;

public class SearchTest extends BaseTest {
    SoftAssert softAssert;
    CategoriesPage categoriesPage;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        softAssert = new SoftAssert();
    }

    //this test case both scenario 1 and scenario 2 pre-requisite and i have used data provider for scenario 2 pre-requisite
    @Test(dataProviderClass = CategoriesDataProvider.class, dataProvider = "testData",
            priority = 1, description = "Test Data creation for Scenario 2 and Scenario 1 is covered with second test data")
    public void searchConditionsSetCorrectly(String category1, String category2, String category3) {
        try {
            //click searchbar
            searchPage.clickSearchBar();
            //select category option in search bar
            categoriesPage = searchPage.selectCategory("カテゴリーからさがす");
            //select all categories
            categoriesPage.selectCategory(category1)
                    .selectSubCategory(category2, category1)
                    .selectSubCategory(category3, category2);
            searchPage.checkSearchPage();//check navigation to
            softAssert.assertEquals(searchPage.getSelectedFilterOnIndex(0), category1, "Expected first category: " + category1
                    + "but actual: " + searchPage.getSelectedFilterOnIndex(0));
            softAssert.assertEquals(searchPage.getSelectedFilterOnIndex(1), category2, "Expected first category: " + category2
                    + "but actual: " + searchPage.getSelectedFilterOnIndex(1));
            softAssert.assertEquals(searchPage.getSelectedFilterAttributeValue(), category3, "Expected first category: " + category3
                    + "but actual: " + searchPage.getSelectedFilterAttributeValue());
            softAssert.assertAll();
        } finally {
            //this is to reset the screen as the filter needs to be removed if added any
            searchPage.clickCloseCategoryOnSearch();
        }
    }

    @Test(dependsOnMethods = {"searchConditionsSetCorrectly"}, priority = 2,
            description = "Test Data creation for Scenario 2 and Scenario 1 is covered with second test data")
    public void searchNewCategory() {
        //this test case will execute only if above test cases as i have added depondsOnMethod configuration for this test case
        // soft assertion is added for
        //2. Verify there are 2 browsing histories
        //3. Verify the latest browsing history is showing correctly (Computers & Technology / コンピュータ/IT)
        softAssert.assertEquals(searchPage.getSearchHistoryCount(), 2,
                "Expected searchHistory count: 2 but actual: " + searchPage.getSearchHistoryCount());
        softAssert.assertEquals(searchPage.getSearchHistoryCategories().get(0), "コンピュータ・IT",
                "Expected latest search history: コンピュータ・IT but actual: " +
                        searchPage.getSearchHistoryCategories().get(0));

//        4. Click on the latest browsing histories
//        5. Verify the search conditions on the left sidebar are set correctly
        searchPage.clickLatestSearchHistory();
        searchPage.checkSearchPage();
        softAssert.assertEquals(searchPage.getSelectedFilterOnIndex(0), "本・雑誌・漫画", "Expected first category: " + "本・雑誌・漫画"
                + "but actual: " + searchPage.getSelectedFilterOnIndex(0));
        softAssert.assertEquals(searchPage.getSelectedFilterOnIndex(1), "本", "Expected first category: " + "本"
                + "but actual: " + searchPage.getSelectedFilterOnIndex(1));
        softAssert.assertEquals(searchPage.getSelectedFilterAttributeValue(), "コンピュータ・IT", "Expected first category: " + "コンピュータ・IT"
                + "but actual: " + searchPage.getSelectedFilterAttributeValue());
//        6. Input "javascript" in the search bar and search with the keyword
//        7. Go back to Mercari top page (https://jp.mercari.com/)
        searchPage = searchPage.searchCategory("javascript");
        driver.get(properties.getProperty("baseURL"));

//        Verify there are 3 browsing histories
//        9. Verify the latest browsing history is showing correctly (javascript, Computers & Technology / javascript, コンピュータ/IT)
        softAssert.assertEquals(searchPage.getSearchHistoryCount(), 3,
                "Expected searchHistory count: 3 but actual: " + searchPage.getSearchHistoryCount());
        softAssert.assertEquals(searchPage.getSearchHistoryCategories().get(0), "javascript, コンピュータ・IT",
                "Expected latest search history:  javascript, コンピュータ・IT but actual: " +
                        searchPage.getSearchHistoryCategories().get(0));
        softAssert.assertAll();
    }
}
