package adplotnikova.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchPage extends BasePage {
    public SearchPage(AppiumDriver driver) {
        super(driver);
    }

    private final By inputOnSearchPage = By.id("org.wikipedia:id/search_src_text");
    private final By goBackButtonToMainMenu = By.xpath("//*[contains(@resource-id, 'search_toolbar')]/android.widget.ImageButton");

    public WebElement getSearchInputWebElement() {
        return driver.findElement(inputOnSearchPage);
    }

    public void typeTextInInput(String text) {
        getSearchInputWebElement().sendKeys(text);
    }

    public void goToMainMenu() {
        waitElementAndClick(goBackButtonToMainMenu);
    }

    public void clickOnSearchInput() {
        waitElementAndClick(inputOnSearchPage);
    }

    public void clearSearchInput() {
        getSearchInputWebElement().clear();
    }

}
