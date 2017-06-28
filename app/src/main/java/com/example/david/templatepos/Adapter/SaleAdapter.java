package com.example.david.templatepos.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.david.templatepos.Data.Sale;
import com.example.david.templatepos.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by David on 28/06/2017.
 */

public class SaleAdapter extends RecyclerView.Adapter {

    public Context context;
    public List<Sale> listSale;

    public SaleAdapter(Context context, List<Sale> sales) {
        this.listSale = sales;
        this.context = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_sale, parent, false);
        return new SaleAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SaleAdapter.ViewHolder v = (SaleAdapter.ViewHolder) holder;
        v.bind(listSale.get(position));
    }

    @Override
    public int getItemCount() {
        return listSale.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvQuantity)
        TextView tvQuantity;
        @BindView(R.id.tvPrice)
        TextView tvPrice;
        @BindView(R.id.tvSubTotal)
        TextView tvSubTotal;

        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

        public void bind(Sale sale) {
            tvName.setText(sale.getProductName());
            tvPrice.setText(String.valueOf(sale.getUnitPrice()));
            tvQuantity.setText(String.valueOf(sale.getQuantity()));
            tvSubTotal.setText(String.valueOf(sale.getUnitPrice()*sale.getQuantity()));
        }

        @Override
        public void onClick(View v) {

        }
    }
}
