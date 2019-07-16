package com.coolu.nokelock.bike.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.base.BaseActivity;
import com.coolu.nokelock.bike.util.BaseUtil;
import com.coolu.nokelock.bike.util.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserActivity extends BaseActivity {
    private WebView webView;

    private static final String APP_CACHE_DIRNAME = "/webcache"; // web缓存目录
    private long exitTime = 0;



//    private ExpandableListView expand;
//    private List<String>groupArray;
//    private List<List<String>> childArray;

    private String currentUrl=null;
    private TextView route_tv;
    private LinearLayout linear;
    private int flag; //区分推荐骑行还是吃喝玩乐
    private ImageView yuan;
    private RelativeLayout relativeLayout;

    TextView qi;
    private ImageView back;
    private ImageView title_img;
    private TextView title_tv;
    private ImageView search;
    private String routeDetailsUrl=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏文字颜色及图标为深色
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_user);
        back = (ImageView)findViewById(R.id.id_back);
        title_img = (ImageView)findViewById(R.id.id_h_title_img);
        title_tv = (TextView)findViewById(R.id.id_h_title_tv);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        qi=(TextView) findViewById(R.id.id_qi);
        qi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserActivity.this,RouteMapActivity.class);
                startActivity(intent);
            }
        });



        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("url");
        String route = bundle.getString("route");
        flag = bundle.getInt("flag");
        routeDetailsUrl = bundle.getString("RouteDetailsUrl");
        if (!TextUtils.isEmpty(route)){
           title_tv.setText(route);
       }

        yuan = (ImageView)findViewById(R.id.id_main_yuan);
        relativeLayout=(RelativeLayout)findViewById(R.id.id_main_relative);
        if (flag==2){
             //   yuan.setImageDrawable(getResources().getDrawable(R.mipmap.enjoy));
        }if (flag==1){
          //  yuan.setImageDrawable(getResources().getDrawable(R.mipmap.route));
        }else if (flag==3){
           // relativeLayout.setVisibility(View.GONE);
        }

        Log.e("mm",url.charAt(0)+"111"+url);
        if ((url.charAt(0)+"").equals("h")){ //用户指南H5
            currentUrl=url;
        }else if(url.length()==4&&!(url.charAt(0)+"").equals("1")){ //吃喝玩乐h5
            currentUrl=Config.getPlayItemUrl(url);
           // qi.setVisibility(View.VISIBLE);
        }else {
            currentUrl= Config.getRouteUrl(url); //推荐骑行
          //  qi.setVisibility(View.VISIBLE);
        }



        linear = (LinearLayout)findViewById(R.id.id_route_linear);
        webView = (WebView)findViewById(R.id.id_webview);
        initWebView();

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);// 关键点
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setDisplayZoomControls(false);
        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
        webSettings.setSupportZoom(true); // 支持缩放
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDomStorageEnabled(true);
        loadUrl(currentUrl);



    }



    public void loadUrl(String url){
        Log.e("jj","url=="+url);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);


            }
        });
    }

    private void initWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        // 建议缓存策略为，判断是否有网络，有的话，使用LOAD_DEFAULT,无网络时，使用LOAD_CACHE_ELSE_NETWORK
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); // 设置缓存模式
        // 开启DOM storage API 功能
        webView.getSettings().setDomStorageEnabled(true);
        // 开启database storage API功能
        webView.getSettings().setDatabaseEnabled(true);
        String cacheDirPath =getFilesDir().getAbsolutePath()  + APP_CACHE_DIRNAME;


        // 设置数据库缓存路径
        webView.getSettings().setDatabasePath(cacheDirPath); // API 19 deprecated
        // 设置Application caches缓存目录
        webView.getSettings().setAppCachePath(cacheDirPath);
        // 开启Application Cache功能
        webView.getSettings().setAppCacheEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        linear.removeView(webView);
        webView.destroy();
    }


}
