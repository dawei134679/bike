package com.coolu.nokelock.bike.fragment;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.android.volley.toolbox.Volley;
import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.activity.App;
import com.coolu.nokelock.bike.activity.LoginActivity;
import com.coolu.nokelock.bike.activity.NcActivity;
import com.coolu.nokelock.bike.base.BaseFragment;
import com.coolu.nokelock.bike.url.Url;
import com.coolu.nokelock.bike.util.ConditionsUtils;
import com.coolu.nokelock.bike.util.Config;
import com.coolu.nokelock.bike.util.VolleyUtils;
import com.fitsleep.sunshinelibrary.utils.IntentUtils;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.fitsleep.sunshinelibrary.utils.UtilSharedPreference;
import com.youzan.androidsdk.YouzanToken;
import com.youzan.androidsdk.account.Shop;
import com.youzan.androidsdk.event.AbsAuthEvent;
import com.youzan.androidsdk.event.AbsChooserEvent;
import com.youzan.androidsdk.event.AbsShareEvent;
import com.youzan.androidsdk.event.AbsStateEvent;

import com.youzan.androidsdk.model.goods.GoodsShareModel;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by admin on 2017/11/8.
 * https://h5.youzan.com/v2/showcase/homepage?alias=hnh0xir0
 * https://h5.youzan.com/v2/feature/2unhwt85
 */

public class ShopFragment extends BaseFragment {
//    private String url="https://h5.youzan.com/v2/feature/2unhwt85";
//   // private YouzanHybrid mView;
//    private SwipeRefreshLayout  refreshLayout;
//    private YouzanToken token;
//    private Handler handler=new Handler();
//
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = LayoutInflater.from(getActivity()).inflate(R.layout.shop_fragment_layout, null);
//
//        setView(view);
//        setupYouzan();
//
//        return view;
//    }
//
//    private void setView(View view) {
//
//        mView = (YouzanHybrid)view.findViewById(R.id.view);
//        refreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe);
//        //刷新
//        refreshLayout.setOnRefreshListener(this);
//        refreshLayout.setColorSchemeColors(Color.BLUE, Color.RED);
//        refreshLayout.setEnabled(false);
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        Log.e("lol","shop");
//      //  mView.loadUrl(url);
//        initData();
//
//
//    }
//
//    private void initData() {
//        HashMap<String,String> map=new HashMap<String, String>();
//        map.put("token", UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(), Config.TOKEN));
//        map.put("name",App.getInstance().getaMapLocation()==null?"":App.getInstance().getaMapLocation().getCity());
//      //  map.put("name","秦皇岛市");
//        VolleyUtils.deStringPost(getActivity(), Url.SHOP, map, "shop", new VolleyUtils.volleyListener() {
//            @Override
//            public void onResponse(JSONObject response) {
//
//            }
//
//            @Override
//            public void onErrorResponse(String errorMessage) {
//
//
//            }
//
//            @Override
//            public void onResponse(String response) {
//                Log.e("kop",response);
//                try {
//                    JSONObject ob=new JSONObject(response.toString());
//                    String errorCode = ob.getString("errorCode");
//                    if ("200".equals(errorCode)){
//                        String result = ob.getString("result");
//                        mView.loadUrl(result);
//
//
//                    }else if ("301".equals(errorCode)){
//                        App.getInstance().getIBLE().disconnect();
//                        UtilSharedPreference.saveString(ConditionsUtils.getContext(), Config.PHONE, "");
//                        UtilSharedPreference.saveString(ConditionsUtils.getContext(), Config.TOKEN, "");
//                        App.getInstance().getDaoSession().getUseBeanDao().deleteAll();
//                        App.getInstance().getDaoSession().getSearchBeanDao().deleteAll();
//                        App.getInstance().getDaoSession().getBikeOrderBeanDao().deleteAll();
//                        App.getInstance().getDaoSession().getOrderBeanDao().deleteAll();
//                        //个人信息清空
//                        App.getInstance().setUserEntityBean(null);
//                        ToastUtils.showMessage("登陆失效，请重新登陆");
//                    } else{
//                        ToastUtils.showMessage("加载失败");
//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//    }
//
//    private long exitTime=0;
//    public void onKeyDown(int keyCode, KeyEvent event) {
//
//
//
//        if (keyCode==KeyEvent.KEYCODE_BACK){
//            if (mView.pageCanGoBack()){
//                mView.pageGoBack();
//            }else {
//                if ( event.getAction() == KeyEvent.ACTION_DOWN) {
//                    if ((System.currentTimeMillis() - exitTime) > 2000){//System.currentTimeMillis()无论何时调用，肯定大于2000  
//                        Toast.makeText(getActivity(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
//                        exitTime = System.currentTimeMillis();
//                    } else {
//                        getActivity().finish();
//                        System.exit(0);
//                    }
//
//
//                }
//            }
//        }
//
//    }
//
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        //重新加载页面
//       // mView.reload();
//
//    }
//
//    @Override
//    public void onRefresh() {
//        //重新加载页面
//        mView.reload();
//    }
//    private String login="https://uic.youzan.com/sso/open/login";
//    private void setupYouzan() {
//
//        //认证事件, 回调表示: 需要需要新的认证信息传入
//        mView.subscribe(new AbsAuthEvent() {
//
//            @Override
//            public void call(Context context, boolean needLogin) {
//                /**
//                 * 建议实现逻辑:
//                 *
//                 *     判断App内的用户是否登录?
//                 *       => 已登录: 请求带用户角色的认证信息(login接口);
//                 *       => 未登录: needLogin为true, 唤起App内登录界面, 请求带用户角色的认证信息(login接口);
//                 *       => 未登录: needLogin为false, 请求不带用户角色的认证信息(initToken接口).
//                 *
//                 *      服务端接入文档: https://www.youzanyun.com/docs/guide/appsdk/683
//                 */
//                //TODO 自行编码实现. 具体可参考开发文档中的伪代码实现
//                if (TextUtils.isEmpty(UtilSharedPreference.getStringValue(getActivity(), Config.PHONE))) {
//                    ToastUtils.showMessage("请先登录");
//                    IntentUtils.startActivity(getActivity(), LoginActivity.class);
//                    return;
//                }
//                Log.e("yyyy", App.getInstance().getUserEntityBean().getUserId());
//                HashMap map=new HashMap();
//                map.put("client_id","a2feecb3ceade7d723");
//                map.put("client_secret","82e847932da4d379d296f3b3e3c7b00c");
//                map.put("open_user_id",App.getInstance().getUserEntityBean().getUserId()==null?"":App.getInstance().getUserEntityBean().getUserId());
//                JSONObject jsonObject=new JSONObject();
//
//                VolleyUtils.deStringPost(getActivity(), login,map, "you", new VolleyUtils.volleyListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//                    }
//
//                    @Override
//                    public void onErrorResponse(String errorMessage) {
//
//                    }
//
//                    @Override
//                    public void onResponse(String response) {
//                        Log.e("kkk",response.toString());
//                        try {
//                            JSONObject object=new JSONObject(response.toString());
//                            JSONObject data = object.getJSONObject("data");
//                            String access_token = data.getString("access_token");
//                            String cookie_key = data.getString("cookie_key");
//                            String cookie_value = data.getString("cookie_value");
//                            token=new YouzanToken();
//                            token.setAccessToken(access_token);
//                            token.setCookieKey(cookie_key);
//                            token.setCookieValue(cookie_value);
//                            mView.sync(token);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//
//
//
//
//
//            }
//        });
//
//        //文件选择事件, 回调表示: 发起文件选择. (如果app内使用的是系统默认的文件选择器, 该事件可以直接删除)
//        mView.subscribe(new AbsChooserEvent() {
//            @Override
//            public void call(Context context, Intent intent, int requestCode) throws ActivityNotFoundException {
//                Log.e("kkk","文件");
//                startActivityForResult(intent, requestCode);
//            }
//        });
//
//        //页面状态事件, 回调表示: 页面加载完成
//        mView.subscribe(new AbsStateEvent() {
//            @Override
//            public void call(Context context) {
//                Log.e("kkk","加载");
//
//                //停止刷新
//                refreshLayout.setRefreshing(false);
//                refreshLayout.setEnabled(true);
//                Log.e("kkk", Shop.getShopUrl()==null?"wu":Shop.getShopUrl());
//                Log.e("kkk", Shop.getShopName()==null?"wu":Shop.getShopName());
//            }
//        });
//
//        //分享事件, 回调表示: 获取到当前页面的分享信息数据
//        mView.subscribe(new AbsShareEvent() {
//            @Override
//            public void call(Context context, GoodsShareModel data) {
//                /**
//                 * 在获取数据后, 可以使用其他分享SDK来提高分享体验.
//                 * 这里调用系统分享来简单演示分享的过程.
//                 */
//
//                String content = data.getDesc() + data.getLink();
//                Intent sendIntent = new Intent();
//                sendIntent.setAction(Intent.ACTION_SEND);
//                sendIntent.putExtra(Intent.EXTRA_TEXT, content);
//                sendIntent.putExtra(Intent.EXTRA_SUBJECT, data.getTitle());
//                sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                sendIntent.setType("text/plain");
//                startActivity(sendIntent);
//            }
//        });
//    }
//
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//    }
}
