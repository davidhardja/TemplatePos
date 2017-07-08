package com.example.david.templatepos.Fragment;

import android.annotation.SuppressLint;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.david.templatepos.Data.Sale;
import com.example.david.templatepos.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by David on 30/06/2017.
 */
@SuppressLint("ValidFragment")
public class EditSaleDialogFragment extends DialogFragment{
    @BindView(R.id.quantityBox)
    EditText etQuantityBox;
    @BindView(R.id.priceBox)
    EditText etPriceBox;
    @BindView(R.id.confirmButton)
    Button bConfirm;
    @BindView(R.id.removeButton)
    Button bRemove;

    private String saleId;
    private String position;

    private UpdatableFragment saleFragment;
    private UpdatableFragment reportFragment;

    Sale sale;

    public EditSaleDialogFragment(UpdatableFragment saleFragment, UpdatableFragment reportFragment, Sale sale) {
        super();
        this.saleFragment = saleFragment;
        this.reportFragment = reportFragment;
        this.sale = sale;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_sale_edit_dialog, container, false);
        ButterKnife.bind(this,v);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();
    }

    private void initUI(){
        etQuantityBox.setText(String.valueOf(sale.getQuantity()));
        etPriceBox.setText(String.valueOf(sale.getUnitPrice()));

        bConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sale.setQuantity(Integer.parseInt(etQuantityBox.getText().toString()));
                sale.setUnitPrice(Double.parseDouble(etPriceBox.getText().toString()));
                sale.save();
                save();
            }
        });
    }

    private void save(){
        saleFragment.update();
        reportFragment.update();
        this.dismiss();
    }
}
