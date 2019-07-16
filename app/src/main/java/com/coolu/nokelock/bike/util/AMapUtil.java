package com.coolu.nokelock.bike.util;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;

import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.LatLonPoint;

import java.text.DecimalFormat;

/**
 * Created by admin on 2017/8/3.
 */
public class AMapUtil {
    /**
     * 把LatLng对象转化为LatLonPoint对象
     */
    public static LatLonPoint convertToLatLonPoint(LatLng latlon) {
        return new LatLonPoint(latlon.latitude, latlon.longitude);
    }

    /**
     * 把LatLonPoint对象转化为LatLon对象
     */
    public static LatLng convertToLatLng(LatLonPoint latLonPoint) {
        return new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude());
    }

    /**
     * 时间的转化
     * @param second
     * @return
     */
    public static String getFriendlyTime(int second) {
        if (second > 3600) {
            int hour = second / 3600;
            int miniate = (second % 3600) / 60;
            return hour + "小时" + miniate + "分钟";
        }
        if (second >= 60) {
            int miniate = second / 60;
            return miniate + "分钟";
        }
        return second + "秒";
    }

    /**
     * 距离的转化
     * @param lenMeter
     * @return
     */
    public static String getFriendlyLength(int lenMeter) {
        if (lenMeter > 10000) // 10 km
        {
            int dis = lenMeter / 1000;
            return dis + ChString.Kilometer;
        }

        if (lenMeter > 1000) {
            float dis = (float) lenMeter / 1000;
            DecimalFormat fnum = new DecimalFormat("##0.0");
            String dstr = fnum.format(dis);
            return dstr + ChString.Kilometer;
        }

        if (lenMeter > 100) {
            int dis = lenMeter / 50 * 50;
            return dis + ChString.Meter;
        }

        int dis = lenMeter / 10 * 10;
        if (dis == 0) {
            dis = 10;
        }

        return dis + ChString.Meter;
    }


    /**
     * 判断是否启动定位服务
     *
     * @param context 全局信息接口
     * @return 是否启动定位服务
     */
    public static boolean isOpenLocService(final Context context) {

        boolean isGps = false; //判断GPS定位是否启动
        boolean isNetwork = false; //判断网络定位是否启动

        if (context != null) {

            LocationManager locationManager
                    = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

            if (locationManager != null) {
                //通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
                isGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                //通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
                isNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            }

            if (isGps || isNetwork) {
                return true;
            }

        }

        return false;
    }

    /**
     * 跳转定位服务界面
     *
     * @param context 全局信息接口
     */
    public static void gotoLocServiceSettings(Context context) {
        final Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }



}
