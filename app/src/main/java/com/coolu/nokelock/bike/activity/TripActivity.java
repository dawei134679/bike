package com.coolu.nokelock.bike.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.adapter.TripAdapter;
import com.coolu.nokelock.bike.base.BaseActivity;
import com.coolu.nokelock.bike.bean.TripBean;
import com.coolu.nokelock.bike.url.Url;
import com.coolu.nokelock.bike.util.BaseUtil;
import com.coolu.nokelock.bike.util.ConditionsUtils;
import com.coolu.nokelock.bike.util.Config;
import com.coolu.nokelock.bike.util.DateTimePickDialogUtil;
import com.coolu.nokelock.bike.util.TimeUtil;
import com.coolu.nokelock.bike.util.VolleyController;
import com.coolu.nokelock.bike.util.VolleyUtils;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.fitsleep.sunshinelibrary.utils.UtilSharedPreference;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class TripActivity extends BaseActivity implements View.OnClickListener {

    private TextView start_time;
    private TextView end_time;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private TripAdapter tripAdapter;
    private ImageView back;
    private TripBean tripBean=null;
    private TextView toolbor_title;
    private TextView seach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏文字颜色及图标为深色
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_red_details);
        back = (ImageView)findViewById(R.id.id_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initView();


    }

    private void initView() {
        toolbor_title = (TextView)findViewById(R.id.id_title_toolbar);
        toolbor_title.setText("我的行程");
       start_time= (TextView)findViewById(R.id.id_start_time);
        start_time.setOnClickListener(this);
        end_time=(TextView)findViewById(R.id.id_end_time);
        end_time.setOnClickListener(this);
        seach = (TextView)findViewById(R.id.id_search);
        seach.setOnClickListener(this);
        recyclerView=(RecyclerView)findViewById(R.id.id_red_recyclerview);
        layoutManager = new LinearLayoutManager(TripActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        tripAdapter = new TripAdapter(TripActivity.this,new ArrayList<TripBean.Result>());
        recyclerView.setAdapter(tripAdapter);
        tripAdapter.setTripListener(new TripAdapter.OnTripListener() {
            @Override
            public void trip(View view, int postion) {
                Intent tripContent=new Intent(TripActivity.this,TripContentActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("tripBean",tripBean);
                bundle.putInt("postion",tripBean.getResult().size()-postion-1);
                tripContent.putExtras(bundle);
                startActivity(tripContent);
            }
        });
        start_time.setText(TimeUtil.currentYear()+"-01-01");
        end_time.setText(TimeUtil.currentTime());
        //得到当前时间
        Log.e("rrr","当前时间"+ TimeUtil.currentYear());
        initData();

    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    private void initData() {
        HashMap<String, String> map = VolleyUtils.getCommomParameter();
       // map.put("token", EncryptUtils.stringToMD5("18813151324"+"20150515"));
        map.put("token",UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(), Config.TOKEN));
        if (!Url.isWifiProxy(this)) {
            VolleyUtils.deStringPost(TripActivity.this, Url.TRIP, map, "trip", new VolleyUtils.volleyListener() {
                @Override
                public void onResponse(JSONObject response) {

                }

                @Override
                public void onErrorResponse(String errorMessage) {

                }

                @Override
                public void onResponse(String response) {
                    Log.e("ww", "result" + response.toString());

                    tripBean = VolleyUtils.parseJsonWithGson(response.toString(), TripBean.class);
                    if (tripBean.getErrorCode() == "200" || "200".equals(tripBean.getErrorCode())) {
                        tripAdapter.updateList(tripBean.getResult());
                    } else if ("301".equals(tripBean.getErrorCode())) {
                        App.getInstance().getIBLE().disconnect();
                        UtilSharedPreference.saveString(ConditionsUtils.getContext(), Config.PHONE, "");
                        UtilSharedPreference.saveString(ConditionsUtils.getContext(), Config.TOKEN, "");
                        App.getInstance().getDaoSession().getUseBeanDao().deleteAll();
                        App.getInstance().getDaoSession().getSearchBeanDao().deleteAll();
                        App.getInstance().getDaoSession().getBikeOrderBeanDao().deleteAll();
                        App.getInstance().getDaoSession().getOrderBeanDao().deleteAll();
                        //个人信息清空
                        App.getInstance().setUserEntityBean(null);
                        ToastUtils.showMessage("登陆失效，请重新登陆");
                    }

                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VolleyController.getInstance(this.getApplicationContext()).cancelPendingRequests("trip");
    }

    /**
     * 显示日历进行选择
     */
    private void choiceTime(TextView view) {
        DateTimePickDialogUtil dateTimePickDialogUtil = new DateTimePickDialogUtil(TripActivity.this, view.getText().toString());
        dateTimePickDialogUtil.dateTimePicKDialog(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_start_time:
                choiceTime(start_time);
                break;
        }
    }
}
