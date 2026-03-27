package tests.navigation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import pages.DetailsPage;
import pages.InventoryPage;
import pages.Utils;
import tests.BaseTests;

public class NavigationTests extends BaseTests {
    @Test
    public void test28_clicking_cart_icon_navigates_to_cart_page() {
        InventoryPage inventoryPage = loginAsStandardUser();
        inventoryPage.clickCartButton();
        Assertions.assertTrue(Utils.isCartPage(this.driver), "Clicked cart button not redirect to Cart Page!!!");
    }
    @Test
    public void test29_burger_menu_opens_when_clicked() {
        InventoryPage inventoryPage = loginAsStandardUser();
        inventoryPage.clickBurgerMenu();
        Assertions.assertTrue(inventoryPage.isSideBarOpen(), "Side bar have not been opened!!!");
    }
    @Test
    public void test30_burger_menu_contains_all_items() {
        InventoryPage inventoryPage = loginAsStandardUser();
        inventoryPage.clickBurgerMenu();
        Assertions.assertTrue(inventoryPage.isAllItemsShown(), "All Items button have not been shown!!!");
        Assertions.assertTrue(inventoryPage.isAboutShown(), "About button have not been shown!!!");
        Assertions.assertTrue(inventoryPage.isLogoutShown(), "Logout button have not been shown!!!");
        Assertions.assertTrue(inventoryPage.isResetShown(), "Reset App State button have not been shown!!!");
    }
    @Test
    public void test31_all_items_menu_navigates_to_inventory_page() {
        InventoryPage inventoryPage = loginAsStandardUser();
        inventoryPage.clickBurgerMenu();
        inventoryPage.clickAllItemsButton();
        Assertions.assertTrue(Utils.isInventoryPage(driver), "Clicked All Items button cannot navigate to Inventory Page!!!");
    }
    @Test
    public void test33_reset_app_state_clears_cart_and_resets_buttons() {
        InventoryPage inventoryPage = loginAsStandardUser();
        inventoryPage.addToCartByName("Sauce Labs Backpack");
        Assertions.assertEquals(1, inventoryPage.getQuantityItemsInCart(), "After add to cart one item, must displayed 1 value in cart bagde!!!");
        inventoryPage.clickBurgerMenu();
        inventoryPage.clickResetButton();
        Assertions.assertFalse(inventoryPage.hasQuantityBadge(), "After reset state, cart quantity badge must not displayed!!!");
    }
    @Test
    public void test34_about_menu_opens_saucelabs_website() {
        InventoryPage inventoryPage = loginAsStandardUser();
        inventoryPage.clickBurgerMenu();
        inventoryPage.clickAboutButton();
        Assertions.assertTrue(Utils.isLabsPage(driver), "Clicked about button cannot navigate to Sauce Labs Page!!!");
    }
    @Test
    public void test35_back_to_products_on_detail_page_returns_to_inventory() {
        InventoryPage inventoryPage = loginAsStandardUser();
        DetailsPage detailsPage = inventoryPage.clickProductName("Sauce Labs Backpack");
        inventoryPage = detailsPage.clickBackToProductsButton();
        Assertions.assertTrue(Utils.isInventoryPage(this.driver), "Clicked Back To Products button not redirect to Inventory Page!!!");
    }
}
