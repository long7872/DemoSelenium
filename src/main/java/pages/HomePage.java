package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private By myAccountLink = By.xpath("//*[@id=\"top\"]/div/div[2]/ul/li[2]/div/a/span");
    private By registerLink = By.linkText("Register");
    
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickMyAccountButton() {
        driver.findElement(myAccountLink).click();
    }
    public RegisterPage clickRegisterButton() {
        clickMyAccountButton();
        driver.findElement(registerLink).click();
        return new RegisterPage(driver);
    }
}
