package adplotnikova.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class AndroidDownMenu extends BasePage{
    public AndroidDownMenu(AppiumDriver driver) {
        super(driver);
    }

    private final By saveButton = By.xpath("//*[contains(@content-desc, 'Save')]");

    public void clickSave() {
        waitElementAndClick(saveButton);
    }

}
