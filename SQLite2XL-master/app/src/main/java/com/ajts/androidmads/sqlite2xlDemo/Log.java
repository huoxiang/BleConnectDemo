package com.ajts.androidmads.sqlite2xlDemo;

/**
 * Created by Administrator on 2017/11/30.
 */

public class Log {
    public static void e(String tag, String log, BleManager.BTReadCharCallback charCallback) {
        if (charCallback!=null) {
            charCallback.status(log);
        }
        android.util.Log.e(tag, log);  //如果isBoolean返回的是true则写入成功
    }

}
