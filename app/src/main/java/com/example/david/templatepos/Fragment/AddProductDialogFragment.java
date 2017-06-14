package com.example.david.templatepos.Fragment;

import android.annotation.SuppressLint;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.david.templatepos.Data.Product;
import com.example.david.templatepos.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by David on 08/06/2017.
 */

@SuppressLint("ValidFragment")
public class AddProductDialogFragment extends DialogFragment {
    @BindView(R.id.barcodeBox)
    EditText etBarcodeBox;
    @BindView(R.id.priceBox)
    EditText etPriceBox;
    @BindView(R.id.nameBox)
    EditText etNameBox;
    @BindView(R.id.confirmButton)
    Button bConfirm;
    @BindView(R.id.clearButton)
    Button bClear;


    private UpdatableFragment fragment;


    public AddProductDialogFragment(UpdatableFragment fragment) {

        super();
        this.fragment = fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_add_product_dialog, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();
    }

    private void initUI(){
        bConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etBarcodeBox.getText().toString().matches("")||etPriceBox.getText().toString().matches("")||etNameBox.getText().toString().matches("")){
                    Toast.makeText(getActivity().getBaseContext(), getString(R.string.please_input_all), Toast.LENGTH_SHORT).show();
                }else{
                    if(Product.findById(Product.class, Integer.valueOf(etBarcodeBox.getText().toString()))==null){
                        Product product = new Product(etNameBox.getText().toString(),etBarcodeBox.getText().toString(),Double.valueOf(etBarcodeBox.getText().toString()));
                        product.save();
                        Toast.makeText(getActivity().getBaseContext(),
                                getString(R.string.success) + ", "
                                        + etNameBox.getText().toString(),
                                Toast.LENGTH_SHORT).show();

                        fragment.update();
                        clearAllBox();
                        AddProductDialogFragment.this.dismiss();
                    }else{
                        Toast.makeText(getActivity().getBaseContext(),
                                getString(R.string.fail),
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        bClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etBarcodeBox.getText().toString().equals("") && etNameBox.getText().toString().equals("") && etPriceBox.getText().toString().equals("")){
                    AddProductDialogFragment.this.dismiss();
                } else {
                    clearAllBox();
                }
            }
        });
    }

    private void clearAllBox() {
        etBarcodeBox.setText("");
        etPriceBox.setText("");
        etNameBox.setText("");
    }

}
