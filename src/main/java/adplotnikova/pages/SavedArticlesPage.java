package adplotnikova.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static adplotnikova.MyConstants.LOCATOR_FOR_LIST_ERROR_MESSAGE;

public class SavedArticlesPage extends BasePage{
    public SavedArticlesPage(AppiumDriver driver) {
        super(driver);
    }

    private final By savedArticleReadingList = By.id("org.wikipedia:id/page_list_item_title");
    private final By savedArticlesReadingLink = By.id("org.wikipedia:id/item_title");

    public void clickSavedReadingLink() {
        waitElementAndClick(savedArticlesReadingLink);
    }

    public List<WebElement> getSavedArticlesList() {
        return waitAllElementsIsVisible(savedArticleReadingList, LOCATOR_FOR_LIST_ERROR_MESSAGE);
    }

    public void swipeArticleInSavedListByIndex(int i) {
        swipeElementToLeft(getSavedArticlesList().get(i));
    }

}
