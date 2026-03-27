package tests.authentication;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import pages.InventoryPage;
import tests.BaseTests;

public class AccessTests extends BaseTests {
    @Test
    public void test_Direct_access_to_inventory_without_login_Redirects_to_login() {
        InventoryPage inventoryPage = homePage.directAccessInventoryPage();
        Assertions.assertNull(inventoryPage, "Direct Access can access to inventory page!!!");
        Assertions.assertTrue(homePage.getErrorMessage().contains("You can only access '/inventory.html' when you are logged in."), 
            "Direct access failed but dont show error");
    }
    @Test
    public void test_inventory_logout_to_home_page() {
        String username = "standard_user";
        String password = "secret_sauce";
        homePage.sendTextToUsernameField(username);
        homePage.sendTextToPasswordField(password);
        InventoryPage inventoryPage = homePage.clickLoginButton();
        inventoryPage.clickBurgerMenu();
        homePage = inventoryPage.clickLogoutButton();
        Assertions.assertNotNull(homePage, "Logout cannot redirect to home page!!!");
    }
    @Test
    public void test_After_logout_Pressing_back_does_not_reenter_inventory() {
        String username = "standard_user";
        String password = "secret_sauce";
        homePage.sendTextToUsernameField(username);
        homePage.sendTextToPasswordField(password);
        InventoryPage inventoryPage = homePage.clickLoginButton();
        inventoryPage.clickBurgerMenu();
        homePage = inventoryPage.clickLogoutButton();
        navigateBack();
        Assertions.assertFalse(homePage.isInventoryPage(), "After logout, back can redirect to home page!!!");
    }
}
