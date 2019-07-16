package com.coolu.nokelock.bike.util;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.util.Log;


import com.coolu.nokelock.bike.activity.App;
import com.coolu.nokelock.bike.url.Url;
import com.fitsleep.sunshinelibrary.utils.ToolsUtils;
import com.fitsleep.sunshinelibrary.utils.UtilSharedPreference;

import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.Request;
import okhttp3.internal.http.HttpMethod;

/**
 * Created by admin on 2017/10/24.
 */

public class AppVersionUtil {
    private static AppVersionUtil appVersionUtil;
    public  static  AppVersionUtil getInstance(){
        if (appVersionUtil==null){
            appVersionUtil=new AppVersionUtil();
        }
        return appVersionUtil;
    }
    public void getVersion(final Context context) {
        HashMap<String,String> map=new HashMap<>();
        map.put("token",UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(),Config.TOKEN));
        map.put("phoneSystem","android");
        map.put("appVersion", ToolsUtils.getVersion(context));
        if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
            VolleyUtils.deStringPost(context, Url.VERSION, map, "version", new VolleyUtils.volleyListener() {
                @Override
                public void onResponse(JSONObject response) {

                }

                @Override
                public void onErrorResponse(String errorMessage) {

                }

                @Override
                public void onResponse(String response) {
                    Log.e("kkk", "版本更新" + response.toString());
                }
            });
        }

    }

    /**
     * 下载apk
     *
     * @param url 下载url
     */
    private void downApk(Context context, final String url) {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        //下载的目录和文件
        request.setDestinationInExternalPublicDir("NokeLockCach", url.substring(url.lastIndexOf("/") + 1));
//        //在通知栏中显示
//        request.setVisibleInDownloadsUi(true);
//        //下载是不显示通知栏  需要权限<uses-permission android:name="android.permission.DOWNLOAD_WITHOUT_NOTIFICATION" />
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //开始下载

        long downloadId = downloadManager.enqueue(request);
        UtilSharedPreference.saveLong(context, "downloadId", downloadId);
    }

    private static class SingletonHolder {
        /**
         * 单例对象实例
         */
        static final HttpHelper INSTANCE = new HttpHelper();
    }
}
