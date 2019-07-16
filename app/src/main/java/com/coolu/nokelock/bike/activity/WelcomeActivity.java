package com.coolu.nokelock.bike.activity;

import android.Manifest;

import android.bluetooth.BluetoothAdapter;

import android.content.Intent;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.chuanglan.shanyan_sdk.OneKeyLoginManager;
import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.base.BaseActivity;
import com.coolu.nokelock.bike.util.BaseUtil;
import com.coolu.nokelock.bike.util.DialogShowUtils;
import com.coolu.nokelock.bike.util.SPUtils;
import com.fitsleep.sunshinelibrary.inter.OnDialogClickListener;
import com.fitsleep.sunshinelibrary.utils.IntentUtils;
import com.fitsleep.sunshinelibrary.utils.MPermissionsActivity;
import com.fitsleep.sunshinelibrary.utils.ScreenUtils;


import java.util.Timer;
import java.util.TimerTask;

/**
 * 登录和初始化蓝牙Ble服务
 */

public class WelcomeActivity extends BaseActivity {
    private BluetoothAdapter bluetoothadapter;  //蓝牙适配器
    private int REQUEST_ENABLE=1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ScreenUtils.hideStatusBar(this);


        setContentView(R.layout.activity_welcome);



        requestPermission(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA}, 101);
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)){
            Toast.makeText(this, "该设备不支持低功耗蓝牙功能",Toast.LENGTH_LONG).show();
            finish();
        }



    }



    @Override
    protected void onDestroy() {
        super.onDestroy();


    }


    @Override
    public void permissionSuccess(int requestCode) {
        super.permissionSuccess(requestCode);
        if (checkPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA})) {
            boolean isEnter = SPUtils.get(getApplicationContext(), "isEnter");
//            闪验初始化网络
            OneKeyLoginManager.getInstance().init();
            OneKeyLoginManager.getInstance().PreInitiaStart();
            if (!isEnter){
                IntentUtils.startActivityForDelayAndFinish(this, GuideActivity.class, 3000);
            }else{
                IntentUtils.startActivityForDelayAndFinish(this, MainActivity.class, 3000);
//                IntentUtils.startActivityForDelayAndFinish(this, BalanceAddActivity.class, 3000);
            }

        }

    }

    @Override
    public void permissionFail(int requestCode) {
        super.permissionFail(requestCode);
        IntentUtils.finish(this);
    }




}
