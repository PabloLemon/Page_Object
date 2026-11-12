package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    private WebDriver driver;

    private By addCartButton = By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']");
    private By cartLink = By.xpath("//a[@data-test='shopping-cart-link']");
    private By cartBadge = By.xpath("//span[@data-test='shopping-cart-badge']");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addFirstItemToCart() {
        driver.findElement(addCartButton).click();
    }

    public void goToCart(){
        driver.findElement(cartLink).click();
    }

    public String getCartCount(){
        return driver.findElement(cartBadge).getText();
    }


}
