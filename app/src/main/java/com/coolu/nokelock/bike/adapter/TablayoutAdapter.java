package com.coolu.nokelock.bike.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.List;

/**
 * Created by admin on 2017/7/27.
 */
public class TablayoutAdapter extends FragmentPagerAdapter {
    private String[]title;
    private List<Fragment>fragments;
    public TablayoutAdapter(FragmentManager fm,String[] title,List<Fragment> fragments) {
        super(fm);
        this.title=title;
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        Log.e("zz","pos"+position);
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
