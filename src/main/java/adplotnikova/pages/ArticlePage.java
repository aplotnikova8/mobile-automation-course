package adplotnikova.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePage extends BasePage{
    public ArticlePage(AppiumDriver driver) {
        super(driver);
    }


    private final By articleTitle = By.xpath("//android.view.View[contains(@resource-id, 'section-title')]/preceding-sibling::android.view.View");
    private final By goBackButtonToSearch = By.xpath("//*[@content-desc='Navigate up']");

    public void waitArticleTitleIsLoaded() {
        waitElementIsVisible(articleTitle);
    }

    public void backToSearch() {
        waitElementAndClick(goBackButtonToSearch);
    }

    public String getArticleTitleText() {
        return getArticleTitleWebElement().getAttribute("text");
    }

    public WebElement getArticleTitleWebElement() {
        return waitElementIsVisible(articleTitle);
    }

    public void assertArticleTitleIsPresent() {
        assertElementPresent(articleTitle);
    }

}
