package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    WebDriver driver;
    private By burgerMenuLink = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickBurgerMenu() {
        driver.findElement(burgerMenuLink).click();
    }
    public HomePage clickLogoutButton() {
        driver.findElement(logoutLink).click();
        return new HomePage(driver);
    }
}
