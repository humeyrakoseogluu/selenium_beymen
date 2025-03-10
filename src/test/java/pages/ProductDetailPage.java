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

    By brandName = By.className("o-productDetail__brandLink");
    By productDetail = By.className("o-productDetail__description");
    By productPrice = By.id("priceNew");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Elementin görünür olmasını bekle
    By activeProductLocator = By.xpath("//span[contains(@class, 'm-variation__item') and contains(@class, '-active')]");
    By addBasket = By.id("addBasket");
    By basketCount = By.className("o-header__userInfo--count");
    By cartIcon = By.className("icon-cart");
    By variationItem = By.className("m-variation__item");
    By productCount = By.className("o-header__userInfo--count");

    public ProductDetailPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getProductPrice() {
        WebElement productNewPriceElement = driver.findElement(productPrice);
        return productNewPriceElement.getText();
    }

    public String getProductInfo(){
        // Ürün detay sayfasının yüklenmesini bekleyin
        wait.until(ExpectedConditions.visibilityOfElementLocated(brandName));

        WebElement brandNameElement = driver.findElement(brandName);
        String brand = brandNameElement.getText();
        WebElement productDetailElement = driver.findElement(productDetail);
        String description = productDetailElement.getText();
        String price = getProductPrice();

        String productInfo = String.format("Brand: %s\nProduct: %s\n Price: %s",
                brand, description, price);

        return productInfo;
    }

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

    public void addToCart() {

            // Tüm öğeleri al
            List<WebElement> variations = driver.findElements(variationItem);

            // "disabled" olmayan öğeleri filtrele
            List<WebElement> enabledVariations = new ArrayList<>();
            for (WebElement variation : variations) {
                if (!variation.getAttribute("class").contains("-disabled")) {
                    enabledVariations.add(variation);
                }
            }

            // Rastgele bir öğe seç
            if (!enabledVariations.isEmpty()) {
                Random random = new Random();
                WebElement randomVariation = enabledVariations.get(random.nextInt(enabledVariations.size()));

                // Öğeye tıkla
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
