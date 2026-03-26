package tests.register;

import org.junit.jupiter.api.Test;

import pages.RegisterPage;
import tests.BaseTests;

public class registerTests extends BaseTests {
    @Test
    public void testClick() {
        RegisterPage registerPage = homePage.clickRegisterButton();
        
    }
}
