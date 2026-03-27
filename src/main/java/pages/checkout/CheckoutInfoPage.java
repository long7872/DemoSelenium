package pages.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutInfoPage {
    private WebDriver driver;
    private By firstNameLink = By.id("first-name");
    private By lastNameLink = By.id("last-name");
    private By postalLink = By.id("postal-code");
    private By continueLink = By.id("continue");

    public CheckoutInfoPage(WebDriver driver) {
        this.driver = driver;
    }

    public void sendTextToFirstNameField(String text) {
        driver.findElement(firstNameLink).sendKeys(text);
    }
    public void sendTextToLastNameField(String text) {
        driver.findElement(lastNameLink).sendKeys(text);
    }
    public void sendTextToPostalField(String text) {
        driver.findElement(postalLink).sendKeys(text);
    }
    public CheckoutOverviewPage clickContinueButton() {
        driver.findElement(continueLink).click();
        return new CheckoutOverviewPage(driver);
    }
}
