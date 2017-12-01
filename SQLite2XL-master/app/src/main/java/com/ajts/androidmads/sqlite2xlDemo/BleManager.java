package com.ajts.androidmads.sqlite2xlDemo;

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
import android.widget.Toast;

import java.util.UUID;

/**
 * Created by Administrator on 2017/11/29.
 */

public class BleManager {
    public static final String TAG = "BleManager";
    private static final int STOP_LESCAN = 222;
    private static final String S_UUID =       "00001000-0000-1000-8000-00805f9b34fb";
    private static final String C_UUID_WRITE = "00001001-0000-1000-8000-00805f9b34fb";
    private static final String C_UUID_READ =  "00001002-0000-1000-8000-00805f9b34fb";
    private static final String READUUID = "00002902-0000-1000-8000-00805f9b34fb";

    Context mContext;
    private BluetoothAdapter mBluetoothAdapter;

    private BluetoothDevice mBluetoothDevice;

    private BluetoothGatt mBluetoothGatt;

    private boolean isScanning = false;

//以上所定义的对象在下面的方法中都有用到，（建议在看蓝牙这方面的东西时，不管看谁的文章，都先把以上或者还有些蓝牙基本用的对象先熟悉是什么意思和基本作用）。

    public BleManager(Context context) {
        this.mContext = context;
        BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);  //BluetoothManager只在android4.3以上有
        if (bluetoothManager == null) {
            Log.e(TAG, "Unable to initialize BluetoothManager.", mBTReadCharCallback);
            return;
        }

        mBluetoothAdapter = bluetoothManager.getAdapter();
    }


//4、既然获得了BluetoothAdapter对象，那么接下来就可以搜索ble设备了，

    //这时就需要用到BluetoothAdapter的startLeScan()这个方法了

    public void startLeScan() {
        if (mBluetoothAdapter == null) {
            return;
        }

        if (isScanning) {
            return;
        }
        isScanning = true;

        mBluetoothAdapter.startLeScan(mLeScanCallback);   //此mLeScanCallback为回调函数

        mHandler.sendEmptyMessageDelayed(STOP_LESCAN, 10000);  //这个搜索10秒，如果搜索不到则停止搜索
    }

    //在4.3之前的api是通过注册广播来处理搜索时发生的一些事件，而支持ble的新的api中，是通过回调的方式来处理的，而mLeScanCallback就是一个接口对象

    private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice device, int arg1, byte[] arg2) {
            Log.e(TAG, "搜索BLE设备中 name=" + device.getName(), mBTReadCharCallback);  //在这里可通过device这个对象来获取到搜索到的ble设备名称和一些相关信息
            if (device.getName() == null) {
                return;
            }
            if (device.getName().contains("BLE#")) {    //判断是否搜索到你需要的ble设备
                Log.e(TAG, "找到BLE设备------>" + device.getAddress(), mBTReadCharCallback);
                mBluetoothDevice = device;   //获取到周边设备
                mHandler.removeMessages(STOP_LESCAN);
                mHandler.sendEmptyMessage(STOP_LESCAN);
                //1、当找到对应的设备后，立即停止扫描；2、不要循环搜索设备，为每次搜索设置适合的时间限制。避免设备不在可用范围的时候持续不停扫描，消耗电量。

                connect();  //连接
            }
        }
    };


//

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case STOP_LESCAN:
                    Toast.makeText(mContext, "停止蓝牙搜索", Toast.LENGTH_LONG);
                    mBluetoothAdapter.stopLeScan(mLeScanCallback);
                    //broadcastUpdate(Config.ACTION_GATT_DISCONNECTED);
                    isScanning = false;
                    Log.e(TAG, "停止搜索", mBTReadCharCallback);
                    break;
            }
        }
    };


//5、搜索到当然就是连接了，
//
//    就是上面那个connect()方法了

    public boolean connect() {
        if (mBluetoothDevice == null) {
            Log.e(TAG, "BluetoothDevice is null.", mBTReadCharCallback);
            return false;
        }

//两个设备通过BLE通信，首先需要建立GATT连接。这里我们讲的是Android设备作为client端，连接GATT Server

        mBluetoothGatt = mBluetoothDevice.connectGatt(mContext, false, mGattCallback);  //mGattCallback为回调接口

        if (mBluetoothGatt != null) {

            if (mBluetoothGatt.connect()) {
                android.util.Log.e(TAG, "连接成功.");
                return true;
            } else {
                Log.e(TAG, "连接失败.", mBTReadCharCallback);
                return false;
            }
        } else {
            Log.e(TAG, "BluetoothGatt null.", mBTReadCharCallback);
            return false;
        }
    }


    private final BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            android.util.Log.e(TAG, "newState=" + newState + "  status="+ status);

            if (mBTReadCharCallback!=null) {
                mBTReadCharCallback.onConnected(newState);
            }

            if (newState == BluetoothProfile.STATE_CONNECTED) {
                gatt.discoverServices(); //执行到这里其实蓝牙已经连接成功了

                Log.e(TAG, "已经连接到GATT server.", mBTReadCharCallback);
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                if (mBluetoothDevice != null) {
                    android.util.Log.e(TAG, "重新连接");
                    connect();
                } else {
                    Log.e(TAG, "Disconnected from GATT server.", mBTReadCharCallback);
                }
            }
        }


        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                Log.e(TAG, "发现服务，可以通信", mBTReadCharCallback);
                autoRead(C_UUID_READ);
            } else {
                Log.e(TAG, "onServicesDiscovered status------>" + status, mBTReadCharCallback);
            }
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            Log.e(TAG, "onCharacteristicRead------>" + bytesToHexString(characteristic.getValue()), mBTReadCharCallback);

//判断UUID是否相等
            if (C_UUID_READ.equals(characteristic.getUuid().toString())) {
            }
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            android.util.Log.e(TAG, "onCharacteristicChanged------>" + bytesToHexString(characteristic.getValue()));

            if (mBTReadCharCallback!=null) {
                mBTReadCharCallback.onCharacteristicChanged(gatt, characteristic);
            }

//判断UUID是否相等
            if (C_UUID_READ.equals(characteristic.getUuid().toString())) {
            }
        }

        //接受Characteristic被写的通知,收到蓝牙模块的数据后会触发onCharacteristicWrite
        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            android.util.Log.e(TAG, "UUID=" + characteristic.getUuid().toString() + " device=" + gatt.getDevice().getAddress() + " status = " + status + " srvcType=" + characteristic.getWriteType() + " srvcInstId=" + characteristic.getService().getInstanceId()
                    + " charInstId=" + characteristic.getInstanceId());
            android.util.Log.e(TAG, "onCharacteristicWrite------>" + bytesToHexString(characteristic.getValue()));
        }
    };

    //
//7、写入数据，在上面的方法中我们已经得到了设备服务和服务里的特征characteristic，那么就可以对这个特征写入或者说是赋值

    public void write(byte[] data) {   //一般都是传byte
        android.util.Log.e(TAG, "准备写入数据" + bytesToHexString(data));
        BluetoothGattCharacteristic writeCharacteristic = getCharcteristic(S_UUID, C_UUID_WRITE);  //这个UUID都是根据协议号的UUID
        if (writeCharacteristic == null) {
            Log.e(TAG, "Write failed. GattCharacteristic is null.", mBTReadCharCallback);
            return;
        }
        writeCharacteristic.setValue(data); //为characteristic赋值
        writeCharacteristic.setWriteType(1);
        writeCharacteristicWrite(writeCharacteristic);
    }

    public void autoRead(String cUUID) {
        BluetoothGattCharacteristic batteryLevelGattC = getCharcteristic(
                S_UUID, cUUID);

        BluetoothGattDescriptor localBluetoothGattDescriptor = batteryLevelGattC.getDescriptor(UUID.fromString(READUUID));

        localBluetoothGattDescriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        mBluetoothGatt.writeDescriptor(localBluetoothGattDescriptor);
        mBluetoothGatt.setCharacteristicNotification(batteryLevelGattC, true);
    }


//
//6、获取服务与特征

//a.获取服务

    public BluetoothGattService getService(UUID uuid) {
        if (mBluetoothAdapter == null || mBluetoothGatt == null) {
            Log.e(TAG, "BluetoothAdapter not initialized", mBTReadCharCallback);
            return null;
        }
        return mBluetoothGatt.getService(uuid);
    }

//b.获取特征

    private BluetoothGattCharacteristic getCharcteristic(String serviceUUID, String characteristicUUID) {

//得到服务对象
        BluetoothGattService service = getService(UUID.fromString(serviceUUID));  //调用上面获取服务的方法

        if (service == null) {
            android.util.Log.e(TAG, "Can not find 'BluetoothGattService'serviceUUID=" + serviceUUID);
            return null;
        }

//得到此服务结点下Characteristic对象
        final BluetoothGattCharacteristic gattCharacteristic = service.getCharacteristic(UUID.fromString(characteristicUUID));
        if (gattCharacteristic != null) {
            return gattCharacteristic;
        } else {
            android.util.Log.e(TAG, "Can not find 'BluetoothGattCharacteristic'characteristicUUID=" + characteristicUUID);
            return null;
        }
    }


//获取数据

    public void readCharacteristic(BluetoothGattCharacteristic characteristic) {
        if (mBluetoothAdapter == null || mBluetoothGatt == null) {
            Log.e(TAG, "BluetoothAdapter not initialized", mBTReadCharCallback);
            return;
        }
        boolean ret = mBluetoothGatt.readCharacteristic(characteristic);
        Log.e(TAG, "单次读取ret=" + ret, mBTReadCharCallback);
    }


    public boolean setCharacteristicNotification(BluetoothGattCharacteristic characteristic, boolean enabled) {
        if (mBluetoothAdapter == null || mBluetoothGatt == null) {
            android.util.Log.e(TAG, "BluetoothAdapter not initialized");
            return false;
        }
        boolean ret = mBluetoothGatt.setCharacteristicNotification(characteristic, enabled);
        Log.e(TAG, "设置自动读取ret=" + ret, mBTReadCharCallback);
        return ret;
    }

    public void writeCharacteristicWrite(BluetoothGattCharacteristic characteristic) {
        if (mBluetoothAdapter == null || mBluetoothGatt == null) {
            Log.e(TAG, "BluetoothAdapter not initialized", mBTReadCharCallback);
            return;
        }

        android.util.Log.e(TAG, "BluetoothAdapter 写入数据");
        boolean isBoolean = false;
        isBoolean = mBluetoothGatt.writeCharacteristic(characteristic);

        android.util.Log.e(TAG, "BluetoothAdapter_writeCharacteristic = " + isBoolean);  //如果isBoolean返回的是true则写入成功
    }


    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }


    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public void setReadCallBack(BTReadCharCallback btReadCharCallback) {
        mBTReadCharCallback = btReadCharCallback;
    }

    BTReadCharCallback mBTReadCharCallback ;

    interface BTReadCharCallback {
        void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic);

        void status(String log);

        void onConnected(int status);
    }

}
