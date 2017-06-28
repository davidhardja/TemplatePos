package com.example.david.templatepos.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.david.templatepos.Data.Product;
import com.example.david.templatepos.Data.Sale;
import com.example.david.templatepos.Fragment.InventoryFragment;
import com.example.david.templatepos.ProductDetailActivity;
import com.example.david.templatepos.R;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by David on 10/06/2017.
 */

public class InventoryAdapter extends RecyclerView.Adapter{
    public InventoryFragment inventoryFragment;
    public Context context;
    public List<Product> listProduct;

    public InventoryAdapter(Context context, List<Product> products, InventoryFragment inventory) {
        this.listProduct = products;
        this.context = context;
        this.inventoryFragment = inventory;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_inventory_product,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder v = (ViewHolder) holder;
        v.bind(listProduct.get(position));
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.bEdit)
        Button bEdit;
        @BindView(R.id.llContainer)
        LinearLayout llContainer;

        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this,v);

            llContainer.setOnClickListener(this);
            bEdit.setOnClickListener(this);
        }

        public void bind(Product product){
            tvName.setText(product.getName());
        }

        @Override
        public void onClick(View v) {
            if(v==bEdit){
                Intent newActivity = new Intent(context,
                        ProductDetailActivity.class);
                newActivity.putExtra("id", listProduct.get(getAdapterPosition()).getId());
                context.startActivity(newActivity);
            }else{
                Product p = listProduct.get(getAdapterPosition());
                Sale sale = new Sale(p.getId(),p.getName(),1,p.getUnitPrice(),new Date().getTime());
                inventoryFragment.addItemToSale(sale);
            }
        }
    }
}
