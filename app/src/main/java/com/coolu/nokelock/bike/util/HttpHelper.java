package com.coolu.nokelock.bike.util;


import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.coolu.nokelock.bike.activity.App;
import com.coolu.nokelock.bike.url.Url;
import com.fitsleep.sunshinelibrary.utils.EncryptUtils;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.fitsleep.sunshinelibrary.utils.UtilSharedPreference;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Request;
import okhttp3.internal.http.HttpMethod;

/**
 * Created by admin on 2017/8/20.
 */

public class HttpHelper {
    private static HttpHelper httpHelper;
    public  static  HttpHelper getInstance(){
        if (httpHelper==null){
            httpHelper=new HttpHelper();
        }
        return  httpHelper;
    }

    public JSONObject getJSONObject(String message) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("message", message);
            jsonObject.put("language", "android");
            jsonObject.put("userid", UtilSharedPreference.getStringValue(ConditionsUtils.getContext(), Config.PHONE));
            jsonObject.put("appcode", UtilSharedPreference.getStringValue(ConditionsUtils.getContext(), Config.TOKEN));
           // jsonObject.put("appcode", UtilSharedPreference.getStringValue(ConditionsUtils.getContext(), Config.TOKEN));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }



    /**
     * 校验服务器返回的json
     *
     * @param json 服务器返回json
     * @return 是否合格
     */
    public boolean isJSON(String json, String type) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            String status = jsonObject.getString("status");
            if (Config.SUCCESS.equals(status)) {
                return true;
            } else {
//                String result = jsonObject.getString("result");Log.e("mm",e.toString());
//                CodeUtils.toastError(result);
//                if ("3001".equals(result)) {
//                    App.getInstance().getIBLE().disconnect();
//                    UtilSharedPreference.saveString(ConditionsUtils.getContext(), Config.PHONE, "");
//                    UtilSharedPreference.saveString(ConditionsUtils.getContext(), Config.TOKEN, "");
//                    UtilSharedPreference.saveString(ConditionsUtils.getContext(), HttpMethod.GET_START_PIC.getValue(), "");
//                    UtilSharedPreference.saveString(ConditionsUtils.getContext(), "head", "");
//                    UtilSharedPreference.saveString(ConditionsUtils.getContext(), "url", "");
//                    App.getInstance().getDaoSession().getUseBeanDao().deleteAll();
//                    App.getInstance().getDaoSession().getSearchBeanDao().deleteAll();
//                    App.getInstance().getDaoSession().getBikeOrderBeanDao().deleteAll();
//                    App.getInstance().getDaoSession().getOrderBeanDao().deleteAll();
//                    App.getInstance().setUserInfo(null);
//                    ToastUtils.showMessage("登陆失效，请重新登陆");
//                    return false;
//                }
               return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }


    //版本控制接口
    public void getVersion(final Context context) {
        HashMap<String, String> map = VolleyUtils.getCommomParameter();
        if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
            VolleyUtils.deStringPost(App.getInstance().getApplicationContext(), Url.VERSION, map, "version", new VolleyUtils.volleyListener() {
                @Override
                public void onResponse(JSONObject response) {

                }

                @Override
                public void onErrorResponse(String errorMessage) {

                }

                @Override
                public void onResponse(String response) {
//                    Log.e("TAG", "版本更新返回的数据是："+response.toString());

                    try {
                        JSONObject jsonObject = new JSONObject(response.toString());
                        String errorCode = jsonObject.getString("errorCode");
                        String messageCode = jsonObject.getString("message");

                        Log.e("klk", "message" + errorCode);
                        if ("200".equals(errorCode) && !TextUtils.equals("查询成功不需要更新", messageCode)) { //可能要改，不合理
                            // openBrowserUpdate("http://a.app.qq.com/o/simple.jsp?pkgname=com.coolu.nokelock.bike");
                            DialogShowUtils.showTipsDialog(context);
                        }

                    } catch (JSONException e) {

                        e.printStackTrace();
                    }


                    //  resolveJson(response, action);
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

    /**
     * 打开浏览器更新下载新版本apk
     * @param apkUrl    apk托管地址
     */
    private void openBrowserUpdate(String apkUrl) {

        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri apk_url = Uri.parse(apkUrl);
        intent.setData(apk_url);
       App.getInstance().getApplicationContext().startActivity(intent);//打开浏览器

    }

    private static class SingletonHolder {
        /**
         * 单例对象实例
         */
        static final HttpHelper INSTANCE = new HttpHelper();
    }
}
