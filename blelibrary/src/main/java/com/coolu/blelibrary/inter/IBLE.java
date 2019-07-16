package com.coolu.blelibrary.inter;

import android.bluetooth.BluetoothDevice;

/**
 * 作者：LiZhao
 * 时间：2017.2.8 11:29
 * 邮箱：44493547@qq.com
 * 备注：BLE操作接口
 */
public interface IBLE {
    /**
     * 开启蓝牙
     * @return 成功或失败
     */
    boolean enableBluetooth();

    /**
     * 关闭蓝牙
     * @return 成功或失败
     */
    boolean disableBluetooth();

    /**
     * 是否开启蓝牙
     * @return 成功或失败
     */
    boolean isEnable();

    /**
     * 扫描设备
     */
    void startScan(OnDeviceSearchListener onDeviceSearchListener);
    /**
     * 扫描指定服务uuid设备
     */
    void startFilterScan(OnDeviceSearchListener onDeviceSearchListener);
    /**
     * 停止扫描
     */
    void stopScan();

    /**
     * 链接设备
     * @param address 设备地址
     * @param onConnectionListener 链接状态回调
     */
    boolean connect(String address,OnConnectionListener onConnectionListener);

    /**
     * 连接设备
     * @param device 设备对象
     * @param onConnectionListener 链接状态回调
     */
    boolean connectDevice(BluetoothDevice device,OnConnectionListener onConnectionListener);

    /**
     * 获取Token
     * @return
     */
    boolean getToken();
    /**
     * 获取电量
     * @return
     */
    boolean getBattery();

    /**
     * 开锁
     * @return
     */
    boolean openLock();
    /**
     * 获取锁状态
     * @return
     */
    boolean getLockStatus();
    /**
     * 停止gps模块读取
     * @return
     */
    boolean stopRead();
    /**
     * 断开链接
     */
    void disconnect();
    /**
     * 清空控制器
     */
    void close();
    /**
     * 当前连接状态
     * @return 连接或断开
     */
    boolean getConnectStatus();
    /**
     * 清空缓存
     * @return 成功或失败
     */
    boolean refreshDeviceCache();

}
