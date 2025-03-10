package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage extends BasePage{

    By salePrice = By.className("priceBox__salePrice");
    private final By countProducts = By.id("quantitySelect0-key-0");
    private final By productCard = By.className("m-basket__item");
    WebDriverWait wait; // Elementin görünür olmasını bekle


    public CartPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getCartProductPrice() {
     return driver.findElement(salePrice).getText();
    }

    public int getCountProduct() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(countProducts));
        List<WebElement> contentList = driver.findElements(productCard);


        for (WebElement content : contentList) {
            try {
                WebElement count = content.findElement(countProducts);
                Select select = new Select(count);

                List<WebElement> options = select.getOptions();
                System.out.println(options.size());
                return options.size();
            } catch (Exception e) {
                System.out.println("bulunamadı.");
            }
        }
        return 0;

    }


}
