package com.demoblaze.test.model;

public class Product {
    private int productId;
    
    public Product(int productId) {
        this.productId = productId;
    }
    
    public int getProductId() {
        return productId;
    }
    
    public boolean equals(Object obj) {
        return ((Product) obj).getProductId() == productId;
    }

}
