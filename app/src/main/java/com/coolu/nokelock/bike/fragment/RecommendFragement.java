package com.coolu.nokelock.bike.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.activity.App;
import com.coolu.nokelock.bike.activity.RouteMapActivity;
import com.coolu.nokelock.bike.base.BaseFragment;
import com.coolu.nokelock.bike.util.Config;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by admin on 2017/5/11.
 * 骑行路线
 */

public class RecommendFragement extends BaseFragment implements View.OnClickListener {


    private WebView webView;
    private static final String APP_CACHE_DIRNAME = "/webcache"; // web缓存目录
    private long exitTime = 0;
    private TextView route;
    private Timer timer;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 101:
                    if (webView!=null&&webView.canGoBack()){
                        if (route.getVisibility()==View.GONE||route.getVisibility()==View.INVISIBLE)
                        route.setVisibility(View.VISIBLE);
                    }else {
                        route.setVisibility(View.GONE);
                    }
                    break;
            }
        }
    };


    public RecommendFragement(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.recommend, null);
        webView = (WebView)view.findViewById(R.id.id_webview);
        route = (TextView)view.findViewById(R.id.id_route);
        route.setOnClickListener(this);
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
        loadUrl(Config.getQxBaseUrl(String.valueOf(App.getInstance().getaMapLocation().getLongitude()),String.valueOf(App.getInstance().getaMapLocation().getLatitude())));

        return view;
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
       String cacheDirPath =getActivity().getFilesDir().getAbsolutePath()  + APP_CACHE_DIRNAME;


        // 设置数据库缓存路径
        webView.getSettings().setDatabasePath(cacheDirPath); // API 19 deprecated
        // 设置Application caches缓存目录
        webView.getSettings().setAppCachePath(cacheDirPath);
        // 开启Application Cache功能
        webView.getSettings().setAppCacheEnabled(true);

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("aaa","re+onresume");
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
               handler.sendEmptyMessage(101);
            }
        },0,2000);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("aaa","re+onActivityCreated");

    }

    public void onKeyDown(int keyCode, KeyEvent event) {

      //  if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
           // webView.goBack(); //goBack()表示返回WebView的上一页面
      //  }

        if (keyCode==KeyEvent.KEYCODE_BACK){
            Log.e("jj",webView.canGoBack()+"");
            if (webView.canGoBack()){
                webView.goBack();
                if (route!=null)
                route.setVisibility(View.GONE);
            }else {
                if ( event.getAction() == KeyEvent.ACTION_DOWN) {
                    if ((System.currentTimeMillis() - exitTime) > 2000){//System.currentTimeMillis()无论何时调用，肯定大于2000  
                        Toast.makeText(getActivity(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                        exitTime = System.currentTimeMillis();
                    } else {
                        getActivity().finish();
                        System.exit(0);
                    }


                }
            }
        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_route:
                Intent routeMap=new Intent(getActivity(), RouteMapActivity.class);
                startActivity(routeMap);
                break;
        }
    }
}
