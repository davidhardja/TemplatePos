package com.example.david.templatepos.Fragment;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.david.templatepos.Data.ProductLot;
import com.example.david.templatepos.ProductDetailActivity;
import com.example.david.templatepos.R;
import com.example.david.templatepos.Tools.DataTimeStrategy;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by David on 23/06/2017.
 */

@SuppressLint("ValidFragment")
public class AddProductLotDialogFragment extends DialogFragment {
    @BindView(R.id.costBox)
    EditText etCostBox;
    @BindView(R.id.quantityBox)
    EditText etQuantityBox;
    @BindView(R.id.confirmButton)
    Button bConfirm;
    @BindView(R.id.clearButton)
    Button bClear;
    ProductDetailActivity productDetailActivity;

    public AddProductLotDialogFragment(ProductDetailActivity productDetailActivity) {
        super();
        this.productDetailActivity = productDetailActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_add_stock_dialog, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();
    }

    private void initUI() {
        bConfirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (etQuantityBox.getText().toString().equals("") || etCostBox.getText().toString().equals("")) {
                    Toast.makeText(getActivity(),
                            getString(R.string.please_input_all), Toast.LENGTH_SHORT)
                            .show();
                } else {
                    ProductLot productLot = new ProductLot(new Date().getTime(),
                            Integer.parseInt(etQuantityBox.getText().toString()),
                            productDetailActivity.getProduct().getId(),
                            Double.parseDouble(etCostBox.getText().toString()));
                    productLot.save();
                    etCostBox.setText("");
                    etQuantityBox.setText("");
                    AddProductLotDialogFragment.this.dismiss();
                }

            }
        });
        bClear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (etQuantityBox.getText().toString().equals("") && etCostBox.getText().toString().equals("")) {
                    AddProductLotDialogFragment.this.dismiss();
                    onResume();
                } else {
                    etCostBox.setText("");
                    etQuantityBox.setText("");
                }
            }
        });
    }
}
