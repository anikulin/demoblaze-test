package com.demoblaze.test.model;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.is;

import org.json.JSONObject;

import com.demoblaze.test.Utils;

import io.restassured.RestAssured;

public class Store {
    private static final String TYPE = "application/json";

    private String sessionId;
    
    private static final String VIEWCART_PATH = "/viewcart";
    private static final String ADDTOCART_PATH = "/addtocart";
    private static final String REMOVEFROMCART_PATH = "/deleteitem";
    
    public Store(String baseUrl) {
        RestAssured.baseURI = baseUrl; 
    }

    public void startNewSession() {
        sessionId = Utils.getUserId();
    }

    public String putProduct(Product p) {
        String itemId = Utils.getItemId();
        
        given().
            contentType(TYPE). 
            body( getProductPutRequest(p.getProductId(), itemId)).
        when().
            post(ADDTOCART_PATH).
        then().
            body(is(emptyString())).
            statusCode(200);
        
        return itemId;
    }

    public Cart getCart() throws Exception {
        return Cart.fromResponse(  
            given().
                contentType(TYPE). 
                body( getViewCartRequest() ).
            when().
                post(VIEWCART_PATH).
            then().
                statusCode(200).
            extract().
                asString()
           );    
    }

    public void removeProduct(String itemId) {
        given().
            contentType(TYPE). 
            body( getRemoveProductRequest(itemId) ).
        when().
            post(REMOVEFROMCART_PATH).
        then().
            statusCode(200);
    }

    private String getViewCartRequest() {
        return new JSONObject()
                .put(Keys.COOKIE, getUserCookie())
                .put(Keys.FLAG, false)
                .toString();

    }

    private String getRemoveProductRequest(String itemId) {
        return new JSONObject()
                .put(Keys.ID, itemId)
                .toString();        
    }
    
    private String getUserCookie() {
        return "user=" + sessionId;
    }
    
    private String getProductPutRequest(int id, String itemId) {
        return new JSONObject()
                .put(Keys.ID, itemId)
                .put(Keys.COOKIE, getUserCookie())
                .put(Keys.FLAG, false)
                .put(Keys.PROD_ID, id)
                .toString();        
    }
    
}
