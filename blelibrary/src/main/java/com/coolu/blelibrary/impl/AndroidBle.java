package com.coolu.blelibrary.impl;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import com.coolu.blelibrary.config.Config;
import com.coolu.blelibrary.dispose.impl.AQ;
import com.coolu.blelibrary.dispose.impl.Battery;
import com.coolu.blelibrary.dispose.impl.CloseLock;
import com.coolu.blelibrary.dispose.impl.LockResult;
import com.coolu.blelibrary.dispose.impl.LockStatus;
import com.coolu.blelibrary.dispose.impl.OpenLock;
import com.coolu.blelibrary.dispose.impl.Password;
import com.coolu.blelibrary.dispose.impl.StopRead;
import com.coolu.blelibrary.dispose.impl.TY;
import com.coolu.blelibrary.dispose.impl.Token;
import com.coolu.blelibrary.dispose.impl.UpdateVersion;
import com.coolu.blelibrary.inter.IBLE;
import com.coolu.blelibrary.inter.OnConnectionListener;
import com.coolu.blelibrary.inter.OnDeviceSearchListener;
import com.coolu.blelibrary.mode.BatteryTxOrder;
import com.coolu.blelibrary.mode.GetLockStatusTxOrder;
import com.coolu.blelibrary.mode.GetTokenTxOrder;
import com.coolu.blelibrary.mode.OpenLockTxOrder;
import com.coolu.blelibrary.mode.StopReadTxOrder;
import com.coolu.blelibrary.mode.TxOrder;
import com.coolu.blelibrary.utils.GlobalParameterUtils;
import com.fitsleep.sunshinelibrary.utils.ConvertUtils;
import com.fitsleep.sunshinelibrary.utils.EncryptUtils;
import com.fitsleep.sunshinelibrary.utils.Logger;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;

import java.lang.reflect.Method;
import java.util.UUID;

import static com.fitsleep.sunshinelibrary.utils.EncryptUtils.Encrypt;

/**
 * 作者：LiZhao
 * 时间：2017.2.8 11:48
 * 邮箱：44493547@qq.com
 * 备注：
 */
public class AndroidBle implements IBLE {

    private static final String TAG = AndroidBle.class.getSimpleName();
    private BluetoothAdapter mBluetoothAdapter;
    private OnDeviceSearchListener mOnDeviceSearchListener;
    private BluetoothGatt mBluetoothGatt;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private OnConnectionListener mOnConnectionListener;
    private BluetoothGattCharacteristic write_characteristic;
    private Token mToken;
    private BluetoothGattCharacteristic OAD_READ;
    private BluetoothGattCharacteristic OAD_WRITE;
    private Context context;
    private boolean isConnected = false;
    public AndroidBle(Context context) {
        this.context = context;
        BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
        GlobalParameterUtils.getInstance().setContext(context.getApplicationContext());

        //实例化解析器
        mToken = new Token();
        Battery battery = new Battery();
        OpenLock openLock = new OpenLock();
        TY ty = new TY();
        CloseLock closeLock = new CloseLock();
        LockStatus lockStatus = new LockStatus();
        Password password = new Password();
        LockResult lockResult = new LockResult();
        AQ aq = new AQ();
        UpdateVersion updateVersion = new UpdateVersion();
        StopRead stopRead = new StopRead();

        //设置责任hanlder
        mToken.nextHandler = battery;
        battery.nextHandler = openLock;
        openLock.nextHandler = ty;
        ty.nextHandler = closeLock;
        closeLock.nextHandler = lockStatus;
        lockStatus.nextHandler = password;
        password.nextHandler = lockResult;
        lockResult.nextHandler = aq;
        aq.nextHandler = updateVersion;
        updateVersion.nextHandler = stopRead;
    }

    @Override
    public boolean enableBluetooth() {
        return mBluetoothAdapter.enable();
    }

    @Override
    public boolean disableBluetooth() {
        return mBluetoothAdapter.disable();
    }

    @Override
    public boolean isEnable() {
        return mBluetoothAdapter.isEnabled();
    }

    @Override
    public void startScan(OnDeviceSearchListener onDeviceSearchListener) {
        if (mBluetoothAdapter == null) return;
        mBluetoothAdapter.stopLeScan(mLeScanCallback);
        this.mOnDeviceSearchListener = onDeviceSearchListener;
        mBluetoothAdapter.startLeScan(mLeScanCallback);
    }

    @Override
    public void startFilterScan(OnDeviceSearchListener onDeviceSearchListener) {
        if (mBluetoothAdapter == null) return;
        mBluetoothAdapter.stopLeScan(mLeScanCallback);
        this.mOnDeviceSearchListener = onDeviceSearchListener;
        mBluetoothAdapter.startLeScan(new UUID[]{Config.bltFilterServerUUID}, mLeScanCallback);
    }

    @Override
    public void stopScan() {
        if (mBluetoothAdapter == null) return;
        mBluetoothAdapter.stopLeScan(mLeScanCallback);
    }

    @Override
    public synchronized boolean connect(String address, OnConnectionListener onConnectionListener) {
        if (null == onConnectionListener) return false;
        this.mOnConnectionListener = onConnectionListener;

        if (TextUtils.isEmpty(address) || mBluetoothAdapter == null) {
            //删掉是因为，20秒扫描不到就会利用mac直连，10秒直连不到就会调用下面的方法，
            //然后又会扫描，然后直连，构成了一个循环，所以出现“未搜索到车辆，请靠近重启蓝牙再试或者换辆车子”
            //还有，蓝牙有时候自动断开，还得扫描，或直连
         //   mOnConnectionListener.onDisconnect(Config.OBJECT_EMPTY);
            return false;
        }

        BluetoothDevice bluetoothDevice = mBluetoothAdapter.getRemoteDevice(address);
        if (null == bluetoothDevice) {
          //  mOnConnectionListener.onDisconnect(Config.OBJECT_EMPTY);
            return false;
        }

        if (mBluetoothGatt != null) {
            mBluetoothGatt.close();
            mBluetoothGatt = null;
        }
        mBluetoothGatt = bluetoothDevice.connectGatt(context, false, mBluetoothGattCallback);
        return mBluetoothGatt.connect();
    }

    @Override
    public synchronized boolean connectDevice(BluetoothDevice device, OnConnectionListener onConnectionListener) {
        if (null == onConnectionListener|| device ==null) return false;
        this.mOnConnectionListener = onConnectionListener;
        if (mBluetoothGatt!=null){
            mBluetoothGatt.close();
            mBluetoothGatt = null;
        }
        mBluetoothGatt = device.connectGatt(context,false,mBluetoothGattCallback);
        return mBluetoothGatt.connect();
    }

    @Override
    public boolean getToken() {
        return writeObject(new GetTokenTxOrder());
    }

    @Override
    public boolean getBattery() {
        return writeObject(new BatteryTxOrder());
    }

    @Override
    public boolean openLock() {
        return writeObject(new OpenLockTxOrder());
    }

    @Override
    public boolean getLockStatus() {
        return writeObject(new GetLockStatusTxOrder());
    }

    @Override
    public boolean stopRead() {
        return writeObject(new StopReadTxOrder());
    }

    @Override
    public void disconnect() {
        if (mBluetoothGatt == null) {
            return;
        }
        mBluetoothGatt.disconnect();

    }

    @Override
    public void close() {
        if (mBluetoothGatt == null) {
            return;
        }
        refreshDeviceCache();
        mBluetoothGatt.close();
        mBluetoothGatt = null;

    }

    @Override
    public boolean getConnectStatus() {
        return isConnected;
    }


    /**
     * 写入指令
     *
     * @param txOrder 发送指令对象
     * @return 是否成功
     */
    private boolean writeObject(TxOrder txOrder) {

        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
            ToastUtils.showMessage("请开启蓝牙");
            return false;
        }

        if (mBluetoothGatt == null || write_characteristic == null) {
            return false;
        }

        byte[] miwen = null;
        switch (GlobalParameterUtils.getInstance().getLockType()) {
            case MTS:
                miwen = Encrypt(ConvertUtils.hexString2Bytes(txOrder.generateString()), Config.key);
                break;
            case YXS:
                miwen = Encrypt(ConvertUtils.hexString2Bytes(txOrder.generateString()), Config.yx_key);
                break;
        }
        if (miwen != null) {
            write_characteristic.setValue(miwen);
            return mBluetoothGatt.writeCharacteristic(write_characteristic);
        }
        return false;
    }

    private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(final BluetoothDevice device, final int rssi, final byte[] scanRecord) {
            if (null != mOnDeviceSearchListener) {
                mOnDeviceSearchListener.onScanDevice(device, rssi, scanRecord);
            }
        }
    };

    private BluetoothGattCallback mBluetoothGattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            Log.e("kok","status"+status);
            if (status == 133) {
                disconnect();
                return;
            }
            switch (newState) {
                case BluetoothProfile.STATE_CONNECTED:
                    isConnected = true;
                    gatt.discoverServices();
                    if (null != mOnConnectionListener) {
                        mOnConnectionListener.onConnect();
                    }
                    break;
                case BluetoothProfile.STATE_DISCONNECTED:
                    isConnected = false;
                    if (null != mOnConnectionListener) {
                        mOnConnectionListener.onDisconnect(Config.DISCONNECT);
                    }
                    gatt.close();
                    break;
            }
        }

        @Override
        public void onServicesDiscovered(final BluetoothGatt gatt, int status) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                isConnected = true;
                BluetoothGattService service = gatt.getService(Config.bltServerUUID);
                if (null != service) {
                    BluetoothGattCharacteristic read_characteristic = service.getCharacteristic(Config.readDataUUID);
                    write_characteristic = service.getCharacteristic(Config.writeDataUUID);
                    int properties = read_characteristic.getProperties();
                    if ((properties | BluetoothGattCharacteristic.PROPERTY_NOTIFY) > 0) {
                        gatt.setCharacteristicNotification(read_characteristic, true);
                        BluetoothGattDescriptor descriptor = read_characteristic.getDescriptor(Config.CLIENT_CHARACTERISTIC_CONFIG);
                        if (null != descriptor) {
                            descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                            gatt.writeDescriptor(descriptor);
                        }
                    }
                }
                if (null != mOnConnectionListener) {
                    mOnConnectionListener.onServicesDiscovered(TextUtils.isEmpty(gatt.getDevice().getName()) ? "NokeLock" : gatt.getDevice().getName(), gatt.getDevice().getAddress());
                }
            }
            super.onServicesDiscovered(gatt, status);
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            byte mingwen[] = null;
            try {
                byte[] values = characteristic.getValue();
                byte[] x = new byte[16];
                System.arraycopy(values, 0, x, 0, 16);
                switch (GlobalParameterUtils.getInstance().getLockType()) {
                    case MTS:
                        mingwen = EncryptUtils.Decrypt(x, Config.key);
                        break;
                    case YXS:
                        mingwen = EncryptUtils.Decrypt(x, Config.yx_key);
                        break;
                }
                Logger.e(TAG, "返回：" + ConvertUtils.bytes2HexString(mingwen));
                mToken.handlerRequest(ConvertUtils.bytes2HexString(mingwen), 0);
            } catch (Exception e) {
                GlobalParameterUtils.getInstance().sendBroadcast(Config.BLE_DATA, "");
                Logger.e(TAG, "没有该指令：" + ConvertUtils.bytes2HexString(mingwen));
            }
        }

    };

    public boolean refreshDeviceCache() {
        if (mBluetoothGatt != null) {
            try {
                BluetoothGatt localBluetoothGatt = mBluetoothGatt;
                Method localMethod = localBluetoothGatt.getClass().getMethod("refresh", new Class[0]);
                if (localMethod != null) {
                    boolean bool = ((Boolean) localMethod.invoke(localBluetoothGatt, new Object[0])).booleanValue();
                    return bool;
                }
            } catch (Exception localException) {
                Logger.e("#############", "An exception occured while refreshing device");
            }
        }
        return false;
    }
}
