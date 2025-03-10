package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class HomePage extends BasePage{

    // Locators for elements on the homepage
    private final By searchBoxLocator = By.className("o-header__search--input");
    private final By clearSearchBox = By.className("o-header__search--close");
    private final By rejectCookiesModal = By.id("onetrust-reject-all-handler");
    private final By closeGenderModal = By.className("o-modal__closeButton");
    private final By input = By.id("o-searchSuggestion__input");

    WebDriverWait wait; // Wait for elements to be visible or clickable

    public HomePage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

     // enter a search term into the search box
    public void enterSearchInput(String searchText){
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBoxLocator));
        WebElement searchInput = driver.findElement(searchBoxLocator);
        searchInput.sendKeys(searchText);
    }

    public void getResult(String searchText) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(input));
        WebElement searchInput = driver.findElement(input);
        searchInput.sendKeys(searchText + Keys.ENTER);
    }

    public void deleteSearchInput() {
        if (isElementPresent(clearSearchBox)) {
            clickToWebElement(clearSearchBox);
        }
    }

    public void closeModals() {
        if (isElementPresent(rejectCookiesModal)) {
            clickToWebElement(rejectCookiesModal);
        }
        if (isElementPresent(closeGenderModal)) {
            clickToWebElement(closeGenderModal);
        }
    }

    public boolean isElementPresent(By locator) {
    try {
        driver.findElement(locator);
        return true;
    } catch (NoSuchElementException e) {
        return false;
    }
}



    /*
    public void closeModals() {
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        Iterator<String> iterator = allWindowHandles.iterator();

        while (iterator.hasNext()) {
            String popupHandle = iterator.next();
            if (!popupHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(popupHandle);
                driver.close();
            }
        }

        driver.switchTo().window(mainWindowHandle);
    }
     */

}
