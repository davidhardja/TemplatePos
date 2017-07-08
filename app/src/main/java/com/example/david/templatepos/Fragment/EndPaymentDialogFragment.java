package com.example.david.templatepos.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.david.templatepos.Data.Detail;
import com.example.david.templatepos.Data.Receipt;
import com.example.david.templatepos.Data.Sale;
import com.example.david.templatepos.R;
import com.example.david.templatepos.Tools.DataTimeStrategy;
import com.example.david.templatepos.Tools.DatabaseHelper;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by David on 07/07/2017.
 */
@SuppressLint("ValidFragment")
public class EndPaymentDialogFragment extends DialogFragment {
    @BindView(R.id.changeTxt)
    TextView tvChange;
    @BindView(R.id.doneButton)
    Button bDone;

    private UpdatableFragment saleFragment;
    private UpdatableFragment reportFragment;
    public EndPaymentDialogFragment(UpdatableFragment saleFragment, UpdatableFragment reportFragment) {
        super();
        this.saleFragment = saleFragment;
        this.reportFragment = reportFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_fragment_finish_dialog, container,false);
        ButterKnife.bind(this,v);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialUI();
    }

    private void initialUI(){
        tvChange.setText(getArguments().getString("change"));
        bDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Receipt receipt = new Receipt(new Date().getTime(), DatabaseHelper.getTotal());
                receipt.save();
                List<Sale> saleList = Sale.listAll(Sale.class);
                for (int i=0;i<saleList.size();i++){
                    Sale sale = saleList.get(i);
                    Detail detail = new Detail(receipt.getId(),sale.getProductName(),sale.getQuantity(),sale.getUnitPrice(),new Date().getTime());
                    detail.save();
                }
                Sale.deleteAll(Sale.class);
                saleFragment.update();
                reportFragment.update();
                EndPaymentDialogFragment.this.dismiss();
            }
        });
    }
}
