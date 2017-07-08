package com.example.david.templatepos.Fragment;

import android.annotation.SuppressLint;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.david.templatepos.R;
import com.example.david.templatepos.Tools.DatabaseHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by David on 07/07/2017.
 */

@SuppressLint("ValidFragment")
public class PaymentDialogFragment extends DialogFragment{
    @BindView(R.id.dialog_saleInput)
    EditText etInput;
    @BindView(R.id.payment_total)
    TextView tvPayment;
    @BindView(R.id.clearButton)
    Button bClear;
    @BindView(R.id.confirmButton)
    Button bConfirm;

    private UpdatableFragment saleFragment;
    private UpdatableFragment reportFragment;

    public PaymentDialogFragment(UpdatableFragment saleFragment, UpdatableFragment reportFragment) {
        super();
        this.saleFragment = saleFragment;
        this.reportFragment = reportFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_fragment_payment_dialog, container,false);
        ButterKnife.bind(this,v);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialUI();
        setListener();
    }

    private void setListener(){
        bClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentDialogFragment.this.dismiss();
            }
        });

        bConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputString = etInput.getText().toString();

                if (inputString.equals("")) {
                    Toast.makeText(getActivity().getBaseContext(), getResources().getString(R.string.please_input_all), Toast.LENGTH_SHORT).show();
                    return;
                }
                double a = Double.parseDouble(tvPayment.getText().toString());
                double b = Double.parseDouble(inputString);
                if (b < a) {
                    Toast.makeText(getActivity().getBaseContext(), getResources().getString(R.string.need_money) + " " + (b - a), Toast.LENGTH_SHORT).show();
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("change", b - a + "");
                    EndPaymentDialogFragment newFragment = new EndPaymentDialogFragment(
                            saleFragment, reportFragment);
                    newFragment.setArguments(bundle);
                    newFragment.show(getFragmentManager(), "");
                    PaymentDialogFragment.this.dismiss();
                }
            }
        });
    }

    private void initialUI(){
        tvPayment.setText(String.valueOf(DatabaseHelper.getTotal()));
    }
}
