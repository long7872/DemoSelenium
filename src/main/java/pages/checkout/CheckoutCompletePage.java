package pages.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pages.InventoryPage;

public class CheckoutCompletePage {
    private WebDriver driver;
    private By backToProductsLink = By.id("back-to-products");

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
    }

    public InventoryPage clickBackToProductButton() {
        driver.findElement(backToProductsLink).click();
        return new InventoryPage(driver);
    }
}
