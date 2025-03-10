package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import pages.HomePage;
import pages.ProductDetailPage;
import pages.ProductsPage;
import utilities.DriverManager;
import utilities.ExcelReader;
import java.time.Duration;


public class BeymenTestStepDefinitons {

    private WebDriver driver;
    private static final String BASE_URL = "https://www.beymen.com";
    private static final String filePath = "src/test/java/utilities/products.xlsx";
    private String productDetailPrice;

    private SoftAssert softAssert;
    private HomePage homePage;
    private ExcelReader excelReader;
    private ProductsPage productsPage;
    private ProductDetailPage detailPage;
    private CartPage cartPage;

    @Given("setting driver")
    public void settingDriver() {
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        initializePages();
        softAssert = new SoftAssert();
        excelReader = new ExcelReader();
    }

    private void initializePages() {
        homePage = new HomePage(driver);
        productsPage = new ProductsPage(driver);
        detailPage = new ProductDetailPage(driver);
        cartPage = new CartPage(driver);
    }

    @Given("go to Beymen website")
    public void go_to_beymen_website() {
        driver.get(BASE_URL);
        homePage.closeModals();
    }

    @Then("verify that is home website")
    public void verify_that_is_home_website() {
        softAssert.assertTrue(homePage.getTitle().equals("Beymen.com – Türkiye’nin Tek Dijital Lüks Platformu"));
    }

    @When("search for sort word")
    public void search_for_sort_word() throws InterruptedException {
        String shorts = excelReader.searchFromExcel(filePath,0,0,0);
        System.out.println(shorts);
        homePage.enterSearchInput(shorts);
    }


    @And("clear the search box")
    public void clear_the_search_box() throws InterruptedException {
        Thread.sleep(2000);
        homePage.deleteSearchInput();
    }

    @When("search and enter for gomlek word")
    public void search_for_shirts() throws InterruptedException {
        Thread.sleep(2000);
        String shirts = excelReader.searchFromExcel(filePath, 0, 0, 1);
        System.out.println(shirts);
        homePage.getResult(shirts);
    }


    @Then("select a random product from search result")
    public void select_a_random_product_from_search_result() {
        productsPage.clickRandomProduct();
    }

    @And("write the product info and price info to a txt file")
    public void write_the_product_info_and_price_info_to_a_txt_file() {
        String productInfo = detailPage.getProductInfo();
        detailPage.writeToTxtFile(productInfo);
        productDetailPrice = detailPage.getProductPrice();
    }

    @And("add this selected product to the cart")
    //önce beden seç sonra sepete ekle
    public void add_this_selected_product_to_the_cart() throws InterruptedException {
        Thread.sleep(2000);
        detailPage.addToCart();
        Thread.sleep(3000);
    }

    @Then("verify the price of the product and the price in the cart")
    public void verify_the_price_of_the_product_and_the_price_in_the_cart() {
      String cartPrice = cartPage.getCartProductPrice();
      softAssert.assertEquals(cartPrice, productDetailPrice, "Product price in cart does not match the price on product detail page");
    }

     @When("increase the quantity to 2")
     public void increase_the_quantity_to() throws InterruptedException {
        Thread.sleep(2000);
         driver.navigate().back();
         Thread.sleep(2000);
         add_this_selected_product_to_the_cart();
     }

    @Then("verify that the quantity")
    public void verify_that_the_quantitiy_is() {
        int cartCount = cartPage.getCountProduct();
        softAssert.assertEquals(cartCount, 2, "The product count in cart does not match the count increased");

    }
 //   @When("remove the product from the cart")
 //   public void remove_the_product_from_the_cart() {
 //       // Write code here that turns the phrase above into concrete actions
 //       throw new io.cucumber.java.PendingException();
 //   }
 //   @Then("verify that the cart is empty")
 //   public void verify_that_the_cart_is_empty() {
 //       // Write code here that turns the phrase above into concrete actions
 //       throw new io.cucumber.java.PendingException();
 //   }
//
}
