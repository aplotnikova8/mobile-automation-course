import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest {

    private AppiumDriver driver;
    private final String locatorErrorMessage = "Не удалось найти нужный элемент на странцие!";

    @Before
    public void openMainWikiPage() throws Exception {
       setUp();
        By continueButton = By.xpath("//android.widget.Button[@text='CONTINUE']");
        By getStartedButton = By.xpath("//android.widget.Button[@text='GET STARTED']");
        waitElementAndClick(continueButton, locatorErrorMessage);
        waitElementAndClick(continueButton, locatorErrorMessage);
        waitElementAndClick(continueButton, locatorErrorMessage);
        waitElementAndClick(getStartedButton, locatorErrorMessage);
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
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void checkInputContainsText() {
        By input = By.xpath("//*[contains(@resource-id, 'search_container')]/android.widget.TextView");
        waitElementIsVisible(input, locatorErrorMessage);
        assertElementHasText(input, "Search Wikipedia", "Текст в инпуте не совпадает с ожидаемым!");
    }

    @Test
    public void checkSearchCanceledOption() {
        By input = By.xpath("//*[contains(@resource-id, 'search_container')]/android.widget.TextView");
        By inputOnSearchPage = By.id("org.wikipedia:id/search_src_text");
        By searchResults = By.xpath("//*[contains(@resource-id, 'search_results_list')]/android.view.ViewGroup");
        String searchRequest = "Java";
        waitElementAndClick(input, locatorErrorMessage);
        driver.findElement(inputOnSearchPage).sendKeys(searchRequest);
        List<WebElement> results = waitAllElementsIsVisible(searchResults, "Ни одного элемента по заданному локатору не найдено!");
        Assert.assertTrue("По запросу найдено не больше одного результата!",
                results.size() > 1);
        waitElementAndClick(inputOnSearchPage, locatorErrorMessage);
        driver.findElement(inputOnSearchPage).clear();
        Assert.assertFalse(driver.findElement(inputOnSearchPage).getAttribute("text").contains(searchRequest));
    }
    private WebElement waitElementIsVisible(By by, String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private List<WebElement> waitAllElementsIsVisible(By by, String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    private void waitElementAndClick(By by, String errorMessage) {
        waitElementIsVisible(by, errorMessage).click();
    }

    private void assertElementHasText(By by, String expectedText, String errorMessage) {
        Assert.assertEquals(errorMessage, expectedText, driver.findElement(by).getAttribute("text"));
    }

}
