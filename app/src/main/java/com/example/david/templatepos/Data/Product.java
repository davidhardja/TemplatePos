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

    public Product(){

    }

    public Product(String name, String barcode, Double unitPrice){
        this.setId(Long.valueOf(barcode));
        this.name = name;
        this.barcode = barcode;
        this.unitPrice = unitPrice;
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

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", name);
        map.put("barcode", barcode);
        map.put("unitPrice", unitPrice + "");
        return map;

    }
}
