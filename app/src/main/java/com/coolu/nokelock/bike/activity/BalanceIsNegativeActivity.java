package com.coolu.nokelock.bike.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BalanceIsNegativeActivity extends BaseActivity implements View.OnClickListener {


    private ImageView id_back;
    private ImageView iv_balance_negative_true;
    private TextView tvMeSurplusMoney;
    private TextView tv_tishi_jine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_is_negative);
        id_back = (ImageView) findViewById(R.id.id_back);
        id_back.setOnClickListener(this);
        tv_tishi_jine = (TextView) findViewById(R.id.tv_tishi_jine);
        iv_balance_negative_true = (ImageView) findViewById(R.id.iv_balance_negative_true);
        iv_balance_negative_true.setOnClickListener(this);
        tvMeSurplusMoney = (TextView) findViewById(R.id.tv_me_surplus_money);
        if (App.getInstance().getUserEntityBean()!=null&&App.getInstance().getUserEntityBean().getUserMoney()!=null){
            tvMeSurplusMoney.setText(App.getInstance().getUserEntityBean().getUserMoney());
        }
        if (App.getInstance().getUserEntityBean()!=null&& (!TextUtils.isEmpty(App.getInstance().getUserEntityBean().getDefaultDeposit()))) {
            tv_tishi_jine.setText("然后再退还 "+App.getInstance().getUserEntityBean().getDefaultDeposit()+" 元保证金");
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_back:
                finish();
                break;
            case R.id.iv_balance_negative_true:
                Intent rechargee=new Intent(BalanceIsNegativeActivity.this,RechargeActivity.class);
                //充余额
                rechargee.putExtra("flag",1);
                startActivity(rechargee);
                finish();
                break;
        }
    }
}
