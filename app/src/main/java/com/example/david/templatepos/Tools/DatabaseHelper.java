package com.example.david.templatepos.Tools;

import com.example.david.templatepos.Data.Sale;

import java.util.List;

/**
 * Created by David on 28/06/2017.
 */

public class DatabaseHelper {

    public static Double getTotal(){
        double total = 0;
        List<Sale> saleList = Sale.listAll(Sale.class);
        for (int i=0;i<saleList.size();i++){
            total = total + (saleList.get(i).getQuantity()*saleList.get(i).getUnitPrice());
        }
        return total;
    }
}
