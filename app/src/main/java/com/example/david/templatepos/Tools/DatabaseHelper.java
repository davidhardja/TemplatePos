package com.example.david.templatepos.Tools;

import com.example.david.templatepos.Data.Product;
import com.example.david.templatepos.Data.ProductLot;
import com.example.david.templatepos.Data.Receipt;
import com.example.david.templatepos.Data.Sale;

import java.util.Calendar;
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

    public static Integer getTotalQuantity(Long productId){
        int total = 0;
        List<ProductLot> productLots = ProductLot.find(ProductLot.class, "product_Id = ?", String.valueOf(productId));
        for (int i=0;i<productLots.size();i++){
            total = total + (productLots.get(i).getQuantity());
        }
        return total;
    }

    public static void setQuantityTotal(Long productId, Integer quantity){
        Product product = Product.findById(Product.class, productId);
        if(product!=null){
            product.setQuantityTotal(product.getQuantityTotal()+quantity);
            product.save();
        }
    }

    public static List<Receipt> getReceiptRange(Calendar start, Calendar end){
        return Receipt.find(Receipt.class, "date_Added >= ? AND date_Added < ?", start.getTime().getTime() + "", end.getTime().getTime() + "");
    }

}
