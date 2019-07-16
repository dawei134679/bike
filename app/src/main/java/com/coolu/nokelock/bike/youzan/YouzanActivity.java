/*
 * Copyright (C) 2017 youzanyun.com, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.coolu.nokelock.bike.youzan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.coolu.nokelock.bike.R;

public class YouzanActivity extends AppCompatActivity {
    public static final String KEY_URL = "url";
    private YouzanFragment mFragment;
    private TextView titlecentent;
    private ImageView back;
    private String lookCard;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏文字颜色及图标为深色
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_placeholder);

        lookCard = getIntent().getStringExtra("url");

        titlecentent = (TextView)findViewById(R.id.id_title_toolbar);
        back = (ImageView)findViewById(R.id.id_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFragment == null ||mFragment.onCanGoBack()) {

                   mFragment.onGoBack();
                }else{
                    setResult(100);
                    finish();
                }
            }
        });
        mFragment = new YouzanFragment();
        Bundle bundle=new Bundle();

        if (lookCard!=null){
            bundle.putString("url",lookCard);
        }
        mFragment.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.placeholder, mFragment)
                .commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        if (mFragment == null || !mFragment.onBackPressed()) {
            setResult(100);
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mFragment!=null){
            mFragment.setOnUpdateTitleListener(new YouzanFragment.OnUpdateTitleListener() {
                @Override
                public void updateTitleListener(String title) {
                  titlecentent.setText(title);
                }
            });
        }
    }
}
