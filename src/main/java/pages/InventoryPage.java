package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryPage {
    WebDriver driver;
    private By burgerMenuLink = By.id("react-burger-menu-btn");
    private By sideBarLink = By.cssSelector("div .bm-menu-wrap[aria-hidden='false']");
    private By allItemsLink = By.id("inventory_sidebar_link");
    private By aboutLink = By.id("about_sidebar_link");
    private By logoutLink = By.id("logout_sidebar_link");
    private By resetLink = By.id("reset_sidebar_link");
    private By cartLink = By.cssSelector("a.shopping_cart_link[data-test='shopping-cart-link']");
    private By quantityCartLink = By.cssSelector("span.shopping_cart_badge[data-test='shopping-cart-badge']");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickBurgerMenu() {
        driver.findElement(burgerMenuLink).click();
    }
    public InventoryPage clickAllItemsButton() {
        driver.findElement(allItemsLink).click();
        return new InventoryPage(driver);
    }
    public HomePage clickLogoutButton() {
        driver.findElement(logoutLink).click();
        return new HomePage(driver);
    }
    public HomePage clickResetButton() {
        driver.findElement(resetLink).click();
        return new HomePage(driver);
    }
    public LabsPage clickAboutButton() {
        driver.findElement(aboutLink).click();
        return new LabsPage(driver);
    }
    public CartPage clickCartButton() {
        driver.findElement(cartLink).click();
        return new CartPage(driver);
    }
    public void addToCartByName(String productName) {
        String id = "add-to-cart-" + productName.toLowerCase().replace(" ", "-");
        System.out.println(id);
        driver.findElement(By.id(id)).click();
    }
    public int getQuantityItemsInCart() {
        WebElement quantityElement = driver.findElement(quantityCartLink);
        return Integer.parseInt(quantityElement.getText());
    }
    public DetailsPage clickProductName(String name) {
        By productNameLocator = By.xpath("//div[contains(text(), '" + name + "')]");
        driver.findElement(productNameLocator).click();
        return new DetailsPage(driver);
    }

    public Boolean isSideBarOpen() {
        return !driver.findElements(sideBarLink).isEmpty();
    }
    public Boolean isAllItemsShown() {
        return !driver.findElements(allItemsLink).isEmpty();
    }
    public Boolean isAboutShown() {
        return !driver.findElements(aboutLink).isEmpty();
    }
    public Boolean isLogoutShown() {
        return !driver.findElements(logoutLink).isEmpty();
    }
    public Boolean isResetShown() {
        return !driver.findElements(resetLink).isEmpty();
    }
    public Boolean hasQuantityBadge() {
        return !driver.findElements(quantityCartLink).isEmpty();
    }
}
