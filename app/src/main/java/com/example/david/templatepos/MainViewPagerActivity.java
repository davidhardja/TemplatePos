package com.example.david.templatepos;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.david.templatepos.Adapter.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by David on 07/06/2017.
 */

public class MainViewPagerActivity extends FragmentActivity {
    @BindView(R.id.pager)
    ViewPager pager;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_view_pager);
        ButterKnife.bind(this);
        initiateActionBar();
        viewPagerAdapter = new ViewPagerAdapter(this, getSupportFragmentManager());
        pager.setAdapter(viewPagerAdapter);
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        getActionBar().setSelectedNavigationItem(position);
                    }
                });
        pager.setCurrentItem(1);

    }

    private void initiateActionBar() {
            ActionBar actionBar = getActionBar();

            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

            ActionBar.TabListener tabListener = new ActionBar.TabListener() {
                @Override
                public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
                }

                @Override
                public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                    pager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
                }
            };
            actionBar.addTab(actionBar.newTab().setText(getString(R.string.inventory))
                    .setTabListener(tabListener), 0, false);
            actionBar.addTab(actionBar.newTab().setText(getString(R.string.sale))
                    .setTabListener(tabListener), 1, true);
            actionBar.addTab(actionBar.newTab().setText(getString(R.string.report))
                    .setTabListener(tabListener), 2, false);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color
                        .parseColor("#73bde5")));
            }
    }

    private void openRemoveDialog() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(
                MainViewPagerActivity.this);
        quitDialog.setTitle(getString(R.string.dialog_remove_product));
        quitDialog.setPositiveButton(getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        quitDialog.setNegativeButton(getString(R.string.remove), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                viewPagerAdapter.update(0);
            }
        });

        quitDialog.show();
    }

}
