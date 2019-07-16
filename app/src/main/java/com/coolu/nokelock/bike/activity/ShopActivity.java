//package com.coolu.nokelock.bike.activity;
//
//import android.content.ActivityNotFoundException;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.Handler;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.KeyEvent;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.amap.api.maps.model.Text;
//import com.coolu.nokelock.bike.R;
//import com.coolu.nokelock.bike.base.BaseActivity;
//import com.coolu.nokelock.bike.url.Url;
//import com.coolu.nokelock.bike.util.ConditionsUtils;
//import com.coolu.nokelock.bike.util.Config;
//import com.coolu.nokelock.bike.util.VolleyUtils;
//import com.fitsleep.sunshinelibrary.utils.IntentUtils;
//import com.fitsleep.sunshinelibrary.utils.ToastUtils;
//import com.fitsleep.sunshinelibrary.utils.UtilSharedPreference;
//import com.umeng.analytics.MobclickAgent;
//import com.youzan.androidsdk.YouzanToken;
//import com.youzan.androidsdk.account.Shop;
//import com.youzan.androidsdk.event.AbsAuthEvent;
//import com.youzan.androidsdk.event.AbsChooserEvent;
//import com.youzan.androidsdk.event.AbsShareEvent;
//import com.youzan.androidsdk.event.AbsStateEvent;
//import com.youzan.androidsdk.hybrid.YouzanHybrid;
//import com.youzan.androidsdk.model.goods.GoodsShareModel;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.HashMap;
//
//public class ShopActivity extends  BaseActivity  implements SwipeRefreshLayout.OnRefreshListener{
//
//    private String url="https://h5.youzan.com/v2/feature/2unhwt85";
//  //  private YouzanHybrid mView;
//    private SwipeRefreshLayout  refreshLayout;
//    private YouzanToken token;
//    private Handler handler=new Handler();
//    private ImageView back;
//    private TextView title;
//    private String lookCard;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //设置状态栏文字颜色及图标为深色
//        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        setContentView(R.layout.activity_shop);
//        lookCard = getIntent().getStringExtra("url");
//
//        initView();
//        setupYouzan();
//
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        Log.e("lpl","card"+lookCard);
//        if (lookCard!=null){
//          //  mView.loadUrl(lookCard);
//        }else {
//            initData();
//        }
//
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//
//    }
//
//    private void initData() {
//        HashMap<String, String> map = VolleyUtils.getCommomParameter();
//
//        map.put("name",App.getInstance().getaMapLocation()==null?"":App.getInstance().getaMapLocation().getCity());
//        Log.e("kop",App.getInstance().getaMapLocation()==null?"":App.getInstance().getaMapLocation().getCity());
//        // map.put("name","秦皇岛市");
//        VolleyUtils.deStringPost(ShopActivity.this, Url.SHOPURL, map, "shop", new VolleyUtils.volleyListener() {
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
//                        Log.e("kop","result"+result);
//                        mView.loadUrl(result);
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
//
//
//    private void initView() {
//        back = (ImageView)findViewById(R.id.id_back);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                if (mView.pageCanGoBack()){
////                    mView.pageGoBack();
////                    Log.e("aaa","11111");
////                }else{
////                    Log.e("aaa","2222");
////                    setResult(100);
////                    finish();
////                }
//            }
//        });
//        title = (TextView)findViewById(R.id.id_title_toolbar);
//
//       // mView = (YouzanHybrid)findViewById(R.id.view);
//        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe);
//        //刷新
//        refreshLayout.setOnRefreshListener(this);
//        refreshLayout.setColorSchemeColors(Color.BLUE, Color.RED);
//        refreshLayout.setEnabled(false);
//    }
//    private String login="https://uic.youzan.com/sso/open/login";
//    private void setupYouzan() {
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
//                if (TextUtils.isEmpty(UtilSharedPreference.getStringValue(ShopActivity.this, Config.PHONE))) {
//                    ToastUtils.showMessage("请先登录");
//                    IntentUtils.startActivity(ShopActivity.this, LoginActivity.class);
//
//                    return;
//                }
//
//                HashMap map=new HashMap();
//                map.put("client_id","a2feecb3ceade7d723");
//                map.put("client_secret","82e847932da4d379d296f3b3e3c7b00c");
//                map.put("open_user_id",App.getInstance().getUserEntityBean()==null?"":App.getInstance().getUserEntityBean().getUserId());
//                map.put("kdt_id","16594038");
//
//                VolleyUtils.deStringPost(ShopActivity.this, login,map, "you", new VolleyUtils.volleyListener() {
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
//                      //  Log.e("yyyy",response.toString());
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
//                title.setText(mView.getTitle());
//                //停止刷新
//                refreshLayout.setRefreshing(false);
//                refreshLayout.setEnabled(true);
//
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
//
//    }
//
//    @Override
//    public void onRefresh() {
//        //重新加载页面
//        mView.reload();
//    }
//
//    @Override
//    public void onBackPressed() {
//        //SDK需要控制页面回退
//        if (!mView.pageGoBack()) {
//            setResult(100);
//            super.onBackPressed();
//
//        }
//    }
//}
