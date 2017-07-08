package com.example.david.templatepos.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.david.templatepos.Data.Receipt;
import com.example.david.templatepos.ProductDetailActivity;
import com.example.david.templatepos.R;
import com.example.david.templatepos.ReportDetailActivity;
import com.example.david.templatepos.Tools.DataTimeStrategy;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by David on 08/07/2017.
 */

public class ReportAdapter extends RecyclerView.Adapter {

    public Context context;
    public List<Receipt> receiptList;

    public ReportAdapter(Context context, List<Receipt> receipts) {
        this.receiptList = receipts;
        this.context = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_report, parent, false);
        return new ReportAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ReportAdapter.ViewHolder v = (ReportAdapter.ViewHolder) holder;
        v.bind(receiptList.get(position));
    }

    @Override
    public int getItemCount() {
        return receiptList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.sid)
        TextView tvId;
        @BindView(R.id.startTime)
        TextView tvDate;
        @BindView(R.id.total)
        TextView tvTotal;

        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            v.setOnClickListener(this);
        }

        public void bind(Receipt receipt) {
            tvId.setText(String.valueOf(receipt.getId()));
            tvDate.setText(DataTimeStrategy.getDate(receipt.getDateAdded()));
            tvTotal.setText(String.valueOf(receipt.getTotal()));
        }

        @Override
        public void onClick(View v) {
            Intent newActivity = new Intent(context,
                    ReportDetailActivity.class);
            newActivity.putExtra("receiptId", receiptList.get(getAdapterPosition()).getId());
            context.startActivity(newActivity);
        }
    }
}
