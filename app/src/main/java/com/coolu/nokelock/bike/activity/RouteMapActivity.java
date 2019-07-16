package com.coolu.nokelock.bike.activity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.Text;
import com.amap.api.maps.model.TextOptions;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkRouteResult;
import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.base.BaseActivity;
import com.coolu.nokelock.bike.overlay.WalkRouteOverlay;
import com.coolu.nokelock.bike.util.AMapUtil;
import com.coolu.nokelock.bike.util.BaseUtil;


import java.util.List;

public class RouteMapActivity extends BaseActivity implements AMap.OnMapClickListener,
        AMap.OnMarkerClickListener, AMap.OnInfoWindowClickListener, AMap.InfoWindowAdapter, RouteSearch.OnRouteSearchListener,
        LocationSource,AMapLocationListener {

    private MapView mapView;
    private AMap aMap;
    //116.191885,39.913033
    private LatLonPoint mStartPoint = new LatLonPoint(39.914033, 116.181885);//起点，116.335891,39.942295
    private LatLonPoint mEndPoint = new LatLonPoint(39.95033, 116.171885);//终点，116.481288,39.995576
    private LatLonPoint mPoint = new LatLonPoint(39.95033, 116.161885);//终点，116.481288,39.995576

    private ProgressDialog progDialog = null;// 搜索时进度条
    private final int ROUTE_TYPE_WALK = 3;
    private WalkRouteResult mWalkRouteResult;
    private RouteSearch mRouteSearch;
    private WalkRouteOverlay  walkRouteOverlay;
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption = null;//定位参数
    private OnLocationChangedListener mListener = null;//定位监听器

    //标识，用于判断是否只显示一次定位信息和用户重新定位
    private boolean isFirstLoc = true;
    private int  walkFlag=0;

    private AMapLocation aMapLocation=null;



    private Handler handler=new Handler();
    private ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏文字颜色及图标为深色
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_route_map);

        back = (ImageView)findViewById(R.id.id_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mapView = (MapView)findViewById(R.id.id_mapView);
        mapView.onCreate(savedInstanceState);
        init();
        setfromandtoMarker();

        searchRouteResult(ROUTE_TYPE_WALK, RouteSearch.WalkDefault);
    }

    private void searchRouteResult(int routeType, int mode) {
        if (mStartPoint == null) {
            // ToastUtil.show(mContext, "定位中，稍后再试...");
            return;
        }
        showProgressDialog();
        if (mEndPoint == null) {
            // ToastUtil.show(mContext, "终点未设置");
        }

        final RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(
                mStartPoint, mEndPoint);
        final RouteSearch.FromAndTo fromAndTo2 = new RouteSearch.FromAndTo(
                mStartPoint, mPoint);
        if (routeType == ROUTE_TYPE_WALK) {// 步行路径规划
            RouteSearch.WalkRouteQuery query = new RouteSearch.WalkRouteQuery(fromAndTo, mode);
            mRouteSearch.calculateWalkRouteAsyn(query);// 异步路径规划步行模式查询
        }
        if (routeType==ROUTE_TYPE_WALK){
            RouteSearch.WalkRouteQuery query = new RouteSearch.WalkRouteQuery(fromAndTo2, mode);
            mRouteSearch.calculateWalkRouteAsyn(query);// 异步路径规划步行模式查询
        }
    }




    /**
     * 显示进度框
     */
    private void showProgressDialog() {
        if (progDialog == null)
            progDialog = new ProgressDialog(this);
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setIndeterminate(false);
        progDialog.setCancelable(true);
        progDialog.setMessage("正在搜索");
        progDialog.show();
        Log.e("mm","showProgressDialog");
    }

    /**
     * 隐藏进度框
     */
    private void dissmissProgressDialog() {
        if (progDialog != null) {
            progDialog.dismiss();
        }
    }

    private TextOptions addMyTextOption(String contont, LatLng pos) {

        TextOptions textOptions = new TextOptions()
                .position(pos)
                .text(contont)
                .backgroundColor(Color.TRANSPARENT)
                .fontSize(30)
                .align(Text.ALIGN_CENTER_HORIZONTAL, Text.ALIGN_CENTER_VERTICAL);
        return textOptions;
    }



    private void setfromandtoMarker() {
        Marker marker1 = aMap.addMarker(new MarkerOptions().position(AMapUtil.convertToLatLng(mStartPoint))
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.green)));
        //  marker1.setTitle("鸽子窝公园");
        //添加文字
        aMap.addText(addMyTextOption("鸽子窝公园",AMapUtil.convertToLatLng(mStartPoint)));

        aMap.addMarker(new MarkerOptions().position(AMapUtil.convertToLatLng(mEndPoint))
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.green)));
        //添加文字
        aMap.addText(addMyTextOption("大潮坪",AMapUtil.convertToLatLng(mEndPoint)));

        aMap.addMarker(new MarkerOptions().position(AMapUtil.convertToLatLng(mPoint))
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.green)));
        //添加文字
        aMap.addText(addMyTextOption("金乌浴场",AMapUtil.convertToLatLng(mPoint)));

//        MarkerOptions mStart = createMarker(mStartPoint.getLatitude(), mStartPoint.getLongitude(), R.mipmap.bike_icon);
//        aMap.addMarker(mStart);
//        MarkerOptions mEnd = createMarker(mEndPoint.getLatitude(), mEndPoint.getLongitude(), R.mipmap.bike_icon);
//        aMap.addMarker(mEnd);

    }

    //创建一个Marker

    private  MarkerOptions createMarker(double lat,double lon,int image){
        Log.e("mm","createMarker");
        MarkerOptions op=new MarkerOptions();

        op.position(new LatLng(lat,lon));
        // op.title("当前位置");
        op.visible(true);
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(image);
        op.icon(bitmapDescriptor);
        return op;
    }

    private void init() {
        //   showProgressDialog();
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        //注册监听
        mRouteSearch = new RouteSearch(this);
        mRouteSearch.setRouteSearchListener(this);
        aMap.setOnMapClickListener(RouteMapActivity.this);
        aMap.setOnMarkerClickListener(RouteMapActivity.this);
        aMap.setOnInfoWindowClickListener(RouteMapActivity.this);
        aMap.setInfoWindowAdapter(RouteMapActivity.this);

        //设置定位参数属性
        UiSettings uiSettings = aMap.getUiSettings();
        aMap.setLocationSource(this);
        //是否显示定位按钮
        // uiSettings.setMyLocationButtonEnabled(true);
        //是否可触发定位并显示定位层
        aMap.setMyLocationEnabled(true);
        aMap.getUiSettings().setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_RIGHT);// logo位置

        aMap.getUiSettings().setTiltGesturesEnabled(false);
        aMap.getUiSettings().setScaleControlsEnabled(false);
        aMap.getUiSettings().setZoomControlsEnabled(false);
        aMap.getUiSettings().setRotateGesturesEnabled(false);
        aMap.getUiSettings().setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_RIGHT);

        MyLocationStyle style=new MyLocationStyle();
        style.myLocationIcon(BitmapDescriptorFactory.fromResource(R.mipmap.mylocation));
        style.radiusFillColor(android.R.color.transparent);
        style.strokeColor(android.R.color.transparent);
        aMap.setMyLocationStyle(style);

        //开始定位
        initLoc();



    }

    private void initLoc() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());

        // 设置定位回调监听
        mLocationClient.setLocationListener(this);
        // 初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        // 设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        // 设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        // 设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        // 设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        // 设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        // 设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        // 给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        // 启动定位
        mLocationClient.startLocation();



    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {

    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult result, int errorCode) {





        // aMap.clear();// 清理地图上的所有覆盖物
//        if (walkRouteOverlay!=null){
//            List<Polyline> allPolyLines = walkRouteOverlay.allPolyLines;
//            for (Polyline p:allPolyLines){
//                p.remove();
//            }
//        }
        if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getPaths() != null) {
                walkFlag++;
                if (result.getPaths().size() > 0) {
                    mWalkRouteResult = result;
                    final WalkPath walkPath = mWalkRouteResult.getPaths()
                            .get(0);
                    walkRouteOverlay = new WalkRouteOverlay(
                            RouteMapActivity.this, aMap, walkPath,
                            mWalkRouteResult.getStartPos(),
                            mWalkRouteResult.getTargetPos());
                    walkRouteOverlay.removeFromMap();
                    walkRouteOverlay.addToMap();
                    // walkRouteOverlay.zoomToSpan();


                } else if (result != null && result.getPaths() == null) {
                    Toast.makeText(RouteMapActivity.this,"没有结果",Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(RouteMapActivity.this,"没有结果",Toast.LENGTH_SHORT).show();
            }
        } else {

            Toast.makeText(RouteMapActivity.this,errorCode+"",Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

    }


    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
        //2表示线路规划画了两条线，回调监听调了2次
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                dissmissProgressDialog();

                if (aMap!=null){
                    LatLngBounds.Builder b=new LatLngBounds.Builder();
                    if (aMapLocation!=null)
                        b.include(new LatLng(aMapLocation.getLatitude(),aMapLocation.getLongitude()));
                    if (mStartPoint!=null){
                        b.include(AMapUtil.convertToLatLng(mStartPoint));
                    }
                    if (mEndPoint!=null){
                        b.include(AMapUtil.convertToLatLng(mEndPoint));
                    }
                    if (mPoint!=null){
                        b.include(AMapUtil.convertToLatLng(mPoint));
                    }

                    aMap.animateCamera(CameraUpdateFactory
                            .newLatLngBounds(b.build(), 50));
                }
            }
        },3000);


    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    /**
     * 定位的回调
     * @param aMapLocation
     */
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        // dissmissProgressDialog();

        if (aMapLocation!=null){
            if (aMapLocation.getErrorCode()==0){
                if (isFirstLoc){
                    this.aMapLocation=aMapLocation;
                    //设置缩放级别
                    // aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
                    //将地图移动到定位点
                    //aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(),aMapLocation.getLongitude())));
                    Log.e("lll",aMapLocation.getLatitude()+"");
                    Log.e("lll",aMapLocation.getLongitude()+"");
                    aMap.addMarker( createMarker(aMapLocation.getLatitude(),aMapLocation.getLongitude(),R.mipmap.mylocation));
                    isFirstLoc=false;

                }
            }else{
                //不为0
            }
        }else{
            //等于空
        }

    }



    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener=onLocationChangedListener;
    }

    @Override
    public void deactivate() {
        mListener=null;
    }
}
