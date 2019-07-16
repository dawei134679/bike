package com.coolu.nokelock.bike.util;

import android.bluetooth.BluetoothAdapter;
import android.content.IntentFilter;

/**
 * Created by admin on 2017/8/18.
 */

public class Config {

    public static String APP_ID = "wx85d9736bce7257a4";

    /**
     * h5 base url
     */
    public static final String H5_URL = "http://web.coolubike.com:1688/Html/";

    /*
     * 用户指南
     */
    public static final String USER_HELP = "help.html";

    /**
     * 用户协议
     * @param lng
     * @param lat
     * @return
     */
    public static final String USER="http://web.coolubike.com:1688/Html/user.html";
    public static String getQxBaseUrl(String lng,String lat){
        //骑行路线
        return "http://info.coolubike.com/routelist.htm?lng="+lng+"&lat="+lat;
    }
    //推荐骑行
    public static String getRouteUrl(String flag){
        return "http://info.coolubike.com/Route.htm?id="+flag;
    }
    //吃喝玩乐详情H5界面
    public static final String getPlayItemUrl(String id){
        return"http://info.coolubike.com/Article.htm?id="+id;
    }
    //我的卡卷
    public static final String getCardUrl="http://119.23.72.53:1688/Html/help_yhq.html";


    //基础网址
    public static final String BASE_URL = "http://web.coolubike.com:16888/Handle";
    //个人信息
    public static final String  GET_INFO="getInfo";
    //得到MAC地址
    public static final String GETMAC = "getMac";
    //登录
    public static final String Login="login";
    //登录
    public static final String GETCODE="getCode";
    //强制还车
    public static final String UP_BLE_STATE="upBleState";

    public static final String ORDER_NONE = "none";

    public static final String PIC="getStartPic";
    //网络请求失败
    public static final int HTTP_NO_CONNECT = -1;
    public static final int NO_JSON = -2;

    //人员
    public static final String PERSON="person";


    /**
     * 获取强制还车金额
     */
    public  static  final String GET_FORCED_MONEY="getForceMoney";
    /**
     * 错误类型
     */
    public static final String  UP_ERROR_MEG="upErrorMeg";
    public static final String  GET_START_PIC="getStartPic";
    public static final String SUCCESS = "ok";
    public static String LOCK_TYPE = "";
    public static final String TOKEN = "token";
    public static final String PHONE = "phone";
    public static final String BLE = "ble1";
    public static final String BLE2 = "ble2";
    public static final String BLE3 = "ble3";
    public static final String GPS = "gps";
    public static final String PREPARE = "prepare";

    public static final String ORDER_HAVE = "using";
    public  static  final  String  member="member"; //判断是否是会员
    public static final String  RECHARGE="RE"; //是否冲押
    public static final String  WX="wxPay";
    public  static  final String ZFB="zgPay";
    public static final String GETNEWMAC="getnewmac"; //新锁的mac
    public static final String YOUZANCARDURL="youzancardUrl"; //新锁的mac




    /**
     * 开锁
     */
    public static final String OPEN = "com.sunshine.notelockbike.api.OPEN";

    /**
     * 蓝牙交互
     */
    public static final String BLE_CONNECT = "com.sunshine.notelockbike.api.BLE_CONNECT";

    /**
     * 开锁成功
     */
    public static final String OPEN_OK = "com.sunshine.notelockbike.api.OPEN_OK";


    /**
     * 还车
     */
    public static final String CLOSE = "com.sunshine.notelockbike.api.CLOSE";

    /**
     * 还车成功
     */
    public static final String CLOSE_OK = "com.sunshine.notelockbike.api.CLOSE_OK";

    /**
     * 连接状态
     */
    public static final String CONNECT_CHANAGE = "com.sunshine.notelockbike.api.CONNECT_CHANAGE";

    /**
     * 进度条
     */
    public static final String PROGRESS_DIALOG = "com.sunshine.notelockbike.api.PROGRESS_DIALOG";

    /**
     * 超时
     */
    public static final String STOP_LOCK = "com.sunshine.notelockbike.api.STOP_LOCK";

    /**
     * 蓝牙状态
     */
    public static final String BLUETOOTH_CHANAGE = "com.sunshine.notelockbike.api.BLUETOOTH_CHANAGE";

    /**
     * 微信充值
     */
    public static final String WX_PAY = "com.sunshine.notelockbike.api.WX_PAY";

    public  static final String REFRESH="com.sunshine.notelockbike.api.REFRESH";

    public static IntentFilter initIntentFilter() {
        IntentFilter intentFilter = new IntentFilter(Config.OPEN);
        intentFilter.addAction(OPEN_OK);
        intentFilter.addAction(CLOSE);
        intentFilter.addAction(CLOSE_OK);
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        intentFilter.addAction(CONNECT_CHANAGE);
        intentFilter.addAction(PROGRESS_DIALOG);
        intentFilter.addAction(STOP_LOCK);
        intentFilter.addAction(BLE_CONNECT);
        intentFilter.addAction(REFRESH);
        return intentFilter;
    }


}
