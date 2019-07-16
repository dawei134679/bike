/*
 * Copyright (C) 2017 youzanyun.com, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.coolu.nokelock.bike.youzan;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.activity.App;
import com.coolu.nokelock.bike.activity.LoginActivity;

import com.coolu.nokelock.bike.adapter.RouteAdatpter;
import com.coolu.nokelock.bike.url.Url;
import com.coolu.nokelock.bike.util.ConditionsUtils;
import com.coolu.nokelock.bike.util.Config;
import com.coolu.nokelock.bike.util.ShanYanLoginUtil;
import com.coolu.nokelock.bike.util.VolleyUtils;
import com.fitsleep.sunshinelibrary.utils.IntentUtils;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.fitsleep.sunshinelibrary.utils.UtilSharedPreference;
import com.youzan.androidsdk.YouzanToken;
import com.youzan.androidsdk.basic.YouzanBrowser;
import com.youzan.androidsdk.event.AbsAuthEvent;
import com.youzan.androidsdk.event.AbsChooserEvent;
import com.youzan.androidsdk.event.AbsShareEvent;
import com.youzan.androidsdk.event.AbsStateEvent;
import com.youzan.androidsdk.model.goods.GoodsShareModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


/**
 * 这里使用{@link WebViewFragment}对{@link android.webkit.WebView}生命周期有更好的管控.
 */
public class YouzanFragment extends WebViewFragment implements SwipeRefreshLayout.OnRefreshListener {
    private YouzanBrowser mView;
    private SwipeRefreshLayout mRefreshLayout;
    private Toolbar mToolbar;
    private YouzanToken token;
    private TextView title;
    private String url;

//    Learning add
    private ShanYanLoginUtil shanYanLoginUtil;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews(view);
        setupYouzan();

        url = getArguments().getString("url");
      //  mView.loadUrl("https://h5.youzan.com/v2/showcase/homepage?alias=kewr19e1");
    }

    @Override
    public void onStart() {
        super.onStart();
        if (url!=null){
            mView.loadUrl(url);
       }else {
            initData();
        }
    }

    private void initData() {
        HashMap<String, String> map = VolleyUtils.getCommomParameter();

        map.put("name", App.getInstance().getaMapLocation()==null?"":App.getInstance().getaMapLocation().getCity());
        Log.e("kop",App.getInstance().getaMapLocation()==null?"":App.getInstance().getaMapLocation().getCity());
        // map.put("name","秦皇岛市");
        if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
            VolleyUtils.deStringPost(App.getInstance().getApplicationContext(), Url.SHOPURL, map, "shop", new VolleyUtils.volleyListener() {
                @Override
                public void onResponse(JSONObject response) {

                }

                @Override
                public void onErrorResponse(String errorMessage) {


                }

                @Override
                public void onResponse(String response) {
                    Log.e("kop", response);
                    try {
                        JSONObject ob = new JSONObject(response.toString());
                        String errorCode = ob.getString("errorCode");
                        if ("200".equals(errorCode)) {
                            String result = ob.getString("result");
                            Log.e("kop", "result" + result);
                            mView.loadUrl(result);
                        } else if ("301".equals(errorCode)) {
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
                        } else {
                            ToastUtils.showMessage("加载失败");

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    }

    private void setupViews(View contentView) {
        //WebView
        mView = getWebView();


        mRefreshLayout = (SwipeRefreshLayout) contentView.findViewById(R.id.swipe);
       // title = (TextView)contentView.findViewById(R.id.id_title_toolbar);


        //刷新
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setColorSchemeColors(Color.BLUE, Color.RED);
        mRefreshLayout.setEnabled(false);
    }
    private String login="https://uic.youzan.com/sso/open/login";
    private void setupYouzan() {
        //认证事件, 回调表示: 需要需要新的认证信息传入
        mView.subscribe(new AbsAuthEvent() {

            @Override
            public void call(Context context, boolean needLogin) {
                /**
                 * 建议实现逻辑:
                 *
                 *     判断App内的用户是否登录?
                 *       => 已登录: 请求带用户角色的认证信息(login接口);
                 *       => 未登录: needLogin为true, 唤起App内登录界面, 请求带用户角色的认证信息(login接口);
                 *       => 未登录: needLogin为false, 请求不带用户角色的认证信息(initToken接口).
                 *
                 *      服务端接入文档: https://www.youzanyun.com/docs/guide/appsdk/683
                 */
                //TODO 自行编码实现. 具体可参考开发文档中的伪代码实现
                if (TextUtils.isEmpty(UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(), Config.PHONE))) {
//                    ToastUtils.showMessage("请先登录");
//                    IntentUtils.startActivity(getActivity(), LoginActivity.class);

//                    Learning add
                    if (shanYanLoginUtil==null){
                        shanYanLoginUtil=new ShanYanLoginUtil(getActivity());
                    }
                    shanYanLoginUtil.shanYanLogin();

                    return;
                }

                HashMap map=new HashMap();
                map.put("client_id","a2feecb3ceade7d723");
                map.put("client_secret","82e847932da4d379d296f3b3e3c7b00c");
                map.put("open_user_id",App.getInstance().getUserEntityBean()==null?"":App.getInstance().getUserEntityBean().getUserId());
                map.put("kdt_id","16594038");

                VolleyUtils.deStringPost(App.getInstance().getApplicationContext(), login,map, "you", new VolleyUtils.volleyListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }

                    @Override
                    public void onErrorResponse(String errorMessage) {

                    }

                    @Override
                    public void onResponse(String response) {
                        //  Log.e("yyyy",response.toString());
                        try {
                            JSONObject object=new JSONObject(response.toString());
                            JSONObject data = object.getJSONObject("data");
                            String access_token = data.getString("access_token");
                            String cookie_key = data.getString("cookie_key");
                            String cookie_value = data.getString("cookie_value");
                            token=new YouzanToken();
                            token.setAccessToken(access_token);
                            token.setCookieKey(cookie_key);
                            token.setCookieValue(cookie_value);
                            mView.sync(token);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });

        //文件选择事件, 回调表示: 发起文件选择. (如果app内使用的是系统默认的文件选择器, 该事件可以直接删除)
        mView.subscribe(new AbsChooserEvent() {
            @Override
            public void call(Context context, Intent intent, int requestCode) throws ActivityNotFoundException {
                startActivityForResult(intent, requestCode);
            }
        });

        //页面状态事件, 回调表示: 页面加载完成
        mView.subscribe(new AbsStateEvent() {
            @Override
            public void call(Context context) {
              //  title.setText(mView.getTitle());
                onUpdateTitleListener.updateTitleListener(mView.getTitle());
                //停止刷新
                mRefreshLayout.setRefreshing(false);
                mRefreshLayout.setEnabled(true);
            }
        });



        //分享事件, 回调表示: 获取到当前页面的分享信息数据
        mView.subscribe(new AbsShareEvent() {
            @Override
            public void call(Context context, GoodsShareModel data) {
                /**
                 * 在获取数据后, 可以使用其他分享SDK来提高分享体验.
                 * 这里调用系统分享来简单演示分享的过程.
                 */
                String content = data.getDesc() + data.getLink();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, content);
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, data.getTitle());
                sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

    }

    //利用回调修改标题
    public OnUpdateTitleListener onUpdateTitleListener;
    public void setOnUpdateTitleListener(OnUpdateTitleListener onUpdateTitleListener){
        this.onUpdateTitleListener=onUpdateTitleListener;
    }

    public interface OnUpdateTitleListener{
        void updateTitleListener(String title);
    }


    @Override
    protected int getWebViewId() {
        //YouzanBrowser在布局文件中的id
        return R.id.view;
    }


    @Override
    protected int getLayoutId() {
        //布局文件
        return R.layout.fragment_youzan;
    }

    @Override
    public boolean onBackPressed() {
        //页面回退
        return getWebView().pageGoBack();
    }

    public void onGoBack(){
        getWebView().pageGoBack();

    }
    public boolean onCanGoBack(){
        return getWebView().pageCanGoBack();
    }

    @Override
    public void onRefresh() {
        //重新加载页面
        mView.reload();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            //另需处理认证等...

            mView.receiveFile(requestCode, data);
        }
    }
}
