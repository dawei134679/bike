package com.coolu.nokelock.bike.util;

import android.app.Activity;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * 工具类
 * Created by admin on 2017/5/17.
 *
 */
public class BaseUtil {
    /**
     * 判断网络连接是否可用
     * @param context
     * @return
     */
     public static Boolean isNetworkAvailable(Context context){

         ConnectivityManager connectivityManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

         if (connectivityManager!=null){
             NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
             if (info != null) {
                 for (int i = 0; i < info.length; i++) {
                     if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                         return true;
                     }
                 }
             }

         }else {
             return false;
         }
         return false;
     }
    /**
     * 得到屏幕的宽的一半 px
     */
    public static int getWidthMetrics(Context context){
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int widthPixels = displayMetrics.widthPixels/2;
        return widthPixels;
    }

    /**
     * 得到屏幕的高  px
     * @param context
     * @return
     */
    public static int getHeighthMetrics(Context context){
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
       // int heightPixels = displayMetrics.heightPixels/2-255;
        int heightPixels = displayMetrics.heightPixels;
        return heightPixels;
    }

    /**
     * 配对指定的设备
     */
    public static BluetoothDevice bindTargetDevice(BluetoothAdapter localBluetoothAdapter,String address) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //在配对之前，一定要停止搜搜
        if (localBluetoothAdapter.isDiscovering()) {
            localBluetoothAdapter.cancelDiscovery();
        }
        //获取配对的设备
        BluetoothDevice btDev = localBluetoothAdapter.getRemoteDevice(address);
        Boolean returnValue;
        if (btDev.getBondState() == BluetoothDevice.BOND_NONE) {
            //利用反射方法调用BluetoothDevice.createBond(BluetoothDevice remoteDevice);
            Method createBondMethod = BluetoothDevice.class.getMethod("createBond");
            Log.i("cc", "开始配对");
            returnValue = (Boolean) createBondMethod.invoke(btDev);
            if (returnValue){
                Log.i("cc", "bindTargetDevice "+"配对成功");

                    return btDev;

            }

        }
        return null;
    }
    //加密算法
    public static byte[] Encrypt(byte[] sSrc, byte[] sKey){
        try{
            SecretKeySpec skeySpec = new SecretKeySpec(sKey, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(sSrc);
            return encrypted; }catch(Exception ex){ return null; }
    }
    //解密算法
    public static byte[] Decrypt(byte[] sSrc, byte[] sKey){
        try{
                SecretKeySpec skeySpec = new SecretKeySpec(sKey, "AES");
                Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
                cipher.init(Cipher.DECRYPT_MODE, skeySpec);
                byte[] dncrypted = cipher.doFinal(sSrc);
                return dncrypted;

            }catch(Exception ex){

                 return null;
        }
    }

    /**
     * 是否为空
     * @param s
     * @return
     */
    public static boolean IsEmptyOrNullString(String s) {
        return (s == null) || (s.trim().length() == 0);
    }

    /**
     * 图片的二次采样
     * @param path
     * @param width
     * @param height
     * @return
     */

    public static Bitmap resizeBitmap(String path, int width, int height){
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeFile(path,options);
        int outWith=options.outWidth;
        int outHeight=options.outHeight;
        int sampleSize=1;
        while (outHeight/sampleSize>height||outWith/sampleSize>width){
            sampleSize*=2;
        }
        options.inJustDecodeBounds=false;
        options.inSampleSize=sampleSize;
        options.inPreferredConfig=Bitmap.Config.ARGB_8888;
        return BitmapFactory.decodeFile(path,options);
    }

    /**
     * 裁剪成圆形
     * @param bitmap
     * @param leng  正方形的边长
     * @return
     */

    public static Bitmap centerSquarScaleBitmap(Bitmap bitmap,int leng){
        if (null==bitmap ||leng<=0){
            return  null;
        }
        Bitmap result=bitmap;
        int widthOrg=bitmap.getWidth();
        int heigthOrg=bitmap.getHeight();
        if (widthOrg>leng&&heigthOrg>leng){
            int longerEdge=(int)(leng*Math.max(widthOrg,heigthOrg))/Math.min(widthOrg,heigthOrg);
            int scaleWith=widthOrg>heigthOrg?longerEdge:leng;
            int scaleHeight=widthOrg>heigthOrg?leng:longerEdge;
            Bitmap scaleBitmap;
            try{
                scaleBitmap=Bitmap.createScaledBitmap(bitmap,scaleWith,scaleHeight,true);

            }catch (Exception e){
                return null;
            }

            int xTopLeft = (scaleWith/leng) / 2;
            int yTopLeft = (scaleHeight - leng) / 2;
            try {
                result=Bitmap.createBitmap(scaleBitmap,xTopLeft,yTopLeft,leng,leng);
                scaleBitmap.recycle();
            }catch (Exception e){
                return  null;
            }

        }
        return result;
    }

    /**
     * 把图片处理成圆角
     * @param bitmap
     * @return
     */
    public static Bitmap toCircleBitmap(Bitmap bitmap){
        int min=bitmap.getWidth()>bitmap.getHeight()?bitmap.getHeight():bitmap.getWidth();
        int max=bitmap.getWidth()>bitmap.getHeight()?bitmap.getWidth():bitmap.getHeight();
        Bitmap output=Bitmap.createBitmap(min,min, Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(output);
        Paint paint=new Paint();
        RectF rectF=new RectF(0,0,min,min);
        canvas.drawOval(rectF,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        if (min==bitmap.getWidth()){
            canvas.drawBitmap(bitmap,0,(min-max)/2,paint);
        }else {
            canvas.drawBitmap(bitmap,(min-max)/2,0,paint);
        }
        return output;
    }


    public static void setWindowStatusBarColor(Activity activity, int colorResId) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(activity.getResources().getColor(colorResId));

                //底部导航栏
                //window.setNavigationBarColor(activity.getResources().getColor(colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setWindowStatusBarColor(Dialog dialog, int colorResId) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = dialog.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(dialog.getContext().getResources().getColor(colorResId));

                //底部导航栏
                //window.setNavigationBarColor(activity.getResources().getColor(colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 屏幕坐标转化成经纬度
     */
    private LatLng toGeoLocation(AMap aMap,int x, int y) {

        Point mPoint = new Point(x, y);
        LatLng mLatlng = aMap.getProjection().fromScreenLocation(mPoint);

        return mLatlng;
    }

    /**
     * 经纬度转化成屏幕坐标
     */
    private Point toScreenLocation(AMap aMap,double lat,double lng) {


        LatLng mLatlng = new LatLng(lat, lng);
        Point point = aMap.getProjection().toScreenLocation(mLatlng);
        return point;
    }


    /**
     * dp转px
     *
     * @param context 上下文
     * @param dpValue dp值
     * @return px值
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转dp
     *
     * @param context 上下文
     * @param pxValue px值
     * @return dp值
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public static String sHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
                hexString.append(":");
            }
            String result=hexString.toString();
            return result.substring(0, result.length()-1);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取控件的高度或者宽度  isHeight=true则为测量该控件的高度，isHeight=false则为测量该控件的宽度
     * @param view
     * @param isHeight
     * @return
     */
    public static int getViewHeight(View view, boolean isHeight){
        int result;
        if(view==null)return 0;
        if(isHeight){
            int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
            view.measure(h,0);
            result =view.getMeasuredHeight();
        }else{
            int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
            view.measure(0,w);
            result =view.getMeasuredWidth();
        }
        return result;
    }

    //  //屏幕像素坐标转化成经纬度

    public static LatLng toGeoLocation(int x,int y,AMap aMap) {

        if (x<=0||y<=0){
         return  null;
        }


        Point point=new Point(x,y);
        LatLng latLng = aMap.getProjection().fromScreenLocation(point);
        return latLng;



    }

    //经纬度转化成屏幕像素坐标
    public static Point toScreenLocation(double lat,double lng,AMap aMap) {
//        if (AMapUtil.IsEmptyOrNullString(latView.getText().toString()) ||
//                AMapUtil.IsEmptyOrNullString(lngView.getText().toString())) {
//            Toast.makeText(GeoToScreenActivity.this, "经纬度为空", Toast.LENGTH_SHORT).show();
//        } else {
//            lat = Float.parseFloat(latView.getText().toString().trim());
//            lng = Float.parseFloat(lngView.getText().toString().trim());
//
//            mPoint = aMap.getProjection().toScreenLocation(mLatlng);
//            if (mPoint != null) {
//                xView.setText(String.valueOf(mPoint.x));
//                yView.setText(String.valueOf(mPoint.y));
//            }
//        }
        if (lat!=0&&lng!=0){
            LatLng mLatlng = new LatLng(lat, lng);
            Point point = aMap.getProjection().toScreenLocation(mLatlng);
            return point;
        }


        return null;


    }

    // 获取屏幕的默认分辨率
    public static void getDefaultScreenDensity(Context context){

        DisplayMetrics mDisplayMetrics = context.getResources().getDisplayMetrics();
        int width = mDisplayMetrics.widthPixels;
        int height = mDisplayMetrics.heightPixels;
        float density = mDisplayMetrics.density;
        int densityDpi = mDisplayMetrics.densityDpi;
       Log.e("ppp","哈哈"+width+"  "+height+"  "+density+"  "+densityDpi);

    }

}
