import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FirstTest {

    private AppiumDriver driver;
    private final String locatorErrorMessage = "Не удалось найти элемент на странцие по локатору %s!";

    @Before
    public void openMainWikiPage() throws Exception {
        setUp();
        By continueButton = By.xpath("//android.widget.Button[@text='CONTINUE']");
        By getStartedButton = By.xpath("//android.widget.Button[@text='GET STARTED']");
        waitElementAndClick(continueButton);
        waitElementAndClick(continueButton);
        waitElementAndClick(continueButton);
        waitElementAndClick(getStartedButton);
    }

    private void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        //capabilities.setCapability("app", "/Users/anastasiyaplotnikova/IdeaProjects/mobile-automation-course/apks/wikipedia.apk");
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        //driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void checkInputContainsText() {
        By input = By.xpath("//*[contains(@resource-id, 'search_container')]/android.widget.TextView");
        waitElementIsVisible(input);
        assertElementHasText(input, "Search Wikipedia", "Текст в инпуте не совпадает с ожидаемым!");
    }

    @Test
    public void checkSearchCanceledOption() {
        By input = By.xpath("//*[contains(@resource-id, 'search_container')]/android.widget.TextView");
        By inputOnSearchPage = By.id("org.wikipedia:id/search_src_text");
        By searchResults = By.xpath("//*[contains(@resource-id, 'search_results_list')]/android.view.ViewGroup");
        String searchRequest = "Java";
        waitElementAndClick(input);
        driver.findElement(inputOnSearchPage).sendKeys(searchRequest);
        List<WebElement> results = waitAllElementsIsVisible(searchResults, "Ни одного элемента по заданному локатору не найдено!");
        Assert.assertTrue("По запросу найдено не больше одного результата!",
                results.size() > 1);
        waitElementAndClick(inputOnSearchPage);
        driver.findElement(inputOnSearchPage).clear();
        Assert.assertFalse(driver.findElement(inputOnSearchPage).getAttribute("text").contains(searchRequest));
    }

    @Test
    public void checkArticleTitle() {
        By inputOnMainMenu = By.xpath("//*[contains(@resource-id, 'search_container')]/android.widget.TextView");
        By inputOnSearchPage = By.id("org.wikipedia:id/search_src_text");
        By searchResults = By.xpath("//*[contains(@resource-id, 'search_results_list')]/android.view.ViewGroup");
        By saveButton = By.xpath("//*[contains(@content-desc, 'Save')]");
        By articleTitle = By.xpath("//android.view.View[contains(@resource-id, 'section-title')]/preceding-sibling::android.view.View");
        By goBackButtonToSearch = By.xpath("//*[@content-desc='Navigate up']");
        By goBackButtonToMainMenu = By.xpath("//*[contains(@resource-id, 'search_toolbar')]/android.widget.ImageButton");
        By savedArticlesPackage = By.id("org.wikipedia:id/item_title");
        By savedArticleListXpath = By.id("org.wikipedia:id/page_list_item_title");
        String searchRequest = "Java";

        waitElementAndClick(inputOnMainMenu);
        driver.findElement(inputOnSearchPage).sendKeys(searchRequest);
        List<WebElement> results = waitAllElementsIsVisible(searchResults, "Ни одного элемента по заданному локатору не найдено!");

        results.get(0).click();
        waitElementIsVisible(articleTitle);
        waitElementAndClick(saveButton);
        waitElementAndClick(goBackButtonToSearch);

        results.get(1).click();
        waitElementIsVisible(articleTitle);
        waitElementAndClick(saveButton);
        waitElementAndClick(goBackButtonToSearch);

        waitElementAndClick(goBackButtonToMainMenu);
        waitElementAndClick(saveButton);
        waitElementAndClick(savedArticlesPackage);

        List<WebElement> savedArticleList = waitAllElementsIsVisible(savedArticleListXpath, "Ни одного элемента по заданному локатору не найдено!");

        Assert.assertTrue(savedArticleList.size() == 2);

        swipeElementToLeft(savedArticleList.get(1));
        List<WebElement> articlesListAfterSwipe = waitAllElementsIsVisible(savedArticleListXpath, "Ни одного элемента по заданному локатору не найдено!");

        Assert.assertTrue(articlesListAfterSwipe.size() == 1);

        savedArticleList.get(0).click();
        Assert.assertTrue(String.valueOf(waitElementIsVisible(articleTitle).getAttribute("text")).equals(searchRequest));
    }

    @Test
    public void checkTitleIsPresent() {
        By inputOnMainMenu = By.xpath("//*[contains(@resource-id, 'search_container')]/android.widget.TextView");
        By inputOnSearchPage = By.id("org.wikipedia:id/search_src_text");
        By searchResults = By.xpath("//*[contains(@resource-id, 'search_results_list')]/android.view.ViewGroup");
        By articleTitle = By.xpath("//android.view.View[contains(@resource-id, 'section-title')]/preceding-sibling::android.view.View");
        waitElementAndClick(inputOnMainMenu);
        driver.findElement(inputOnSearchPage).sendKeys("Java");
        waitAllElementsIsVisible(searchResults, "Ни одного элемента по заданному локатору не найдено!").get(0).click();
        assertElementPresent(articleTitle);
    }

    private void assertElementPresent(By by) {
        try {
            driver.findElement(by);
        } catch (NoSuchElementException e) {
            throw new AssertionError(String.format("На экране нет элемента по локатору %s", by));
        }
    }

    private void swipeElementToLeft(WebElement element) {
        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY)/2;

       new TouchAction(driver)
               .press(rightX, middleY)
               .waitAction(150)
               .moveTo(leftX, middleY)
               .release()
               .perform();
    }

    private WebElement waitElementIsVisible(By by) {
        String errorMessage = String.format(locatorErrorMessage, by);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private List<WebElement> waitAllElementsIsVisible(By by, String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    private void waitElementAndClick(By by) {
        waitElementIsVisible(by).click();
    }

    private void assertElementHasText(By by, String expectedText, String errorMessage) {
        Assert.assertEquals(errorMessage, expectedText, driver.findElement(by).getAttribute("text"));
    }

}
