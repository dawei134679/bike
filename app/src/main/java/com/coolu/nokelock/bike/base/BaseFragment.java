package com.coolu.nokelock.bike.base;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.amap.api.maps.SupportMapFragment;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by admin on 2017/11/14.
 */

/**
 * Fragment 基类
 * 里面封装了Android 6.0的运行时权限
 * Created by 123 on 17/2/21.
 */

public abstract class BaseFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(getClass().getName());

    }

    @Override
    public void onStop() {
        super.onStop();
        MobclickAgent.onPageEnd(getActivity().getLocalClassName());
    }
}




