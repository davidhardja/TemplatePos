package com.example.david.templatepos.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.david.templatepos.R;

import butterknife.BindView;

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

    private UpdatableFragment reportFragment;

    public SaleFragment(UpdatableFragment reportFragment) {
        super();
        this.reportFragment = reportFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_sale_fragment, container, false);
        return view;
    }

    @Override
    public void update() {

    }
}
