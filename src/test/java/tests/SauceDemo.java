package tests;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import pages.AuthPage;
import pages.CartPage;
import pages.InventoryPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SauceDemo {
    private WebDriver driver;
    private AuthPage authPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        authPage = new AuthPage(driver);
    }

    @AfterMethod
    public void tearDown(){
        if(driver !=null){
            driver.quit();
        }
    }
    @Test
    public void testValidLogin() {
     authPage.open();
     authPage.login("standard_user","secret_sauce");
     assertTrue(driver.getCurrentUrl().contains("inventory.html"),"URL должен содержать inventory.html");
    }
    @Test
    public void testInvalidPassword() {
        authPage.open();
        authPage.login("standard_user","wrong_pass");

        String error = authPage.getErrorText();
        assertTrue(error.contains("Username and password do not match"),"Должна быть ошибка о неверном пароле");
    }

    @Test
    public void testEmptyFields(){
        authPage.open();
        authPage.login(" ", " ");

        String error = authPage.getErrorText();
        assertTrue(error.contains("Username is required"),"Должна быть ошибка о пустом логине");
    }
    @Test
    public void testLockedOutUser() {
        authPage.open();
        authPage.login("locked_out_user", "secret_sauce");

        String error = authPage.getErrorText();
        assertTrue(error.contains("Sorry,this user has been locked out"), "Должна быть ошибка о блокировке");
    }

    @Test
    public void testAddToCartAndView() {
    authPage.open();
    authPage.login("standard_user","secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addFirstItemToCart();

        assertEquals("1",inventoryPage.getCartCount(),"Счетчик корзины не равен 1");

        inventoryPage.goToCart();
        CartPage cartPage = new CartPage(driver);

        assertTrue(cartPage.isCheckoutButtonDisplayed(),"Кнопка Checkout не видна");

        String itemName = cartPage.getItemName();
        assertEquals("Sauce Labs Backpack",itemName,"В корзине не тот товар");
    }
    }
