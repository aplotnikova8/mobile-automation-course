package adplotnikova.pages;

import adplotnikova.MyConstants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {

    protected AppiumDriver driver;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
    }

    protected void swipeElementToLeft(WebElement element) {
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

    protected WebElement waitElementIsVisible(By by) {
        String errorMessage = String.format(MyConstants.LOCATOR_ERROR_MESSAGE, by);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected List<WebElement> waitAllElementsIsVisible(By by, String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    protected void waitElementAndClick(By by) {
        waitElementIsVisible(by).click();
    }

    protected void assertElementPresent(By by) {
        try {
            driver.findElement(by);
        } catch (NoSuchElementException e) {
            throw new AssertionError(String.format(MyConstants.LOCATOR_ERROR_MESSAGE, by));
        }
    }

    protected void assertElementHasText(By by, String expectedText, String errorMessage) {
        Assert.assertEquals(errorMessage, expectedText, driver.findElement(by).getAttribute("text"));
    }
}
