package com.coolu.nokelock.bike.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.adapter.PalyAdapter;
import com.coolu.nokelock.bike.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/14.
 */
public class PlayFragment extends BaseFragment {

    private ViewPager viewpager;
    private TabLayout tabLayout;
    private String[]title=new String[]{"推荐","美食","酒店","旅游","娱乐"};
    private List<Fragment>list=null;
    private PalyAdapter palyAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View play = inflater.inflate(R.layout.play_fragment, null);
        tabLayout = (TabLayout) play.findViewById(R.id.id_play_tab);
        viewpager = (ViewPager)play.findViewById(R.id.id_paly_viewpager);
        list=new ArrayList<>();
        for (int i=0;i<5;i++){
            PlayItemFragment playItemFragment = new PlayItemFragment();
            Bundle bundle=new Bundle();
            bundle.putInt("flag",i);

            playItemFragment.setArguments(bundle);
            list.add(playItemFragment);
        }

        palyAdapter = new PalyAdapter(getChildFragmentManager(),list,title);

        viewpager.setAdapter(palyAdapter);
        tabLayout.setupWithViewPager(viewpager);

        return play;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }



}
