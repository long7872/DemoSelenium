package pages;

import org.jspecify.annotations.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;
    @NonNull private By usernameFieldLink = By.id("user-name");
    @NonNull private By passwordLink = By.id("password");
    @NonNull private By loginButtonLink = By.id("login-button");
    @NonNull private By errorMessageLink = By.cssSelector("h3[data-test='error']");
    
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void sendTextToUsernameField(String text) {
        driver.findElement(usernameFieldLink).sendKeys(text);
    }
    public void sendTextToPasswordField(String text) {
        driver.findElement(passwordLink).sendKeys(text);
    }
    public InventoryPage clickLoginButton() {
        driver.findElement(loginButtonLink).click();

        if (isInventoryPage()) {
            return new InventoryPage(driver); 
        }
        return null;
    }
    public String getErrorMessage() {
        WebElement error = driver.findElement(errorMessageLink);
        return error.getText();
    }
    public InventoryPage directAccessInventoryPage() {
        driver.get("https://www.saucedemo.com/inventory.html");
        
        if (isInventoryPage()) {
            return new InventoryPage(driver); 
        }
        return null;
    }
    public Boolean isInventoryPage() {
        return driver.getCurrentUrl().contains("inventory.html");
    }
}
