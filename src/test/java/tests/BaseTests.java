package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.HomePage;

public class BaseTests {
    private WebDriver driver;
    protected HomePage homePage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://demo.opencart.com/");
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        // driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @AfterEach
    public void tearDown() {
        driver.quit();
    }
    public static void main(String[] args) {
        BaseTests test = new BaseTests();
        test.setUp();
    }
}
