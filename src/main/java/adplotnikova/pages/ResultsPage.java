package adplotnikova.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static adplotnikova.MyConstants.LOCATOR_FOR_LIST_ERROR_MESSAGE;

public class ResultsPage extends BasePage{
    public ResultsPage(AppiumDriver driver) {
        super(driver);
    }

    private final By searchResults = By.xpath("//*[contains(@resource-id, 'search_results_list')]/android.view.ViewGroup");

    public List<WebElement> getResultsList() {
        return waitAllElementsIsVisible(searchResults, LOCATOR_FOR_LIST_ERROR_MESSAGE);
    }


}
