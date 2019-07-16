package com.coolu.nokelock.bike.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.adapter.BalanceAddAdapter;
import com.coolu.nokelock.bike.adapter.VipComboAdapter;
import com.coolu.nokelock.bike.base.BaseActivity;
import com.coolu.nokelock.bike.bean.BalanceAddBean;
import com.coolu.nokelock.bike.dialog.BuyDialog;
import com.coolu.nokelock.bike.fragment.Freefragment;
import com.coolu.nokelock.bike.url.Url;
import com.coolu.nokelock.bike.util.DialogShowUtils;
import com.coolu.nokelock.bike.util.VolleyUtils;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.google.gson.Gson;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Learning
 * @date 2019/3/4
 */
public class VipComboActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.id_back_vip_combo)
    ImageView id_back_vip_combo;
    @BindView(R.id.lv_vip_combo)
    ListView lv_vip_combo;
    @BindView(R.id.tv_is_buy)
    TextView tv_is_buy;
    @BindView(R.id.tv_money)
    TextView tv_money;
    @BindView(R.id.tv_buy)
    TextView tv_buy;

//    选择的套餐
    private String payMoney="0.01";
//    是否强制购买会员套餐，0是不强制购买，1是强制购买
    private String isbuyridingcard;

    private  VipComboAdapter vipComboAdapter;
    private BalanceAddBean balanceAddBean;
    private String tag;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_combo);
        ButterKnife.bind(this);
        tag = getIntent().getStringExtra("tag");
        if (App.getInstance().getUserEntityBean()!=null){
            isbuyridingcard=App.getInstance().getUserEntityBean().getIsbuyridingcard();
        }
        initData();
    }

    private void initData() {
        if (TextUtils.equals("action",tag)){
            tv_is_buy.setVisibility(View.GONE);
        }else {
            if (TextUtils.equals("0",isbuyridingcard)){
                tv_is_buy.setVisibility(View.VISIBLE);
            }else {
                tv_is_buy.setVisibility(View.GONE);
            }
        }
        id_back_vip_combo.setOnClickListener(this);
        tv_is_buy.setOnClickListener(this);
        tv_buy.setOnClickListener(this);
        getDataFromServer();
        lv_vip_combo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                payMoney=balanceAddBean.getResult().get(i).getCardMoney()+"";
                tv_money.setText(payMoney);
                vipComboAdapter.setSelectedPosition(i);
                vipComboAdapter.notifyDataSetInvalidated();
            }
        });
    }

    private void getDataFromServer() {
        if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
            VolleyUtils.deStringPost(VipComboActivity.this, Url.LESSBALANCE, null,null, new VolleyUtils.volleyListener() {
                @Override
                public void onResponse(JSONObject response) {

                }

                @Override
                public void onErrorResponse(String errorMessage) {
                    Log.e("TAG","错误信息是："+errorMessage);

                }

                @Override
                public void onResponse(String response) {
                    jsonData(response);
                }
            });
        }
    }
    private void jsonData(String response) {
        Gson gson=new Gson();
        balanceAddBean = gson.fromJson(response, BalanceAddBean.class);
        payMoney=balanceAddBean.getResult().get(0).getCardMoney()+"";
        tv_money.setText(payMoney);
        vipComboAdapter=new VipComboAdapter(VipComboActivity.this,balanceAddBean.getResult());
        lv_vip_combo.setAdapter(vipComboAdapter);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_back_vip_combo:
                VipComboActivity.this.finish();
                break;
            case R.id.tv_is_buy:
                Freefragment.isScan=true;
                VipComboActivity.this.finish();
                break;
            case R.id.tv_buy:
                BuyDialog buyDialog=new BuyDialog(VipComboActivity.this,R.style.NewDialog,payMoney);
                buyDialog.show();
                break;
        }

    }
}
