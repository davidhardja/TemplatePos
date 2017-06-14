package com.example.david.templatepos.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.example.david.templatepos.Fragment.InventoryFragment;
import com.example.david.templatepos.Fragment.ReportFragment;
import com.example.david.templatepos.Fragment.SaleFragment;
import com.example.david.templatepos.Fragment.UpdatableFragment;
import com.example.david.templatepos.R;

/**
 * Created by David on 07/06/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    Context ctxt=null;
    private UpdatableFragment[] fragments;
    private String[] fragmentNames;

    public ViewPagerAdapter(Context ctxt, FragmentManager mgr) {
        super(mgr);
        this.ctxt=ctxt;

        UpdatableFragment reportFragment = new ReportFragment();
        UpdatableFragment saleFragment = new SaleFragment(reportFragment);
        UpdatableFragment inventoryFragment = new InventoryFragment(saleFragment);

        fragments = new UpdatableFragment[] { inventoryFragment, saleFragment,
                reportFragment };
        fragmentNames = new String[] { ctxt.getString(R.string.inventory),
                ctxt.getString(R.string.sale),
                ctxt.getString(R.string.report) };
    }

    @Override
    public Fragment getItem(int i) {
        return fragments[i];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int i) {
        return fragmentNames[i];
    }

    public void update(int index) {
        fragments[index].update();
    }
}
