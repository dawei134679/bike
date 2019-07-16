package com.coolu.nokelock.bike.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.activity.App;
import com.coolu.nokelock.bike.adapter.RedAdapter;
import com.coolu.nokelock.bike.base.BaseFragment;
import com.coolu.nokelock.bike.bean.CardBean;
import com.coolu.nokelock.bike.url.Url;
import com.coolu.nokelock.bike.util.Config;
import com.coolu.nokelock.bike.util.VolleyController;
import com.coolu.nokelock.bike.util.VolleyUtils;
import com.fitsleep.sunshinelibrary.utils.UtilSharedPreference;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by admin on 2017/7/21.
 */
public class RedFragment extends BaseFragment {

    private RecyclerView recycleView;
    private LinearLayoutManager layoutManager;
    private RedAdapter redAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.redfragment, null);
        initView(inflate);
        Log.e("zz","RedFragment");
       //recycleView.setAdapter(redAdapter);
        return inflate;
    }

    private void initView(View inflate) {
        recycleView = (RecyclerView)inflate.findViewById(R.id.id_recycleView);
        layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recycleView.setLayoutManager(layoutManager);
        recycleView.setItemAnimator(new DefaultItemAnimator());
        //适配器
         redAdapter = new RedAdapter(getActivity(), new ArrayList<CardBean.ResultBean>());
        recycleView.setAdapter(redAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }


    private void initData() {
        Log.e("zz","initData");
        HashMap map=new HashMap();
     //   map.put("token", EncryptUtils.stringToMD5("18813151324"+"20150515"));
        map.put("token",UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(), Config.TOKEN));
        map.put("status",0+"");
        if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
            VolleyUtils.deStringPost(getActivity(), Url.CARD, map, "cardList", new VolleyUtils.volleyListener() {
                @Override
                public void onResponse(JSONObject response) {
                    // Toast.makeText(getActivity(),"re",Toast.LENGTH_SHORT).show();
                    Log.e("zz", response.toString());
//                Gson gson=new Gson();
//                gson.fromJson(response.toString(), TopActivitybean.class);
//                TopActivitybean topActivitybean = VolleyUtils.parseJsonWithGson(response.toString(), TopActivitybean.class);
//
//                //更新适配器
//                redAdapter.updateList(topActivitybean.getResults());

                }

                @Override
                public void onErrorResponse(String errorMessage) {

                }

                @Override
                public void onResponse(String response) {
                    Log.e("zzz", response.toString());
                    CardBean cardBean = VolleyUtils.parseJsonWithGson(response.toString(), CardBean.class);
                    if (cardBean.getErrorCode() == 200) {
                        redAdapter.updateList(cardBean.getResult());
                    }

                }
            });
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //volley的取消
        VolleyController.getInstance(getActivity()).getRequestQueue().cancelAll("cardList");
    }
}
