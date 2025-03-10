package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {

    protected WebDriver driver;

    // constructor method
    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public void navigateTo(String URL){
        driver.get(URL);
    }

    public void clickToWebElement(By locator){
        driver.findElement(locator).click();
    }

    public String getTitle(){
        return driver.getTitle();
    }


}
