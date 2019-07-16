package com.coolu.nokelock.bike.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.base.BaseActivity;
import com.coolu.nokelock.bike.util.VolleyUtils;
import com.fitsleep.sunshinelibrary.utils.IntentUtils;


public class DepositOkActivity extends BaseActivity {

    private TextView suceess;
    private Handler handler=new Handler();
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit_ok);
        //设置状态栏文字颜色及图标为深色
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        suceess = (TextView)findViewById(R.id.id_post_success);
        tv = (TextView) findViewById(R.id.id_active_one);
        int flag = getIntent().getExtras().getInt("flag");
        if (flag==0){
            tv.setText("恭喜你 保证金充值成功");
        }
        suceess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        Intent intent=new Intent(DepositOkActivity.this,MainActivity.class);
//                        startActivity(intent);
//                    }
//                },500);
                Intent intent=new Intent(DepositOkActivity.this,VipComboActivity.class);
                startActivity(intent);
                DepositOkActivity.this.finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //更新人员信息
        VolleyUtils.GetPerson();
    }
}
