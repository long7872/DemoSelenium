package tests.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import pages.CartPage;
import pages.InventoryPage;
import pages.Utils;
import pages.checkout.CheckoutCompletePage;
import pages.checkout.CheckoutInfoPage;
import pages.checkout.CheckoutOverviewPage;
import tests.BaseTests;

public class StandardUserTests extends BaseTests {
    @Test
    public void test39_standard_user_can_complete_full_checkout_flow() {
        InventoryPage inventoryPage = loginAsStandardUser();
        inventoryPage.addToCartByName("Sauce Labs Backpack");
        
        CartPage cartPage = inventoryPage.clickCartButton();
        Assertions.assertTrue(Utils.isCartPage(this.driver), 
            "Clicked Cart button in Inventory Page not redirect to Cart Page!!!");

        CheckoutInfoPage checkoutInfoPage = cartPage.clickCheckoutButton();
        Assertions.assertTrue(Utils.isCheckoutInfoPage(this.driver), 
            "Clicked Checkout button in Cart Page not redirect to Checkout Infomation Page!!!");
        
        checkoutInfoPage.sendTextToFirstNameField("John");
        checkoutInfoPage.sendTextToLastNameField("Doe");
        checkoutInfoPage.sendTextToPostalField("50000");
        CheckoutOverviewPage checkoutOverviewPage = checkoutInfoPage.clickContinueButton();
        Assertions.assertTrue(Utils.isCheckoutOverviewPage(this.driver), 
            "Clicked Continue button in Checkout Infomation Page not redirect to Checkout Overview Page!!!");

        CheckoutCompletePage checkoutCompletePage = checkoutOverviewPage.clickFinishButton();
        Assertions.assertTrue(Utils.isCheckoutCompletePage(this.driver), 
            "Clicked Finish button in Checkout Overview Page not redirect to Checkout Complete Page!!!");

        inventoryPage = checkoutCompletePage.clickBackToProductButton();
        Assertions.assertTrue(Utils.isInventoryPage(this.driver), 
            "Clicked Back To Products button in Checkout Complete Page not redirect to Inventory Page!!!");
    }
}
