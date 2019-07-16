package com.coolu.nokelock.bike.util;

import android.text.format.Time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TimeUtil {

    public static String currentTime() {
        Date dt = new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置显示格式
        String nowTime = df.format(dt);
        return nowTime;
    }

    public static String currentYear() {
        Date dt = new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
        DateFormat df = new SimpleDateFormat("yyyy");//设置显示格式
        String nowTime = df.format(dt);
        return nowTime;
    }

    public static String currentHour(){
        Calendar calendar = Calendar.getInstance();
        //获取系统的日期
//        //年
//        int year = calendar.get(Calendar.YEAR);
//        //月
//        int month = calendar.get(Calendar.MONTH)+1;
//        //日
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //获取系统时间
        //小时
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        //分钟
        int minute = calendar.get(Calendar.MINUTE);
        //秒
//        int second = calendar.get(Calendar.SECOND);
//        time2.setText("Calendar获取当前日期"+year+"年"+month+"月"+day+"日"+hour+":"+minute+":"+second);
        return "今天 "+hour+":"+minute;
    }

    private static final int MIN_DELAY_TIME= 15000;  // 两次点击间隔不能少于2000ms
    private static long lastClickTime=0;
    public synchronized static boolean isFastClick() {//是否大于5秒
        long time = System.currentTimeMillis();
        if ( time - lastClickTime < MIN_DELAY_TIME) {
            return false;
        }
        lastClickTime = time;
        return true;
    }


}
