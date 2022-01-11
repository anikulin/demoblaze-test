package com.demoblaze.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.demoblaze.test.model.Cart;
import com.demoblaze.test.model.Product;
import com.demoblaze.test.model.Store; 

/**
 * Unit test for simple App.
 */
public class CartApiTest {
    private static final String BASE_URL = "https://api.demoblaze.com";

    private static final int PRODUCT_ID_1 = 7; // HTC One M9
    
    private static Store store;
    
    @BeforeClass( alwaysRun = true )
    public static void setup() {
    // set the base url for requests
        store = new Store(getBaseUrl());
    }

    @BeforeMethod( alwaysRun = true )
    public void setupSession() {
    // reset the user to get new cart
        store.startNewSession();
    }
    
    /**
     * Adding products to the cart
     * @throws Exception 
     */
    @Test( groups = { "add" } )
    public void shouldPlaceProductToCart() throws Exception {
        // given I have an empty cart
        // when I add a product
        Product p = new Product(PRODUCT_ID_1);
        store.putProduct(p);
        
        // then cart has 1 item
        Cart cart = store.getCart();
        assertThat( cart.size(), equalTo(1) );
        // and the item I added is in the cart
        assertThat( cart.getItems().values(), hasItems(p) );
    }

    /**
     * Removing product from the cart
     * @throws Exception 
     */
    @Test( groups = { "remove" } )
    public void shouldRemoveProductFromCart() throws Exception {
        // given I have added 1 product to the cart
        Product p = new Product(PRODUCT_ID_1);
        String itemId = store.putProduct(p);

        // when I remove the product
        store.removeProduct(itemId);

        // then cart is empty
        Cart cart = store.getCart();
        assertThat(cart.size(), equalTo(0));
    }

    /**
     * Returns user-defined base url
     * @return
     */
    private static String getBaseUrl() {
        return System.getProperty("url", BASE_URL);
    }
    
}
