package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {

    protected WebDriver driver;

    // constructor method
    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public void navigateTo(String URL){ // Method to navigate to a specific URL
        driver.get(URL);
    }

    public void clickToWebElement(By locator){ // Method to click on a web element identified by a locator
        driver.findElement(locator).click();
    }

    public String getTitle(){// Method to get the title of the current page
        return driver.getTitle();
    }


}
