package com.example.david.templatepos.Data;

import com.orm.SugarRecord;

/**
 * Created by David on 23/06/2017.
 */

public class Sale extends SugarRecord {
    private String productName;
    private Long productId;
    private int quantity;
    private double unitPrice;
    private Long dateAdded;

    public Sale(){

    }

    public Sale(Long productId, String productName, int quantity, double unitPrice, Long dateAdded){
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.dateAdded = dateAdded;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
