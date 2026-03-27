package tests;

import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import pages.HomePage;
import pages.InventoryPage;

public class BaseTests {
    protected WebDriver driver;
    protected HomePage homePage;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        Duration timeout = Duration.ofSeconds(15);
        if (timeout != null) {
            driver.manage().timeouts().implicitlyWait(timeout);
        }
    }
    @AfterEach
    public void tearDown() {
        try {
            Thread.sleep(Duration.ofSeconds(3));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    protected InventoryPage loginAsStandardUser() {
        String username = "standard_user";
        String password = "secret_sauce";
        homePage.sendTextToUsernameField(username);
        homePage.sendTextToPasswordField(password);
        return homePage.clickLoginButton();
    }
    
    public static void main(String[] args) {
        BaseTests test = new BaseTests();
        test.setUp();
    }
}
