package tests.login;

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
    public void all_user_except_locked_out_user_can_login() {
        String[] usernames = {
            "standard_user",
            "problem_user",
            "performance_glitch_user",
            "error_user",
            "visual_user"
        };
        String password = "secret_sauce";
        for (String username : usernames) {
            homePage.sendTextToUsernameField(username);
            homePage.sendTextToPasswordField(password);
            InventoryPage inventoryPage = homePage.clickLoginButton();
            Assertions.assertNotNull(inventoryPage, "User " + username + " failed to login!!!");
            if (inventoryPage != null) inventoryPage.navigateBack();
        }
    }
}
