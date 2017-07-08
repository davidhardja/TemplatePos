package com.example.david.templatepos.Data;

import com.orm.SugarRecord;

/**
 * Created by David on 08/07/2017.
 */

public class Detail extends SugarRecord {
    private String productName;
    private int quantity;
    private double unitPrice;
    private Long dateAdded;
    private Long receiptId;

    public Detail(){

    }

    public Detail(Long receiptId, String productName, Integer quantity, Double unitPrice, Long dateAdded){
        this.receiptId = receiptId;
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

    public Long getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Long dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Long getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
    }
}
