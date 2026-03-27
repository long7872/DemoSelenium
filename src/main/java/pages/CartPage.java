package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pages.checkout.CheckoutInfoPage;

public class CartPage {
    private WebDriver driver;
    private By checkoutLink = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public CheckoutInfoPage clickCheckoutButton() {
        driver.findElement(checkoutLink).click();
        return new CheckoutInfoPage(driver);
    }
}
