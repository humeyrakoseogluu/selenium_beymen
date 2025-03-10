package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class ProductsPage extends BasePage{

    By productItems = By.className("o-productList__itemWrapper");


    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    // Yeni metot ekleniyor
    public void clickRandomProduct() {
        List<WebElement> products = driver.findElements(productItems);
        if (!products.isEmpty()) {
            int randomIndex = new Random().nextInt(products.size());
            products.get(randomIndex).click();
        } else {
            throw new RuntimeException("Ürün listesi boş.");
        }
    }

}
