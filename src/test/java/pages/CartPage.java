package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
   private WebDriver driver;

   private By cartItem = By.xpath("//div[@data-test='inventory-item-name']");
   private By checkButton = By.xpath("//button[@id='checkout']");

   public CartPage(WebDriver driver){
       this.driver = driver;
   }

   public String getItemName(){
       return driver.findElement(cartItem).getText();
   }

   public boolean isCheckoutButtonDisplayed(){
       return driver.findElement(checkButton).isDisplayed();
   }
}
