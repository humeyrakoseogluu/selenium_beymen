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

    private final By searchBoxLocator = By.className("o-header__search--input");
    private final By clearSearchBox = By.className("o-header__search--close");
    private final By rejectCookiesModal = By.id("onetrust-reject-all-handler");
    private final By closeGenderModal = By.className("o-modal__closeButton");
    private final By input = By.id("o-searchSuggestion__input");

    WebDriverWait wait; // Elementin görünür olmasını bekle

    public HomePage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterSearchInput(String searchText) throws InterruptedException {
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
        clickToWebElement(clearSearchBox);
        //WebElement searchInput = driver.findElement(searchBoxLocator);
        //searchInput.sendKeys(Keys.DELETE);
    }


    public void closeModals() {
        //windowsHandle
        wait.until(ExpectedConditions.visibilityOfElementLocated(rejectCookiesModal));
        clickToWebElement(rejectCookiesModal);
        wait.until(ExpectedConditions.visibilityOfElementLocated(closeGenderModal));
        clickToWebElement(closeGenderModal);

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
