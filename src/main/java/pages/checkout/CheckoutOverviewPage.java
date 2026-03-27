package pages.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage {
    private WebDriver driver;
    private By finishLink = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    public CheckoutCompletePage clickFinishButton() {
        driver.findElement(finishLink).click();
        return new CheckoutCompletePage(driver);
    }
}
