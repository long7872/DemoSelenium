package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Utils {
    public static Boolean isHomePage(WebDriver driver) {
        By loginButtonLink = By.id("login-button");
        return !driver.findElements(loginButtonLink).isEmpty();
    }
    public static Boolean isInventoryPage(WebDriver driver) {
        return driver.getCurrentUrl().contains("inventory.html");
    }
    public static Boolean isCartPage(WebDriver driver) {
        return driver.getCurrentUrl().contains("cart.html");
    }
    public static Boolean isDetailsPage(WebDriver driver) {
        return driver.getCurrentUrl().contains("inventory-item.html");
    }
    public static Boolean isLabsPage(WebDriver driver) {
        return driver.getCurrentUrl().contains("saucelabs.com");
    }
    public static Boolean isCheckoutInfoPage(WebDriver driver) {
        return driver.getCurrentUrl().contains("checkout-step-one.html");
    }
    public static Boolean isCheckoutOverviewPage(WebDriver driver) {
        return driver.getCurrentUrl().contains("checkout-step-two.html");
    }
    public static Boolean isCheckoutCompletePage(WebDriver driver) {
        return driver.getCurrentUrl().contains("checkout-complete.html");
    }
}
