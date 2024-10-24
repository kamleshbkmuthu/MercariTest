package dataProviders;

import org.testng.annotations.DataProvider;

public class CategoriesDataProvider {
    @DataProvider(name = "testData")
    public Object[][] getData() {
        return new Object[][] {
                {"本・雑誌・漫画","本", "文学・小説"},
                {"本・雑誌・漫画", "本","コンピュータ・IT"}
        };
    }
}
