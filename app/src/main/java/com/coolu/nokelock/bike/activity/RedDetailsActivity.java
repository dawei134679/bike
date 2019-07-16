package com.coolu.nokelock.bike.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.adapter.RedDetailsAdapter;
import com.coolu.nokelock.bike.base.BaseActivity;
import com.coolu.nokelock.bike.bean.RedDetailBean;
import com.coolu.nokelock.bike.url.Url;
import com.coolu.nokelock.bike.util.BaseUtil;
import com.coolu.nokelock.bike.util.Config;
import com.coolu.nokelock.bike.util.DateTimePickDialogUtil;
import com.coolu.nokelock.bike.util.TimeUtil;
import com.coolu.nokelock.bike.util.VolleyController;
import com.coolu.nokelock.bike.util.VolleyUtils;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.fitsleep.sunshinelibrary.utils.UtilSharedPreference;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 红包明细
 */

public class RedDetailsActivity extends BaseActivity implements View.OnClickListener {

    private TextView start_time;
    private TextView end_time;
    private TextView  search;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
   private RedDetailsAdapter adapter;
    private ImageView back;
    private String flag;
    private String title;
    private TextView title_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏文字颜色及图标为深色
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_red_details);
        flag = getIntent().getStringExtra("flag");
        title = getIntent().getStringExtra("title");
        initView();
    }

    private void initView() {
        back = (ImageView)findViewById(R.id.id_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        title_tv = (TextView)findViewById(R.id.id_title_toolbar);
        if (title!=null)
        title_tv.setText(title);

        start_time=(TextView)findViewById(R.id.id_start_time);
        end_time=(TextView) findViewById(R.id.id_end_time);
        search=(TextView)findViewById(R.id.id_search);
        start_time.setOnClickListener(this);
        end_time.setOnClickListener(this);
        search.setOnClickListener(this);
        recyclerView = (RecyclerView)findViewById(R.id.id_red_recyclerview);
        layoutManager = new LinearLayoutManager(RedDetailsActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //适配器
        adapter=new RedDetailsAdapter(RedDetailsActivity.this,new ArrayList<RedDetailBean.Result>());
        recyclerView.setAdapter(adapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        start_time.setText(TimeUtil.currentYear()+"-01-01");
        end_time.setText(TimeUtil.currentTime());
       initData();
    }

    private void initData() {
        HashMap<String, String> map = VolleyUtils.getCommomParameter();
        map.put("token", UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(), Config.TOKEN));
        map.put("status",flag);
        if (!Url.isWifiProxy(this)) {
            VolleyUtils.deStringPost(RedDetailsActivity.this, Url.RED, map, "reddetail", new VolleyUtils.volleyListener() {
                @Override
                public void onResponse(JSONObject response) {

                }

                @Override
                public void onErrorResponse(String errorMessage) {

                }

                @Override
                public void onResponse(String response) {
//                    Log.e("TAG", "明细是："+response.toString());
                    RedDetailBean redDetailBean = VolleyUtils.parseJsonWithGson(response.toString(), RedDetailBean.class);
//                    if (redDetailBean.getResult().size() == 0) {
//                        ToastUtils.showMessage("目前还没有收到红包");
//                        return;
//                    }
                    adapter.updateList(redDetailBean.getResult());

                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VolleyController.getInstance(this.getApplicationContext()).getRequestQueue().cancelAll("reddetail");
    }

    /**
     * 显示日历进行选择
     */
    private void choiceTime(TextView view) {
        DateTimePickDialogUtil dateTimePickDialogUtil = new DateTimePickDialogUtil(RedDetailsActivity.this, view.getText().toString());
        dateTimePickDialogUtil.dateTimePicKDialog(view);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_start_time:
                choiceTime(start_time);
                break;
            case R.id.id_end_time:
                choiceTime(end_time);
                break;
            case R.id.id_search:
                break;
        }
    }
}
