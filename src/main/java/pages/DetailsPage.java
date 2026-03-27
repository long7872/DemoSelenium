package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DetailsPage {
    private WebDriver driver;
    private By backToProductsLink = By.id("back-to-products");

    public DetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public InventoryPage clickBackToProductsButton() {
        driver.findElement(backToProductsLink).click();
        return new InventoryPage(driver);
    }
}