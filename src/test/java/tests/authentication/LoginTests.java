package tests.authentication;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import pages.InventoryPage;
import tests.BaseTests;

public class LoginTests extends BaseTests {
    @Test
    public void test_locked_out_user_cannot_login() {
        String username = "locked_out_user";
        String password = "secret_sauce";
        homePage.sendTextToUsernameField(username);
        homePage.sendTextToPasswordField(password);
        homePage.clickLoginButton();
        Assertions.assertTrue(homePage.getErrorMessage().contains("has been locked out."), 
            "Error message incorrect or locked_out_user has been login!!!");
    }
    @Test
    public InventoryPage test_standard_user_can_login_successfully() {
        String username = "standard_user";
        String password = "secret_sauce";
        homePage.sendTextToUsernameField(username);
        homePage.sendTextToPasswordField(password);
        InventoryPage inventoryPage = homePage.clickLoginButton();
        Assertions.assertNotNull(inventoryPage, "User " + username + " failed to login!!!");
        return inventoryPage;
    }
    @Test
    public void test_invalid_credentials_show_error_message() {
        String username = "test_user";
        String password = "secret_sauce";
        homePage.sendTextToUsernameField(username);
        homePage.sendTextToPasswordField(password);
        homePage.clickLoginButton();
        Assertions.assertTrue(homePage.getErrorMessage().contains("Username and password do not match any user in this service"), 
            "Invalid Credentials Dont Show Error Message!!!");
    }
    @Test
    public void test_empty_username_shows_error_message() {
        String username = "";
        String password = "secret_sauce";
        homePage.sendTextToUsernameField(username);
        homePage.sendTextToPasswordField(password);
        homePage.clickLoginButton();
        Assertions.assertTrue(homePage.getErrorMessage().contains("Username is required"), 
            "Empty Username Dont Show Error Message!!!");
    }
    @Test
    public void test_empty_password_shows_error_message() {
        String username = "test_user";
        String password = "";
        homePage.sendTextToUsernameField(username);
        homePage.sendTextToPasswordField(password);
        homePage.clickLoginButton();
        Assertions.assertTrue(homePage.getErrorMessage().contains("Password is required"), 
            "Empty Password Dont Show Error Message!!!");
    }
}
