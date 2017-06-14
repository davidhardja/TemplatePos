package com.example.david.templatepos.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.david.templatepos.R;

import butterknife.BindView;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_report, container, false);
        return view;
    }

    @Override
    public void update() {

    }
}
