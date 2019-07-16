package com.coolu.nokelock.bike.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.adapter.RouteAdatpter;
import com.coolu.nokelock.bike.base.BaseActivity;
import com.coolu.nokelock.bike.bean.QxrouteBean;
import com.coolu.nokelock.bike.bean.RouteBean;
import com.coolu.nokelock.bike.url.Url;
import com.coolu.nokelock.bike.util.Config;
import com.coolu.nokelock.bike.util.VolleyController;
import com.coolu.nokelock.bike.util.VolleyUtils;
import com.fitsleep.sunshinelibrary.utils.UtilSharedPreference;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TuiActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private  RouteBean routeBean;
    private RouteAdatpter adapter;
    private QxrouteBean qx;
    private Handler handler=new Handler(){


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 101:
                    qx = (QxrouteBean)msg.obj;
                    if ("1101".equals(qx.getCode())){
                        adapter.updateList(qx.getDatas().getRouteList());
                    }
                    break;
            }
        }
    };
    private SwipeRefreshLayout swip;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏文字颜色及图标为深色
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_tui);
        initView();
      //  initData();
        getRouteList();
    }

    private void initData() {
        //先得到推荐骑行的地址
        HashMap<String, String> map = VolleyUtils.getCommomParameter();
        map.put("token", UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(), Config.TOKEN));
        map.put("name","秦皇岛市");
        if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
            VolleyUtils.deStringPost(TuiActivity.this, Url.ROUTEURL, map, "routeUrl", new VolleyUtils.volleyListener() {
                @Override
                public void onResponse(JSONObject response) {

                }

                @Override
                public void onErrorResponse(String errorMessage) {

                }

                @Override
                public void onResponse(String response) {
                    Log.e("kop", response);
                    routeBean = VolleyUtils.parseJsonWithGson(response.toString(), RouteBean.class);
                    if (routeBean.getErrorCode() == 200) {
                        //得到推荐骑行列表
                        // getRouteList(routeBean);
                    }
                }
            });
        }

    }

    //得到推荐骑行列表
    private void getRouteList() {

        HashMap<String, String> map = VolleyUtils.getCommomParameter();
            map.put("lng", App.getInstance().getaMapLocation()==null?"":App.getInstance().getaMapLocation().getLongitude()+"");
            map.put("lat", App.getInstance().getaMapLocation()==null?"":App.getInstance().getaMapLocation().getLatitude()+"");
      //  map.put("lng","119.535945");
        //map.put("lat","39.886491");

            map.put("pager","0");
        if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
            VolleyUtils.deStringPost(TuiActivity.this, Url.ROUTEURL, map, "qing", new VolleyUtils.volleyListener() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.e("kop", response.toString());
                }

                @Override
                public void onErrorResponse(String errorMessage) {

                }

                @Override
                public void onResponse(String response) {
                    Log.e("kop", response.toString());
                    QxrouteBean qxrouteBean = VolleyUtils.parseJsonWithGson(response.toString(), QxrouteBean.class);
                    Message message = new Message();
                    message.what = 101;
                    message.obj = qxrouteBean;
                    handler.sendMessage(message);
                }
            });
        }


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        setResult(100);
        return super.onKeyDown(keyCode, event);
    }

    private void initView() {
        back = (ImageView)findViewById(R.id.id_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(100);
                finish();
            }
        });
        recyclerView = (RecyclerView)findViewById(R.id.id_recycleView);
        layoutManager = new LinearLayoutManager(TuiActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置适配器
        adapter = new RouteAdatpter(TuiActivity.this,new ArrayList<QxrouteBean.DatasBean.RouteListBean>());
        recyclerView.setAdapter(adapter);
        adapter.setOnRouteItemListener(new RouteAdatpter.OnRouteItemListener() {
            @Override
            public void routeItemListener(View view, int postion) {

                int pos= qx.getDatas().getRouteList().get(postion).getId();
                Log.e("kop",pos+"");
                String title = qx.getDatas().getRouteList().get(postion).getTitle();
                Intent user=new Intent(TuiActivity.this,UserActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("url",pos+"");
                bundle.putString("route",title);
                bundle.putInt("flag",1);
                user.putExtras(bundle);
                startActivity(user);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VolleyController.getInstance(TuiActivity.this).getRequestQueue().cancelAll("qing");
        VolleyController.getInstance(TuiActivity.this).getRequestQueue().cancelAll("routeUrl");
    }
}
