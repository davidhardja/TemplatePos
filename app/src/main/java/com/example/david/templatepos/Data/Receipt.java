package com.example.david.templatepos.Data;

import com.orm.SugarRecord;

/**
 * Created by David on 08/07/2017.
 */

public class Receipt extends SugarRecord {
    private Double total;
    private Long dateAdded;

    public Receipt(){

    }

    public Receipt(Long dateAdded, Double total){
        this.dateAdded = dateAdded;
        this.total = total;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Long getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Long dateAdded) {
        this.dateAdded = dateAdded;
    }
}
