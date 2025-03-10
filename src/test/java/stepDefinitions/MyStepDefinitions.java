package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utilities.DriverManager;

public class MyStepDefinitions {

    private static WebDriver driver;

   // @BeforeClass
   // public static void setUp(){
   //     driver = DriverManager.getDriver();
   // }
//
   // @AfterClass
   // public static void tearDown(){
   //     DriverManager.closeDriver();
   // }

    @Given("go to login page")
    public void go_to_login_page() {
        driver = DriverManager.getDriver();
        driver.get("http:www.google.com");
    }
    @And("type username")
    public void type_username() {
        System.out.println("username dolduruldu");
    }
    @And("type password")
    public void type_password() {
        System.out.println("sifre dolduruldu");
    }
    @When("click button")
    public void click_button() {
        System.out.println("butona tiklandi");
    }
    @Then("assert to success message")
    public void assert_to_success_message() {
        System.out.println("basarili mesaj goruldu");
    }


}
