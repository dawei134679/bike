package com.coolu.nokelock.bike.activity;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.base.BaseActivity;
import com.coolu.nokelock.bike.util.BaseUtil;
import com.fitsleep.sunshinelibrary.utils.PhoneUtils;
import com.fitsleep.sunshinelibrary.utils.ToolsUtils;

public class AboutActivity extends BaseActivity {

    private ImageView back;
    private TextView phone;
    private TextView version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏文字颜色及图标为深色
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_about);
        back = (ImageView)findViewById(R.id.id_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        phone = (TextView)findViewById(R.id.id_phone);

        ((RelativeLayout)findViewById(R.id.id_about_two)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermission(new String[]{Manifest.permission.CALL_PHONE},120);
            }
        });

        version = (TextView)findViewById(R.id.id_version);
        version.setText("版本:"+ ToolsUtils.getVersion(this));
    }

    @Override
    public void permissionSuccess(int requestCode) {
        super.permissionSuccess(requestCode);
        if (checkPermissions(new String[]{Manifest.permission.CALL_PHONE})){
            PhoneUtils.call(AboutActivity.this, phone.getText().toString().trim());
        }
    }


}
