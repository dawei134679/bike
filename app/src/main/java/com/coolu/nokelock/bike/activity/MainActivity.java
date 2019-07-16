package com.coolu.nokelock.bike.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.amap.api.services.route.RouteSearch;
import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.api.Constant;
import com.coolu.nokelock.bike.base.BaseActivity;
import com.coolu.nokelock.bike.bean.BikeClockBean;
import com.coolu.nokelock.bike.bean.BikeOpenClockBean;
import com.coolu.nokelock.bike.bean.BikeOrderBean;
import com.coolu.nokelock.bike.bean.MacBeann;
import com.coolu.nokelock.bike.bean.UseBean;
import com.coolu.nokelock.bike.bean.UserEntityBean;
import com.coolu.nokelock.bike.fragment.Freefragment;
import com.coolu.nokelock.bike.fragment.MyAlertDialogFragment;
import com.coolu.nokelock.bike.fragment.PlayFragment;
import com.coolu.nokelock.bike.fragment.RecommendFragement;
import com.coolu.nokelock.bike.fragment.ShopFragment;
import com.coolu.nokelock.bike.fragment.TuiJianfragment;
import com.coolu.nokelock.bike.prensenter.MainPresenter;
import com.coolu.nokelock.bike.service.CommandService;
import com.coolu.nokelock.bike.service.DemoIntentService;
import com.coolu.nokelock.bike.service.DemoPushService;
import com.coolu.nokelock.bike.util.AnimatorHelper;
import com.coolu.nokelock.bike.util.AppVersionUtil;
import com.coolu.nokelock.bike.util.BaseUtil;
import com.coolu.nokelock.bike.util.CircleTransform;
import com.coolu.nokelock.bike.util.Config;
import com.coolu.nokelock.bike.util.DialogShowUtils;
import com.coolu.nokelock.bike.util.HttpHelper;
import com.coolu.nokelock.bike.util.ShanYanLoginUtil;
import com.coolu.nokelock.bike.util.VolleyUtils;
import com.coolu.nokelock.bike.view.MainView;
import com.coolu.nokelock.bike.view.impl.MainViewImpl;
import com.coolu.nokelock.bike.youzan.YouzanActivity;
import com.fitsleep.sunshinelibrary.inter.OnDialogClickListener;
import com.fitsleep.sunshinelibrary.utils.ConstUtils;
import com.fitsleep.sunshinelibrary.utils.ConvertUtils;
import com.fitsleep.sunshinelibrary.utils.IntentUtils;
import com.fitsleep.sunshinelibrary.utils.LocationServiceUtils;
import com.fitsleep.sunshinelibrary.utils.NetworkUtils;
import com.fitsleep.sunshinelibrary.utils.PhoneUtils;
import com.fitsleep.sunshinelibrary.utils.ScreenUtils;
import com.fitsleep.sunshinelibrary.utils.SharedPreferencesUtils;
import com.fitsleep.sunshinelibrary.utils.TimeUtils;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.fitsleep.sunshinelibrary.utils.UtilSharedPreference;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;
import com.igexin.sdk.PushManager;
import com.squareup.picasso.Picasso;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.youzan.androidsdk.YouzanSDK;


import android.view.ViewGroup.LayoutParams;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements View.OnClickListener ,MainViewImpl {

    //蓝牙变量6666
    private byte[] token = new byte[4];
    private byte CHIP_TYPE;
    private byte DEV_TYPE;
    byte[] gettoken = {0x06, 0x01, 0x01, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
    byte[] sendDataBytes = null;


    private BluetoothAdapter bluetoothadapter;  //蓝牙适配器
    private int REQUEST_ENABLE = 1000;


    private Toolbar toolbar;
    private ImageView left;
    private DrawerLayout drawerLayout;
    //fragment切换
    private RadioButton[] arrayButton;
    private List<Fragment> fragments = new ArrayList<Fragment>();
    //   记录当前的Fragment
    private int currentPosition=0;

    String result = null;
    private int count = 0;



    //进度条对话框
    ProgressDialog pd = null;
    private AlertDialog.Builder builder;

    public String cityName = null;
    private ImageView search;
    private Freefragment freefragment;
    private RecommendFragement recommendFragement;
    private String latitude;
    private String longitude;

    private static MainActivity activity = null;//单例
    private ImageView touxiang;
    private TextView userguide;
    private LinearLayout trip;
    private LinearLayout money;
    private TextView rewerd;
    private Button no_member;
    private PopupWindow pop;
    private Button member;
    private PlayFragment playFragment;
    private TextView login;
    private TextView about;
    private TuiJianfragment tuiJianfragment;
    private NavigationView navigationView;


    private MainPresenter mainPresenter;
    public static final int QR_SCAN_REQUEST_CODE = 3638;
    public static final int SEARCH_CODE = 5869;
    private boolean isLockMarker = false;  //开锁后，地图上的点不动
    private boolean isexpande=true;  //判断Radiogroup是否展开

    Drawable drawable=null;

    private ProgressDialog dismissProgressDialog;
    private CollapsingToolbarLayout viewById;

//    Learning add
    private ShanYanLoginUtil shanYanLoginUtil;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case 4:
                    updateProgress();
                    break;
            }
        }
    };
    private IWXAPI api;

    private ImageView yuan;
    private TextView agreement;
    private TextView logout;
    private ImageView member_img;
    private BikeOpenClockBean bikeOpenClockBean;
    private TextView tvNet;
    private TextView credit;
    private ListView listView;
    private RecyclerView recyclerView;
    private ShopFragment shopFragment;
    private Drawable red_card;
    private LinearLayout ll_near_pass;

    public static MainActivity getInstacne() {
        if (activity == null) {
            activity = new MainActivity();
        }
        return activity;
    }
    private RelativeLayout bike_rela=null; //半圆
    public  View getHalf(){
        return bike_rela;
    }
    private RadioGroup group_rp=null; //RadioGroup
    public  View getRadioGroup(){
        return group_rp;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
    private   ImageView imageView=null; //红包商城旁边的图片
    public View getImageView(){
        return  imageView;
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏文字颜色及图标为深色
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_main);

        api = WXAPIFactory.createWXAPI(this, Config.APP_ID);
        api.registerApp(Config.APP_ID);

        App.getInstance().setApi(api);
        activity = this;
        ButterKnife.bind(this);
        mainPresenter = new MainPresenter(this);

        initCommandService();
        initToolBar();
        initPopWindow();
        initDialog();
        initDrawer();
        initFragemnt();
        initView();

        Log.e("shaa","签名"+ BaseUtil.sHA1(this));

       // Log.e("main", "onCreate");

//        if (!AMapUtil.isOpenLocService(this)) {
//           // ToastUtils.showMessage("需要打开定位服务");
//            Toast.makeText(MainActivity.this,"需要打开定位服务",Toast.LENGTH_SHORT).show();
//            AMapUtil.gotoLocServiceSettings(this);
//        }
        xunFeiSpeak("欢迎使用酷游单车");
    }


    @Override
    protected void onStart() {
        super.onStart();
        // com.getui.demo.DemoPushService 为第三方自定义推送服务
        PushManager.getInstance().initialize(this.getApplicationContext(),DemoPushService.class);

    }

    /**
     * 网络判断
     * @param netMobile
     */
    private Boolean isNet=false;
    @Override
    public void onNetChange(int netMobile) {
        super.onNetChange(netMobile);
        //网络状态变化时的操作
        if (netMobile == NetworkUtils.NETWORK_NO) {
          //  tvNet.setVisibility(View.VISIBLE);
            ToastUtils.showMessage("当前网络不可用");
            isNet=true;

        } else {
          // tvNet.setVisibility(View.GONE);
            Log.e("kkk","有网");
            if (!TextUtils.isEmpty(UtilSharedPreference.getStringValue(getApplicationContext(), Config.PHONE))) {
             //  mainPresenter.getInfo2();
               if (freefragment!=null){
                   freefragment.getRefresh_Iamg().clearAnimation();
               }
               if (isNet){
                   if (freefragment!=null){
                       //得到地图上红包单车，停车点,广告
                      freefragment.initVolley();
                   }
               }


            }

//            FrameLayout.LayoutParams layout = new FrameLayout.LayoutParams(
//                    FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
//            layout.setMargins(0, BaseUtil.getViewHeight(toolbar,true), 0, 0);
//            group_rp.setLayoutParams(layout);
//            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
//                    FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.WRAP_CONTENT);
//            params.setMargins(0,BaseUtil.getViewHeight(group_rp,true)+BaseUtil.getViewHeight(toolbar,true),0,0);
//            bike_rela.setLayoutParams(layout);


        }
    }

    private void initDialog() {

        if (!LocationServiceUtils.isOpenLocService(this)) {
            //ToastUtils.showMessage("需要打开定位服务");
            LocationServiceUtils.gotoLocServiceSettings(this);
           LocationServiceUtils.openGPS(this);
        }

        //弹窗提示信息
//        // DialogShowUtils.showTipsDialog(this);
//        HttpHelper.getInstance().getVersion(MainActivity.this);
        dashSpinnerDialog = DialogShowUtils.showDialogResult(this, R.layout.dialog_progress_spinner, 0);
        progressBar = (ProgressBar) dashSpinnerDialog.findViewById(R.id.pb_progressbar);
        //初始化dialog信息
        this.dismissProgressDialog = new ProgressDialog(MainActivity.this, 0);
        this.dismissProgressDialog.setCancelable(false);
        this.dismissProgressDialog.setCanceledOnTouchOutside(false);

        //启动时判断网络状态
        boolean netConnect = this.isNetConnect();
        if (netConnect) {
           // tvNet.setVisibility(View.GONE);
        } else {
           // tvNet.setVisibility(View.VISIBLE);
        }
    }

    private void initCommandService() {
        List<UseBean> useBeen = App.getInstance().getDaoSession().getUseBeanDao().loadAll();
        if (useBeen.size() > 0) {
            App.getInstance().setUserInfo(useBeen.get(0));
        }
        startService(new Intent(this, CommandService.class));

        App.getInstance().getIBLE().enableBluetooth();

        //注册蓝牙广播
        registerReceiver(broadcastReceiver, Config.initIntentFilter());
    }
    private TextView tvCarIdOrder;
    private TextView tvCarStartTime;
    private TextView tvMoneyOrder;
    private PopupWindow orderPopWindow;
    private TextView tvReturnOrder;
    private Dialog dashSpinnerDialog;
    private ProgressBar progressBar;
    /**
     * 初始化一些弹出窗体
     */
    private void initPopWindow() {

        //订单进行
      //  View orderView = View.inflate(this, R.layout.dialog_order, null);
        View orderView= LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_order,null);
        tvCarIdOrder = (TextView) orderView.findViewById(R.id.tv_car_id_order);
        tvCarStartTime = (TextView) orderView.findViewById(R.id.tv_car_start);
        tvMoneyOrder = (TextView) orderView.findViewById(R.id.tv_money_order);
        ll_near_pass = (LinearLayout) orderView.findViewById(R.id.ll_near_pass);
        ll_near_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                freefragment.makeLine();
            }
        });

        tvReturnOrder = ((TextView) orderView.findViewById(R.id.tv_return_order));
        tvReturnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = UtilSharedPreference.getStringValue(getApplicationContext(), Config.PHONE);
                if (!TextUtils.isEmpty(phone)) {
                    if ("结束用车".equals(tvReturnOrder.getText().toString().trim())) {
                        if (!App.getInstance().getIBLE().isEnable()) {
                            ToastUtils.showMessage("请开启蓝牙");

                            return;
                        }
                        if (dismissProgressDialog == null) {
                            dismissProgressDialog = new ProgressDialog(MainActivity.this, 0);
                            dismissProgressDialog.setCancelable(false);
                            dismissProgressDialog.setCanceledOnTouchOutside(false);
                        }
                        mainPresenter.getForcedMoney(App.getInstance().getUserEntityBean().getBarCode());
                        dismissProgressDialog.setMessage("正在还车...");
                        dismissProgressDialog.show();
                        xunFeiSpeak("正在环车，请您保持手机在车锁3米范围内");
                        sendBroadcast(new Intent(Config.CLOSE));
                    } else {
                        //mainPresenter.upBleCancelLock();
                    }
                }
            }
        });
       // showPopupWindow(toolbar);
        orderPopWindow = new PopupWindow(orderView, ScreenUtils.getScreenWidth(this)-BaseUtil.dp2px(MainActivity.this,20) , LayoutParams.WRAP_CONTENT);


    }
    private LinearLayoutManager layoutManager;
    private void initDrawer() {
        navigationView = (NavigationView) findViewById(R.id.id_nv_menu);
        View headerView = navigationView.getHeaderView(0);
        touxiang = (ImageView)headerView.findViewById(R.id.id_touxiang);

        touxiang.setOnClickListener(this);
        //用户指南
        userguide = (TextView)headerView.findViewById(R.id.id_User_guide);
        userguide.setOnClickListener(this);
        trip = (LinearLayout)headerView.findViewById(R.id.id_trip);
        trip.setOnClickListener(this);
        money = (LinearLayout)headerView.findViewById(R.id.id_money);
        money.setOnClickListener(this);
        //我的奖励
        rewerd = (TextView)headerView.findViewById(R.id.id_rewerd);
        rewerd.setOnClickListener(this);
        //注册
        login = (TextView)headerView.findViewById(R.id.id_login);
        login.setOnClickListener(this);
        //关于
        about = (TextView)headerView.findViewById(R.id.id_about);
        about.setOnClickListener(this);

        //用户协议
        agreement = (TextView)headerView.findViewById(R.id.id_user_agreement);
        agreement.setOnClickListener(this);
        //退出
        logout = (TextView)headerView.findViewById(R.id.id_out);
        logout.setOnClickListener(this);
        //会员
        member_img = (ImageView)headerView.findViewById(R.id.id_main_member);
        credit = (TextView)headerView.findViewById(R.id.id_user_credit);
        if (!TextUtils.isEmpty( UtilSharedPreference.getStringValue(getApplicationContext(), Config.PHONE))) {
            setPhoneNum();
        }
    }

    private void setPhoneNum() {
       login.setText( UtilSharedPreference.getStringValue(getApplicationContext(), Config.PHONE));
    }


    /**
     * 进度展示
     */
    int progress;

    private void updateProgress() {
        long diffMillis = System.currentTimeMillis() - App.getInstance().getStartMillis();
        if (diffMillis >= 60 * 1000) {
            if (dashSpinnerDialog != null && dashSpinnerDialog.isShowing()) {
                dashSpinnerDialog.dismiss();
            }
            return;
        }
        progress = progressBar.getProgress();
        switch (App.OPEN_TYPE) {
            case 0://获取mac地址
                if (progress >= 25) {
                    progressBar.setProgress(25);
                } else {
                    progress++;
                    progressBar.setProgress(progress);
                }
                break;
            case 1://连接设备开锁
                if (progress >= 75) {
                    progressBar.setProgress(75);
                } else if (progress < 25) {
                    progressBar.setProgress(25);
                } else {
                    progress++;
                    progressBar.setProgress(progress);
                }
                break;
            case 2://更新锁状态
                if (progress >= 99) {
                    progressBar.setProgress(99);
                } else if (progress < 75) {
                    progressBar.setProgress(75);
                } else {
                    progress++;
                    progressBar.setProgress(progress);
                }
                break;
            case 3:
                progressBar.setProgress(100);
                break;
        }
        handler.sendEmptyMessageDelayed(4, 200);

    }
    private int leftMar = 20;
    private boolean isFirst=false;

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("uuuu", "onResume");
//        Bundle bundle = getIntent().getBundleExtra("bundle");
//        latitude = bundle.getString("latitude");
//        longitude = bundle.getString("longitude");

        if (!TextUtils.isEmpty(UtilSharedPreference.getStringValue(getApplicationContext(), Config.PHONE))){

                Log.e("yyy","有意义");
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mainPresenter.getInfo2(); //先得用户的到信息列表
                    }
                },1000);
                setPhoneNum();




        }
      //  Log.e("token",UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(),Config.TOKEN));




        if (!TextUtils.isEmpty(UtilSharedPreference.getStringValue(getApplicationContext(), Config.PHONE))) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED);
        } else {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
//        版本升级
        HttpHelper.getInstance().getVersion(MainActivity.this);
    }



    @Override
    protected void onPause() {
        super.onPause();
        Log.e("uuuu","Main+onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("uuuu","Main+onDestroy");
        if (orderPopWindow != null && orderPopWindow.isShowing()) {
            orderPopWindow.dismiss();
            orderPopWindow = null;
        }

        stopService(new Intent(this, CommandService.class));

        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
            broadcastReceiver = null;
        }
        handler.removeCallbacksAndMessages(null);



    }






    public void initView() {

        // 添加红包商城图片
        RelativeLayout main_content = (RelativeLayout) findViewById(R.id.id_main_content);
        imageView =new ImageView(this);
        imageView.setImageResource(R.mipmap.red_group);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                new LayoutParams(LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT));
        lp.setMargins(BaseUtil.px2dp(MainActivity.this,0f),BaseUtil.dp2px(MainActivity.this,56f),0,0);
      //  imageView.setPadding(BaseUtil.dp2px(MainActivity.this,110),0,0,0);

        imageView.setPadding((int) getResources().getDimension(R.dimen.x30),0,0,0);
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);

        imageView.setLayoutParams(lp);
        main_content.addView(imageView);

        group_rp = (RadioGroup) findViewById(R.id.main_group);
        //  实例化RadioButton数组
        arrayButton = new RadioButton[group_rp.getChildCount()];
        //   循环赋值
        for (int i = 0; i < group_rp.getChildCount(); i++) {
            arrayButton[i] = (RadioButton) group_rp.getChildAt(i);
        }
        arrayButton[0].setTextColor(Color.parseColor("#03DF77"));



        arrayButton[0].setChecked(true);
        //设置监听器
        group_rp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                // 循环遍历每一个RadioButton的Id  判断是否为当前选中的ID
                for (int i = 0; i < arrayButton.length; i++) {
                    if (arrayButton[i].getId() == checkedId) {
                        //  拿到是第几个Button后   执行替换操作
                        replace(i);
                    }
                }
            }


        });

    }
    FragmentTransaction transaction;
    Fragment cuFragment;
    Fragment tarFragment;
    //fragment的替换
    public void replace(int targetPosition) {
        Log.e("kop","凄rtyuy");
        //   事务职能commit 一次  所以 每次调用该方法的时候 都需要实例化一次事务
//        transaction = getSupportFragmentManager()
//                .beginTransaction();
//        Log.e("kop",fragments.size()+"长度");
//
//        // 获取当前显示的Fragment
//       cuFragment = fragments.get(currentPosition);
//
//        //获取目标的 Fragment
//        tarFragment = fragments.get(targetPosition);
//        if (!tarFragment.isAdded()) {
//            // 没有添加过 则添加 并将当前Fragment 隐藏
//            Log.e("kop","tar"+"4");
//            transaction.add(R.id.main_framelayout, tarFragment).hide(cuFragment).commit();
//
//        } else {
//            Log.e("kop","tar"+"5");
//            //如果添加过 则将当前Fragment 隐藏 将目标Fragment 展示出来
//            transaction.hide(cuFragment).show(tarFragment).commit();
//        }



//        //  判断 目标Fragment 是否被添加过
//        if (!tarFragment.isAdded()) {
//            // 没有添加过 则添加 并将当前Fragment 隐藏
//
//            transaction.add(R.id.main_framelayout, tarFragment).hide(cuFragment)
//                    .commit();
//        } else {
//            //如果添加过 则将当前Fragment 隐藏 将目标Fragment 展示出来
//            transaction.show(tarFragment).hide(cuFragment).commit();
//
//        }



        switch (currentPosition){
            //这里可以写成一个方法
            case 0:
                Log.e("kop","current"+"0");
                arrayButton[currentPosition].setTextColor(Color.parseColor("#000000"));
                drawable=getResources().getDrawable(R.mipmap.gray_line);
                // / 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                        drawable.getMinimumHeight());
                arrayButton[currentPosition].setCompoundDrawables(null,null,null,drawable);
                break;
            case 1:
                Log.e("kop","current"+"1");
                arrayButton[currentPosition].setTextColor(Color.parseColor("#000000"));
                drawable=getResources().getDrawable(R.mipmap.gray_line);

                // / 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                        drawable.getMinimumHeight());
                arrayButton[currentPosition].setCompoundDrawables(null,null,null,drawable);
                break;
            case 2:
                Log.e("kop","current"+"2");
                arrayButton[currentPosition].setTextColor(Color.parseColor("#000000"));
                drawable=getResources().getDrawable(R.mipmap.gray_line);
                // / 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                        drawable.getMinimumHeight());
                arrayButton[currentPosition].setCompoundDrawables(null,null,null,drawable);
                break;
        }

        switch (targetPosition){
            case 0:
                Log.e("TAG","tar"+"0");
                //设置半圆图片
               // yuan.setImageDrawable(getResources().getDrawable(R.mipmap.bike));
                arrayButton[targetPosition].setTextColor(Color.parseColor("#03DF77"));
                drawable=getResources().getDrawable(R.mipmap.linee);
                // / 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                        drawable.getMinimumHeight());
                arrayButton[targetPosition].setCompoundDrawables(null,null,null,drawable);
//                if (isexpande){
//                    //参数 控件，开始，结束，时间
//                    AnimatorHelper.translationY(group_rp,0,-group_rp.getHeight(),500);
//                    AnimatorHelper.translationY(bike_rela,0,-group_rp.getHeight(),500);
//                    isexpande=false;
//               }



                break;
            case 1:

                if (TextUtils.isEmpty(UtilSharedPreference.getStringValue(this,Config.PHONE))){

                    arrayButton[0].setTextColor(Color.parseColor("#03DF77"));
                    drawable=getResources().getDrawable(R.mipmap.linee);
                    // / 这一步必须要做,否则不会显示.
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                            drawable.getMinimumHeight());
                    arrayButton[0].setCompoundDrawables(null,null,null,drawable);
                    arrayButton[targetPosition].setChecked(false);
                    arrayButton[0].setChecked(true);
//                    IntentUtils.startActivity(this, LoginActivity.class);

//                    Learning add
                    if (shanYanLoginUtil==null){
                        shanYanLoginUtil=new ShanYanLoginUtil(MainActivity.this);
                    }
                    shanYanLoginUtil.shanYanLogin();
                    return;
                }
                startActivityForResult(new Intent(MainActivity.this,YouzanActivity.class),100);

                //设置半圆图片
            //    yuan.setImageDrawable(getResources().getDrawable(R.mipmap.route));

                arrayButton[targetPosition].setTextColor(Color.parseColor("#03DF77"));
                drawable=getResources().getDrawable(R.mipmap.linee);
                // / 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                        drawable.getMinimumHeight());
                arrayButton[targetPosition].setCompoundDrawables(null,null,null,drawable);
//
//                if (isexpande){
//                    //参数 控件，开始，结束，时间
//                    AnimatorHelper.translationY(group_rp,0,-group_rp.getHeight(),500);
//                    AnimatorHelper.translationY(bike_rela,0,-group_rp.getHeight(),500);
//                    isexpande=false;
//                }
                break;
            case 2:
                if (TextUtils.isEmpty(UtilSharedPreference.getStringValue(this,Config.PHONE))){
                    arrayButton[0].setTextColor(Color.parseColor("#03DF77"));
                    drawable=getResources().getDrawable(R.mipmap.linee);
                    // / 这一步必须要做,否则不会显示.
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                            drawable.getMinimumHeight());
                    arrayButton[0].setCompoundDrawables(null,null,null,drawable);
                    arrayButton[targetPosition].setChecked(false);
                    arrayButton[0].setChecked(true);
//                    IntentUtils.startActivity(this, LoginActivity.class);

//                    Learning add
                    if (shanYanLoginUtil==null){
                        shanYanLoginUtil=new ShanYanLoginUtil(MainActivity.this);
                    }
                    shanYanLoginUtil.shanYanLogin();
                    return;
                }
                startActivityForResult(new Intent(MainActivity.this,TuiActivity.class),100);
                Log.e("kop","tar"+"2");
                //设置半圆图片
              //  yuan.setImageDrawable(getResources().getDrawable(R.mipmap.enjoy));

                arrayButton[targetPosition].setTextColor(Color.parseColor("#03DF77"));
                drawable=getResources().getDrawable(R.mipmap.linee);
                // / 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                        drawable.getMinimumHeight());
                arrayButton[targetPosition].setCompoundDrawables(null,null,null,drawable);
//
//                if (isexpande){
//                    //参数 控件，开始，结束，时间
//                    AnimatorHelper.translationY(group_rp,0,-group_rp.getHeight(),500);
//                    AnimatorHelper.translationY(bike_rela,0,-group_rp.getHeight(),500);
//                    isexpande=false;
//                }
                break;
        }

        //  判断 目标Fragment 是否被添加过
        //可能问题原因，  transaction或者事务的状态丢失啦,commitAllowingStateLoss(),但是会造成其他问题





        currentPosition = targetPosition;
    }

    /**
     * 设置toolbar和drawerlayout
     */
    private void initToolBar() {
        drawerLayout = (DrawerLayout) findViewById(R.id.id_drawer);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);//侧滑关闭
        search = (ImageView) findViewById(R.id.id_search);
        search.setOnClickListener(this);
        //设置Toolbar
        toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        toolbar.setTitle("");

        setSupportActionBar(toolbar);
        // Toolbar中左边的t图片
        left = (ImageView) findViewById(R.id.id_left);
        left.setOnClickListener(this);

        //groupButton的动画
        bike_rela = (RelativeLayout)findViewById(R.id.id_main_relative);
        bike_rela.setOnClickListener(this);
        //下面的半圆图片
        yuan = (ImageView)findViewById(R.id.id_main_yuan);
        //网络判断
        tvNet = (TextView)findViewById(R.id.id_net);

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                Log.d("wyy","侧拉菜单打开了");
                if (orderPopWindow.isShowing()){
                    orderPopWindow.dismiss();
                    popShow=true;
                }
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                Log.d("wyy","侧拉菜单关闭了");
                if (popShow){
                    popShow=false;
                    orderPopWindow.showAsDropDown(toolbar,BaseUtil.dp2px(MainActivity.this,10),BaseUtil.dp2px(MainActivity.this,40), Gravity.CENTER_HORIZONTAL);
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);//侧滑关闭
                }
            }
            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });

    }

    private boolean popShow=false;

    /**
     * 创建fragment
     *
     * @param
     */
    private void initFragemnt() {
        freefragment = new Freefragment();
       // recommendFragement = new RecommendFragement();
       // tuiJianfragment = new TuiJianfragment();
      //  playFragment = new PlayFragment();
       // shopFragment = new ShopFragment();
        fragments.add(freefragment);
    //    fragments.add(tuiJianfragment);
     //   fragments.add(recommendFragement);
      //  fragments.add(playFragment);r44
      //  fragments.add(shopFragment);
        //默认提交第一个
//        freefragment.initVolley();//开屏广告
        getSupportFragmentManager().beginTransaction().add(R.id.main_framelayout, fragments.get(0)).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_left:
              // drawerLayout.openDrawer(Gravity.LEFT);
                if (TextUtils.isEmpty(UtilSharedPreference.getStringValue(getApplicationContext(), Config.PHONE))) {
//                    IntentUtils.startActivity(this, LoginActivity.class);

//                    Learning add
                    if (shanYanLoginUtil==null){
                        shanYanLoginUtil=new ShanYanLoginUtil(MainActivity.this);
                    }
                    shanYanLoginUtil.shanYanLogin();
                } else {
                    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        drawerLayout.closeDrawer(GravityCompat.START);
                    } else {
                        if (orderPopWindow.isShowing()){
                            orderPopWindow.dismiss();
                            popShow=true;
                        }
                        drawerLayout.openDrawer(GravityCompat.START);
                        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED);
                    }
                }
               // showPopupWindow(toolbar);
                break;
            case R.id.id_search:
//                if (freefragment!=null){
//                    freefragment.cityNameInterface(new Freefragment.CityNameInterface() {
//                        @Override
//                        public void setCityName(String cityName) {
//
//                            intent.putExtra("city",cityName);
//
//                        }
//                    });
//                }

//                Intent intent = new Intent(MainActivity.this, PoiActivity.class);
//                startActivity(intent);
                startActivityForResult(new Intent(this, PoiActivity.class), 100);
                break;
            case R.id.id_touxiang:

                Intent personal=new Intent(MainActivity.this,Personctivity.class);
               // startActivity(personal);
                startActivityForResult(personal,101);
                break;
            case  R.id.id_User_guide:
                Intent user=new Intent(MainActivity.this,UserActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("url", Config.H5_URL+Config.USER_HELP);
                bundle.putString("route","用户指南");
                bundle.putInt("flag",3);
                user.putExtras(bundle);
                startActivity(user);
                break;
            case R.id.id_trip: //我的行程
                Intent trip=new Intent(MainActivity.this,TripActivity.class);
               // Intent trip=new Intent(MainActivity.this,TripContentActivity.class);
                startActivity(trip);
                break;
            case R.id.id_money:

                Intent money=new Intent(MainActivity.this,MoneyActivity.class);
                startActivity(money);
                break;
            case R.id.id_rewerd:  //我的卡卷
                //上下文对话框
              //  showPopupWindow(v);
                Intent action=new Intent(MainActivity.this,ActionActivity.class);
                startActivity(action);
                break;
            case R.id.id_no_member: //非会员
               // Intent rewerd=new Intent(MainActivity.this,RewerdActivity.class);
              //  rewerd.putExtra("flag",0);
               // startActivity(rewerd);
                //popwindow关闭
               // pop.dismiss();
                break;
            case R.id.id_member://会员
             //   Intent action=new Intent(MainActivity.this,ActionActivity.class);
             //   startActivity(action);
                break;
            case R.id.id_login:
                if (TextUtils.isEmpty(UtilSharedPreference.getStringValue(MainActivity.this,Config.PHONE))){
//                    Intent login=new Intent(MainActivity.this,LoginActivity.class);
//                    startActivity(login);

//                    Learning add
                    if (shanYanLoginUtil==null){
                        shanYanLoginUtil=new ShanYanLoginUtil(MainActivity.this);
                    }
                    shanYanLoginUtil.shanYanLogin();
                }
              //  Intent login=new Intent(MainActivity.this,LoginActivity.class);


                break;

            case R.id.id_about:
                Intent about=new Intent(MainActivity.this,AboutActivity.class);
                startActivity(about);
                break;
            case R.id.id_main_relative:
                //group_rp和bike_rela
                //  ObjectAnimator.ofFloat(a2,"translationX",start,to).setDuration(1000).start();
                if (isexpande){ //收回
                    //参数 控件，开始，结束，时间
                    AnimatorHelper.translationY(group_rp,0,-group_rp.getHeight(),500);
                    AnimatorHelper.translationY(bike_rela,0,-group_rp.getHeight(),500);
                    isexpande=false;
                }else{//展开
                    Log.e("ppp","高度"+group_rp.getHeight());
                    Log.e("ppp",toolbar.getHeight()+"toolbar");
                  //  AnimatorHelper.translationY(group_rp,-BaseUtil.px2dp(MainActivity.this,500),0,500);
                    AnimatorHelper.translationY(group_rp,-group_rp.getHeight(),0,500);
                    AnimatorHelper.translationY(bike_rela,-group_rp.getHeight(),0,500);
                    isexpande=true;
                }
                break;
            case R.id.id_user_agreement://红包商城
                Intent agreememt=new Intent(MainActivity.this,YouzanActivity.class);
                Bundle b=new Bundle();
                b.putString("url","https://h5.youzan.com/v2/coupons?kdt_id=16594038");
              //  b.putString("route","用户协议");
              //  b.putInt("flag",3);
                agreememt.putExtras(b);
                startActivity(agreememt);
                break;
            case R.id.id_out:
                logout();
                break;
        }
    }

    /**
     * 退出登陆
     */
    private void logout() {
        DialogShowUtils.getProgressDialogg(this, getString(R.string.logout_me), R.layout.dialog_post, new OnDialogClickListener() {
            @Override
            public void OnClickListener() {
                App.getInstance().getIBLE().disconnect();

                App.getInstance().getDaoSession().getUseBeanDao().deleteAll();
                App.getInstance().getDaoSession().getSearchBeanDao().deleteAll();
                App.getInstance().getDaoSession().getBikeOrderBeanDao().deleteAll();
                App.getInstance().getDaoSession().getOrderBeanDao().deleteAll();
                UtilSharedPreference.saveString(getApplicationContext(), Config.TOKEN, "");
                App.getInstance().setUserInfo(null);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
              //  drawerLayout.closeDrawer(Gravity.LEFT);
                //用来判断会员
                UtilSharedPreference.saveString(MainActivity.this, Config.member,"");
                UtilSharedPreference.saveString(MainActivity.this, Config.PHONE,"");
                if (member_img!=null){
                    member_img.setImageResource(R.mipmap.vip2);
                }

                if(login!=null){
                    login.setText("登录/注册");
                }
                //关闭订单
                if (orderPopWindow != null && orderPopWindow.isShowing()) {
                    orderPopWindow.dismiss();

                }
                //有赞
                YouzanSDK.userLogout(MainActivity.this);
                isFirst=false;

            }
        });
    }

    private void showPopupWindow(View v) {
       View view= LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_order,null);
//        member = (Button)view.findViewById(R.id.id_member);
//        member.setOnClickListener(this);
//        no_member = (Button)view.findViewById(R.id.id_no_member);
//        no_member.setOnClickListener(this);
        pop = new PopupWindow(view, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,
                true);
        pop.setTouchable(true);
        pop.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                //这里如果返回true的话，touch事件被拦截
                //拦截后PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

       // pop.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        pop.showAsDropDown(toolbar, leftMar, leftMar, Gravity.CENTER_HORIZONTAL);

    }


    //创建进度条对话框
    public void createProgressDialog() {
        Log.i("result", "sDialog");
//        pd=new ProgressDialog(MainActivity.this);
//        pd.setProgressStyle(ProgressDialog.BUTTON1);
//        pd.setTitle("正在开启，请稍等");
//        //pd.setMessage("");
//        pd.setIcon(R.mipmap.bike_icon);
//        pd.setIndeterminate(false);//设置对话框的进度条是否不明确 false 就是不设置为不明确
//        pd.setCancelable(true);// 设置对话框是否可以按退回键取消
//        pd.setMax(100);
//        pd.show();
//        View inflate = LayoutInflater.from(this).inflate(R.layout.progress_dialog, null);
//        ProgressBar progress =(ProgressBar) inflate.findViewById(R.id.myProgress);
//        progress.setMax(100);
//        builder = new AlertDialog.Builder(MainActivity.this);
//        builder.setIcon(R.mipmap.bike_icon);
//        builder.setTitle("正在开启中，请稍等");
//        builder.setView(inflate);
//        builder.show();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true){
//                    try {
//                        Thread.sleep(100);
//                        pd.setProgress(count++);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
//        //开启一个线程来延时
//        Thread thread=new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(10000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                handler.sendEmptyMessage(0);
//
//            }
//        });
//        thread.start();
        MyAlertDialogFragment.getInstace().show(getFragmentManager(), "ProgressDialog");


    }

    private long exitTime = 0;



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

//        if (fragments.get(currentPosition)instanceof ShopFragment){
//            ((ShopFragment)fragments.get(currentPosition)).onKeyDown(keyCode,event);
//                return  false;
//        }else{

            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if ((System.currentTimeMillis() - exitTime) > 2000){//System.currentTimeMillis()无论何时调用，肯定大于2000  
                        Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                        exitTime = System.currentTimeMillis();
                    } else {
                        finish();
                        System.exit(0);
                    }
                return true;
            }
      //  }


        return super.onKeyDown(keyCode, event);
    }
    //取消对话框
    public void CancleDiolag(){
            if (dashSpinnerDialog!=null){
                dashSpinnerDialog.dismiss();

            }
    }

    /**
     * 新锁
     * 开锁成功
     * @param json
     */
    @Override
    public void getNewMac(String json) {
        handler.removeMessages(4);
        App.OPEN_TYPE=3;
        mainPresenter.getInfo2();
        //开锁的对话框
        if (dashSpinnerDialog!=null){
            dashSpinnerDialog.dismiss();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //地图搜索返回
        if (requestCode == 100 && resultCode == 100) {
            // 执行Fragment事务
            Log.e("lpl","current"+currentPosition);
            replace(0);
            arrayButton[currentPosition].setChecked(false);
            arrayButton[0].setChecked(true);
        }



        Log.e("kok", "requestCode" + requestCode);
        Log.e("kok", "resultCode" + resultCode);
        //头像
        if (requestCode == 101 && resultCode == Activity.RESULT_OK) {

            Bundle extras = data.getExtras();
            String result = extras.getString("result");
            if (result != null) {
                Bitmap bitmap = BitmapFactory.decodeFile(result);
                if (bitmap != null) {
                    Bitmap bitmap1 = BaseUtil.centerSquarScaleBitmap(bitmap, 200);
                    if (bitmap1 != null) {
                        Bitmap center = BaseUtil.toCircleBitmap(bitmap1);
                        if (center != null) {
                            touxiang.setImageBitmap(center);
                        }
                    }
                }


            }
        }

        if (requestCode == 111 && resultCode == Activity.RESULT_OK) {

            Log.e("kok", "result吞吞吐吐拖拖");
            Bundle extras = data.getExtras();
            String code = extras.getString("result");

            if ("".equals(code)) {
                ToastUtils.showMessage("二维码错误");
                return;
            }
            //扫描商家二维码http://fx.coolubike.com/share.html?ChannelId=974076786759565312
            if (code.contains("http://fx.coolubike.com/share.html?ChannelId")) {
                //调用支付接口
                payCard(code.substring(code.lastIndexOf("=")+1));
                return;
            }

//            //新锁
//            if(code.contains("9891")){
//                Log.e("kok", "code");
//                //开锁的进度条
//                if (dashSpinnerDialog == null) {
//                    dashSpinnerDialog = DialogShowUtils.showDialogResult(this, R.layout.dialog_progress_spinner, 0);
//                    progressBar = (ProgressBar) dashSpinnerDialog.findViewById(R.id.pb_progressbar);
//                }
//                dashSpinnerDialog.show();
//
//                App.getInstance().setStartMillis(System.currentTimeMillis());
//                App.getInstance().OPEN_TYPE = 0;
//                updateProgress();//更新进度
//                mainPresenter.getNewMac(code);
//                return;
//            }

            boolean flag = extras.getBoolean("flag", false);
            //手动输入编号
            if (flag) {
                code = "http://app.coolubike.com/app.html?id=" + code;
            }

            if (!flag && !code.contains("http://app.coolubike.com/app.html")) {
                Toast.makeText(MainActivity.this, "二维码错误", Toast.LENGTH_SHORT).show();
                return;
            }
            if (freefragment!=null){
                freefragment.cancelWalkRouteOverlay();
            }
            //开锁的进度条
            if (dashSpinnerDialog == null) {
                dashSpinnerDialog = DialogShowUtils.showDialogResult(this, R.layout.dialog_progress_spinner, 0);
                progressBar = (ProgressBar) dashSpinnerDialog.findViewById(R.id.pb_progressbar);
            }
            dashSpinnerDialog.show();
            App.getInstance().setStartMillis(System.currentTimeMillis());
            App.getInstance().OPEN_TYPE = 0;
            updateProgress();//更新进度
            mainPresenter.getMac(code);
        }

    }

    @Override
    public void errorMessage(String action, int code) {

    }


    //充骑行卡
    private void payCard(String cardId) {
        Log.e("lol","cord"+cardId);
//        HashMap map = VolleyUtils.CommonParameters(MainActivity.this);
//        map.put("payWhat","充骑行卡");
//        map.put("channelId",cardId);
//       getPayData(Url.WX,map,"wx");
        Intent intent=new Intent(MainActivity.this,ActivationActivity.class);
        intent.putExtra("cardId",cardId);
        //骑行卡激活界面
        intent.putExtra("flag",2);
        startActivity(intent);

    }

    @Override
    public void getInfo(UseBean useBean) {

        Log.e("pp","userInfo");
        resolveOrder(useBean);

    }
    long intervalTime=0;//预算金额
    @Override
    public void getInfo2(UserEntityBean user){
        Log.e("kkkk","状态"+user.getUserStatus());
        Log.e("kkk","Mac"+user.getLockmac());
        Log.e("kkk", "lelal"+user.getUserLevel());
        Log.e("kkk","数据"+user.getLockdata());
        if (user.getUserPic()!=null||user.getUserPic()!=null){
            Picasso.with(MainActivity.this).load(user.getUserPic()).resize(200,200).transform(new CircleTransform()).into(touxiang);
        }
        //更新是否为会员

        if (App.getInstance().getUserEntityBean()!=null&&"1".equals(App.getInstance().getUserEntityBean().getUserLevel())){
            member_img.setImageResource(R.mipmap.vip);
        }

        //信用积分
        if (App.getInstance().getUserEntityBean()!=null&&App.getInstance().getUserEntityBean().getUserCredit()!=null){
            credit.setText("信用积分"+App.getInstance().getUserEntityBean().getUserCredit());
        }


        List<BikeOrderBean> orderListBeen = App.getInstance().getDaoSession().getBikeOrderBeanDao().loadAll();
        Log.e("kkk",orderListBeen.size()+"chang");
//        Log.e("kkk","chang"+orderListBeen.get(0).getMac());
//        Log.e("kkk","chang"+orderListBeen.get(0).getBarcode());
        /**
         * 这里要是不把订单存到数据库，就不用写啦，或者还可以优化
         */
        //等于0 是解决退出后，订单就删除啦，后面判断换手机号

        if ("1".equals(user.getUserStatus())){
            if (user.getBarCode()!=null&&(orderListBeen.size()==0||!orderListBeen.get(0).getBarcode().equals(user.getBarCode()))){
                Log.e("kkkk",orderListBeen.size()+"caocao");
                if (orderListBeen.size()>=0){
                    //要是换个手机号
                    App.getInstance().getDaoSession().getBikeOrderBeanDao().deleteAll();
                }
                //构造订单表
                String data = user.getLockdata();
                String key = null;
                String password = null;
                //  dd ="NTEsMTQsMSw0OSwyLDk1LDg2LDE0LDYsMjgsNzMsNjIsMTMsOTUsNjcsNDcsMyw1LDIsMywyLDY=";
                if (!TextUtils.isEmpty(data)) {
                    String decodeHexString = ConvertUtils.bytes2HexString(Base64.decode(data, 0));
                    // String decode= new String(base64Decode(dd)));
                    //  String bytes = ConvertUtils.bytes2HexString(this.base64Decode(dd));
                    //  Log.e("ppp","decode"+decodeHexString);
                    //  Log.e("ppp","de"+bytes);
                    key = decodeHexString.substring(0, decodeHexString.length() - 12);
                    password = decodeHexString.substring(decodeHexString.length() - 12, decodeHexString.length());
                }

                BikeOrderBean bikeOrderBean=new BikeOrderBean(System.currentTimeMillis(),user.getOrderNo(),user.getLockmac(),user.getLocktype(),user.getStarttime(),"",user.getBarCode(),key,password,user.getShebieId());
                App.getInstance().getDaoSession().getBikeOrderBeanDao().insert(bikeOrderBean);

                List<BikeOrderBean> order = App.getInstance().getDaoSession().getBikeOrderBeanDao().loadAll();
                Log.e("kkk","order"+order.size());
                Log.e("kkk","长度"+order.get(0).getMac());
                for (int i=0;i<order.size();i++){
                    Log.e("kkk","长度"+order.get(i).getMac());
                }

            }
        }


        Log.e("kkk","系统当前时间"+new Date());

        switch (user.getUserStatus()){
            case "1":
                tvReturnOrder.setVisibility(View.VISIBLE);
                isLockMarker = true;
                //展示进行订单
               // if (Config.ORDER_HAVE.equals(userInfo.getState())) {
                    tvReturnOrder.setText("结束用车");
               // } else {
                   // tvReturnOrder.setText("取消订单");
               // }
                Date currentDate=new Date();
                if (user!=null&&user.getStarttime()!=null&&!"".equals(user.getStarttime())){
                    Date date = TimeUtils.string2Date(user.getStarttime());
//                    intervalTime = TimeUtils.getIntervalTime(currentDate, date, ConstUtils.TimeUnit.HOUR);//原时间差（有问题）

                    long diff = currentDate.getTime() - date.getTime();//时间差
                    intervalTime = diff / (1000 * 60 * 30);

                    tvCarStartTime.setText(user.getStarttime().substring(5));
                    tvMoneyOrder.setText((intervalTime+1)*3+".00");
                }else{
                    intervalTime=0;
                }

              if (user!=null&&user.getLockid()!=null){
                  tvCarIdOrder.setText(user.getLockid());//userInfo.getResult().getLockid()
              }


                App.getInstance().getDaoSession().getOrderBeanDao().deleteAll();
                if (!orderPopWindow.isShowing()) {
                    if (!popShow) {
                        orderPopWindow.showAsDropDown(toolbar, BaseUtil.dp2px(MainActivity.this, 10), BaseUtil.dp2px(MainActivity.this, 40), Gravity.CENTER_HORIZONTAL);
                    }
                }
                //左侧侧滑屏蔽
//                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
//                left.setClickable(false);

//                if (App.getInstance().mAMapLocation != null) {
//                    mainPresenter.getStopPoint(App.getInstance().mAMapLocation.getLatitude(), App.getInstance().mAMapLocation.getLongitude());
////                    mAMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(App.getInstance().mAMapLocation.getLatitude(), App.getInstance().mAMapLocation.getLongitude()), mAMap.getMaxZoomLevel() - 2));
//                }
                break;
            case "0":
            case "2":
            case "3":
                isLockMarker = false;
                //关闭popWindows
                if (orderPopWindow != null && orderPopWindow.isShowing()) {
                    orderPopWindow.dismiss();
                }
                //侧滑可以打开
//                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED);
//                left.setClickable(true);
//                if (App.getInstance().mAMapLocation != null) {
//                    mainPresenter.getStopPoint(App.getInstance().mAMapLocation.getLatitude(), App.getInstance().mAMapLocation.getLongitude());
////                    mAMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(App.getInstance().mAMapLocation.getLatitude(), App.getInstance().mAMapLocation.getLongitude()), zoomPosition));
//                }

        }
    }

    private void resolveOrder(UseBean userInfo) {

        Log.e("pp",userInfo.getState());
        switch (userInfo.getState()) {

            case Config.PREPARE:
            case Config.ORDER_HAVE:
                tvReturnOrder.setVisibility(View.VISIBLE);
                isLockMarker = true;
                //展示进行订单
                if (Config.ORDER_HAVE.equals(userInfo.getState())) {
                    tvReturnOrder.setText("结束用车");
                } else {
                    tvReturnOrder.setText("取消订单");
                }
                tvCarStartTime.setText(TimeUtils.convertTotime(Long.parseLong(userInfo.getStarttime())));
                tvCarIdOrder.setText(userInfo.getLockid());//userInfo.getResult().getLockid()
                tvMoneyOrder.setText(userInfo.getMoney());
                App.getInstance().getDaoSession().getOrderBeanDao().deleteAll();
                if (!orderPopWindow.isShowing()) {
                    orderPopWindow.showAsDropDown(toolbar, 0, 0, Gravity.CENTER_HORIZONTAL);
                }
//                if (App.getInstance().mAMapLocation != null) {
//                    mainPresenter.getStopPoint(App.getInstance().mAMapLocation.getLatitude(), App.getInstance().mAMapLocation.getLongitude());
////                    mAMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(App.getInstance().mAMapLocation.getLatitude(), App.getInstance().mAMapLocation.getLongitude()), mAMap.getMaxZoomLevel() - 2));
//                }
                break;
            case Config.ORDER_NONE:
                isLockMarker = false;
                //关闭popWindows
                if (orderPopWindow != null && orderPopWindow.isShowing()) {
                    orderPopWindow.dismiss();

                }
//                if (App.getInstance().mAMapLocation != null) {
//                    mainPresenter.getStopPoint(App.getInstance().mAMapLocation.getLatitude(), App.getInstance().mAMapLocation.getLongitude());
////                    mAMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(App.getInstance().mAMapLocation.getLatitude(), App.getInstance().mAMapLocation.getLongitude()), zoomPosition));
//                }
                break;
        }
    }



    /**
     * 得到mac
     * @param macBean
     */
    @Override
    public void getMac(MacBeann macBean) {

       MacBeann.ResultBean result = macBean.getResult();

        String data = result.getData();
        String key = null;
        String password = null;

        if (!TextUtils.isEmpty(data)) {
            String decodeHexString = ConvertUtils.bytes2HexString(Base64.decode(data, 0));
           // String decode= new String(base64Decode(dd)));
          //  String bytes = ConvertUtils.bytes2HexString(this.base64Decode(dd));
          //  Log.e("ppp","decode"+decodeHexString);
          //  Log.e("ppp","de"+bytes);
            key = decodeHexString.substring(0, decodeHexString.length() - 12);
            password = decodeHexString.substring(decodeHexString.length() - 12, decodeHexString.length());
        }

        //Log.e("ppp","key"+key);
       // Log.e("ppp","password"+password);

       // BikeOrderBean bikeOrderBean = new BikeOrderBean(System.currentTimeMillis(), entity.getOrdernum(), entity.getLockmac(), entity.getLocktype(), String.valueOf(System.currentTimeMillis() / 1000), "", entity.getBarcode(), key, password, entity.getShebeiid());
        BikeOrderBean bikeOrderBean=new BikeOrderBean(System.currentTimeMillis(),result.getOrderNum(),result.getSheBeiMac(),result.getSheBeiType(),String.valueOf(System.currentTimeMillis() / 1000),"",result.getBarCode(),key,password,result.getSheBeiId());
        App.getInstance().getDaoSession().getBikeOrderBeanDao().insert(bikeOrderBean);

        List<BikeOrderBean> orderListBeen = App.getInstance().getDaoSession().getBikeOrderBeanDao().loadAll();
         Log.e("kok","长度"+orderListBeen.get(0).getMac());
        //Toast.makeText(MainActivity.this,"长度"+orderListBeen.size(),Toast.LENGTH_SHORT).show();
        mainPresenter.upErrorMessage("扫码获取信息成功", "1001");
        isLockMarker = true;
        App.OPEN_TYPE = 1;
        Config.LOCK_TYPE = macBean.getResult().getSheBeiType();
        if (Config.GPS.equals(macBean.getResult().getSheBeiType())) {
           // mainPresenter.gprsOpen();
        } else if (Config.BLE3.equals(macBean.getResult().getSheBeiType())) {
            if (!App.getInstance().getIBLE().isEnable()) {
                mainPresenter.upErrorMessage("蓝牙未开启", "1003");
                // mainPresenter.gprsOpen();
            } else {
                sendBroadcast(new Intent(Config.OPEN));
            }
        } else {
            sendBroadcast(new Intent(Config.OPEN));
        }

    }
    //开锁
    @Override
    public void upBleOpenLock(String json) {
        //解析下开锁返回的字符串
        bikeOpenClockBean = VolleyUtils.parseJsonWithGson(json.toString(), BikeOpenClockBean.class);
        Log.e("kok","开锁");
        App.OPEN_TYPE = 3;
        mainPresenter.upErrorMessage("订单执行成功", "1009");
        handler.removeMessages(4);
       // mainPresenter.getInfo();
        mainPresenter.getInfo2();
        //开锁的对话框
        dashSpinnerDialog.dismiss();
        xunFeiSpeak("请您用车后将车停到酷游专用停车位并点击结束用车");
    }



    /**
     * 还车
     * @param time
     * @param money
     * @param youhui
     */
    private Dialog dialogResult;
    @Override
    public void upBleCloseLock(String time,String money,List<BikeClockBean.ResultBean.YouhuiBean> youhui,String forceMoney) {
        if (youhui!=null&&youhui.size()>0){
           dialogResult=DialogShowUtils.showTipsDialog(MainActivity.this,youhui);
            ((TextView)dialogResult.findViewById(R.id.card_title)).setText("骑行辛苦 感谢用车");

            String[] str = time.split("!");
            if (str.length>1){
                ((TextView) dialogResult.findViewById(R.id.card_login_success)).setText(Html.fromHtml(str[0]));
                TextView textView = (TextView) dialogResult.findViewById(R.id.card_login_success_two);
                textView.setVisibility(View.VISIBLE);
                textView.setText(Html.fromHtml(str[1]));
            }else {
                ((TextView) dialogResult.findViewById(R.id.card_login_success)).setText(Html.fromHtml(time));
            }


            ((TextView) dialogResult.findViewById(R.id.card_gift)).setText("同时获得商城红包,");
//            ((TextView)dialogResult.findViewById(R.id.card_account)).setText("已放入您的账户, "+UtilSharedPreference.getStringValue(MainActivity.this,Config.PHONE));
            ((TextView)dialogResult.findViewById(R.id.card_account)).setText("选购酷游红包商城产品,可享全网最低价");

            dialogResult.findViewById(R.id.card_rela).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialogResult.isShowing()) {
                        dialogResult.dismiss();
                    }
                }
            });
            dialogResult.findViewById(R.id.card_go).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent lookcard=new Intent(MainActivity.this,YouzanActivity.class);
//                    Bundle b=new Bundle();
//                    b.putString("url","https://h5.youzan.com/v2/showcase/homepage?alias=hnh0xir0");
//
//                    lookcard.putExtras(b);
                    startActivity(lookcard);
                }
            });
        }else{
            dialogResult = DialogShowUtils.showDialogResult(MainActivity.this, R.layout.dialog_result, 0);

        ((TextView) dialogResult.findViewById(R.id.tv_result_content)).setText(Html.fromHtml(time));
        ((TextView)dialogResult.findViewById(R.id.tv_result_money)).setText(Html.fromHtml(money));
        ((TextView)dialogResult.findViewById(R.id.tv_result_diaodu_money)).setText(Html.fromHtml(forceMoney));
        dismissProgressDialog.setMessage("还车成功，谢谢使用。");
        dialogResult.findViewById(R.id.bt_ok_result).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogResult.isShowing()) {
                    dialogResult.dismiss();
                }
            }
            });
        }






        handler.removeMessages(4);
        dismissProgressDialog.dismiss();
        if (!dialogResult.isShowing()) {
            dialogResult.show();
            dialogResult.setCancelable(true);
        }
       // mainPresenter.getInfo();
        mainPresenter.getInfo2();
        App.getInstance().setConnectStatus(0);
        App.getInstance().getIBLE().disconnect();
    }

    @Override
    public void upBleCancelLock(String json) {
        mainPresenter.upErrorMessage("订单取消成功", "1009");
        App.getInstance().getDaoSession().getBikeOrderBeanDao().deleteAll();
        mainPresenter.getInfo();
    }

    @Override
    public void ForgetClose(String json) {

    }

    /**
     * 变更UI监听广播
     */
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case Config.OPEN_OK:
                    Log.e("kkkk","开锁车");
                    if (App.getInstance().getUserEntityBean() != null &&"1".equals(App.getInstance().getUserEntityBean().getUserStatus())) {
                        return;
                    }
                    App.OPEN_TYPE = 2;
                    mainPresenter.upBleOpenLock();
                    break;
                case Config.CLOSE_OK://还车
                    Log.e("kok","还车");
                    mainPresenter.upBleCloseLock(intent.getStringExtra("command"));
                    break;
                case Config.PROGRESS_DIALOG:
                     lockStatus(intent);
                    break;
                case Config.BLE_CONNECT:
                    bleStatus(intent);
                    break;
                case Config.STOP_LOCK:
                   // ToastUtils.showMessage("没有查询到要结束的车辆，请靠近车辆重启蓝牙再试");
                    // dismissProgressDialog.dismiss();
                    // dashSpinnerDialog.dismiss();
                    break;
            }
        }
    };

    private void bleStatus(Intent intent) {
        String command = intent.getStringExtra("command");
        switch (command) {
            case Constant.BLE_SEARCH://开启搜索
                mainPresenter.upErrorMessage("开启搜索设备", "1002");
                break;
            case Constant.BLE_NOT_SEARCH://没有搜索到
                mainPresenter.upErrorMessage("未搜索到设备", "1002");
                   dashSpinnerDialog.dismiss();
                  dismissProgressDialog.dismiss();
                ToastUtils.showMessage("未搜索到车辆，请靠近重启蓝牙再试或者换辆车子");

                break;
            case Constant.BLE_START_CONNECT://发起连接
                mainPresenter.upErrorMessage("发起连接", "1002");
                break;
            case Constant.BLE_CONNECT://连接成功
                mainPresenter.upErrorMessage("连接成功", "1004");
                break;
            case Constant.BLE_DISCONNECT://断开连接
                mainPresenter.upErrorMessage("断开连接", "1004");
                 dashSpinnerDialog.dismiss();
                 dismissProgressDialog.dismiss();
                App.getInstance().getIBLE().disconnect();
                if (App.getInstance().getDaoSession().getBikeOrderBeanDao().loadAll().size() > 0) {
                    ToastUtils.showMessage("开锁失败，请扫码重试或还车失败，重试");
                }
                break;
            case Constant.BLE_CONNECTED://已链接
                mainPresenter.upErrorMessage("已链接", "1004");
                break;
            case Constant.BLE_TONKEN://获取token
                mainPresenter.upErrorMessage("获取token", "1004");
                break;
            case Constant.BLE_OPEN_OK://开锁成功
                mainPresenter.upErrorMessage("开锁成功", "1006");
                break;
            case Constant.BLE_OPEN_FAIL://开锁失败
                mainPresenter.upBleCancelLock();  //上传开锁失败
                mainPresenter.upErrorMessage("开锁失败", "1006");
                App.getInstance().getIBLE().disconnect();
                ToastUtils.showMessage("车辆异常，请重试或者更换车辆再试");
                dashSpinnerDialog.dismiss();
                dismissProgressDialog.dismiss();
                xunFeiSpeak("开锁失败，请您检查车锁是否被辐条压住并再次扫码开锁");
                break;
            case Constant.BLE_CLOSE_NOT://未上锁
                mainPresenter.upErrorMessage("未上锁", "1006");
                ToastUtils.showMessage("请上锁后再进行结束用车");

                 dashSpinnerDialog.dismiss();
                 dismissProgressDialog.dismiss();
                break;
            case Constant.BLE_CLOSE_OK://锁已关闭
                mainPresenter.upErrorMessage("锁已关闭", "1006");
                break;
            case com.coolu.blelibrary.config.Config.CLOSE_ACTION://更新关锁状态
                xunFeiSpeak("已关锁，请在软件内手动结束用车");
                break;
            case Constant.BLE_SEARCH_XB://搜索信标
                mainPresenter.upErrorMessage("搜索信标", "1006");
                break;
            case Constant.BLE_NOT_XB://未搜索到信标
                mainPresenter.upErrorMessage("未搜索到信标", "1006");
                forcedCar("");
                break;
            case Constant.BLE_ZJ_START://搜索不到直接去连接
                mainPresenter.upErrorMessage("搜索不到直接去连接设备", "1002");
                break;
        }
    }

    /**
     * 锁状态更新
     *
     * @param intent 意图
     */
    private void lockStatus(Intent intent) {
        String command = intent.getStringExtra("command");
        if ("3".equals(command)) {
            mainPresenter.upErrorMessage("GPS开锁失败", "1006");
            handler.removeMessages(5);
            ToastUtils.showMessage("开锁失败，请换辆车试试");
            dashSpinnerDialog.dismiss();
            mainPresenter.getInfo();
        } else if ("4".equals(command)) {
            mainPresenter.upErrorMessage("订单结束成功", "1009");
            mainPresenter.upErrorMessage("推送收到上锁成功", "1008");
            String time = intent.getStringExtra("time");
            String money=intent.getStringExtra("money");
            overOrder(time,money);
        } else if ("5".equals(command)) {
            mainPresenter.upErrorMessage("GPS开锁成功", "1006");
            dashSpinnerDialog.dismiss();
            mainPresenter.getInfo();
        }
    }

    /**
     * 结束订单
     *
     * @param time
     * @param money
     * @param money
     */
    private void overOrder(String time,String money) {
        Log.e("mpm","time"+time+"money"+money);
        mainPresenter.getInfo2();
        final Dialog dialogResult = DialogShowUtils.showDialogResult(MainActivity.this, R.layout.dialog_result, 0);
        ((TextView) dialogResult.findViewById(R.id.tv_result_content)).setText(Html.fromHtml(time));
        ((TextView)dialogResult.findViewById(R.id.tv_result_money)).setText(Html.fromHtml(money));
        dismissProgressDialog.setMessage("还车成功，谢谢使用。");
        dialogResult.findViewById(R.id.bt_ok_result).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogResult.isShowing()) {
                    dialogResult.dismiss();
                }
            }
        });
        handler.removeMessages(4);
        dismissProgressDialog.dismiss();
        if (!dialogResult.isShowing()) {
            dialogResult.show();
            dialogResult.setCancelable(true);
        }
        // mainPresenter.getInfo();
        mainPresenter.getInfo2();
        App.getInstance().setConnectStatus(0);
        //  App.getInstance().getIBLE().disconnect();

    }

    /**
     * 强制还车
     *
     * @param state
     */
    private void forcedCar(final String state) {
        if (dismissProgressDialog.isShowing()) {
            dismissProgressDialog.dismiss();
        }
        String message = App.getInstance().getForceMoney();
     //   Log.e("kkkk",message);
        DialogShowUtils.getProgressDialog(this, message, R.layout.dialog_forced, new OnDialogClickListener() {
            @Override
            public void OnClickListener() {
                dismissProgressDialog.setMessage("正在还车...");
                dismissProgressDialog.show();
                mainPresenter.upBleForceCar(state);

            }
        });
    }


    //讯飞监听
    //合成监听器
    private SynthesizerListener mSynListener = new SynthesizerListener() {
        //会话结束回调接口，没有错误时，error为null
        public void onCompleted(SpeechError error) {
        }

        //缓冲进度回调
        //percent为缓冲进度0~100，beginPos为缓冲音频在文本中开始位置，endPos表示缓冲音频在文本中结束位置，info为附加信息。
        public void onBufferProgress(int percent, int beginPos, int endPos, String info) {
        }

        //开始播放
        public void onSpeakBegin() {
        }

        //暂停播放
        public void onSpeakPaused() {
        }

        //播放进度回调
        //percent为播放进度0~100,beginPos为播放音频在文本中开始位置，endPos表示播放音频在文本中结束位置.
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
        }

        //恢复播放回调接口
        public void onSpeakResumed() {
        }

        //会话事件回调接口
        public void onEvent(int arg0, int arg1, int arg2, Bundle arg3) {
        }
    };

    private void xunFeiSpeak(String str) {
        //1.创建SpeechSynthesizer对象, 第二个参数：本地合成时传InitListener
        SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer(MainActivity.this, null);

        /**
         2.合成参数设置，详见《科大讯飞MSC API手册(Android)》SpeechSynthesizer 类
         *
         */

        try {
            // 清空参数
            mTts.setParameter(SpeechConstant.PARAMS, null);
        }catch (NullPointerException e){

        }

        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");//设置发音人
        mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速
        //设置合成音调
        mTts.setParameter(SpeechConstant.PITCH, "50");
        mTts.setParameter(SpeechConstant.VOLUME, "100");//设置音量，范围0~100
        mTts.setParameter(SpeechConstant.STREAM_TYPE, "3");
        // 设置播放合成音频打断音乐播放，默认为true
        mTts.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true");

        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
//        mTts.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
//        boolean isSuccess = mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/msc/tts2.wav");
//        Toast.makeText(MainActivity.this, "语音合成 保存音频到本地：\n" + isSuccess, Toast.LENGTH_LONG).show();
        //3.开始合成
        int code = mTts.startSpeaking(str, mSynListener);

        if (code != ErrorCode.SUCCESS) {
            if (code == ErrorCode.ERROR_COMPONENT_NOT_INSTALLED) {
                //上面的语音配置对象为初始化时：
                Toast.makeText(MainActivity.this, "语音组件未安装", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, "语音合成失败,错误码: " + code, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void permissionSuccess(int requestCode) {
        super.permissionSuccess(requestCode);
        if (checkPermissions(new String[]{Manifest.permission.CALL_PHONE})){
            PhoneUtils.call(MainActivity.this, "4006789819");
        }
    }

}
