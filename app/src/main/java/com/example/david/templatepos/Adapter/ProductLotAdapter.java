package com.example.david.templatepos.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.david.templatepos.Data.ProductLot;
import com.example.david.templatepos.Data.Sale;
import com.example.david.templatepos.Fragment.ReportFragment;
import com.example.david.templatepos.Fragment.SaleFragment;
import com.example.david.templatepos.Fragment.UpdatableFragment;
import com.example.david.templatepos.R;
import com.example.david.templatepos.Tools.DataTimeStrategy;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by David on 01/07/2017.
 */

public class ProductLotAdapter extends RecyclerView.Adapter {

    public Context context;
    public List<ProductLot> productLotList;

    public ProductLotAdapter(Context context, List<ProductLot> productLots) {
        this.productLotList = productLots;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_product_lot, parent, false);
        return new ProductLotAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProductLotAdapter.ViewHolder v = (ProductLotAdapter.ViewHolder) holder;
        v.bind(productLotList.get(position));
    }

    @Override
    public int getItemCount() {
        return productLotList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.dateAdded)
        TextView tvDataAdded;
        @BindView(R.id.cost)
        TextView tvCost;
        @BindView(R.id.quantity)
        TextView tvQuantity;

        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            v.setOnClickListener(this);
        }

        public void bind(ProductLot productLot) {
            tvDataAdded.setText(DataTimeStrategy.getDate(productLot.getDateAdded(),DataTimeStrategy.format));
            tvCost.setText(String.valueOf(productLot.getUnitCapitalPrice()));
            tvQuantity.setText(String.valueOf(productLot.getQuantity()));
        }

        @Override
        public void onClick(View v) {

        }
    }
}
