package com.coolu.nokelock.bike.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by admin on 2017/8/14.
 */
public class PalyAdapter extends FragmentPagerAdapter {
    private List<Fragment>fragments=null;
    private String[]title=null;
    public PalyAdapter(FragmentManager fm,List<Fragment> fragments,String[]title) {
        super(fm);
        this.fragments=fragments;
        this.title=title;

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size()==0?0:fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
