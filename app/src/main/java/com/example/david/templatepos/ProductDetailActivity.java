package com.example.david.templatepos;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.david.templatepos.Data.Product;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by David on 10/06/2017.
 */

public class ProductDetailActivity extends Activity {
    @BindView(R.id.stockListView)
    RecyclerView rvStockList;
    @BindView(R.id.nameBox)
    EditText etNameBox;
    @BindView(R.id.priceBox)
    EditText etPriceBox;
    @BindView(R.id.barcodeBox)
    EditText etBarcodeBox;
    @BindView(R.id.stockSumBox)
    TextView etStockSumBox;
    @BindView(R.id.submitEditButton)
    Button bSubmiitEditButton;
    @BindView(R.id.cancelEditButton)
    Button bCancelEditButton;
    @BindView(R.id.openEditButton)
    Button bOpenEditButton;
    @BindView(R.id.addProductLotButton)
    Button bAddProductLotButton;
    @BindView(android.R.id.tabhost)
    TabHost tabHost;
    Product product;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_product_detail);
        initiateActionBar();
        ButterKnife.bind(this);

        this.product = Product.findById(Product.class, getIntent().getLongExtra("id", 0));
        initUI();
    }

    private void initUI() {
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("tab_test1").setIndicator(getString(R.string.product_detail))
                .setContent(R.id.tab1));
        tabHost.addTab(tabHost.newTabSpec("tab_test2").setIndicator(getString(R.string.stock))
                .setContent(R.id.tab2));
        tabHost.setCurrentTab(0);
    }

    @SuppressLint("NewApi")
    private void initiateActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getString(R.string.product_detail));
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#33B5E5")));

    }

}
