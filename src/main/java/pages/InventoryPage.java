package pages;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryPage {
    WebDriver driver;
    
    private By addToCartButton = By.cssSelector(".btn.btn_primary.btn_small.btn_inventory");
    private By removeButton = By.cssSelector(".btn.btn_secondary.btn_small.btn_inventory");
    private By cartLink = By.className("shopping_cart_link");
    private By quantityCartLink = By.className("shopping_cart_badge");

    private By sortDropdown = By.className("product_sort_container");
    private By itemNames = By.className("inventory_item_name");
    private By itemPrices = By.className("inventory_item_price");
    private By itemDescriptions = By.className("inventory_item_desc");
    private By itemImages = By.cssSelector(".inventory_item_img img");
    private By itemButtons = By.className("btn_inventory");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }
    
    // Add one
    public void addToCartByName(String productName) {
        String id = "add-to-cart-" + productName.toLowerCase().replace(" ", "-");
        driver.findElement(By.id(id)).click();
    }

    // Add all 
    public void clickAllAddToCartButton() {
        List<WebElement> buttons = driver.findElements(addToCartButton);

        for(int i = 0; i < buttons.size(); i++) {
            buttons.get(i).click();
        }
    }

    // Remove one
    public void removeByName(String productName) {
        String id = "remove-" + productName.toLowerCase().replace(" ", "-");
        driver.findElement(By.id(id)).click();
    }

    // Remove all
    public void clickAllRemoveButton() {
        List<WebElement> buttons = driver.findElements(removeButton);

        for(int i = 0; i < buttons.size(); i++) {
            buttons.get(i).click();
        }
    }
    
    public int getCartCount() {
        return Integer.parseInt(driver.findElement(cartLink).getText());
    }

    public boolean isRemoveButtonDisplayed(String productName) {
        String id = "remove-" + productName.toLowerCase().replace(" ", "-");
        return driver.findElements(By.id(id)).size() > 0;
    }    

    public boolean isProductAdded(String productName) {
        String id = "remove-" + productName.toLowerCase().replace(" ", "-");
        return driver.findElements(By.id(id)).size() > 0;
    }

    public Boolean hasQuantityBadge() {
        return !driver.findElements(quantityCartLink).isEmpty();
    }








    public void selectSort(String text) {
        new Select(driver.findElement(sortDropdown)).selectByVisibleText(text);
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getSelectedSortTest() {
        Select select = new Select(driver.findElement(sortDropdown));

        return select.getFirstSelectedOption().getText();
    }

    public int getProductCount() {
        return driver.findElements(itemNames).size();
    }

    // Kiểm tra tính đủ
    public boolean allProductsHaveFullInfo() {
        int names = driver.findElements(itemNames).size();
        int decs = driver.findElements(itemDescriptions).size();
        int prices = driver.findElements(itemPrices).size();
        int images = driver.findElements(itemImages).size();
        int buttons = driver.findElements(itemButtons).size();

        int productCount = getProductCount();
        return (names == productCount && decs == productCount && prices == productCount && images == productCount && buttons == productCount);
    }

    public List<String> getProductNames() {
        return driver.findElements(itemNames).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<Double> getProductPrices() {
        return driver.findElements(itemPrices).stream().map(e -> Double.parseDouble(e.getText().replace("$", ""))).collect(Collectors.toList());
    }

    // Lấy còn $ 
    public List<String> getProductPriceStrings() {
        return driver.findElements(itemPrices).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public boolean areAllImagesLoaded() {
        try { Thread.sleep(3000); } catch (InterruptedException e) {}

        List <WebElement> images = driver.findElements(itemImages);
        boolean allLoaded = true;

        for (WebElement img : images) {
            Object isLoaded = ((JavascriptExecutor) driver).executeScript(
                "return (typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0);", img
            );

            boolean loaded = (Boolean) isLoaded;
            if (!loaded) {
                System.err.println("Broken image detected: " + img.getAttribute("src"));
                allLoaded = false;
            }
        }

        return allLoaded;
    }

    public void clickProductName(String name) {
        By locator = By.xpath("//div[text()='" + name + "']");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement element = wait.until(
            ExpectedConditions.elementToBeClickable(locator)
        );

        element.click();

        wait.until(ExpectedConditions.urlContains("inventory-item"));
    }
}
