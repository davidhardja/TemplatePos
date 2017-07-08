package com.example.david.templatepos;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.david.templatepos.Adapter.ProductLotAdapter;
import com.example.david.templatepos.Data.Product;
import com.example.david.templatepos.Data.ProductLot;
import com.example.david.templatepos.Fragment.AddProductLotDialogFragment;
import com.example.david.templatepos.Tools.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    TextView tvStockSumBox;
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

    ProductLotAdapter productLotAdapter;
    List<ProductLot> productLotList = new ArrayList<>();

    Dialog dialog;
    AlertDialog.Builder popDialog;
    EditText etCostBox;
    Button bConfirm;
    Button bClear;

    Product product;
    String[] remember = new String[3];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_product_detail);
        initiateActionBar();
        ButterKnife.bind(this);

        this.product = Product.findById(Product.class, getIntent().getLongExtra("id", 0));
        initUI();
    }

    public Product getProduct(){
        return this.product;
    }

    private void initUI() {
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("tab_test1").setIndicator(getString(R.string.product_detail))
                .setContent(R.id.tab1));
        tabHost.addTab(tabHost.newTabSpec("tab_test2").setIndicator(getString(R.string.stock))
                .setContent(R.id.tab2));
        tabHost.setCurrentTab(0);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                showList();
            }
        });

        productLotAdapter = new ProductLotAdapter(this,productLotList);
        rvStockList.setLayoutManager(new LinearLayoutManager(this));
        rvStockList.setAdapter(productLotAdapter);

        etNameBox.setText(product.getName());
        etBarcodeBox.setText(product.getBarcode());
        etPriceBox.setText(String.valueOf(product.getUnitPrice()));
        tvStockSumBox.setText(String.valueOf(DatabaseHelper.getTotalQuantity(product.getId())));

        bCancelEditButton.setVisibility(View.INVISIBLE);
        bSubmiitEditButton.setVisibility(View.INVISIBLE);

        bAddProductLotButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showPopup();
            }
        });

        bOpenEditButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edit();
            }
        });

        bSubmiitEditButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                submitEdit();
            }
        });

        bCancelEditButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cancelEdit();
            }
        });

        showList();
    }

    @SuppressLint("NewApi")
    private void initiateActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getString(R.string.product_detail));
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#33B5E5")));

    }

    private void showList() {
        productLotList.clear();
        productLotList.addAll(ProductLot.find(ProductLot.class, "product_Id = ?", String.valueOf(product.getId())));
        productLotAdapter.notifyDataSetChanged();
        rvStockList.setAdapter(productLotAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        update();
    }

    public void update(){
        tvStockSumBox.setText(String.valueOf(DatabaseHelper.getTotalQuantity(product.getId())));
        showList();
    }

    private void edit() {
        etNameBox.setFocusable(true);
        etNameBox.setFocusableInTouchMode(true);
        etNameBox.setBackgroundColor(Color.parseColor("#FFBB33"));
        etPriceBox.setFocusable(true);
        etPriceBox.setFocusableInTouchMode(true);
        etPriceBox.setBackgroundColor(Color.parseColor("#FFBB33"));
        etBarcodeBox.setFocusable(true);
        etBarcodeBox.setFocusableInTouchMode(true);
        etBarcodeBox.setBackgroundColor(Color.parseColor("#FFBB33"));
        bSubmiitEditButton.setVisibility(View.VISIBLE);
        bCancelEditButton.setVisibility(View.VISIBLE);
        bOpenEditButton.setVisibility(View.INVISIBLE);
        remember[0] = etNameBox.getText().toString();
        remember[1] = etPriceBox.getText().toString();
        remember[2] = etBarcodeBox.getText().toString();
    }

    private void cancelEdit() {
        etNameBox.setText(remember[0]);
        etPriceBox.setText(remember[1]);
        etBarcodeBox.setText(remember[2]);
        etNameBox.setFocusable(false);
        etNameBox.setFocusableInTouchMode(false);
        etNameBox.setBackgroundColor(Color.parseColor("#87CEEB"));
        etPriceBox.setFocusable(false);
        etPriceBox.setFocusableInTouchMode(false);
        etPriceBox.setBackgroundColor(Color.parseColor("#87CEEB"));
        etBarcodeBox.setFocusable(false);
        etBarcodeBox.setFocusableInTouchMode(false);
        etBarcodeBox.setBackgroundColor(Color.parseColor("#87CEEB"));
        bSubmiitEditButton.setVisibility(View.INVISIBLE);
        bCancelEditButton.setVisibility(View.INVISIBLE);
        bOpenEditButton.setVisibility(View.VISIBLE);
    }

    private void submitEdit() {
        etNameBox.setFocusable(false);
        etNameBox.setFocusableInTouchMode(false);
        etNameBox.setBackgroundColor(Color.parseColor("#87CEEB"));
        etPriceBox.setFocusable(false);
        etPriceBox.setFocusableInTouchMode(false);
        etPriceBox.setBackgroundColor(Color.parseColor("#87CEEB"));
        etBarcodeBox.setFocusable(false);
        etBarcodeBox.setFocusableInTouchMode(false);
        etBarcodeBox.setBackgroundColor(Color.parseColor("#87CEEB"));
        product.setName(etBarcodeBox.getText().toString());
        if(etPriceBox.getText().toString().equals(""))
            etPriceBox.setText("0.0");
        product.setUnitPrice(Double.parseDouble(etPriceBox.getText().toString()));
        product.setBarcode(etBarcodeBox.getText().toString());
        bSubmiitEditButton.setVisibility(View.INVISIBLE);
        bCancelEditButton.setVisibility(View.INVISIBLE);
        bOpenEditButton.setVisibility(View.VISIBLE);
    }

    public void showPopup() {
        AddProductLotDialogFragment newFragment = new AddProductLotDialogFragment(this);
        newFragment.show(getFragmentManager(), "");
    }

}
