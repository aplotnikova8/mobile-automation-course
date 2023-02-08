package adplotnikova;

import adplotnikova.pages.*;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchTest extends BaseTest {
    private MainWikiPage mainWikiPage;
    private ArticlePage articlePage;
    private ResultsPage resultsPage;
    private SearchPage searchPage;


    protected void setUp() throws Exception {
        super.setUp();
        mainWikiPage = new MainWikiPage(driver);
        articlePage = new ArticlePage(driver);
        resultsPage = new ResultsPage(driver);
        searchPage = new SearchPage(driver);
    }

    @Before
    public void testMainWikiPage() {
        mainWikiPage.openMainWikiPage();
    }

    public void testInputContainsText() {
        //mainWikiPage.openMainWikiPage(); вот если добавить строчку - все работает
        mainWikiPage.waitInputOnMainMenu();
        mainWikiPage.assertInputHasText("Search Wikipedia");
    }

    public void testSearchCanceledOption() {
        //mainWikiPage.openMainWikiPage(); вот если добавить строчку - все работает
        mainWikiPage.openMainWikiPage();
        String searchRequest = "Java";
        mainWikiPage.waitInputOnMainMenuAndClick();
        searchPage.typeTextInInput(searchRequest);

        List<WebElement> results = resultsPage.getResultsList();
        Assert.assertTrue("По запросу найдено не больше одного результата!",
                results.size() > 1);
        searchPage.clickOnSearchInput();
        searchPage.clearSearchInput();

        Assert.assertFalse(searchPage.getSearchInputWebElement().getAttribute("text").contains(searchRequest));
    }

}
