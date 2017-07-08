package com.example.david.templatepos.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.david.templatepos.Adapter.InventoryAdapter;
import com.example.david.templatepos.Adapter.SaleAdapter;
import com.example.david.templatepos.Data.Product;
import com.example.david.templatepos.Data.Sale;
import com.example.david.templatepos.MainActivity;
import com.example.david.templatepos.R;
import com.example.david.templatepos.Tools.DatabaseHelper;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by David on 07/06/2017.
 */

@SuppressLint("ValidFragment")
public class SaleFragment extends UpdatableFragment {
    @BindView(R.id.totalPrice)
    TextView tvTotalPrice;
    @BindView(R.id.clearButton)
    Button bClear;
    @BindView(R.id.endButton)
    Button bEnd;
    @BindView(R.id.sale_List)
    RecyclerView rvSale;
    List<Sale> sales = new ArrayList<>();
    private UpdatableFragment reportFragment;
    SaleAdapter saleAdapter;

    public SaleFragment(UpdatableFragment reportFragment) {
        super();
        this.reportFragment = reportFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_sale_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void update() {
        showList();
        tvTotalPrice.setText(String.valueOf(DatabaseHelper.getTotal()));
    }

    private void showList(){
        sales.clear();
        sales.addAll(Sale.listAll(Sale.class));
        saleAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUi();
        sales.addAll(Sale.listAll(Sale.class));
    }

    private void initUi() {
        bClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPager viewPager = ((MainActivity) getActivity()).getViewPager();
                viewPager.setCurrentItem(1);
                Sale.deleteAll(Sale.class);
                update();
            }
        });

        bEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sales.size()>0){
                    PaymentDialogFragment newFragment = new PaymentDialogFragment(SaleFragment.this, reportFragment);
                    newFragment.show(getFragmentManager(), "");
                }else{
                    Toast.makeText(getActivity().getBaseContext() , getString(R.string.hint_empty_sale), Toast.LENGTH_SHORT).show();
                }
            }
        });

        saleAdapter = new SaleAdapter(getActivity(),sales,SaleFragment.this,reportFragment);

        rvSale.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvSale.setAdapter(saleAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        update();
    }

    public void showEditPopup(Sale sale){
        EditSaleDialogFragment newFragment = new EditSaleDialogFragment(SaleFragment.this, reportFragment,sale);
        newFragment.show(getFragmentManager(), "");

    }

    private void showConfirmClearDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(getString(R.string.dialog_clear_sale));
        dialog.setPositiveButton(getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog.setNegativeButton(getString(R.string.clear), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                update();
            }
        });

        dialog.show();
    }
}
