package com.example.david.templatepos;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.david.templatepos.Adapter.DetailAdapter;
import com.example.david.templatepos.Data.Detail;
import com.example.david.templatepos.Data.ProductLot;
import com.example.david.templatepos.Data.Receipt;
import com.example.david.templatepos.Tools.DataTimeStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by David on 08/07/2017.
 */

public class ReportDetailActivity extends Activity{
    @BindView(R.id.totalBox)
    TextView tvTotalBox;
    @BindView(R.id.dateBox)
    TextView tvDateBox;
    @BindView(R.id.lineitemList)
    RecyclerView rvDetailList;
    DetailAdapter detailAdapter;

    Long idReceipt;
    Receipt receipt;
    List<Detail> detailList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_detail_report);
        ButterKnife.bind(this);

        idReceipt = getIntent().getLongExtra("receiptId",-1);
        receipt = Receipt.findById(Receipt.class,idReceipt);
        initialUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        update();
    }

    private void initialUI(){
        detailAdapter = new DetailAdapter(this,detailList);
        rvDetailList.setLayoutManager(new LinearLayoutManager(this));
        rvDetailList.setAdapter(detailAdapter);
        update();
    }

    public void update() {
        tvTotalBox.setText(String.valueOf(receipt.getTotal()));
        tvDateBox.setText(DataTimeStrategy.getDate(receipt.getDateAdded()));
        showList();
    }

    private void showList(){
        detailList.clear();
        detailList.addAll(Detail.find(Detail.class, "receipt_Id = ?", String.valueOf(idReceipt)));
    }
}
