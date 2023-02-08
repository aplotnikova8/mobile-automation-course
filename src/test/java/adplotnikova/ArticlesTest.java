package adplotnikova;

import adplotnikova.pages.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ArticlesTest extends BaseTest {

    private MainWikiPage mainWikiPage;
    private ArticlePage articlePage;
    private ResultsPage resultsPage;
    private SavedArticlesPage savedArticlesPage;
    private SearchPage searchPage;

    private AndroidDownMenu androidDownMenu;
    private BasePage basePage;

    protected void setUp() throws Exception {
        super.setUp();
        basePage = new BasePage(driver);
        mainWikiPage = new MainWikiPage(driver);
        articlePage = new ArticlePage(driver);
        resultsPage = new ResultsPage(driver);
        savedArticlesPage = new SavedArticlesPage(driver);
        searchPage = new SearchPage(driver);
        androidDownMenu = new AndroidDownMenu(driver);
    }

    @Before
    public void openMainWikiPage() {
        mainWikiPage.openMainWikiPage();
    }

    public void testArticleTitleIsPresent() {
        mainWikiPage.waitInputOnMainMenuAndClick();
        searchPage.typeTextInInput("Java");
        resultsPage.getResultsList().get(0).click();
        articlePage.assertArticleTitleIsPresent();
    }

    public void testArticleTitle() {
        String searchRequest = "Java";

        mainWikiPage.waitInputOnMainMenuAndClick();
        searchPage.typeTextInInput(searchRequest);
        List<WebElement> results = resultsPage.getResultsList();

        results.get(0).click();
        articlePage.waitArticleTitleIsLoaded();
        androidDownMenu.clickSave();
        articlePage.backToSearch();

        results.get(1).click();
        articlePage.waitArticleTitleIsLoaded();
        androidDownMenu.clickSave();
        articlePage.backToSearch();

        searchPage.goToMainMenu();
        androidDownMenu.clickSave();
        savedArticlesPage.clickSavedReadingLink();

        List<WebElement> savedArticleList = savedArticlesPage.getSavedArticlesList();

        Assert.assertTrue(savedArticleList.size() == 2);

        savedArticlesPage.swipeArticleInSavedListByIndex(1);
        List<WebElement> articlesListAfterSwipe = savedArticlesPage.getSavedArticlesList();

        Assert.assertTrue(articlesListAfterSwipe.size() == 1);

        savedArticleList.get(0).click();
        Assert.assertTrue(articlePage.getArticleTitleText().equals(searchRequest));
    }

}
