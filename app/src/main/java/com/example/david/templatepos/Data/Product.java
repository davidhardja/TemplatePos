package com.example.david.templatepos.Data;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by David on 08/06/2017.
 */


public class Product extends SugarRecord {
    private String name;
    private String barcode;
    private Double unitPrice;
    private Integer quantityTotal;

    public Product(){

    }

    public Product(String name, String barcode, Double unitPrice){
        this.setId(Long.valueOf(barcode));
        this.name = name;
        this.barcode = barcode;
        this.unitPrice = unitPrice;
        this.quantityTotal = 0;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(Integer quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

}
