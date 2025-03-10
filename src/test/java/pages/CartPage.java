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

    // Locators for elements on the cart page
    By salePrice = By.className("priceBox__salePrice");
    private final By countProducts = By.id("quantitySelect0-key-0");
    private final By productCard = By.className("m-basket__item");
    private final By removeProductButton = By.className("m-basket__remove"); // Remove product button
    private final By emptyCartMessage = By.className("m-empty__message"); // Message when the cart is empty

    WebDriverWait wait; // Wait for elements to be visible or clickable


    public CartPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getCartProductPrice() { // get the sale price of the product in the cart
     return driver.findElement(salePrice).getText();
    }

    public int getCountProduct() { //get the count of products in the cart
        wait.until(ExpectedConditions.visibilityOfElementLocated(countProducts));
        List<WebElement> contentList = driver.findElements(productCard);

        // Locate the quantity dropdown for each product
        for (WebElement content : contentList) {
            try {
                WebElement count = content.findElement(countProducts);
                Select select = new Select(count);

                List<WebElement> options = select.getOptions();
                System.out.println(options.size());
                return options.size();
            } catch (Exception e) {
                System.out.println("Product not found.");
            }
        }
        return 0;  // Return 0 if no products were found or quantity options were not found

    }

    public void removeProductFromCart() {// Method to remove a product from the cart
        wait.until(ExpectedConditions.elementToBeClickable(removeProductButton)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(productCard)); // Wait until the product is no longer in the cart (element becomes invisible)
    }

    public boolean isCartEmpty() {// Method to check if the cart is empty
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(emptyCartMessage));
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
