package com.example.david.templatepos.Data;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by David on 08/06/2017.
 */


public class ProductLot extends SugarRecord {
    private Long dateAdded;
    private int quantity;
    private Product product;
    private Long productId;
    private double unitCapitalPrice;

    public ProductLot(){

    }

    public ProductLot(long dateAdded, int quantity, long productId, double unitCapitalPrice){
        this.dateAdded = dateAdded;
        this.quantity = quantity;
        this.productId = productId;
        this.unitCapitalPrice = unitCapitalPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        productId = productId;
    }

    public Long getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Long dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getUnitCapitalPrice() {
        return unitCapitalPrice;
    }

    public void setUnitCapitalPrice(double unitCapitalPrice) {
        this.unitCapitalPrice = unitCapitalPrice;
    }
}
