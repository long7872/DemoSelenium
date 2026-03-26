package tests.login;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import tests.BaseTests;

public class LoginTests extends BaseTests {
    @Test
    public void test_locked_out_user_CannotLogin() {
        String username = "locked_out_user";
        String password = "secret_sauce";
        homePage.sendTextToUsernameField(username);
        homePage.sendTextToPasswordField(password);
        homePage.clickLoginButton();
        Assertions.assertTrue(homePage.getErrorMessage().contains("has been locked out."), 
            "Error message incorrect or locked_out_user has been login!!!");
    }
}
