package com.coolu.nokelock.bike.prensenter;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;


import com.coolu.nokelock.bike.activity.App;
import com.coolu.nokelock.bike.bean.BaseBean;
import com.coolu.nokelock.bike.bean.BikeClockBean;
import com.coolu.nokelock.bike.bean.BikeOpenClockBean;
import com.coolu.nokelock.bike.bean.BikeOrderBean;
import com.coolu.nokelock.bike.bean.MacBeann;
import com.coolu.nokelock.bike.bean.OpenCloseBean;
import com.coolu.nokelock.bike.bean.UseBean;
import com.coolu.nokelock.bike.bean.UserEntity;
import com.coolu.nokelock.bike.bean.UserEntityBean;
import com.coolu.nokelock.bike.bean.UserInfo;
import com.coolu.nokelock.bike.url.Url;
import com.coolu.nokelock.bike.util.ConditionsUtils;
import com.coolu.nokelock.bike.util.Config;
import com.coolu.nokelock.bike.util.HttpHelper;
import com.coolu.nokelock.bike.util.OkHttpClientManager;
import com.coolu.nokelock.bike.util.VolleyUtils;
import com.coolu.nokelock.bike.view.impl.MainViewImpl;
import com.fitsleep.sunshinelibrary.utils.ConvertUtils;
import com.fitsleep.sunshinelibrary.utils.DeviceUtils;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.fitsleep.sunshinelibrary.utils.ToolsUtils;
import com.fitsleep.sunshinelibrary.utils.UtilSharedPreference;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;

import static com.coolu.nokelock.bike.util.Config.GET_FORCED_MONEY;
import static com.coolu.nokelock.bike.util.Config.GET_INFO;


/**
 * 作者: Sunshine
 * 时间: 2017/4/20.
 * 邮箱: 44493547@qq.com
 * 描述:
 */

public class MainPresenter extends BasePresenter<MainViewImpl> {

    private MainViewImpl implMain;
    /**
     * 开锁成功
     */
    public static final int OPEN_OK = 1;
    /**
     * 关锁成功
     */
    public static final int CLOSE_OK = 0;
    /**
     * 开锁失败
     */
    public static final int OPEN_FAIL = 2;

    /**
     * 强制还车
     */
    public static final int CLOSE_FORCE = 3;
    private int upBleStatus = OPEN_OK;
    private BikeOpenClockBean bikeOpenClockBean;
    private BikeOrderBean bikeOrderBean;
    private BikeClockBean bikeClockBean;
    private String time=null;


    public MainPresenter(MainViewImpl implMain) {
        this.implMain = implMain;
    }


    /**
     * 个人信息
     */
    public void getInfo() {
        try {
            JSONObject jsonObject = HttpHelper.getInstance().getJSONObject(GET_INFO);
            jsonObject.put("lat","116.19195772652797" );
            jsonObject.put("lng","39.912884534392155");
            postHttp(GET_INFO, jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 个人信息（自己的接口）
     */
    public void getInfo2(){
      // HashMap map=new HashMap();
       HashMap<String, String> map = VolleyUtils.getCommomParameter();
        map.put("token",UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(),Config.TOKEN));
       // map.put("lat",App.getInstance().getaMapLocation().getLatitude()+"");
       // map.put("lng",App.getInstance().getaMapLocation().getLongitude()+"");
        if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
            postHttp(Config.PERSON, Url.PEOPLE, map, "getPerson");
        }
    }


    /**
     * 上传错误类型
     * @param data 描述信息
     * @param code 错误编号
     */
    public void upErrorMessage(String data,String code){
        try {
            List<BikeOrderBean> orderListBeen = App.getInstance().getDaoSession().getBikeOrderBeanDao().loadAll();
            if (orderListBeen.size() > 0) {
                JSONObject jsonObject = HttpHelper.getInstance().getJSONObject(Config.UP_ERROR_MEG);
                jsonObject.put("barcode", orderListBeen.get(0).getBarcode());
                jsonObject.put("ordernum", orderListBeen.get(0).getOrderNumber());
                jsonObject.put("shebeiid",orderListBeen.get(0).getShebeiid());
                jsonObject.put("apptype","android,"+ DeviceUtils.getManufacturer()+","+DeviceUtils.getModel()+","+ToolsUtils.getVersion(ConditionsUtils.getContext()));
                jsonObject.put("data",data);
                jsonObject.put("code",code);
                postHttp(Config.UP_ERROR_MEG, jsonObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取mac对应的车辆信息
     * lat39.912884534392155
     * lon116.19195772652797
     *
     * @param code 二维码信息
     *             http://app.coolubike.com/app.html?id=335122849
     */
    public void getMac(String code) {
        Log.e("kok","code"+code);
     //   try {
//            JSONObject jsonObject = HttpHelper.getInstance().getJSONObject(Config.GETMAC);
//        try {
//            jsonObject.put("barcode",code);
//            jsonObject.put("lat","116.19195772652797");
//            jsonObject.put("lng","39.912884534392155");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


        HashMap<String, String> map = VolleyUtils.getCommomParameter();
            map.put("token", UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(),Config.TOKEN));
            map.put("barCode",code);
            map.put("lat",App.getInstance().getaMapLocation()==null?"0":App.getInstance().getaMapLocation().getLatitude()+"");
            map.put("lng",App.getInstance().getaMapLocation()==null?"0":App.getInstance().getaMapLocation().getLongitude()+"");
            map.put("sheBeiBianHao",code.substring(code.lastIndexOf("=")+1));
           //  map.put("sheBeiBianHao",code);
        if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
            postHttp(Config.GETMAC, Url.OPENCOLK, map, "getMac");
        }
      //  postHttp(Config.GETMAC,jsonObject);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }
    /**
     * 新锁的mac
     */

    public void getNewMac(String code) {
        Log.e("mm", "code" + code);
        //   try {
//            JSONObject jsonObject = HttpHelper.getInstance().getJSONObject(Config.GETMAC);
//        try {
//            jsonObject.put("barcode",code);
//            jsonObject.put("lat","116.19195772652797");
//            jsonObject.put("lng","39.912884534392155");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


        HashMap<String, String> map = VolleyUtils.getCommomParameter();
//            map.put("token", UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(),Config.TOKEN));
//            map.put("barCode",code);
//            map.put("lat",App.getInstance().getaMapLocation().getLatitude()+"");
//            map.put("lng",App.getInstance().getaMapLocation().getLongitude()+"");
//            map.put("sheBeiBianHao",code.substring(code.lastIndexOf("=")+1));
        map.put("token", UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(), Config.TOKEN));
        map.put("barCode", code);
        map.put("lat", App.getInstance().getaMapLocation() == null ? "0" : App.getInstance().getaMapLocation().getLatitude() + "");
        map.put("lng", App.getInstance().getaMapLocation() == null ? "0" : App.getInstance().getaMapLocation().getLongitude() + "");
        //map.put("sheBeiBianHao",code.substring(code.lastIndexOf("=")+1));
        map.put("sheBeiBianHao", code);


//            OkHttpClientManager.Param[]params=new OkHttpClientManager.Param[]{
//                    new OkHttpClientManager.Param("sheBeiBianHao",code.substring(code.lastIndexOf("=")+1)),
//                    new OkHttpClientManager.Param("barCode",code),
//                    new OkHttpClientManager.Param("lat",App.getInstance().getaMapLocation().getLatitude()+""),
//                new OkHttpClientManager.Param("lng",App.getInstance().getaMapLocation().getLongitude()+"")
//            };

        if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
            postHttp(Config.GETNEWMAC, Url.opp, map, "getMac");
        }

    }

   private void postHttp(final  String action,String url, HashMap<String,String> map,String flag){


    VolleyUtils.deStringPost(App.getInstance().getApplicationContext(),url, map, flag, new VolleyUtils.volleyListener() {
        @Override
        public void onResponse(JSONObject response) {

        }

        @Override
        public void onErrorResponse(String errorMessage) {

        }

        @Override
        public void onResponse(String response) {
            Log.e("kok",response.toString());
            try {
                JSONObject jsonObject = new JSONObject(response.toString());
                String errorCode = jsonObject.getString("errorCode");
                String messageCode=jsonObject.getString("message");

                if ("200".equals(errorCode)){
                    resolveJson(response, action);
                }else if ("301".equals(errorCode)){
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
                }else {
                    implMain.errorMessage(action, Config.NO_JSON);
                    if (TextUtils.equals(action,Config.GETMAC)){//查询订单信息有误，开锁的对话框消失
                        implMain.CancleDiolag();
                        ToastUtils.showMessage(messageCode);
                    }
                }
            } catch (JSONException e) {
                implMain.errorMessage(action, Config.HTTP_NO_CONNECT);
                e.printStackTrace();
            }


          //  resolveJson(response, action);
        }
    });


   }

    private void postHttp(final String action, JSONObject json) {
        //原来地址 Config.BASE_URL


        OkHttpClientManager.postJson(Config.BASE_URL, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("mm",e.toString());

                implMain.errorMessage(action, Config.HTTP_NO_CONNECT);
            }

            @Override
            public void onResponse(String response) {
                if (HttpHelper.getInstance().isJSON(response, action)) {
                    resolveJson(response, action);
                } else {
                   implMain.errorMessage(action, Config.NO_JSON);
                    Log.e("mmon",response.toString());
                }
            }
        }, json);

//        //得到String类型
//        OkHttpClientManager.postAsyn(Config.BASE_URL, new OkHttpClientManager.StringCallback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(String response) {
//
//            }
//        },new OkHttpClientManager.Param[]{
//                new OkHttpClientManager.Param("token", "1888888888" + 20150515)
//                }
//
//        );
    }


    /**
     * 上传蓝牙关锁状态
     */
    public void upBleCloseLock(String mac) {
        upBleStatus = CLOSE_OK;
       // try {
            List<BikeOrderBean> orderListBeen = App.getInstance().getDaoSession().getBikeOrderBeanDao().loadAll();
            if (orderListBeen.size() > 0) {
//                JSONObject jsonObject = HttpHelper.getInstance().getJSONObject(Config.UP_BLE_STATE);
//                jsonObject.put("barcode", orderListBeen.get(0).getBarcode());
//                jsonObject.put("ordernum", orderListBeen.get(0).getOrderNumber());
//                jsonObject.put("lat","116.19195772652797");
//                jsonObject.put("lng","39.912884534392155");
//                jsonObject.put("lockstate", "close");
//                jsonObject.put("power", App.getInstance().getBattery());
//                jsonObject.put("result", "ok");
//                jsonObject.put("locktype",orderListBeen.get(0).getLockType());
//                jsonObject.put("mac", mac);
//                jsonObject.put("timestamp", System.currentTimeMillis() / 1000);
//                jsonObject.put("shebeiid",orderListBeen.get(0).getShebeiid());

                //postHttp(Config.UP_BLE_STATE, jsonObject);

                HashMap<String, String> map = VolleyUtils.getCommomParameter();
                map.put("token", UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(),Config.TOKEN));
                map.put("barCode",orderListBeen.get(0).getBarcode());
                map.put("sheBeiId",orderListBeen.get(0).getShebeiid());
                map.put("ordernum",orderListBeen.get(0).getOrderNumber());
                map.put("lat",App.getInstance().getaMapLocation()==null?"0":App.getInstance().getaMapLocation().getLatitude()+"");
                map.put("lng",App.getInstance().getaMapLocation()==null?"0":App.getInstance().getaMapLocation().getLongitude()+"");
                map.put("power",App.getInstance().getBattery()+"");
                map.put("lockstate","close");
                map.put("mac",orderListBeen.get(0).getMac());
                map.put("lockresult","ok");
                map.put("system","Android");
                if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
                    postHttp(Config.UP_BLE_STATE, Url.ORDER, map, "upBleCloseLock");
                }
            }
      //  } catch (JSONException e) {
          //  e.printStackTrace();
      //  }
    }

    /**
     * 上传蓝牙开锁成功  lat 39
     */
    public void upBleOpenLock() {
        upBleStatus = OPEN_OK;
     //   try {
            List<BikeOrderBean> orderListBeen = App.getInstance().getDaoSession().getBikeOrderBeanDao().loadAll();
            if (orderListBeen.size() > 0) {
//                JSONObject jsonObject = HttpHelper.getInstance().getJSONObject(Config.UP_BLE_STATE);
//                try {
//                    jsonObject.put("barcode", orderListBeen.get(0).getBarcode());
//                    jsonObject.put("ordernum", orderListBeen.get(0).getOrderNumber());
//                    jsonObject.put("lat","116.19195772652797");
//                    jsonObject.put("lng","39.912884534392155");
//                    jsonObject.put("lockstate", "open");
//                    jsonObject.put("power", App.getInstance().getBattery());
//                    jsonObject.put("result", "ok");
//                    jsonObject.put("locktype",orderListBeen.get(0).getLockType());
//                    jsonObject.put("mac", orderListBeen.get(0).getMac());
//                    jsonObject.put("timestamp", System.currentTimeMillis() / 1000);
//                    jsonObject.put("shebeiid",orderListBeen.get(0).getShebeiid());
//                    postHttp(Config.UP_BLE_STATE,jsonObject);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }

                /*
                String barCode = request.getParameter("barCode");

		String sheBeiId = request.getParameter("sheBeiId");

		String lng = request.getParameter("lng");

		String lat = request.getParameter("lat");

		String power = request.getParameter("power");

		String lockstate = request.getParameter("lockstate");

		String lockresult = request.getParameter("lockresult");

		String mac = request.getParameter("mac");

		String ordernum = request.getParameter("ordernum");
                 */
               // HashMap<String, String> map = VolleyUtils.getCommomParameter();
                HashMap map=VolleyUtils.getCommomParameter();

                map.put("token", UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(),Config.TOKEN));
                map.put("barCode",orderListBeen.get(0).getBarcode());
                map.put("sheBeiId",orderListBeen.get(0).getShebeiid());
                map.put("ordernum",orderListBeen.get(0).getOrderNumber());
                map.put("lat",App.getInstance().getaMapLocation()==null?"0":App.getInstance().getaMapLocation().getLatitude()+"");
                map.put("lng",App.getInstance().getaMapLocation()==null?"0":App.getInstance().getaMapLocation().getLongitude()+"");
                map.put("power",App.getInstance().getBattery()+"");
                map.put("lockstate","open");
                map.put("mac",orderListBeen.get(0).getMac());
                map.put("lockresult","ok");
                map.put("system","Android");
                if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
                    postHttp(Config.UP_BLE_STATE, Url.ORDER, map, "upBleOpenLock");
                }
            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 上传蓝牙开锁失败
     */
    public void upBleCancelLock() {
        upBleStatus = OPEN_FAIL;
//        try {
//            List<BikeOrderBean> orderListBeen = App.getInstance().getDaoSession().getBikeOrderBeanDao().loadAll();
//            if (orderListBeen.size() > 0) {
//                JSONObject jsonObject = HttpHelper.getInstance().getJSONObject(Config.UP_BLE_STATE);
//                jsonObject.put("barcode", orderListBeen.get(0).getBarcode());
//                jsonObject.put("ordernum", orderListBeen.get(0).getOrderNumber());
//                jsonObject.put("lat",App.getInstance().getaMapLocation().getLatitude()+"");
//                jsonObject.put("lng",App.getInstance().getaMapLocation().getLongitude()+"");
//                jsonObject.put("lockstate", "open");
//                jsonObject.put("power", App.getInstance().getBattery());
//                jsonObject.put("result", "fail");
//                jsonObject.put("locktype",orderListBeen.get(0).getLockType());
//                jsonObject.put("mac", orderListBeen.get(0).getMac());
//                jsonObject.put("timestamp", System.currentTimeMillis() / 1000);
//                jsonObject.put("shebeiid",orderListBeen.get(0).getShebeiid());
//                postHttp(Config.UP_BLE_STATE, jsonObject);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        //   try {
        List<BikeOrderBean> orderListBeen = App.getInstance().getDaoSession().getBikeOrderBeanDao().loadAll();
        if (orderListBeen.size() > 0) {
//                JSONObject jsonObject = HttpHelper.getInstance().getJSONObject(Config.UP_BLE_STATE);
//                try {
//                    jsonObject.put("barcode", orderListBeen.get(0).getBarcode());
//                    jsonObject.put("ordernum", orderListBeen.get(0).getOrderNumber());
//                    jsonObject.put("lat","116.19195772652797");
//                    jsonObject.put("lng","39.912884534392155");
//                    jsonObject.put("lockstate", "open");
//                    jsonObject.put("power", App.getInstance().getBattery());
//                    jsonObject.put("result", "ok");
//                    jsonObject.put("locktype",orderListBeen.get(0).getLockType());
//                    jsonObject.put("mac", orderListBeen.get(0).getMac());
//                    jsonObject.put("timestamp", System.currentTimeMillis() / 1000);
//                    jsonObject.put("shebeiid",orderListBeen.get(0).getShebeiid());
//                    postHttp(Config.UP_BLE_STATE,jsonObject);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }

                /*
                String barCode = request.getParameter("barCode");

		String sheBeiId = request.getParameter("sheBeiId");

		String lng = request.getParameter("lng");

		String lat = request.getParameter("lat");

		String power = request.getParameter("power");

		String lockstate = request.getParameter("lockstate");

		String lockresult = request.getParameter("lockresult");

		String mac = request.getParameter("mac");

		String ordernum = request.getParameter("ordernum");
                 */
            HashMap<String, String> map = VolleyUtils.getCommomParameter();
            map.put("token", UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(),Config.TOKEN));
            map.put("barCode",orderListBeen.get(0).getBarcode());
            map.put("sheBeiId",orderListBeen.get(0).getShebeiid());
            map.put("ordernum",orderListBeen.get(0).getOrderNumber());
            map.put("lat",App.getInstance().getaMapLocation()==null?"0":App.getInstance().getaMapLocation().getLatitude()+"");
            map.put("lng",App.getInstance().getaMapLocation()==null?"0":App.getInstance().getaMapLocation().getLongitude()+"");
            map.put("power",App.getInstance().getBattery()+"");
            map.put("lockstate","open");
            map.put("mac",orderListBeen.get(0).getMac());
            map.put("lockresult","fail");
            map.put("system","Android");
            if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
                postHttp(Config.UP_BLE_STATE, Url.ORDER, map, "upBleCancleLock");
            }
        }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

    }

    /**
     * 获取强制停车
     * @param code 二维码信息
     */
    public void getForcedMoney(String code){
        try {
            JSONObject jsonObject = HttpHelper.getInstance().getJSONObject(GET_FORCED_MONEY);
            jsonObject.put("barcode", code);
            postHttp(GET_FORCED_MONEY,jsonObject);
        }catch (JSONException e){
            e.printStackTrace();
        }

    }



    /**
     * 处理json数据
     * @param response 返回数据
     * @param action 标识
     */
    private void resolveJson(String response, String action) {
        Gson gson = new Gson();
        switch (action) {

            case Config.GET_INFO://获取个人信息
               // handlerInfo(response, gson);

                break;
            case Config.GETMAC://获取二维码信息

                MacBeann macBean = gson.fromJson(response, MacBeann.class);

                App.getInstance().getDaoSession().getBikeOrderBeanDao().deleteAll();

                implMain.getMac(macBean);
                break;
            case Config.UP_ERROR_MEG:
                break;
            case Config.UP_BLE_STATE: //锁的状态
                if (upBleStatus == OPEN_FAIL) {
                    implMain.upBleCancelLock(response);
                } else if ((upBleStatus == CLOSE_OK) ||(upBleStatus == CLOSE_FORCE)) {
                    handlerCloseLock2(response, gson); //关锁
                } else {
                    Log.e("kok","开锁");
                    Log.e("kok",response.toString());
                    implMain.upBleOpenLock(response);//开锁
                }
                break;
            case GET_FORCED_MONEY://强制还车金额
                BaseBean baseBean = gson.fromJson(response, BaseBean.class);
                Log.e("kkkk",baseBean.getResult()+"");
                App.getInstance().setForceMoney(baseBean.getResult());
                break;
            case Config.PERSON: //个人信息表
                Log.e("kkkk","人员"+response.toString());
                handlerInfo2(response,gson);
                break;
            case Config.GETNEWMAC:
                //新锁的mac
            //    ToastUtils.showMessage("新锁"+response);
                implMain.getNewMac(response);
                break;

        }
    }



    /**
     * 上传蓝牙强制还车
     */
    public void upBleForceCar(String mac) {
        upBleStatus = CLOSE_FORCE;
      //  try {
            List<BikeOrderBean> orderListBeen = App.getInstance().getDaoSession().getBikeOrderBeanDao().loadAll();
            if (orderListBeen.size() > 0) {
//                JSONObject jsonObject = HttpHelper.getInstance().getJSONObject(Config.UP_BLE_STATE);
//                jsonObject.put("barcode", orderListBeen.get(0).getBarcode());
//                jsonObject.put("ordernum", orderListBeen.get(0).getOrderNumber());
//                jsonObject.put("lat","116.19195772652797");
//                jsonObject.put("lng","39.912884534392155");
//                jsonObject.put("lockstate", "close");
//                jsonObject.put("power", App.getInstance().getBattery());
//                jsonObject.put("result", "force");
//                jsonObject.put("mac",mac);
//                jsonObject.put("locktype",orderListBeen.get(0).getLockType());
//                jsonObject.put("timestamp", System.currentTimeMillis() / 1000);
//                jsonObject.put("shebeiid",orderListBeen.get(0).getShebeiid());
//                postHttp(Config.UP_BLE_STATE, jsonObject);

                HashMap<String, String> map = VolleyUtils.getCommomParameter();
                map.put("token",UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(),Config.TOKEN));
                map.put("barCode",orderListBeen.get(0).getBarcode());
                map.put("sheBeiId",orderListBeen.get(0).getShebeiid());
                map.put("ordernum",orderListBeen.get(0).getOrderNumber());
                map.put("lat",App.getInstance().getaMapLocation()==null?"0":App.getInstance().getaMapLocation().getLatitude()+"");
                map.put("lng",App.getInstance().getaMapLocation()==null?"0":App.getInstance().getaMapLocation().getLongitude()+"");
                map.put("power",App.getInstance().getBattery()+"");
                map.put("lockstate","close");
                map.put("mac",orderListBeen.get(0).getMac());
                map.put("lockresult","force");
                map.put("system","Android");
                if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
                    postHttp(Config.UP_BLE_STATE, Url.ORDER, map, "upBleCloseLock");
                }

            }
       // } catch (JSONException e) {
          //  e.printStackTrace();
      //  }
    }


    /**
     * 处理关锁成功
     *
     * @param response 数据包
     * @param gson     gson
     */
    private void handlerCloseLock(String response, Gson gson) {
        upErrorMessage("订单结束成功", "1009");
        if (Config.LOCK_TYPE.equals(Config.BLE2)) {
            App.getInstance().getIBLE().stopRead();
        }
        Log.e("kok","结算"+response.toString());
        App.getInstance().getDaoSession().getBikeOrderBeanDao().deleteAll();
       // OrderMoneyBean orderMoneyBean = gson.fromJson(response, OrderMoneyBean.class);
        OpenCloseBean openCloseBean = gson.fromJson(response, OpenCloseBean.class);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("骑行总时长:");
        stringBuffer.append(openCloseBean.getResult().getRiding().getUseMinute());
        stringBuffer.append("分钟\n");
        stringBuffer.append("行程花费:");
        stringBuffer.append(openCloseBean.getResult().getRiding().getUserMoney());
        stringBuffer.append("元\n");
      //  implMain.upBleCloseLock(stringBuffer.toString());
    }

    /**
     * 处理关锁成功
     *
     * @param response 数据包
     * @param gson     gson
     */
    private void handlerCloseLock2(String response, Gson gson) {
        upErrorMessage("订单结束成功", "1009");
        if (Config.LOCK_TYPE.equals(Config.BLE2)) {
            App.getInstance().getIBLE().stopRead();
        }
        Log.e("kok","结算"+response.toString());
        App.getInstance().getDaoSession().getBikeOrderBeanDao().deleteAll();
        // OrderMoneyBean orderMoneyBean = gson.fromJson(response, OrderMoneyBean.class);
        //解析下开锁返回的字符串
        bikeClockBean = VolleyUtils.parseJsonWithGson(response.toString(), BikeClockBean.class);

//        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.append("骑行时长:");
//        stringBuffer.append(bikeClockBean.getResult().getRiding().getUseMinute());
//        stringBuffer.append("分钟\n");
//        stringBuffer.append("行程花费:");
//        stringBuffer.append(bikeClockBean.getResult().getRiding().getMoney());
//        stringBuffer.append("元\n");

        String money="骑行花费：<font color='#EE5676'>"+bikeClockBean.getResult().getRiding().getBeiYong2()+"</font> 元";
        List<BikeClockBean.ResultBean.YouhuiBean> youhui = bikeClockBean.getResult().getYouhui();
        if(youhui!=null&&youhui.size()>0){
//            time ="总时长"+bikeClockBean.getResult().getRiding().getUseMinute()+ "分钟,花费 <font color='#FFD733'>"+bikeClockBean.getResult().getRiding().getMoney()+"</font> 元 ";
            time ="您本次骑行,总时长"+"<font color='#FFD733'>"+bikeClockBean.getResult().getRiding().getUseMinute()+"</font>"+"分钟,!花费"+"<font color='#FFD733'><big>"+bikeClockBean.getResult().getRiding().getMoney()+"</big></font>"+"元 ";
            UtilSharedPreference.saveString(App.getInstance().getApplicationContext(), Config.YOUZANCARDURL,youhui.get(0).getDetail_url());
        }else{
            time ="您本次骑行,总时长"+"<font color='#FFD733'>"+bikeClockBean.getResult().getRiding().getUseMinute()+"</font>"+"分钟 ";
        }
          String forceMoney="调度费用：<font color='#EE5676'>"+bikeClockBean.getResult().getRiding().getBeiYong1()+"</font> 元";
        implMain.upBleCloseLock(time,money,youhui,forceMoney);
    }

    //人员信息表
    private void  handlerInfo2(String response,Gson gson){
        UserEntity entity=gson.fromJson(response, UserEntity.class);


        UserEntityBean user=new UserEntityBean();
        user.setUserId(entity.getResult().getUser().getUserId()+"");
        user.setNicName(entity.getResult().getUser().getNicName());

        user.setIdcheck(entity.getResult().getUser().getIdcheck()+"");
        user.setPinNo(entity.getResult().getUser().getPinNo());
        user.setPinTime(entity.getResult().getUser().getPinTime());
        user.setUserToken(entity.getResult().getUser().getUserToken());
        user.setLoginTime(entity.getResult().getUser().getLoginTime());
        user.setDeposit(entity.getResult().getUser().getDeposit()+"");
        App.getInstance().setDeposit(entity.getResult().getUser().getDeposit());  //保证金
        user.setDefaultDeposit(entity.getResult().getUser().getDefaultDeposit()+"");
        user.setOrderNo(entity.getResult().getUser().getOrderNo());
        user.setUserMoney(entity.getResult().getUser().getUserMoney());
        user.setUserBonus(entity.getResult().getUser().getUserBonus());
        user.setUserType(entity.getResult().getUser().getUserType()+"");
        user.setUserStatus(entity.getResult().getUser().getUserStatus());
        user.setUserCredit(entity.getResult().getUser().getUserCredit());
        user.setIdealMoney(entity.getResult().getUser().getIdealMoney());
        user.setUserLevel(entity.getResult().getUser().getUserLevel());
        user.setUserFrom(entity.getResult().getUser().getUserFrom());
        user.setShebieId(entity.getResult().getShebeiid());
        user.setRidingprice(entity.getResult().getRidingprice());
        user.setLockid(entity.getResult().getLockid());
        user.setUserPic(entity.getResult().getUser().getUserPic());
        user.setOpenmoney(entity.getResult().getOpenmoney());
        user.setNewopenmoney(entity.getResult().getNewopenmoney());
        user.setUserLevelEndTime(entity.getResult().getUser().getUserLevelEndTime());
        user.setBarCode(entity.getResult().getBarcode());
        user.setLockmac(entity.getResult().getLockmac());
        user.setLockdata(entity.getResult().getLockdata());
        user.setStarttime(entity.getResult().getStarttime());
        user.setLocktype(entity.getResult().getLocktype());
        user.setUserName(entity.getResult().getUser().getUserName());
        user.setIdno(entity.getResult().getUser().getIdno());
        user.setForcemoney(entity.getResult().getForcemoney()); //调度费20
        App.getInstance().setForceMoney(entity.getResult().getForcemoney());
        user.setUserCredit(entity.getResult().getUser().getUserCredit());
        user.setCardprice(entity.getResult().getCardprice()); //优惠券你的金额
        user.setUserBonus(entity.getResult().getUser().getUserBonus());
        user.setIsbuyridingcard(entity.getResult().getIsbuyridingcard());
        user.setWarningmoney(entity.getResult().getWarningmoney());
        App.getInstance().setUserEntityBean(user);
        implMain.getInfo2(user);

    }
    /**
     * 处理个人信息
     * @param response 服务端数据
     * @param gson gson
     */
    private void handlerInfo(String response, Gson gson) {
        UserInfo userInfo = gson.fromJson(response, UserInfo.class);
        App.getInstance().getDaoSession().getUseBeanDao().deleteAll();
        UseBean useBean = new UseBean();
        useBean.setBalance(userInfo.getResult().getBalance());
        useBean.setDeposit(userInfo.getResult().getDeposit());
        useBean.setEndtime(userInfo.getResult().getEndtime());
        useBean.setLockid(userInfo.getResult().getLockid());
        useBean.setMoney(userInfo.getResult().getMoney());
        useBean.setDepositDefault(userInfo.getResult().getDepositDefault());
        useBean.setPicurl(userInfo.getResult().getPicurl());
        useBean.setStarttime(userInfo.getResult().getStarttime());
        useBean.setState(userInfo.getResult().getState());
        useBean.setUserstate(userInfo.getResult().getUserstate());
        useBean.setData(userInfo.getResult().getData());
        useBean.setLockmac(userInfo.getResult().getLockmac());
        useBean.setLocktype(userInfo.getResult().getLocktype());
        useBean.setNickname(userInfo.getResult().getNickname());
        useBean.setOrdernum(userInfo.getResult().getOrdernum());
        useBean.setState(userInfo.getResult().getState());
        useBean.setBarcode(userInfo.getResult().getBarcode());
        useBean.setUsername(userInfo.getResult().getUsername());
        useBean.setIdcard(userInfo.getResult().getIdcard());
        App.getInstance().setOpenMeney(userInfo.getResult().getOpenMoney());
        App.getInstance().getDaoSession().getUseBeanDao().insert(useBean);
      //  App.getInstance().setUserInfo(useBean);
        String data = userInfo.getResult().getData();
        if (!TextUtils.isEmpty(data)&&!TextUtils.isEmpty(userInfo.getResult().getLockmac())) {
            App.getInstance().getDaoSession().getBikeOrderBeanDao().deleteAll();
            String decodeHexString = ConvertUtils.bytes2HexString(Base64.decode(data, 0));
            com.coolu.blelibrary.config.Config.key = ConvertUtils.hexString2Bytes(decodeHexString.substring(0, decodeHexString.length() - 12));
            com.coolu.blelibrary.config.Config.password = ConvertUtils.hexString2Bytes(decodeHexString.substring(decodeHexString.length() - 12, decodeHexString.length()));
            BikeOrderBean bikeOrderBean = new BikeOrderBean(System.currentTimeMillis(), userInfo.getResult().getOrdernum(), userInfo.getResult().getLockmac(), userInfo.getResult().getLocktype(), userInfo.getResult().getStarttime(), userInfo.getResult().getEndtime(), userInfo.getResult().getBarcode(), decodeHexString.substring(0, decodeHexString.length() - 12), decodeHexString.substring(decodeHexString.length() - 12, decodeHexString.length()),userInfo.getResult().getShebeiid());
            App.getInstance().getDaoSession().getBikeOrderBeanDao().insert(bikeOrderBean);
        }
        implMain.getInfo(useBean);
    }




}
