package com.example.david.templatepos.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.david.templatepos.Data.Detail;
import com.example.david.templatepos.Data.Sale;
import com.example.david.templatepos.Fragment.ReportFragment;
import com.example.david.templatepos.Fragment.SaleFragment;
import com.example.david.templatepos.Fragment.UpdatableFragment;
import com.example.david.templatepos.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by David on 08/07/2017.
 */

public class DetailAdapter extends RecyclerView.Adapter {

    public Context context;
    public List<Detail> detailList;

    public DetailAdapter(Context context, List<Detail> details) {
        this.detailList = details;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_detail, parent, false);
        return new DetailAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DetailAdapter.ViewHolder v = (DetailAdapter.ViewHolder) holder;
        v.bind(detailList.get(position));
    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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

        public void bind(Detail detail) {
            tvName.setText(detail.getProductName());
            tvQuantity.setText(String.valueOf(detail.getQuantity()));
            tvPrice.setText(String.valueOf(detail.getUnitPrice()));
            tvSubTotal.setText(String.valueOf(detail.getUnitPrice()*detail.getQuantity()));
        }

    }
}
