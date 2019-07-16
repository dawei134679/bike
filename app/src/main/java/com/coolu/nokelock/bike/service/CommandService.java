package com.coolu.nokelock.bike.service;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.coolu.blelibrary.config.Config;
import com.coolu.blelibrary.inter.OnConnectionListener;
import com.coolu.blelibrary.inter.OnDeviceSearchListener;


import com.coolu.nokelock.bike.activity.App;
import com.coolu.nokelock.bike.api.Constant;
import com.coolu.nokelock.bike.bean.BikeOrderBean;
import com.coolu.nokelock.bike.util.ParseLeAdvData;
import com.fitsleep.sunshinelibrary.utils.ConvertUtils;
import com.fitsleep.sunshinelibrary.utils.Logger;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.fitsleep.sunshinelibrary.utils.UtilSharedPreference;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import okhttp3.Request;

/**
 * 指令服务
 * Created by sunshine on 2017/3/11.
 */

public class CommandService extends Service implements OnConnectionListener {
    private boolean isStart = true;
    private int lockStatus = -1;
    private String DeviceAddress = null;
    private AtomicBoolean hasFound = new AtomicBoolean(false);
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1://连接超时
                    App.getInstance().getIBLE().disconnect();
                    Logger.e(CommandService.class.getSimpleName(), "连接执行");
                    Log.e("kok","连接超时");
                    refreshAddress();
                    break;
                case 3: //未搜索到信标
                    App.getInstance().getIBLE().stopScan();
                    sendBroadcast(com.coolu.nokelock.bike.util.Config.BLE_CONNECT, Constant.BLE_NOT_XB);
                    break;
                case 9://开启搜索
                    startScan();
                    break;
                case 10://搜索不到
                    App.getInstance().getIBLE().stopScan();
                    Log.e("kok","扫描不到");
                    sendBroadcast(com.coolu.nokelock.bike.util.Config.BLE_CONNECT, Constant.BLE_ZJ_START);
                    if (TextUtils.isEmpty(DeviceAddress)){
                        sendBroadcast(com.coolu.nokelock.bike.util.Config.BLE_CONNECT, Constant.BLE_NOT_SEARCH);
                    }else {
                        if (App.getInstance().getIBLE()!=null){
                            handler.sendEmptyMessageDelayed(13,10000);
                            App.getInstance().getIBLE().connect(DeviceAddress,CommandService.this);
                        }
                    }
                    break;
                case 11://找到设备，进行连接
                    Log.e("kok","扫描到");
                    sendBroadcast(com.coolu.nokelock.bike.util.Config.BLE_CONNECT, Constant.BLE_START_CONNECT);
                    BluetoothDevice device = (BluetoothDevice) msg.obj;
                    App.getInstance().getIBLE().connectDevice(device, CommandService.this);
                    break;
                case 13:
                    sendBroadcast(com.coolu.nokelock.bike.util.Config.BLE_CONNECT, Constant.BLE_NOT_SEARCH);
                    break;

            }
        }
    };
 //   private PushOrderThread pushOrderThread;
    private boolean isCount;

    @Override
    public void onCreate() {
        super.onCreate();
      //  Log.e("oo","onCreate"+"CommandService");
        registerReceiver(broadcastReceiver, Config.initFilter());
        registerReceiver(commandReceiver,com.coolu.nokelock.bike.util.Config.initIntentFilter() );
       // pushOrderThread = new PushOrderThread();
       // pushOrderThread.start();

    }

    private void refreshAddress() {
      //  Log.e("kok","refreshAddress"+"分hi啊ufhi");
        List<BikeOrderBean> orderListBeen = App.getInstance().getDaoSession().getBikeOrderBeanDao().loadAll();
       // Log.e("kok","refreshAddress"+"分hi啊ufhi"+orderListBeen.size());
        if (orderListBeen.size() > 0) {
            if (!App.getInstance().getIBLE().isEnable()) {
                sendBroadcast(com.coolu.nokelock.bike.util.Config.BLUETOOTH_CHANAGE, "-1");
                App.getInstance().getIBLE().enableBluetooth();
                return;
            }
            BikeOrderBean orderListBean = orderListBeen.get(0);
        //    Log.e("kok","refreshAddress"+"分hi啊ufhi"+orderListBean.getLockType());
            if (orderListBean.getLockType().equals(com.coolu.nokelock.bike.util.Config.GPS)) return;
            if (TextUtils.isEmpty(orderListBean.getMac())) return;
            Config.key = ConvertUtils.hexString2Bytes(orderListBean.getKey());
            Config.password = ConvertUtils.hexString2Bytes(orderListBean.getPassword());
            if (App.getInstance().getIBLE().getConnectStatus() && DeviceAddress.equalsIgnoreCase(orderListBean.getMac())) {
            //    Log.e("kok",App.getInstance().getIBLE().getConnectStatus()+"状态");
                sendBroadcast(com.coolu.nokelock.bike.util.Config.BLE_CONNECT, Constant.BLE_CONNECTED);//ble_connected
                App.getInstance().getIBLE().getLockStatus();
            } else {
                sendBroadcast(com.coolu.nokelock.bike.util.Config.BLE_CONNECT, Constant.BLE_SEARCH);//ble_search
          //      Log.e("kok","Mac"+orderListBean.getMac().toUpperCase());
                App.getInstance().getIBLE().disconnect(); //断开连接
                DeviceAddress = orderListBean.getMac().toUpperCase();
                handler.sendEmptyMessage(9);
            }
        }
    }

    /**
     * 开启搜索
     */
    private void startScan() {
       // Log.e("kok","service"+"startScan");
        handler.removeMessages(10);//搜索不到
        handler.sendEmptyMessageDelayed(10, 20000);
        App.getInstance().getIBLE().stopScan();
        hasFound.set(false);
        App.getInstance().getIBLE().startScan(new OnDeviceSearchListener() {
            @Override
            public void onScanDevice(BluetoothDevice device, int rssi, byte[] scanRecord) {
                if (device == null || TextUtils.isEmpty(device.getAddress())) return;
               // Logger.e(CommandService.class.getSimpleName(),"address:"+device.getAddress());
                if (!hasFound.get()) {
                    try {
                        if (DeviceAddress.equalsIgnoreCase(device.getAddress())) {
                            hasFound.set(true);
                            App.getInstance().getIBLE().stopScan();
                            handler.removeMessages(10);
                            Message message = handler.obtainMessage();
                            message.obj = device;
                            message.what = 11;
                            handler.sendMessage(message);
                        }
                    }catch (NullPointerException e){

                    }
                }
            }
        });
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
            broadcastReceiver = null;
        }
        if (commandReceiver != null) {
            unregisterReceiver(commandReceiver);
            commandReceiver = null;
        }

      //  if (pushOrderThread != null) {
           // isStart = false;
          //  pushOrderThread = null;
      //  }

        handler.removeCallbacksAndMessages(null);
    }

    private BroadcastReceiver commandReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            switch (action) {
                case com.coolu.nokelock.bike.util.Config.OPEN:
                 //   Log.e("kok","action"+ com.coolu.nokelock.bike.util.Config.OPEN);
                    lockStatus = 0;
                    refreshAddress();
                    Logger.e(CommandService.class.getSimpleName(), "OPEN");
                    break;
                case com.coolu.nokelock.bike.util.Config.CLOSE:
                    lockStatus = 1;
                    refreshAddress();
                    Logger.e(CommandService.class.getSimpleName(), "CLOSE");
                    break;
                case BluetoothAdapter.ACTION_STATE_CHANGED:
                    int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
                    switch (state) {
                        case BluetoothAdapter.STATE_OFF:
                            lockStatus = -1;
                            sendBroadcast(com.coolu.nokelock.bike.util.Config.BLUETOOTH_CHANAGE, "-1");
                            break;
                        case BluetoothAdapter.STATE_ON:
                            if (App.getInstance().getConnectStatus() == 0) {
                                sendBroadcast(com.coolu.nokelock.bike.util.Config.BLUETOOTH_CHANAGE, "0");
                                refreshAddress();
                            }
                            break;
                    }
                    break;
            }
        }
    };

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String data = intent.getStringExtra("data");
          //  Log.e("bb","token返回的值data"+data);
            switch (action) {
                case Config.TOKEN_ACTION://获取token
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            sendBroadcast(com.coolu.nokelock.bike.util.Config.BLE_CONNECT, Constant.BLE_TONKEN);
                            App.getInstance().getIBLE().getBattery();

                        }
                    }, 500);

                    break;
                case Config.BATTERY_ACTION:
                    if (TextUtils.isEmpty(data)) {
                        App.getInstance().setBattery(0);
                    } else {
                        App.getInstance().setBattery(Integer.parseInt(data, 16));
                    }
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                           App.getInstance().getIBLE().getLockStatus();

                        }
                    }, 500);
                    break;
                case Config.OPEN_ACTION://开锁
                    if (TextUtils.isEmpty(data)) {
                        sendBroadcast(com.coolu.nokelock.bike.util.Config.BLE_CONNECT, Constant.BLE_OPEN_FAIL);
                    } else {
                     //   Log.e("kkkk","com+开锁");
                        sendBroadcast(com.coolu.nokelock.bike.util.Config.BLE_CONNECT, Constant.BLE_OPEN_OK);
                        sendBroadcast(com.coolu.nokelock.bike.util.Config.OPEN_OK, "");
                    }
                    break;
                case Config.CLOSE_ACTION://关锁锁
                    sendBroadcast(com.coolu.nokelock.bike.util.Config.BLE_CONNECT,Config.CLOSE_ACTION);
                    break;
                case Config.LOCK_STATUS_ACTION://锁状态
               //     Log.e("kok",lockStatus+"");
                    if (lockStatus == 0) {//开锁
                        if (TextUtils.isEmpty(data)) {
                            App.getInstance().getIBLE().openLock();
                        }
                    } else if (lockStatus == 1) {//还车
                        handler.removeMessages(0x98);
                        if (TextUtils.isEmpty(data)) {
                            //TODO 检查是否在停车区域内
                            sendBroadcast(com.coolu.nokelock.bike.util.Config.BLE_CONNECT,Constant.BLE_CLOSE_OK);
                            checkInParking();
                        } else {
                         //  ToastUtils.showMessage("还未上锁");
                            sendBroadcast(com.coolu.nokelock.bike.util.Config.BLE_CONNECT,Constant.BLE_CLOSE_NOT);
                        }
                    }
                    break;
                case Config.BLE_DATA:
                    App.getInstance().getIBLE().close();
                    break;
            }
        }
    };

    /**
     * 检查是否在停车区域内
     */
    private void checkInParking() {
     //   Log.e("kkkk","service"+"checkInparking");
        isCount = false;
        handler.removeMessages(3);
        handler.sendEmptyMessageDelayed(3, 20000);
        sendBroadcast(com.coolu.nokelock.bike.util.Config.BLE_CONNECT,Constant.BLE_SEARCH_XB);
        App.getInstance().getIBLE().stopScan();
        App.getInstance().getIBLE().startFilterScan(new OnDeviceSearchListener() {
            @Override
            public void onScanDevice(BluetoothDevice device, int rssi, byte[] scanRecord) {
                App.getInstance().getIBLE().stopScan();
                Logger.e(CommandService.class.getSimpleName(), "device:" + device.getAddress());
                parseAdvData(scanRecord);
            }
        });
    }


    /**
     * 解析广播
     *
     * @param scanRecord 广播
     * @return true 停止继续扫描 false 继续扫描
     */
    private boolean parseAdvData(byte[] scanRecord) {
        //获取广播包内BLE信息
        byte[] bytes = ParseLeAdvData.adv_report_parse(ParseLeAdvData.BLE_GAP_AD_TYPE_MANUFACTURER_SPECIFIC_DATA, scanRecord);
        if (null == bytes || bytes.length < 11) {
            checkInParking();
            return false;
        }
        //判断是否符合要求
        if (bytes[0] == 0x01 && bytes[1] == 0x02 &&!isCount) {
            isCount = true;
            handler.removeMessages(3);
            Logger.e(CommandService.class.getSimpleName(), "scanRecord:" + ConvertUtils.bytes2HexString(bytes));
            //解析出mac数组和time数组
            byte[] mac = new byte[6];
            byte[] time = new byte[4];
            System.arraycopy(bytes, 2, mac, 0, 6);
            System.arraycopy(bytes, 8, time, 0, 3);
            Logger.e(CommandService.class.getSimpleName(), "mac:" + ConvertUtils.bytes2HexString(mac));
            Logger.e(CommandService.class.getSimpleName(), "time:" + ConvertUtils.bytes2HexString(time));
            //获取time数组内的最大值
            int max = 0x00;
            int current;
            for (int i = 0; i < 4; i++) {
                current = time[i] & 0xFF;
                max = max < current ? current : max;
            }
            if (max == 0xFF) {
                max = (byte) 0xAA;
            } else if (max == 0x00) {
                max = 0x55;
            }
            //按照协议获取真实的mac地址
            for (int i = 0; i < mac.length; i++) {
                mac[i] = (byte) (mac[i] ^ max);
            }
            String mDeviceAddress = ConvertUtils.bytes2HexString(mac);
            mDeviceAddress = mDeviceAddress.substring(0, 2) + ":"
                    + mDeviceAddress.substring(2, 4) + ":"
                    + mDeviceAddress.substring(4, 6) + ":"
                    + mDeviceAddress.substring(6, 8) + ":"
                    + mDeviceAddress.substring(8, 10) + ":"
                    + mDeviceAddress.substring(10, 12);
            //广播可以换车
            sendBroadcast(com.coolu.nokelock.bike.util.Config.CLOSE_OK, mDeviceAddress.toUpperCase());
            return true;
        }
        checkInParking();
        return false;
    }


    @Override
    public void onConnect() {
        Logger.d(CommandService.class.getSimpleName(), "连接成功");
       // Log.e("kok","service"+"onConnect+连接成功");
    }

    @Override
    public void onDisconnect(int state) {
      //  Log.e("kok","service"+"OnDisconnect");
        App.getInstance().setConnectStatus(0);
        sendBroadcast(com.coolu.nokelock.bike.util.Config.BLE_CONNECT, Constant.BLE_DISCONNECT);
        DeviceAddress = null;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
              refreshAddress();
            }
        },2000);
        handler.sendEmptyMessageDelayed(14,8);
    }

    @Override
    public void onServicesDiscovered(String name, String address) {
       // Log.e("kok","onServicesDiscovered");
        App.getInstance().setConnectStatus(1);
        handler.removeMessages(1);
        handler.removeMessages(13);
        DeviceAddress = address;
       // Log.e("kok","address"+address);
        sendBroadcast(com.coolu.nokelock.bike.util.Config.BLE_CONNECT, Constant.BLE_CONNECT);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                App.getInstance().getIBLE().getToken();//为什么这样,发送指令吗
            }
        }, 1000);
    }

    private void sendBroadcast(String action, String data) {
        Intent intent = new Intent(action);
        if (!TextUtils.isEmpty(data)) {
            intent.putExtra("command", data);
        }
        sendBroadcast(intent);
    }

//    class PushOrderThread extends Thread {
//        @Override
//        public void run() {
//            super.run();
//            while (isStart) {
//                try {
//                    if (!TextUtils.isEmpty(UtilSharedPreference.getStringValue(getApplicationContext(), com.example.admin.testble.Config.PHONE))) {
//                        List<UndoneOrderBean> undoneOrderBeen = App.getInstance().getDaoSession().getUndoneOrderBeanDao().loadAll();
//
//                        if (undoneOrderBeen.size() > 0) {
//                            JSONObject jsonObject = HttpHelper.getInstance().getJSONObject(HttpMethod.UP_BLE_STATE.getValue());
//                            jsonObject.put("barcode", undoneOrderBeen.get(0).getBarcode());
//                            jsonObject.put("ordernum", undoneOrderBeen.get(0).getOrdernum());
//                            jsonObject.put("lat", App.getInstance().mAMapLocation == null ? "" : App.getInstance().mAMapLocation.getLatitude());
//                            jsonObject.put("lng", App.getInstance().mAMapLocation == null ? "" : App.getInstance().mAMapLocation.getLongitude());
//                            jsonObject.put("lockstate", undoneOrderBeen.get(0).getLockstate());
//                            jsonObject.put("power", App.getInstance().getBattery());
//                            jsonObject.put("result", undoneOrderBeen.get(0).getResult());
//                            jsonObject.put("locktype", undoneOrderBeen.get(0).getLocktype());
//                            jsonObject.put("mac", undoneOrderBeen.get(0).getMac());
//                            jsonObject.put("timestamp", System.currentTimeMillis() / 1000);
//                            jsonObject.put("shebeiid", undoneOrderBeen.get(0).getShebeiid());
//                            OkHttpClientManager.postJson(com.coolu.nokelock.bike.api.Config.BASE_URL, new OkHttpClientManager.StringCallback() {
//                                @Override
//                                public void onFailure(Request request, IOException e) {
//                                }
//
//                                @Override
//                                public void onResponse(String response) {
//                                    App.getInstance().getDaoSession().getUndoneOrderBeanDao().deleteAll();
//                                    sendBroadcast(com.coolu.nokelock.bike.api.Config.CONNECT_CHANAGE, "");
//                                }
//                            }, jsonObject);
//                        }
//                    }
//                    Thread.sleep(5000);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}
