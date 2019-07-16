package com.coolu.nokelock.bike.fragment;


import android.Manifest;
import android.animation.ObjectAnimator;
import android.app.Dialog;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;


import com.amap.api.maps.AMap;

import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;

import com.amap.api.maps.MapView;
import com.amap.api.maps.Projection;
import com.amap.api.maps.SupportMapFragment;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Polyline;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkRouteResult;

import com.chuanglan.shanyan_sdk.OneKeyLoginManager;
import com.chuanglan.shanyan_sdk.listener.OneKeyLoginListener;
import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.activity.AboutActivity;
import com.coolu.nokelock.bike.activity.ActionActivity;
import com.coolu.nokelock.bike.activity.ActionListActivity;
import com.coolu.nokelock.bike.activity.ActionListDetailActivity;
import com.coolu.nokelock.bike.activity.App;
import com.coolu.nokelock.bike.activity.BalanceAddActivity;
import com.coolu.nokelock.bike.activity.CaptureActivity;
import com.coolu.nokelock.bike.activity.ExitBalanceAddActivity;
import com.coolu.nokelock.bike.activity.FaultUploadActivity;
import com.coolu.nokelock.bike.activity.LoginActivity;
import com.coolu.nokelock.bike.activity.MainActivity;
import com.coolu.nokelock.bike.activity.RechargeActivity;

import com.coolu.nokelock.bike.activity.UserActivity;
import com.coolu.nokelock.bike.activity.VipComboActivity;
import com.coolu.nokelock.bike.adapter.CardAdapter;
import com.coolu.nokelock.bike.adapter.RedAdapter;
import com.coolu.nokelock.bike.bean.ActionListBean;
import com.coolu.nokelock.bike.bean.CardBean;
import com.coolu.nokelock.bike.bean.CardTipBean;
import com.coolu.nokelock.bike.bean.MarkerWindow;
import com.coolu.nokelock.bike.bean.OpenImageBean;
import com.coolu.nokelock.bike.bean.Pilebean;
import com.coolu.nokelock.bike.bean.RedPacketBean;
import com.coolu.nokelock.bike.bean.ShanYan;
import com.coolu.nokelock.bike.bean.ShanYanResult;
import com.coolu.nokelock.bike.bean.TopActivitybean;
import com.coolu.nokelock.bike.overlay.WalkRouteOverlay;
import com.coolu.nokelock.bike.url.Url;
import com.coolu.nokelock.bike.util.AMapUtil;
import com.coolu.nokelock.bike.util.BaseUtil;
import com.coolu.nokelock.bike.util.Config;
import com.coolu.nokelock.bike.util.DialogShowUtils;

import com.coolu.nokelock.bike.util.ShanYanLoginUtil;
import com.coolu.nokelock.bike.util.VolleyController;
import com.coolu.nokelock.bike.util.VolleyUtils;
import com.coolu.nokelock.bike.youzan.YouzanActivity;
import com.fitsleep.sunshinelibrary.utils.AnimUtils;
import com.fitsleep.sunshinelibrary.utils.IntentUtils;
import com.fitsleep.sunshinelibrary.utils.PhoneUtils;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.fitsleep.sunshinelibrary.utils.UtilSharedPreference;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import static android.content.Context.NOTIFICATION_SERVICE;


/**
 * Created by admin on 2017/5/11.
 * 问题：锁屏时，定位监听已经停止，所以再打开屏幕后，不在自动定位 ,定位应该能成小蓝点样式
 */
public class Freefragment extends SupportMapFragment implements LocationSource, AMapLocationListener, View.OnClickListener,
        AMap.OnMapLoadedListener, AMap.OnMarkerClickListener, RouteSearch.OnRouteSearchListener, AMap.OnMapClickListener,
        GeocodeSearch.OnGeocodeSearchListener, AMap.OnMapTouchListener {

    private static final String TAG = "Freefragment";


    private int count = 0;
    private static Freefragment freefragment = null;
    private AMap aMap;
    private LinearLayoutManager layoutManager;
    private CardAdapter redAdapter;
    private MapView mapView = null;
    private UiSettings setting;

    private AMapLocationClient client = null;//定位的对象
    private AMapLocationClientOption option = null;//参数配置

    private OnLocationChangedListener locationChangeListener; //定位改变的监听

    public AMapLocationListener mLocationListener;
    private AMapLocation mMyLocationPoint;
    private ImageView image_center; //右下角的刷新居中图片
    Marker markercenter = null; //一直在屏幕中间的点
    Marker markerlocation; //定位的点


    private Timer timer; //定时器判断网络

    //标识，用于判断是否只显示一次定位信息和用户重新定位
    private boolean isFirstLoc = true;
    private boolean isFirstCenter = true;
    private boolean isNetWork = false; //有网时自动移动到中心点
    private ImageView image_refresh;
    // 旋转动画
    Animation animation_360, animation_45;

    //线路规划
    private final int ROUTE_TYPE_WALK = 3;
    private WalkRouteResult mWalkRouteResult;
    private WalkRouteOverlay walkRouteOverlay = null;
    private PopupWindow pop;
    //逆地理编码
    private GeocodeSearch geocoderSearch;
    private String addressName = null;
    //红包单车
    RedPacketBean redPacketBean = null;
    //停车桩
    Pilebean pilebean = null;
    private static final int REDBIKE = 11; //表示红包单车
    private static final int PILE = 22; //停车桩

    private LatLng latlng = new LatLng(39.91746, 116.396481);

    private ActionListBean topActivitybean;
    private List<Marker> stopPointMarkerList = new ArrayList<>(); //停车点的集合
    private List<Marker> redMarkerList = new ArrayList<>(); //停车点的集合
    private boolean isTip = true; //提示框

    //    Learning add
    public static boolean isScan = false;
    private ShanYanLoginUtil shanYanLoginUtil;
    private String userLevel;
    private String city;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0x01:
                    image_refresh.clearAnimation();
                    break;
                case 0x02:
                    refreshMarker();
                    break;

                case 101: //促销活动
                    topActivitybean = (ActionListBean) msg.obj;
                    if (topActivitybean != null) {
                        if (topActivitybean != null && topActivitybean.getResult().size() > 0 && topActivitybean.getResult().get(0).getActivityOutUrl() != null) {
                            //加载开启app的提示框

                            if (App.isTip && !getActivity().isFinishing()) {
                                DialogShowUtils.showTipsDialog(getActivity(), R.layout.cue_dialog, topActivitybean.getResult().get(0).getActivityOutUrl(), topActivitybean.getResult().get(0).getOpenUrl());
                                //show(R.style.no_title, R.layout.cue_dialog,Gravity.CENTER, BaseUtil.getWidthMetrics(getActivity())*2-150,BaseUtil.px2dp(getActivity(),1000),0,2,topActivitybean.getResults().get(0).getActivityOutUrl());
//                                openImage();
                                App.isTip = false;
                            }
                            if (!("".equals(topActivitybean.getResult().get(0).getWeixinUrl())) && topActivitybean.getResult().get(0).getWeixinUrl() != null) {
                                action_list.setVisibility(View.VISIBLE);
                                Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.main_weixin_dong);
                                // 启动动画
                                action_list.startAnimation(animation);
                            } else {
                                action_list.clearAnimation();
                                action_list.setVisibility(View.GONE);
                            }

                        }
                        //广告
                        if (topActivitybean != null && topActivitybean.getResult().size() > 0 && topActivitybean.getResult().get(0).getActivityTopUrl() != null) {
                            Picasso.with(getActivity()).load(topActivitybean.getResult().get(0).getActivityTopUrl()).fit().into(a2);
                        } else {
                            //没有广告，隐藏
                            if (a2 != null) {
                                a2.setVisibility(View.GONE);
                            }
                            if (a1 != null) {
                                a1.setVisibility(View.GONE);
                            }

                        }

                    }


                    break;
                case 102:
                    //红包单车
                    redPacketBean = (RedPacketBean) msg.obj;
                    for (Marker marker : redMarkerList) {
                        Log.e("vvvv", redMarkerList.size() + "");
                        marker.destroy();
                    }
                    redMarkerList.clear();

                    for (RedPacketBean.Result result : redPacketBean.getResult()) {
                        Log.e("zz", "哈哈" + result.getLng());
                        Log.e("zz", "哈哈" + result.getLat());
                        MarkerOptions redMarkerOptions = createMarker(Double.parseDouble(result.getLat()), Double.parseDouble(result.getLng()), R.mipmap.red);
                        Marker redMarker = aMap.addMarker(redMarkerOptions);
                        redMarker.setObject(REDBIKE);
                        redMarkerList.add(redMarker);
                    }
                    break;
                case REDBIKE: //红包单车
                    Log.e("zz", "hahah");
                    Marker mm = (Marker) msg.obj;
                    Log.e("zz", mm.getObject() + "");
                    double redlat = mm.getPosition().latitude;
                    double redlon = mm.getPosition().longitude;
                    Log.e("kk", "hahah" + redlat);
                    Log.e("kk", "hahah" + redlon);
                    for (RedPacketBean.Result result : redPacketBean.getResult()) {
                        double llo = Double.parseDouble(result.getLng());
                        double lla = Double.parseDouble(result.getLat());
                        Log.e("kk", "ha" + lla);
                        Log.e("kk", "ha" + llo);
                        if (redlat == lla && redlon == llo) {
                            if (m != null)
                                m.setNumber(result.getSheBeiBianHao());
                        }
                    }

                    setMarkerWindow(REDBIKE);
                    break;
                case PILE://停车桩
                    Marker pm = (Marker) msg.obj;
                    double latitude = pm.getPosition().latitude;
                    double longitude = pm.getPosition().longitude;
                    Log.e("zz", "停车桩lat" + latitude);
                    Log.e("zz", "停车桩lon" + longitude);
                    for (Pilebean.Result result : pilebean.getResult()) {
                        Double lon = Double.parseDouble(result.getLnglat().get(0));
                        Double lat = Double.parseDouble(result.getLnglat().get(1));
                        if (lon == longitude && lat == latitude) {
                            Log.e("zz", "名字" + result.getName());
                            if (m != null) {
                                m.setSite(result.getName());
                            }
                        }
                    }
                    setMarkerWindow(PILE);
                    break;
                case 103://停车桩
                    pilebean = (Pilebean) msg.obj;
                    if ("200".equals(pilebean.getErrorCode())) {
                        for (Marker marker : stopPointMarkerList) {
                            marker.destroy();
                        }
                        stopPointMarkerList.clear();
                        if (pilebean != null) {
                            if (pilebean.getResult().size() > 0) {
                                for (Pilebean.Result result : pilebean.getResult()) {
                                    if ("0".equals(result.getStatus())) {
                                        MarkerOptions pileMarkerOption = createMarker(Double.parseDouble(result.getLnglat().get(1)), Double.parseDouble(result.getLnglat().get(0)), R.mipmap.p);
                                        Marker pileMarker = aMap.addMarker(pileMarkerOption);
                                        pileMarker.setObject(PILE);
                                        stopPointMarkerList.add(pileMarker);
                                    }
                                }
                            }


                        }
                    }


                    break;
            }
        }
    };


    private ImageView photo_choose;
    private Dialog dialog;
    private View inflate;
    private TextView upload_fault;
    private TextView user_guide;
    private TextView dialog_cancel;
    private ImageView scan_linear;

    View free;
    private Button to_h5;
    private ImageView popup;
    private ImageView top_photo;
    private RouteSearch routeSearch;
    private LatLonPoint latLonPoint;
    private double lat_center;
    private double lon_center;
    private MarkerOptions markerOptions;
    private LinearLayout marker_popup;
    private TextView marker_address;
    private MarkerWindow m = null;
    private TextView marker_time;
    private TextView marker_distance;
    private TextView station_name;
    private ImageView action_list;
    private ImageView action_popup;
    private ImageView a1;
    private ImageView a2; //右侧的广告图片

    private int dis;
    private ListView card_listView;
    private RecyclerView recyclerView;
    private String cityName;
    private TextView id_weixin_guide;
    private LatLonPoint myNowPoint;


    public Freefragment() {

    }

    public static Freefragment getInstance() {
        if (freefragment == null) {
            synchronized (Freefragment.class) {
                if (freefragment == null) {
                    freefragment = new Freefragment();
                }
            }
        }

        return freefragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (free == null) {

            initView();
            initAnimation();
            //初始化地图
            initMap();


        }
        //地图的生命周期
        mapView.onCreate(savedInstanceState);
        return free;
    }

    private void initMap() {
        if (null == aMap) {
            aMap = mapView.getMap();
            //  setMapUp();
            initMayLocation();
            initTuoLuo();
        }
    }


    private void setMapUp() {
//        MyLocationStyle myLocationStyle;
//        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
//        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.mipmap.mylocation));
//        myLocationStyle.anchor(0.5f, 0.5f);
//        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));
//        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));
//        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style

        aMap.getUiSettings().setZoomControlsEnabled(false);
        aMap.getUiSettings().setScaleControlsEnabled(false);
        aMap.getUiSettings().setMyLocationButtonEnabled(false);
        //aMap.setOnMapTouchListener(this);
        aMap.setOnMarkerClickListener(this);
        aMap.setLocationSource(this);
        aMap.setMyLocationEnabled(true);
        // 设置定位的类型为定位模式，有定位、跟随或地图根据面向方向旋转几种
//        mAMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_FOLLOW);
        aMap.getUiSettings().setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_RIGHT);

    }

    private Animation animation;
    private Bitmap b1 = null;
    private Bitmap b2 = null;
    private float start;
    private float to;

    //自定义定位小蓝点的Marker
    Marker locationMarker;
    //坐标和经纬度转换工具
    Projection projection;

    private void initAnimation() {
        b1 = BitmapFactory.decodeResource(getResources(), R.mipmap.ad);
        b2 = BitmapFactory.decodeResource(getResources(), R.mipmap.ad1);
        dis = (b1.getWidth() - b2.getWidth());

        ObjectAnimator animator = ObjectAnimator.ofFloat(a2, "translationX", 0, dis);
        animator.setDuration(500);
        animator.start();
//        //动画监听，结束时可见
//        animator.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animator) {
//                a2.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animator) {
//
//            }
//        });

    }


    public void initVolley() {
        HashMap<String, String> map = VolleyUtils.getCommomParameter();
        map.put("name", cityName);//cityName
        //促销活动
        if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
            VolleyUtils.deStringPost(getActivity(), Url.ACTION_LIST, map, "action", new VolleyUtils.volleyListener() {
                @Override
                public void onResponse(JSONObject response) {
                    // Toast.makeText(getActivity(),"re",Toast.LENGTH_SHORT).show();
                    Log.e("dddd", response.toString());

//               TopActivitybean topActivitybean = VolleyUtils.parseJsonWithGson(response.toString(), TopActivitybean.class);
//                Message message=new Message();
//                message.obj=topActivitybean;
//                message.what=101;
//                handler.sendMessage(message);
                    ActionListBean actionListBean = VolleyUtils.parseJsonWithGson(response.toString(), ActionListBean.class);
                    Message message = new Message();
                    message.obj = actionListBean;
                    message.what = 101;
                    handler.sendMessage(message);

                }

                @Override
                public void onErrorResponse(String errorMessage) {

                }

                @Override
                public void onResponse(String response) {
                    ActionListBean actionListBean = VolleyUtils.parseJsonWithGson(response.toString(), ActionListBean.class);
                    Message message = new Message();
                    message.obj = actionListBean;
                    message.what = 101;
                    handler.sendMessage(message);
                }
            });
        }
        //红包单车和停车点
        bikeAndRedPoint();
    }

    public void bikeAndRedPoint() {

        if (!Url.isWifiProxy(getContext())) {

            //红包单车
            VolleyUtils.doGet(getActivity(), Url.redurl, "redpacket", new VolleyUtils.volleyListener() {
                @Override
                public void onResponse(JSONObject response) {
                    // Toast.makeText(getActivity(),"re",Toast.LENGTH_SHORT).show();
                    Log.e("zz", response.toString());
                    //Json解析
                    RedPacketBean redPacketBean = VolleyUtils.parseJsonWithGson(response.toString(), RedPacketBean.class);
                    Message message = new Message();
                    message.obj = redPacketBean;
                    message.what = 102;
                    handler.sendMessage(message);


                }

                @Override
                public void onErrorResponse(String errorMessage) {

                }

                @Override
                public void onResponse(String response) {

                }
            });
            //停车桩
            VolleyUtils.doGet(getActivity(), Url.pile, "plie", new VolleyUtils.volleyListener() {
                @Override
                public void onResponse(JSONObject response) {
                    // Toast.makeText(getActivity(),"re",Toast.LENGTH_SHORT).show();
                    Log.e("rer", response.toString());
                    //Json解析
                    Pilebean pilebean = VolleyUtils.parseJsonWithGson(response.toString(), Pilebean.class);

                    Message message = new Message();
                    message.obj = pilebean;
                    message.what = 103;
                    handler.sendMessage(message);


                }

                @Override
                public void onErrorResponse(String errorMessage) {

                }

                @Override
                public void onResponse(String response) {

                }
            });
        }
    }


    private void initView() {
        free = LayoutInflater.from(getActivity()).inflate(R.layout.freefragment, null);
        //对地图的操作
        mapView = (MapView) free.findViewById(R.id.mapview);
        image_center = (ImageView) free.findViewById(R.id.id_image_center);
        image_refresh = (ImageView) free.findViewById(R.id.id_image_refresh);
        photo_choose = (ImageView) free.findViewById(R.id.id_photo_choose);
        scan_linear = (ImageView) free.findViewById(R.id.id_scan_zxing);
        station_name = (TextView) free.findViewById(R.id.id_marker_station);
        action_list = (ImageView) free.findViewById(R.id.id_action_list);
        action_list.setOnClickListener(this);
        scan_linear.setOnClickListener(this);
        photo_choose.setOnClickListener(this);
        image_center.setOnClickListener(this);
        image_refresh.setOnClickListener(this);

        //上面的top 图片
        top_photo = (ImageView) free.findViewById(R.id.id_top_photo);
        //点击地图上的图标出现的对话框
        marker_popup = (LinearLayout) free.findViewById(R.id.id_marker_popup);
        marker_address = (TextView) free.findViewById(R.id.id_marker_address);
        marker_time = (TextView) free.findViewById(R.id.id_marker_time);
        marker_distance = (TextView) free.findViewById(R.id.id_marker_distance);
        //点击会弹出的活动图片
        a1 = (ImageView) free.findViewById(R.id.id_action_a1);
        a1.setOnClickListener(this);
        a2 = (ImageView) free.findViewById(R.id.id_action_a2);
        a2.setOnClickListener(this);

//        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.main_weixin_dong);
//        // 启动动画
//        action_list.startAnimation(animation);
    }


    private void initMayLocation() {
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.mipmap.mylocation));
        myLocationStyle.anchor(0.5f, 0.5f);
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style

//        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
        aMap.setLocationSource(this);
        aMap.setMyLocationEnabled(true);//打开定位层


        aMap.getUiSettings().setMyLocationButtonEnabled(false);
        aMap.getUiSettings().setTiltGesturesEnabled(false);
        aMap.getUiSettings().setScaleControlsEnabled(false);
        aMap.getUiSettings().setZoomControlsEnabled(false);
        aMap.getUiSettings().setRotateGesturesEnabled(false);
        aMap.getUiSettings().setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_RIGHT);

        //aMap.getUiSettings().setScaleControlsEnabled(true);// 标尺开关  
        //  aMap.getUiSettings().setCompassEnabled(true);// 指南针开关 

        //设置屏幕中心点
        aMap.setOnMapLoadedListener(this);
        aMap.setOnMarkerClickListener(this);
        aMap.setOnMapTouchListener(this);
        //初始化线路规划
        routeSearch = new RouteSearch(getActivity());
        routeSearch.setRouteSearchListener(this);
        //对地图点击事件
        aMap.setOnMapClickListener(this);
        //逆地理编码
        geocoderSearch = new GeocodeSearch(getActivity());
        geocoderSearch.setOnGeocodeSearchListener(this);
    }


    private void initLocatioin() {
        Log.e("aaa", " initLocatioi");
        client = new AMapLocationClient(getActivity().getApplicationContext());
        option = new AMapLocationClientOption();
        //option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        //高精度定位，可选
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //    option.setOnceLocation(false);
        option.setInterval(5000);
        // option.setNeedAddress(true);

        client.setLocationOption(option);
        client.setLocationListener(this);
        client.startLocation();


    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        mapView.onSaveInstanceState(bundle);
    }

    boolean useMoveToLocationWithMapMode = true;

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
        //新人注册大礼包
        if (App.getInstance().getCardTipBean() != null && App.getInstance().getCardTipBean().getResult().getYouhui() != null) {
            showNewCardTipDialog();
            App.getInstance().setCardTipBean(null);
        }

        //检索后的定位

        if (App.getInstance().getLatitude() != null && App.getInstance().getLatitude() != null) {
            // Log.i("ll","f"+App.getInstance().getLatitude());
            //  Log.i("ll","f"+App.getInstance().getLongitude());
            String lon = App.getInstance().getLongitude();
            String lat = App.getInstance().getLatitude();
            Double aDouble = Double.valueOf(lon);
            Double aDouble1 = Double.valueOf(lat);//为了搜索定位时“绿图标”位置正好
            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(aDouble1, aDouble), 16));
            App.getInstance().setLatitude(null);
            App.getInstance().setLongitude(null);
        } else {
//            if (client!=null){
//                client.startLocation();
//            }
//           // Log.e("ll","free+onResume");
//            //  aMap.moveCamera(CameraUpdateFactory.zoomTo(16));
//              isFirstLoc=true; //解决锁屏后打开自动定位
//            if (locationChangeListener!=null){
//               // Log.e("ll","free+locationChangeListener");
//                activate(locationChangeListener);
//            }
        }
        //重新定位
        isFirstLoc = true;
        useMoveToLocationWithMapMode = true;

//        点击先不购买，按此骑行后的扫码
        if (TextUtils.equals("0", userLevel) && isScan) {
            isScan = false;
            toCaptureActivty();
        }
    }

    /**
     * 新人注册对话框
     */
    public void showNewCardTipDialog() {
        final Dialog dialogResult = DialogShowUtils.showNewCardTipsDialog(getActivity(), App.getInstance().getCardTipBean().getResult().getYouhui());
        ((TextView) dialogResult.findViewById(R.id.card_login_success)).setText(Html.fromHtml("注册成功，送您<font color='#FFD733'> 新人大礼包</font>"));
        ((TextView) dialogResult.findViewById(R.id.card_account)).setText("已放入您的账户, " + UtilSharedPreference.getStringValue(getActivity(), Config.PHONE));
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
                Intent lookcard = new Intent(App.getInstance().getApplicationContext(), YouzanActivity.class);
                //  Bundle b=new Bundle();
                //  b.putString("url","https://h5.youzan.com/v2/showcase/homepage?alias=hnh0xir0");
                //   b.putString("route","用户协议");
                //  b.putInt("flag",3);
                //  lookcard.putExtras(b);
                startActivity(lookcard);
                if (dialogResult.isShowing()) {
                    dialogResult.dismiss();
                }
            }
        });
        if (!dialogResult.isShowing()) {
            dialogResult.show();
            dialogResult.setCancelable(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
        isFirstLoc = true;
        Log.e("aaa", "free+OnPause");
//        if (client!=null){
//            client.stopLocation();
//        }
        //暂停定位
        // if (App.getInstance().getaMapLocation()!=null){
        //  deactivate();
        //  }

        isFirstLoc = false;
        useMoveToLocationWithMapMode = false;


        //直接把启动页关闭
        if (dialog != null) {
            dialog.cancel();
        }
        App.getInstance().setLatitude(null);
        App.getInstance().setLongitude(null);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapView.onDestroy();
        locationChangeListener = null;
        Log.e("aaa", "free+onDestroyView");

        if (client != null) {
            client.stopLocation();

            client = null;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("aaa", "free+OnDestroy");
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        //volley的取消
        VolleyController.getInstance(getActivity()).getRequestQueue().cancelAll("action");
        VolleyController.getInstance(getActivity()).getRequestQueue().cancelAll("redpacket");
        VolleyController.getInstance(getActivity()).getRequestQueue().cancelAll("plie");
    }


    //激活定位
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        locationChangeListener = onLocationChangedListener;
        Log.e("aaa", "activate");
        if (client == null) {
            initLocatioin();
        }

    }

    //停止定位
    @Override
    public void deactivate() {
        locationChangeListener = null;
        if (client != null) {
            client.stopLocation();
            client.onDestroy();
            client = null;
        }


    }
    //创建一个Marker

    private MarkerOptions createMarker(double lat, double lon, int image) {
        MarkerOptions op = new MarkerOptions();

        op.position(new LatLng(lat, lon));
        // op.title("当前位置");
        op.visible(true);
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(image);
        op.icon(bitmapDescriptor);

        return op;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        Log.e("aaa", "changed" + aMapLocation.getErrorCode());
        if (aMapLocation != null) {
            city = aMapLocation.getCity();
            Log.e("TAG","城市是："+aMapLocation.getCity());
            if (aMapLocation.getErrorCode() == 0) {
                if (isFirstLoc) {
//                    if (markerlocation!=null){
//                        markerlocation.remove();
//                    }

                    App.getInstance().setaMapLocation(aMapLocation);
//                    aMapLocation.getLocationType();
//                    aMapLocation.getLatitude();
//                    aMapLocation.getLongitude();aMapLocation.getAccuracy();
                    //利用回调监听监听把定位城市传递到MainActivity
                    // cityNameInterface.setCityName(aMapLocation.getCity()) ;
                    //  mMyLocationPoint=aMapLocation;
                    cityName = aMapLocation.getCity();
                    myNowPoint = new LatLonPoint(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                    locationChangeListener.onLocationChanged(aMapLocation);

                    // aMap.clear();
                    Log.e("ccc", aMapLocation.getLatitude() + "");
                    Log.e("ccc", aMapLocation.getLongitude() + "");
                    Log.e("ccc", aMapLocation.getCity() + "");

                    double lat = aMapLocation.getLatitude();
                    double lon = aMapLocation.getLongitude();

//                    LatLng location = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
//                    addMarker(location);//添加定位图标
//                    mSensorHelper.setCurrentMarker(mLocMarker);//定位图标旋转

                    if (isFirstCenter) {
                        //我的位置
                        if (markercenter == null) {
                            addMarkersInScreenCenter();
                            Log.e("aaa", "markercenter为空");
                        } else {
                            Log.e("aaa", "markercenter不为空");
                        }
//                        aMap.moveCamera(CameraUpdateFactory.zoomTo(15));
//                        aMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lat,lon)));
                        aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 15));

                        initVolley();
                        isFirstCenter = false;
                    }
                    //定位图片在下面
                    // MarkerOptions op=createMarker(lat,lon,R.mipmap.mylocation);
                    // markerlocation = aMap.addMarker(op);

                    //  isFirstLoc=false;
                }


            }
        }
    }


    //得到刷新t图片
    public View getRefresh_Iamg() {
        return image_refresh;
    }

    public void refre(View v) {
        if (TextUtils.isEmpty(UtilSharedPreference.getStringValue(getActivity(), Config.PHONE))) {
            ToastUtils.showMessage("请先登录");
//            IntentUtils.startActivity(getActivity(), LoginActivity.class);
//                    Learning add
            if (shanYanLoginUtil == null) {
                shanYanLoginUtil = new ShanYanLoginUtil(getActivity());
            }
            shanYanLoginUtil.shanYanLogin();
            return;
        }
        RotateAnimation rotateAnimation = AnimUtils.getRotateAnimation();
        image_refresh.startAnimation(rotateAnimation);

        //刷新人员信息
        VolleyUtils.GetPerson(v);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onTouch(MotionEvent motionEvent) {
        Log.i("amap", "onTouch 关闭地图和小蓝点一起移动的模式");
        useMoveToLocationWithMapMode = false;
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_UP:
//                if (TextUtils.isEmpty(UtilSharedPreference.getStringValue(getActivity(), Config.PHONE))) {
//                    return;
//                }
//
//                if (walkRouteOverlay!=null&&walkRouteOverlay.allPolyLines.size()>0){
//                    return;
//                }
//
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        bikeAndRedPoint();
//
//
//                    }
//                }, 3000);
                break;
        }
    }


    /**
     * 定位图标缓慢移动
     */
    public void refreshMarker() {
        if (aMap != null && App.getInstance().getaMapLocation() != null) {
            // double latitude = markerlocation.getOptions().getPosition().latitude;
            // double longitude = markerlocation.getOptions().getPosition().longitude;
            //  aMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(latitude,longitude)));
            double longitude = App.getInstance().getaMapLocation().getLongitude();
            double latitude = App.getInstance().getaMapLocation().getLatitude();
            aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 16));


            //刷新个人信息
            image_refresh.setVisibility(View.VISIBLE);
            refre(image_refresh);


        } else {
            //  Toast.makeText(getActivity(),"网络阻塞",Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 对话框的显示
     *
     * @param style      样式
     * @param layout     布局
     * @param gravity    位置
     * @param width      宽度
     * @param percentage 经度
     * @param y          距XX的距离
     * @param flag       标记
     */
    public void show(int style, int layout, int gravity, int width, double percentage, int y, int flag, String outUrl) {
        dialog = new Dialog(getActivity(), style);
        if (flag == 1) {
            inflate = LayoutInflater.from(getActivity()).inflate(layout, null);
            upload_fault = (TextView) inflate.findViewById(R.id.id_Upload_fault);
            user_guide = (TextView) inflate.findViewById(R.id.id_User_guide);
            dialog_cancel = (TextView) inflate.findViewById(R.id.id_dialog_cancel);
            //微信在线客服
            id_weixin_guide = (TextView) inflate.findViewById(R.id.id_weixin_guide);
            id_weixin_guide.setOnClickListener(this);
            upload_fault.setOnClickListener(this);
            user_guide.setOnClickListener(this);
            dialog_cancel.setOnClickListener(this);
        } else if (flag == 2) {
            inflate = LayoutInflater.from(getActivity()).inflate(layout, null);
            popup = (ImageView) inflate.findViewById(R.id.id_popup);
            // to_h5 = (Button)inflate.findViewById(R.id.id_to_h5);
            //to_h5.setOnClickListener(this);
            popup.setOnClickListener(this);
            //设置图片
            if (popup != null && !"".equals(outUrl)) {
                //Picasso.with(getActivity()).invalidate(Uri.parse(outUrl));
                Log.e("kkk", "outUrl" + outUrl);
                Picasso.with(getActivity()).load(outUrl).into(popup);


            }
        }

        dialog.setContentView(inflate);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(gravity);

        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        lp.y=y;
        lp.width = width;
        //  if (percentage!=0)
        // lp.height=(int)(BaseUtil.getHeighthMetrics(getActivity())*percentage);
//        if (height!=0){
//            lp.height=height;
//        }


        dialogWindow.setAttributes(lp);
        dialog.show();
    }

    private void showPopupWindow(View v) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.popwindow_layout, null);
        pop = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,
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
        pop.showAsDropDown(v);

    }

    /**
     * 跳到扫描界面
     */
    private void toCaptureActivty() {
        final Dialog dialog = DialogShowUtils.showOpenDialog(getActivity());
        Button opp = (Button) dialog.findViewById(R.id.id_guide_button);
        opp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null) dialog.dismiss();
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                getActivity().startActivityForResult(intent, 111);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_image_center:  //移动绿图标到定位点
                refreshMarker();
                // refre();

                break;
            case R.id.id_image_refresh:
//                if (!BaseUtil.isNetworkAvailable(getActivity())){
//
//                       animation_360 = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_360);
//                        animation_360.setInterpolator(new LinearInterpolator());
//                        animation_360.setRepeatCount(20);
//                        animation_360.setDuration(5000);
//
//                        image_refresh.startAnimation(animation_360);

//                }else{
//
//                        animation_45=null;
//                        animation_45 = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_45);
//                        animation_45.setInterpolator(new LinearInterpolator());
//                        animation_45.setRepeatCount(1);
//                        animation_45.setDuration(500);
//
//                        image_refresh.startAnimation(animation_45);
//                }

                //  refreshMarker();

                //   refre(v);

                break;
            case R.id.id_photo_choose:
                //  show();

                if (TextUtils.isEmpty(UtilSharedPreference.getStringValue(getActivity(), Config.PHONE))) {
//                    IntentUtils.startActivity(getActivity(), LoginActivity.class);

//                    Learning add
                    if (shanYanLoginUtil == null) {
                        shanYanLoginUtil = new ShanYanLoginUtil(getActivity());
                    }
                    shanYanLoginUtil.shanYanLogin();
                    return;
                }
                show(R.style.ActionSheetDialogStyle, R.layout.photodialog, Gravity.BOTTOM, BaseUtil.getWidthMetrics(getActivity()) * 2 - 60, 0, 25, 1, null);
                // Intent intent1=new Intent(getActivity(), FaultUploadActivity.class);
                // startActivity(intent1);
                break;
            case R.id.id_scan_zxing: //扫描
                if (TextUtils.isEmpty(UtilSharedPreference.getStringValue(getActivity(), Config.PHONE))) {
                    if (shanYanLoginUtil == null) {
                        shanYanLoginUtil = new ShanYanLoginUtil(getActivity());
                    }
                    shanYanLoginUtil.shanYanLogin();
                    return;
                }
                if (App.getInstance().getUserEntityBean() != null) {
//                余额
                    double userMoney = Double.parseDouble(App.getInstance().getUserEntityBean().getUserMoney());
//                余额必须大于或等于29
                    float openmoney = App.getInstance().getUserEntityBean().getOpenmoney();
                    float newopenmoney = App.getInstance().getUserEntityBean().getNewopenmoney();
                    float warningmoney = App.getInstance().getUserEntityBean().getWarningmoney();
                    userLevel = App.getInstance().getUserEntityBean().getUserLevel();
                    String isbuyridingcard=App.getInstance().getUserEntityBean().getIsbuyridingcard();
//                    Log.e("TAG","余额是："+userMoney);
//                    Log.e("TAG","openmoney是："+openmoney);

                    try {
                        if (TextUtils.isEmpty(UtilSharedPreference.getStringValue(getActivity(), Config.PHONE))) {
//                            IntentUtils.startActivity(getActivity(), LoginActivity.class);

//                    Learning add
                            if (shanYanLoginUtil == null) {
                                shanYanLoginUtil = new ShanYanLoginUtil(getActivity());
                            }
                            shanYanLoginUtil.shanYanLogin();
                        } else if (App.getInstance().getUserEntityBean() != null) {
                            if ("1".equals(App.getInstance().getUserEntityBean().getUserStatus())) {
                                //有订单，返回
                                Log.e("kkk", "有订单");
                                ToastUtils.showMessage("已有订单");
                                return;
                            }
//                        冲保证金注释
                            if (Double.parseDouble(App.getInstance().getUserEntityBean().getDeposit()) <= 0) {
                                Log.e("kkk", "未付");
                                Intent toMoney = new Intent(getActivity(), RechargeActivity.class);
                                //充保证金
                                toMoney.putExtra("flag", 0);
                                toMoney.putExtra("city",city);
                                Log.e("TAG","传递的城市是："+city);
                                startActivity(toMoney);
//                        }else if (TextUtils.equals("0",App.getInstance().getUserEntityBean().getUserLevel())&&TextUtils.equals("0.00",App.getInstance().getUserEntityBean().getUserBonus())) {
//                            if (Double.parseDouble(App.getInstance().getUserEntityBean().getUserMoney()) <= App.getInstance().getUserEntityBean().getOpenmoney()) {
//                                Intent intent = new Intent(getActivity(), RechargeActivity.class);
//                                intent.putExtra("flag", 1);
//                                startActivity(intent);
//                            } else {
//                                toCaptureActivty();
//                            }

                            } else {
                                if (userMoney <= openmoney) {
                                    Intent intent = new Intent(getActivity(), RechargeActivity.class);
                                    intent.putExtra("flag", 1);
                                    startActivity(intent);
                                }else {
//                                    0：非会员，1：会员
                                    if (TextUtils.equals("0",userLevel)){
//                                        if (TextUtils.equals("0",isbuyridingcard)){
//                                            toCaptureActivty();
//                                        }else {
                                        Intent intent = new Intent(getActivity(), VipComboActivity.class);
                                        startActivity(intent);
//                                        }
                                    }else {
                                        toCaptureActivty();
                                    }
                                }
                            }
//                            if (userMoney == 0.00) {
////                            新用戶余额充值
//                                Intent intent = new Intent(getActivity(), BalanceAddActivity.class);
//                                intent.putExtra("newopenmoney",newopenmoney);
//                                startActivity(intent);
//                            } else if (userMoney < warningmoney) {
////                            不足20元的余额充值
//                                Intent intent = new Intent(getActivity(), ExitBalanceAddActivity.class);
//                                intent.putExtra("newopenmoney", newopenmoney);
//                                intent.putExtra("userMoney", userMoney);
//                                startActivity(intent);
//                            } else {
////                            Log.e("TAG","是否是扫码："+isScan);
//                                if (TextUtils.equals("1", userLevel)) {
//                                    toCaptureActivty();
//                                } else {
//                                    Intent intent = new Intent(getActivity(), VipComboActivity.class);
//                                    startActivity(intent);
//                                }
//                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                break;
            case R.id.id_Upload_fault: //故障上传
                dialog.cancel();
                Intent intent = new Intent(getActivity(), FaultUploadActivity.class);
                startActivity(intent);

                break;
            case R.id.id_dialog_cancel:

                dialog.cancel();
                break;
            case R.id.id_User_guide:
                Intent user = new Intent(getActivity(), UserActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url", Config.H5_URL + Config.USER_HELP);
                bundle.putString("route", "用户指南");
                bundle.putInt("flag", 3);
                user.putExtras(bundle);
                startActivity(user);
                break;
            case R.id.id_weixin_guide://微信客服
//                if (!getActivity().isFinishing()){//展示微信
//                    DialogShowUtils.showWeiXinDialog(getActivity(),R.layout.main_weixin_dialog,topActivitybean.getResult().get(0).getWeixinUrl());
//                }
//                if (dialog.isShowing()) {
//                    dialog.dismiss();
//                }
                ((MainActivity) getActivity()).requestPermission(new String[]{Manifest.permission.CALL_PHONE}, 120);
//                intent = new Intent(Intent.ACTION_DIAL);
//                Uri data = Uri.parse("tel:" + "4006789819");
//                intent.setData(data);
//                startActivity(intent);
                break;
            case R.id.id_popup: //点击启动页对话框
                //Toast.makeText(getActivity(),"点击了按钮",Toast.LENGTH_SHORT).show();
//                Intent web=new Intent(getActivity(), ActionActivity.class);
//                startActivity(web);

                //对话框消失
                if (dialog != null)
                    dialog.cancel();
                break;
            case R.id.id_action_list:
                if (TextUtils.isEmpty(UtilSharedPreference.getStringValue(getActivity(), Config.PHONE))) {
//                    IntentUtils.startActivity(getActivity(), LoginActivity.class);

//                    Learning add
                    if (shanYanLoginUtil == null) {
                        shanYanLoginUtil = new ShanYanLoginUtil(getActivity());
                    }
                    shanYanLoginUtil.shanYanLogin();
                } else {
//                    Intent action_list=new Intent(getActivity(), ActionListActivity.class);//跳转我的消息也页面
//                    startActivity(action_list);

                    if (!getActivity().isFinishing()) {//展示微信
                        DialogShowUtils.showWeiXinDialog(getActivity(), R.layout.main_weixin_dialog, topActivitybean.getResult().get(0).getWeixinUrl());
                    }
                }

//                makeLine();//测试

                break;

            case R.id.id_action_a2:
                // a1.setVisibility(View.GONE);
                // a2.setVisibility(View.VISIBLE);
                //ObjectAnimator.ofFloat(a2,"translationX",start,to).setDuration(500).start();
                //  isexpand=true;
                if (TextUtils.isEmpty(UtilSharedPreference.getStringValue(getActivity(), Config.PHONE))) {
//                    IntentUtils.startActivity(getActivity(), LoginActivity.class);

//                    Learning add
                    if (shanYanLoginUtil == null) {
                        shanYanLoginUtil = new ShanYanLoginUtil(getActivity());
                    }
                    shanYanLoginUtil.shanYanLogin();
                } else {
                    if (topActivitybean != null) {
                        Intent actionintent = new Intent(getActivity(), ActionListDetailActivity.class);
                        actionintent.putExtra("jump", topActivitybean.getResult().get(0).getJumpUrl());
                        actionintent.putExtra("id", topActivitybean.getResult().get(0).getId());
                        actionintent.putExtra("title", topActivitybean.getResult().get(0).getActivityName());
                        startActivity(actionintent);
                    }

                }


                break;
            case R.id.id_action_a1:
                if (isexpand) {
                    ObjectAnimator.ofFloat(a1, "translationX", -dis, 0).setDuration(500).start();
                    ObjectAnimator.ofFloat(a2, "translationX", 0, dis).setDuration(500).start();
                    isexpand = false;
                } else {
                    a2.setVisibility(View.VISIBLE);
                    ObjectAnimator.ofFloat(a1, "translationX", 0, -dis).setDuration(500).start();
                    ObjectAnimator.ofFloat(a2, "translationX", dis, 0).setDuration(500).start();
                    isexpand = true;
                }
                //  Log.e("mm","a2222222");
                break;
        }
    }

    private boolean isexpand = false;

    //地图加载点
    @Override
    public void onMapLoaded() {
        // addMarkersInScreenCenter();
    }

    /*
     *设置屏幕中心点
     *  markerlocation 定位的点
     */
    private void addMarkersInScreenCenter() {
        // Log.e("uuu",BaseUtil.getWidthMetrics(getActivity())+"");
        //  Log.e("uuu",BaseUtil.getHeighthMetrics(getActivity())+"");
        Log.e("aaa", "onMapLoaded");

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LatLng target = aMap.getCameraPosition().target;
                //    LatLng position = markerlocation.getOptions().getPosition();
                // double latitude = App.getInstance().getaMapLocation().getLatitude();
                //double longitude = App.getInstance().getaMapLocation().getLongitude();
                // LatLng latLng=new LatLng(latitude,longitude);

                Point point = aMap.getProjection().toScreenLocation(target);
                // Log.e("aaa",target.latitude+"       "+position.latitude+ "   "+latitude);
                // Log.e("aaa",target.longitude+"     "+position.longitude+"   "+longitude);
                Log.e("aaa", point.x + "");
                Log.e("aaa", point.y + "");
                if (point.x <= 0 || point.y <= 0) {
                    point = aMap.getProjection().toScreenLocation(target);
                }

                Log.e("aaa", point.x + "");
                Log.e("aaa", point.y + "");
                markercenter = aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.end))
                );

                markercenter.setPositionByPixels(point.x, point.y - BaseUtil.dp2px(getActivity(), 18)); //37

            }
        }, 500);

    }


    /**
     * 点击地图上点的回调
     *
     * @param marker
     * @return
     */
    @Override
    public boolean onMarkerClick(Marker marker) {
        // Toast.makeText(getActivity(),marker.getPosition().latitude+""+marker.getPosition().longitude,Toast.LENGTH_SHORT).show();
        Log.e("zz", marker.getPosition().latitude + "纬度" + marker.getPosition().longitude);
        Log.e("zz", marker.getObject() + "");
        if (walkRouteOverlay != null) {
            walkRouteOverlay.removeFromMap();
        }

        if (m == null) {
            m = new MarkerWindow(); //保存点击“停车点”的信息
        }

        //线路规划
        searchRouteResult(ROUTE_TYPE_WALK, RouteSearch.RIDING_DEFAULT, marker);//WALK_DEFAULT
//        //逆地理编码
//        if (marker!=null){
//            getAddress(new LatLonPoint(marker.getPosition().latitude,marker.getPosition().longitude));
//        }


        // handler.sendEmptyMessageDelayed((int)marker.getObject(),200);
        Message message = new Message();
        message.obj = marker;
        message.what = (int) marker.getObject();

        handler.sendMessageDelayed(message, 1000);


        return true;
    }

    /**
     * 开始线路规划
     */

    LatLng latLng;
    Point point;
    int i = 100;

    private void searchRouteResult(int routeType, int mode, Marker marker) {
        Log.e("zz", "searchRouteResult");
        //起点，不应该在这写着
        if (markercenter != null) {
            lat_center = markercenter.getPosition().latitude;
            lon_center = markercenter.getPosition().longitude;
            //经纬度转成 屏幕 减去图片的高度 在转化成经纬度
            point = BaseUtil.toScreenLocation(lat_center, lon_center, aMap);
            latLng = BaseUtil.toGeoLocation(point.x, point.y, aMap);


        } else {
            LatLng ll = WalkRouteOverlay.ll;
            lat_center = ll.latitude;
            lon_center = ll.longitude;
        }
        Log.e("ttt", lat_center + "   " + latlng.latitude);
        Log.e("ttt", lon_center + "    " + latlng.longitude);
        Log.e("ttt", point.x + "   " + point.y);
        //起点
//        LatLonPoint  mStartPoint  = new LatLonPoint(lat_center,lon_center);//原起点
        LatLonPoint mStartPoint = myNowPoint;
        //终点
        LatLonPoint mEndPoint = new LatLonPoint(marker.getPosition().latitude, marker.getPosition().longitude);
        if (mStartPoint == null) {
            Toast.makeText(getActivity(), "起点不存在", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mEndPoint == null) {
            Toast.makeText(getActivity(), "终点不存在", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mStartPoint.equals(mEndPoint) || mStartPoint == mEndPoint) {
            Toast.makeText(getActivity(), "起点和中点相同", Toast.LENGTH_SHORT).show();
            return;
        }

        //把屏幕中心的点删除掉，再添加到地图上并且随地图移动

        if (markercenter != null) {
            markercenter.remove();
            markercenter = null;
            // markerOptions = createMarker(lat_center, lon_center, R.mipmap.end);

            //   markercenter=aMap.addMarker(markerOptions);
        }


        final RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(
                mStartPoint, mEndPoint);
        if (routeType == ROUTE_TYPE_WALK) {// 步行路径规划
            RouteSearch.WalkRouteQuery query = new RouteSearch.WalkRouteQuery(fromAndTo, mode);
            routeSearch.calculateWalkRouteAsyn(query);// 异步路径规划步行模式查询

        }

        try {
            Thread.sleep(200);
            //对话框的展示 逆地理编码
            getAddress(mEndPoint);
            suoFangMap(mStartPoint, mEndPoint);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    /**
     * 响应逆地理编码
     */
    public void getAddress(final LatLonPoint latLonPoint) {

        RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 200,
                GeocodeSearch.AMAP);// 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
        geocoderSearch.getFromLocationAsyn(query);// 设置异步逆地理编码请求

        Log.e("zz", "getAddress");
    }

    //下面四个方法是路径规划监听调用的
    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {

    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult result, int errorCode) {
        //  aMap.clear();// 清理地图上的所有覆盖物
        Log.e("zz", "onWalkRouteSearched");
        //清除地图上的线路
        if (walkRouteOverlay != null && walkRouteOverlay.allPolyLines.size() > 0) {
            List<Polyline> allPolyLines = walkRouteOverlay.allPolyLines;
            for (Polyline p : allPolyLines) {
                p.remove();
            }
            walkRouteOverlay = null;
        }
        if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getPaths() != null) {
                if (result.getPaths().size() > 0) {
                    mWalkRouteResult = result;
                    final WalkPath walkPath = mWalkRouteResult.getPaths()
                            .get(0);
                    walkRouteOverlay = new WalkRouteOverlay(
                            getActivity(), aMap, walkPath,
                            mWalkRouteResult.getStartPos(),
                            mWalkRouteResult.getTargetPos());
                    walkRouteOverlay.removeFromMap();
                    walkRouteOverlay.addToMap();
                    walkRouteOverlay.zoomToSpan();
                    //保存路径规划的信息（时间，距离）
                    m.setTime(AMapUtil.getFriendlyTime((int) walkPath.getDuration()));
                    m.setDistance(AMapUtil.getFriendlyLength((int) walkPath.getDistance()));
                    Log.e("zz", "time" + m.getTime());
                    Log.e("zz", "dis" + m.getDistance());
                    Toast.makeText(getActivity(), "距离" + m.getDistance() + "，需骑行" + m.getTime(), Toast.LENGTH_LONG).show();
                } else if (result != null && result.getPaths() == null) {
                    Toast.makeText(getActivity(), "没有结果", Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(getActivity(), "没有结果", Toast.LENGTH_SHORT).show();
            }
        } else {

            Toast.makeText(getActivity(), "线路规划失败", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

    }

    /**
     * 如果点击地图上marker以外的区域回调（删除图层）
     *
     * @param latLng
     */
    @Override
    public void onMapClick(LatLng latLng) {

        cancelWalkRouteOverlay();

    }

    /**
     * 删除图层
     */
    public void cancelWalkRouteOverlay() {
        Log.e("zz", "onMapClick");
        if (walkRouteOverlay != null && walkRouteOverlay.allPolyLines.size() > 0) {
            List<Polyline> allPolyLines = walkRouteOverlay.allPolyLines;
            for (Polyline polyline : allPolyLines) {
                polyline.remove();
            }
            walkRouteOverlay.removeFromMap(); //去除walkRouteOverlay上所有的点
            //把起始点设置在屏幕中心点
            if (markercenter != null) {
                markercenter.remove();
                markercenter = null;
                addMarkersInScreenCenter();

            } else {
                addMarkersInScreenCenter();
            }

            //对话框消失
            marker_popup.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.marker_popoup_out));
            marker_popup.setVisibility(View.GONE);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //先隐藏radioGroup
            if (MainActivity.getInstacne().getHalf() != null && MainActivity.getInstacne().getRadioGroup() != null) {
                MainActivity.getInstacne().getHalf().setVisibility(View.VISIBLE);
                MainActivity.getInstacne().getRadioGroup().setVisibility(View.VISIBLE);
                MainActivity.getInstacne().getImageView().setVisibility(View.VISIBLE);
            }

            walkRouteOverlay = null;
        }
    }

    /**
     * 逆地理编码(下面两个方法)
     */
    @Override
    public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
        Log.e("zz", "onRegeocodeSearched");
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getRegeocodeAddress() != null && result.getRegeocodeAddress().getFormatAddress() != null) {
                addressName = result.getRegeocodeAddress().getFormatAddress();
                //aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(AMapUtil.convertToLatLng(latLonPoint), 15));
                Log.e("zz", "地址" + addressName);


                //设置对话框
                m.setAddress(addressName);
                //  setMarkerWindow();

            } else {

                Toast.makeText(getActivity(), "没有点", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "线路规划失败", Toast.LENGTH_SHORT).show();

        }
    }

    /**
     * 展示停车点的信息
     */
    private void setMarkerWindow(int flag) {
        Log.e("zz", "setMarkerWindow");
        if (App.getInstance().getUserEntityBean() != null && TextUtils.equals(App.getInstance().getUserEntityBean().getUserStatus(), "1")) {
            return;
        }
        //先隐藏radioGroup
        if (MainActivity.getInstacne().getHalf() != null && MainActivity.getInstacne().getRadioGroup() != null) {
            MainActivity.getInstacne().getHalf().setVisibility(View.GONE);
            MainActivity.getInstacne().getRadioGroup().setVisibility(View.GONE);
            MainActivity.getInstacne().getImageView().setVisibility(View.GONE);
        }
        if (marker_popup != null && (marker_popup.getVisibility() == View.GONE || marker_popup.getVisibility() == View.INVISIBLE)) {
            marker_popup.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.marker_popoup_in));
            top_photo.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.marker_popoup_out));
            //   top_photo.setVisibility(View.GONE);
            if (m != null) {
                if (flag == REDBIKE) {
                    // station_name.setVisibility(View.GONE);
                    station_name.setText("编号：" + m.getNumber());
                } else if (flag == PILE) {
                    station_name.setVisibility(View.VISIBLE);
                    station_name.setText("站点：" + m.getSite());
                }
                marker_address.setText("地址：" + m.getAddress());
                marker_distance.setText(m.getDistance());
                marker_time.setText(m.getTime());
            }

            marker_popup.setVisibility(View.VISIBLE);
        } else if (marker_popup != null && marker_popup.getVisibility() == View.VISIBLE) {
            if (m != null) {
                if (flag == REDBIKE) {
                    // station_name.setVisibility(View.GONE);
                    station_name.setText("编号：" + m.getNumber());
                } else if (flag == PILE) {
                    // station_name.setVisibility(View.VISIBLE);
                    station_name.setText("站点：" + m.getSite());
                }
                marker_address.setText("地址：" + m.getAddress());
                marker_distance.setText(m.getDistance());
                marker_time.setText(m.getTime());

            }
        }

    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }

//  //得到定位城市的回调监听
//    public void  cityNameInterface(CityNameInterface  cityNameInterface){
//        this.cityNameInterface=cityNameInterface;
//    }
//   public interface CityNameInterface{
//
//       public void setCityName(String cityName);
//    }


    private void openImage() {
        if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
            VolleyUtils.doGet(getActivity(), Url.OPENIMAGE + cityName, "action", new VolleyUtils.volleyListener() {
                @Override
                public void onResponse(JSONObject response) {
                    // Toast.makeText(getActivity(),"re",Toast.LENGTH_SHORT).show();
//                Log.e("dddd",response.toString());
//                Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
//                OpenImageBean openImageBean = VolleyUtils.parseJsonWithGson(response.toString(), OpenImageBean.class);

//                DialogShowUtils.showTipsDialog(getActivity(),R.layout.cue_dialog,openImageBean.getResult().getactivityUrl());

                }

                @Override
                public void onErrorResponse(String errorMessage) {

                }

                @Override
                public void onResponse(String response) {

                }
            });
        }
    }

    public void makeLine() {
        int routeType = ROUTE_TYPE_WALK;
        int mode = RouteSearch.RIDING_DEFAULT;
        LatLonPoint mStartPoint = myNowPoint;
        double oldMi = 100000000;
        int remNum = 0;
        if (pilebean != null && pilebean.getResult().size() > 0) {
            for (int i = 0; i < pilebean.getResult().size(); i++) {
                List<String> lnglat = pilebean.getResult().get(i).getLnglat();
                LatLng firstpoint = new LatLng(Double.parseDouble(lnglat.get(1)), Double.parseDouble(lnglat.get(0)));
                LatLng nowpoint = AMapUtil.convertToLatLng(mStartPoint);
                double mi = getDistance(nowpoint, firstpoint);
                if (mi <= oldMi) {
                    oldMi = mi;
                    remNum = i;
                }

            }
            Log.i("fei", "======>" + remNum + "|||||>" + pilebean.getResult().get(remNum).getName());
            LatLonPoint mEndPoint = new LatLonPoint(Double.parseDouble(pilebean.getResult().get(remNum).getLnglat().get(1)), Double.parseDouble(pilebean.getResult().get(remNum).getLnglat().get(0)));

            final RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(
                    mStartPoint, mEndPoint);
            if (routeType == ROUTE_TYPE_WALK) {// 步行路径规划
                RouteSearch.WalkRouteQuery query = new RouteSearch.WalkRouteQuery(fromAndTo, mode);
                routeSearch.calculateWalkRouteAsyn(query);// 异步路径规划步行模式查询

            }

            try {
                Thread.sleep(200);
                //对话框的展示 逆地理编码
                getAddress(mEndPoint);
                suoFangMap(mStartPoint, mEndPoint);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //缩放地图
    private void suoFangMap(final LatLonPoint mStartPoint, final LatLonPoint mEndPoint) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LatLng startLatLng = AMapUtil.convertToLatLng(mStartPoint);
                LatLng endLatLng = AMapUtil.convertToLatLng(mEndPoint);
                //设置中心点和缩放比例la lon
//                LatLng centerLatLng=new LatLng((startLatLng.latitude+endLatLng.latitude)/2,(startLatLng.longitude+endLatLng.longitude)/2);
                if (startLatLng.longitude >= endLatLng.longitude) {
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(startLatLng));
                } else {
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(endLatLng));
                }
                aMap.moveCamera(CameraUpdateFactory.zoomTo(13));
                AMapUtils.calculateLineDistance(startLatLng, endLatLng);
            }
        }, 800);
    }

    public double getDistance(LatLng start, LatLng end) {

        double lon1 = (Math.PI / 180) * start.longitude;
        double lon2 = (Math.PI / 180) * end.longitude;
        double lat1 = (Math.PI / 180) * start.latitude;
        double lat2 = (Math.PI / 180) * end.latitude;

        // 地球半径
        double R = 6371;

        // 两点间距离 km，如果想要米的话，结果*1000就可以了
        double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1))
                * R;

        return d * 1000;
    }


    //初始化陀螺仪传感器，注册回调函数
    private SensorManager mSM;
    private Sensor mSensor;

    private void initTuoLuo() {
        mSM = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSM.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        mSM.registerListener(new SensorEventListener() {
            //实现陀螺仪状态变化回调函数
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
                    float degree = event.values[0];
                    float bearing = aMap.getCameraPosition().bearing;
                    if (degree + bearing > 360)
                        aMap.setMyLocationRotateAngle(degree + bearing - 360);// 设置小蓝点旋转角度
                    else
                        aMap.setMyLocationRotateAngle(degree + bearing);//
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        }, mSensor, SensorManager.SENSOR_DELAY_UI);//注册回调函数
    }


    public boolean hasPermission(String permission) {//权限
        try {
            PackageInfo info = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), PackageManager.GET_PERMISSIONS);
            if (info.requestedPermissions != null) {
                for (String p : info.requestedPermissions) {
                    if (p.equals(permission)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}



