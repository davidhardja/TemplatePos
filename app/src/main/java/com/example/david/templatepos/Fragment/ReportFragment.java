package com.example.david.templatepos.Fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.david.templatepos.Adapter.ReportAdapter;
import com.example.david.templatepos.Adapter.SaleAdapter;
import com.example.david.templatepos.Data.Detail;
import com.example.david.templatepos.Data.Receipt;
import com.example.david.templatepos.Data.Sale;
import com.example.david.templatepos.R;
import com.example.david.templatepos.Tools.DataTimeStrategy;
import com.example.david.templatepos.Tools.DatabaseHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by David on 07/06/2017.
 */

public class ReportFragment extends UpdatableFragment{
    @BindView(R.id.previousButton)
    Button bPrevious;
    @BindView(R.id.nextButton)
    Button bNext;
    @BindView(R.id.currentBox)
    TextView tvCurrentBox;
    @BindView(R.id.totalBox)
    TextView tvTotalBox;
    @BindView(R.id.spinner)
    Spinner sDate;
    @BindView(R.id.saleListView)
    RecyclerView rvReceipt;

    ReportAdapter reportAdapter;
    List<Receipt> receiptList = new ArrayList<>();
    private Calendar currentTime;
    private DatePickerDialog datePicker;

    public static final int DAILY = 0;
    public static final int WEEKLY = 1;
    public static final int MONTHLY = 2;
    public static final int YEARLY = 3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_report, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialUI();
    }

    @Override
    public void update() {
        int period = sDate.getSelectedItemPosition();
        List<Receipt> receipts = null;
        Calendar cTime = (Calendar) currentTime.clone();
        Calendar eTime = (Calendar) currentTime.clone();

        if(period == DAILY){
            tvCurrentBox.setText(" [" + DataTimeStrategy.getDateReport(currentTime.getTime().getTime()) +  "] ");
            tvCurrentBox.setTextSize(16);
        } else if (period == WEEKLY){
            while(cTime.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
                cTime.add(Calendar.DATE, -1);
            }

            String toShow = " [" + DataTimeStrategy.getDateReport(cTime.getTime().getTime()) +  "] ~ [";
            eTime = (Calendar) cTime.clone();
            eTime.add(Calendar.DATE, 7);
            toShow += DataTimeStrategy.getDateReport(eTime.getTime().getTime()) +  "] ";
            tvCurrentBox.setTextSize(16);
            tvCurrentBox.setText(toShow);
        } else if (period == MONTHLY){
            cTime.set(Calendar.DATE, 1);
            eTime = (Calendar) cTime.clone();
            eTime.add(Calendar.MONTH, 1);
            eTime.add(Calendar.DATE, -1);
            tvCurrentBox.setTextSize(18);
            tvCurrentBox.setText(" [" + currentTime.get(Calendar.YEAR) + "-" + (currentTime.get(Calendar.MONTH)+1) + "] ");
        } else if (period == YEARLY){
            cTime.set(Calendar.DATE, 1);
            cTime.set(Calendar.MONTH, 0);
            eTime = (Calendar) cTime.clone();
            eTime.add(Calendar.YEAR, 1);
            eTime.add(Calendar.DATE, -1);
            tvCurrentBox.setTextSize(20);
            tvCurrentBox.setText(" [" + currentTime.get(Calendar.YEAR) +  "] ");
        }
        currentTime = cTime;
        receipts = DatabaseHelper.getReceiptRange(cTime,eTime);

        showList(cTime,eTime);
    }

    private void addDate(int increment) {
        int period = sDate.getSelectedItemPosition();
        currentTime.set(Calendar.HOUR_OF_DAY, 0);
        currentTime.set(Calendar.MINUTE, 0);
        currentTime.set(Calendar.SECOND, 0);
        currentTime.set(Calendar.MILLISECOND, 0);
        if (period == DAILY){
            currentTime.add(Calendar.DATE, 1 * increment);
        } else if (period == WEEKLY){
            currentTime.add(Calendar.DATE, 7 * increment);
        } else if (period == MONTHLY){
            currentTime.add(Calendar.MONTH, 1 * increment);
        } else if (period == YEARLY){
            currentTime.add(Calendar.YEAR, 1 * increment);
        }
        update();
    }

    private void showList(Calendar cTime, Calendar eTime) {
        cTime.set(Calendar.HOUR_OF_DAY, 0);
        cTime.set(Calendar.MINUTE, 0);
        cTime.set(Calendar.SECOND, 0);
        cTime.set(Calendar.MILLISECOND, 0);
        eTime.add(Calendar.DATE, 1);
        receiptList.clear();
        receiptList.addAll(DatabaseHelper.getReceiptRange(cTime,eTime));
        reportAdapter.notifyDataSetChanged();

        double total =0;
        for (int i=0;i<receiptList.size();i++){
            total = total+ receiptList.get(i).getTotal();
        }
        tvTotalBox.setText(String.valueOf(total));
    }

    private void initialUI(){
        reportAdapter = new ReportAdapter(getActivity(),receiptList);
        rvReceipt.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvReceipt.setAdapter(reportAdapter);

        currentTime = Calendar.getInstance();
        currentTime.set(Calendar.HOUR_OF_DAY, 0);
        currentTime.set(Calendar.MINUTE, 0);
        currentTime.set(Calendar.SECOND, 0);
        currentTime.set(Calendar.MILLISECOND, 0);
        datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int y, int m, int d) {
                currentTime.set(Calendar.YEAR, y);
                currentTime.set(Calendar.MONTH, m);
                currentTime.set(Calendar.DAY_OF_MONTH, d);
                update();
            }
        }, currentTime.get(Calendar.YEAR), currentTime.get(Calendar.MONTH), currentTime.get(Calendar.DAY_OF_MONTH));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.period, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sDate.setAdapter(adapter);
        sDate.setSelection(0);
        sDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                update();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }

        });

        tvCurrentBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.show();
            }
        });

        bPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDate(-1);
            }
        });

        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDate(1);
            }
        });
    }
}
