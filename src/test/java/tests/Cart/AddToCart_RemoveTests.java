package tests.Cart;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import pages.InventoryPage;
import tests.BaseTests;

public class AddToCart_RemoveTests extends BaseTests {

    @Test
    public void test20_AddOneProductToCart() {  
        InventoryPage inventoryPage = loginAsStandardUser(); 

        //Check inventory page or not
        Assertions.assertTrue(driver.getCurrentUrl().contains("inventory"));

        inventoryPage.addToCartByName("Sauce Labs Backpack");

        Assert.assertEquals(1, inventoryPage.getCartCount());
    }


    @Test
    public void test21_AddMultipleProductToCart() {
        InventoryPage inventoryPage = loginAsStandardUser(); 

        //Check inventory page or not
        Assertions.assertTrue(driver.getCurrentUrl().contains("inventory"));

        inventoryPage.addToCartByName("Sauce Labs Backpack");
        inventoryPage.addToCartByName("Sauce Labs Bike Light");

        Assert.assertEquals(2, inventoryPage.getCartCount());
    }


    @Test
    public void test22_AddToCartButtonChangeToRemoveButton() {
        InventoryPage inventoryPage = loginAsStandardUser();

        //Check inventory page or not
        Assertions.assertTrue(driver.getCurrentUrl().contains("inventory"));

        inventoryPage.addToCartByName("Sauce Labs Bike Light");

        Assert.assertTrue(inventoryPage.isRemoveButtonDisplayed("Sauce Labs Bike Light"));
    }


    @Test
    public void test23_RemoveDecreasesCartCount() {
        InventoryPage inventoryPage = loginAsStandardUser();

        //Check inventory page or not
        Assertions.assertTrue(driver.getCurrentUrl().contains("inventory"));

        inventoryPage.clickAllAddToCartButton();
        int numberOfItemBeforeRemove = inventoryPage.getCartCount();

        inventoryPage.removeByName("Sauce Labs Bike Light");
        int numberOfItemAfterRemove = inventoryPage.getCartCount();
        
        Assert.assertEquals(numberOfItemBeforeRemove, numberOfItemAfterRemove + 1);
    }
    
    
    @Test
    public void test24_RemoveAllProductHideCartBadge() {
        InventoryPage inventoryPage = loginAsStandardUser();

        //Check inventory page or not
        Assertions.assertTrue(driver.getCurrentUrl().contains("inventory"));

        inventoryPage.clickAllAddToCartButton();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        inventoryPage.clickAllRemoveButton();

        Assert.assertFalse(inventoryPage.hasQuantityBadge());
    }

    
    @Test
    public void test25_AddAll6ProductToCart() {
        InventoryPage inventoryPage = loginAsStandardUser();

        //Check inventory page or not
        Assertions.assertTrue(driver.getCurrentUrl().contains("inventory"));

        inventoryPage.clickAllAddToCartButton();
        Assert.assertEquals(6, inventoryPage.getCartCount());
    }


    @Test
    public void test26_ProductCartStateAndSelectionPreservedAfterSorting() {
        InventoryPage inventoryPage = loginAsStandardUser();

        Assertions.assertTrue(driver.getCurrentUrl().contains("inventory"));

        String product1 = "Sauce Labs Backpack";
        String product2 = "Sauce Labs Bike Light";
        inventoryPage.addToCartByName(product1);
        inventoryPage.addToCartByName(product2);

        Assert.assertTrue(inventoryPage.isProductAdded(product1));
        Assert.assertTrue(inventoryPage.isProductAdded(product2));
        int numberOfItemInCartBefore = inventoryPage.getCartCount();
        Assert.assertEquals(2, numberOfItemInCartBefore);


        inventoryPage.selectSort("Price (low to high)");


        Assert.assertTrue(inventoryPage.isProductAdded(product1));
        Assert.assertTrue(inventoryPage.isProductAdded(product2));
        int numberOfItemInCartAfter = inventoryPage.getCartCount();

        Assert.assertEquals(numberOfItemInCartBefore, numberOfItemInCartAfter);
    }


    @Test
    public void test27_ProductCartStateAndSelectionPreservedAfterNavigation() {
        InventoryPage inventoryPage = loginAsStandardUser();

        Assertions.assertTrue(driver.getCurrentUrl().contains("inventory"));

        String product = "Sauce Labs Backpack";
        inventoryPage.addToCartByName(product);

        // Check
        Assert.assertTrue(inventoryPage.isProductAdded(product));
        int numberOfItemInCartBefore = inventoryPage.getCartCount();
        Assert.assertEquals(1, numberOfItemInCartBefore);

        // Move to product detail
        inventoryPage.clickProductName(product);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.navigate().back();

        // Recheck
        Assert.assertTrue(inventoryPage.isProductAdded(product));
        int numberOfItemInCartAfter = inventoryPage.getCartCount();
        Assert.assertEquals(numberOfItemInCartBefore, numberOfItemInCartAfter);
    }
}
