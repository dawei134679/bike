package com.coolu.nokelock.bike.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.coolu.nokelock.bike.base.BaseActivity;
import com.fitsleep.sunshinelibrary.utils.IntentUtils;

/**
 * @author Learning
 * @date 2019/3/25
 */
public class ShanYanActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntentUtils.startActivityAndFinish(this, MainActivity.class);
    }
}
