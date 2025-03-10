package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ProductDetailPage extends BasePage{

    // Locators for elements on the product detail page
    private final By brandName = By.className("o-productDetail__brandLink");
    private final By productDetail = By.className("o-productDetail__description");
    private final By productPrice = By.id("priceNew");
    private final By activeProductLocator = By.xpath("//span[contains(@class, 'm-variation__item') and contains(@class, '-active')]");
    private final By addBasket = By.id("addBasket");
    private final By basketCount = By.className("o-header__userInfo--count");
    private final By cartIcon = By.className("icon-cart");
    private final By variationItem = By.className("m-variation__item");
    private final By productCount = By.className("o-header__userInfo--count");
    private final WebDriverWait wait; // WebDriverWait instance for waiting for elements to become visible

    public ProductDetailPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds wait time for element visibility
    }

    public String getProductPrice() { // Method to get the product price from the page
        wait.until(ExpectedConditions.visibilityOfElementLocated(productPrice));
        return driver.findElement(productPrice).getText();
    }

    public String getProductInfo(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(brandName)); // Wait for the brand name element to be visible

        // Get brand name, description, and price of the product
        WebElement brandNameElement = driver.findElement(brandName);
        String brand = brandNameElement.getText();
        WebElement productDetailElement = driver.findElement(productDetail);
        String description = productDetailElement.getText();
        String price = getProductPrice();

        // Format and return the product information as a string
        String productInfo = String.format("Brand: %s\nProduct: %s\n Price: %s",
                brand, description, price);

        return productInfo;
    }

    //write the product information to a text file
    public void writeToTxtFile(String info) {
        String idForTxtFile = new SimpleDateFormat("dd.MM.yyyy_HH.mm.ss").format(new Date());
        File file = new File("ProductInfo_" + idForTxtFile + ".txt");

        try (FileWriter fw = new FileWriter(file)) {
            fw.write(info);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Dosya yazma hatası: " + e.getMessage());
        }
    }

    public void addToCart() { // add a product to the cart, selecting a random variation if available
            List<WebElement> variations = driver.findElements(variationItem);  // Find all variations of the product
            List<WebElement> enabledVariations = new ArrayList<>();// "disabled" olmayan öğeleri filtrele
            for (WebElement variation : variations) { // Loop through variations and add enabled ones to the list
                if (!variation.getAttribute("class").contains("-disabled")) {
                    enabledVariations.add(variation);
                }
            }

            if (!enabledVariations.isEmpty()) {
                Random random = new Random();// Rastgele bir öğe seç
                WebElement randomVariation = enabledVariations.get(random.nextInt(enabledVariations.size()));
                randomVariation.click();
                System.out.println("Clicked on: " + randomVariation.getText());
            } else {
                System.out.println("No enabled variations found.");
            }

        clickToWebElement(addBasket);
        clickToWebElement(cartIcon);
    }


    public String getProductCount() {
        return driver.findElement(productCount).getText();
    }
}
