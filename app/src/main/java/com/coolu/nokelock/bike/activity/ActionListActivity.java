package com.coolu.nokelock.bike.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.adapter.ActionListAdapter;
import com.coolu.nokelock.bike.base.BaseActivity;
import com.coolu.nokelock.bike.bean.ActionListBean;
import com.coolu.nokelock.bike.bean.TopActivitybean;
import com.coolu.nokelock.bike.url.Url;
import com.coolu.nokelock.bike.util.BaseUtil;
import com.coolu.nokelock.bike.util.ConditionsUtils;
import com.coolu.nokelock.bike.util.Config;
import com.coolu.nokelock.bike.util.VolleyController;
import com.coolu.nokelock.bike.util.VolleyUtils;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.fitsleep.sunshinelibrary.utils.UtilSharedPreference;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ActionListActivity extends BaseActivity {

    private TopActivitybean topaction;
    private ImageView topImg;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ActionListAdapter actionListAdapter;
    private ActionListBean bean=null;
    private Handler handler=new Handler(){


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 101:
                    bean = (ActionListBean)msg.obj;
                    if (bean.getErrorCode()==200){
                        actionListAdapter.updateList(bean.getResult());
                    }else if ("301".equals(bean.getErrorCode())) {
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
                    break;
            }
        }
    };
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏文字颜色及图标为深色
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_action_list);
       // Bundle bundle = getIntent().getExtras();
       // topaction = (TopActivitybean) bundle.getSerializable("topaction");
        back = (ImageView)findViewById(R.id.id_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        recyclerView = (RecyclerView)findViewById(R.id.id_action_recycleView);
        layoutManager = new LinearLayoutManager(ActionListActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        actionListAdapter = new ActionListAdapter(ActionListActivity.this,new ArrayList<ActionListBean.ResultBean>());

        recyclerView.setAdapter(actionListAdapter);
        actionListAdapter.setListListener(new ActionListAdapter.ActionListListener() {
            @Override
            public void ItemListener(View view, int postion) {
                //Toast.makeText(ActionListActivity.this,"postion"+postion,Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(ActionListActivity.this,ActionListDetailActivity.class);
                intent.putExtra("jump",bean.getResult().get(postion).getJumpUrl());
                intent.putExtra("id",bean.getResult().get(postion).getId());
                intent.putExtra("title",bean.getResult().get(postion).getActivityName());
                startActivity(intent);
            }
        });


//        if (topaction!=null){
//            Log.e("mm","action"+topaction.getResults().get(0).getActivityOutUrl());
//            Picasso.with(ActionListActivity.this).load(topaction.getResults().get(0).getActivityTopUrl()).into(topImg);
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (topaction!=null){
//            actionListAdapter.updateList(topaction.getResults());
//        }
        if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
            VolleyUtils.doGet(ActionListActivity.this, Url.ACTION_LIST, "actionList", new VolleyUtils.volleyListener() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.e("kkk", response.toString());
                    ActionListBean actionListBean = VolleyUtils.parseJsonWithGson(response.toString(), ActionListBean.class);
                    Message message = new Message();
                    message.obj = actionListBean;
                    message.what = 101;
                    handler.sendMessage(message);
                }

                @Override
                public void onErrorResponse(String errorMessage) {

                }

                @Override
                public void onResponse(String response) {


                }
            });
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        VolleyController.getInstance(this.getApplicationContext()).getRequestQueue().cancelAll("actionList");
    }
}
