package com.coolu.nokelock.bike.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.activity.App;
import com.coolu.nokelock.bike.activity.UserActivity;
import com.coolu.nokelock.bike.adapter.RouteAdatpter;
import com.coolu.nokelock.bike.base.BaseFragment;
import com.coolu.nokelock.bike.bean.QxrouteBean;
import com.coolu.nokelock.bike.url.Url;
import com.coolu.nokelock.bike.util.VolleyUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by admin on 2017/8/29.
 * 推荐骑行
 */

public class TuiJianfragment extends BaseFragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tuijian_fragment, null);
      //  initSwipe(view);

        recyclerView = (RecyclerView)view.findViewById(R.id.id_recycleView);
        layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置适配器
        adapter = new RouteAdatpter(getActivity(),new ArrayList<QxrouteBean.DatasBean.RouteListBean>());
        recyclerView.setAdapter(adapter);
        adapter.setOnRouteItemListener(new RouteAdatpter.OnRouteItemListener() {
            @Override
            public void routeItemListener(View view, int postion) {

                int pos= qx.getDatas().getRouteList().get(postion).getId();
                String title = qx.getDatas().getRouteList().get(postion).getTitle();
                Intent user=new Intent(getActivity(),UserActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("url",pos+"");
                bundle.putString("route",title);
                bundle.putInt("flag",1);
                user.putExtras(bundle);
                startActivity(user);
            }
        });
        return view;
    }

  //  private void initSwipe(View view) {
//        swip = (SwipeRefreshLayout)view.findViewById(R.id.id_swipe);
//        swip.setColorSchemeColors(Color.RED,Color.YELLOW,Color.BLUE);
//      //  swip.setBackgroundColor(Color.GREEN); //背景颜色
//        swip.setSize(SwipeRefreshLayout.LARGE);//大小
//        swip.setDistanceToTriggerSync(50);
//        swip.setProgressViewEndTarget(false,200);
//        swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                if (adapter!=null){
//                    adapter.clear();
//                    adapter.updateList(qx.getDatas().getRouteList());
//                }
//
//                try {
//
//                    Thread.sleep(2000);
//                    swip.setRefreshing(false);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });

//    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        HashMap<String,String>map=new HashMap<>();
        map.put("lng", App.getInstance().getaMapLocation()==null?"":App.getInstance().getaMapLocation().getLongitude()+"");
        map.put("lat", App.getInstance().getaMapLocation()==null?"":App.getInstance().getaMapLocation().getLatitude()+"");
        map.put("pager","0");
        if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
            VolleyUtils.deStringPost(getActivity(), Url.QIXING, map, "qing", new VolleyUtils.volleyListener() {
                @Override
                public void onResponse(JSONObject response) {

                }

                @Override
                public void onErrorResponse(String errorMessage) {

                }

                @Override
                public void onResponse(String response) {
                    Log.e("pp", response.toString());
                    QxrouteBean qxrouteBean = VolleyUtils.parseJsonWithGson(response.toString(), QxrouteBean.class);

                    Message message = new Message();
                    message.what = 101;
                    message.obj = qxrouteBean;
                    handler.sendMessage(message);
                }
            });
        }
    }
}
