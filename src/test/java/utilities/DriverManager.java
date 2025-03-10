package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
    static WebDriver driver; //static program calistirilmadan onun bellekteki yeri acilir

    public static  WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();// Initialize the WebDriver with ChromeDriver
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();// Close the browser and clean up the WebDriver
            driver = null;
        }
    }
}