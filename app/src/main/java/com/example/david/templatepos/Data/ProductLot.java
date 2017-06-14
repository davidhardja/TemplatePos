package com.example.david.templatepos.Data;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by David on 08/06/2017.
 */


public class ProductLot extends SugarRecord {
    @Unique
    String dateAdded;
    int quantity;
    Product product;
    double unitCost;
}
