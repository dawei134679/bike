package com.coolu.nokelock.bike.activity;

import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.multidex.MultiDex;
import android.text.TextUtils;


import com.amap.api.location.AMapLocation;

import com.chuanglan.shanyan_sdk.OneKeyLoginManager;
import com.coolu.blelibrary.config.LockType;
import com.coolu.blelibrary.impl.AndroidBle;
import com.coolu.blelibrary.inter.IBLE;
import com.coolu.blelibrary.utils.GlobalParameterUtils;

import com.coolu.nokelock.bike.bean.CardTipBean;
import com.coolu.nokelock.bike.bean.UseBean;
import com.coolu.nokelock.bike.bean.UserEntityBean;
import com.coolu.nokelock.bike.util.ConditionsUtils;
import com.coolu.nokelock.bike.util.LogcatHelper;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.sunshine.dao.db.DaoMaster;
import com.sunshine.dao.db.DaoSession;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.umeng.analytics.MobclickAgent;
import com.youzan.androidsdk.YouzanSDK;
import com.youzan.androidsdk.basic.YouzanBasicSDKAdapter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * 作者: Sunshine
 * 时间: 2017/4/14.
 * 邮箱: 44493547@qq.com
 * 描述:
 */

public class App extends Application {

    private static App app;
    private String result=null; //二维码结果
    private boolean isMember=false; //是否为会员
    private AMapLocation aMapLocation=null; //定位的对象
    public static int msgId=1;

    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private UseBean resultEntity;

    private int connectStatus = 0;
    private long startMillis = 0;
    private int battery = 0;
    private String forceMoney = null;
    public static int OPEN_TYPE = -1;
    public static final int SCANER_CODE=111;
    private IBLE ible;
    private String openMeney;
    private String deposit="";
    private UserEntityBean userEntityBean; //人员信息表
    private CardTipBean cardTipBean;//优惠券
    public static boolean isTip=true; //引导页只出现一次

    public CardTipBean getCardTipBean() {
        return cardTipBean;
    }

    public void setCardTipBean(CardTipBean cardTipBean) {
        this.cardTipBean = cardTipBean;
    }

    private String cid=null;// 个推的cid

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public UserEntityBean getUserEntityBean() {
        return userEntityBean;
    }

    public void setUserEntityBean(UserEntityBean userEntityBean) {
        this.userEntityBean = userEntityBean;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    IWXAPI api=null;

    public IWXAPI getApi() {
        return api;
    }

    public void setApi(IWXAPI api) {
        this.api = api;
    }

    public AMapLocation getaMapLocation() {
        return aMapLocation;
    }

    public void setaMapLocation(AMapLocation aMapLocation) {
        this.aMapLocation = aMapLocation;
    }

    @Override

    public void onCreate() {
        super.onCreate();
        app = this;
        ToastUtils.init(this);
        ible = new AndroidBle(getApplicationContext());
        setDatabase();
        ConditionsUtils.init(this);
        GlobalParameterUtils.getInstance().setLockType(LockType.MTS);
        //Log日志开启
        LogcatHelper.getInstance(this).start();
        //有赞
        YouzanSDK.init(this, "a2feecb3ceade7d723", new YouzanBasicSDKAdapter());
        //讯飞
        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=5aed0642");

        //bugly
        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName =getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        // 初始化Bugly
        CrashReport.initCrashReport(context, "1ef0efc921", true, strategy);
//        闪验
        OneKeyLoginManager.getInstance().set(getApplicationContext(),"ni2etsx7","bBuW5OSU");

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }

    public static App getInstance(){
        return app;
    }

    public IBLE getIBLE(){
        if (ible==null){
            ible = new AndroidBle(getApplicationContext());
        }
        return ible;
    }

    /**
     * 设置greenDao
     */
    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }


    public SQLiteDatabase getDb() {
        return db;
    }

    public void setUserInfo(UseBean result) {
        resultEntity = result;
    }
    public UseBean getUseBean(){
        return resultEntity;
    }

    public int getConnectStatus(){
        return connectStatus;
    }

    public void setConnectStatus(int i) {
        connectStatus =  i;
    }

    public long getStartMillis() {
        return startMillis;
    }

    public void setStartMillis(long startMillis) {
        this.startMillis = startMillis;
    }

    public void setBattery(int i) {
        this.battery = i;
    }

    public int getBattery() {
        return battery;
    }

    public String getForceMoney() {
        return forceMoney;
    }

    public void setForceMoney(String forceMoney) {
        this.forceMoney = forceMoney;
    }



    public String getOpenMeney() {
        return openMeney;
    }

    public void setOpenMeney(String openMeney) {
        this.openMeney = openMeney;
    }

    //得到二维码扫描的结果
    public String getResult(){
        return  result;
    }

    public void setResult(String result){
        this.result=result;
    }

    //得到经纬度
    private String latitude;
    private String longitude;
    public String getLatitude(){
        return latitude;
    }
    public void setLatitude(String latitude){
        this.latitude=latitude;
    }
    public String getLongitude(){
        return longitude;
    }
    public void setLongitude(String longitude){
        this.longitude= longitude;
    }

    //会员的判断


    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        isMember = member;
    }




    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

}
