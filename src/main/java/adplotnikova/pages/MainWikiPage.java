package adplotnikova.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MainWikiPage extends BasePage{

    public MainWikiPage(AppiumDriver driver) {
        super(driver);
    }

    private final By continueButton = By.xpath("//android.widget.Button[@text='CONTINUE']");
    private final By getStartedButton = By.xpath("//android.widget.Button[@text='GET STARTED']");
    private final By inputOnMainMenu = By.xpath("//*[contains(@resource-id, 'search_container')]/android.widget.TextView");


    public void openMainWikiPage() {
        waitElementAndClick(continueButton);
        waitElementAndClick(continueButton);
        waitElementAndClick(continueButton);
        waitElementAndClick(getStartedButton);
    }

    public void waitInputOnMainMenuAndClick() {
        waitElementAndClick(inputOnMainMenu);
    }

    public void waitInputOnMainMenu() {
        waitElementIsVisible(inputOnMainMenu);
    }

    public void assertInputHasText(String textInInput) {
        assertElementHasText(inputOnMainMenu, textInInput, "Текст в инпуте не совпадает с ожидаемым!");
    }

}
