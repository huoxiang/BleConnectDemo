package com.ajts.androidmads.sqlite2xlDemo.db;

class DBConstants {

    static final String USER_TABLE = "customers";
    static final String CONTACT_ID = "id";
    static final String CONTACT_TIME = "获得数据时间";
    static final String CONTACT_DATA = "原始数据";

//    //电池组串数
    static final String CONTACT_DY1 = "单体电压1（mV）";
    static final String CONTACT_DY2 = "单体电压2（mV）";
    static final String CONTACT_DY3 = "单体电压3（mV）";
    static final String CONTACT_DY4 = "单体电压4（mV）";
    static final String CONTACT_DY5 = "单体电压5（mV）";
    static final String CONTACT_DY6 = "单体电压6（mV）";
    static final String CONTACT_DY7 = "单体电压7（mV）";
    static final String CONTACT_DY8 = "单体电压8（mV）";
    static final String CONTACT_DY9 = "单体电压9（mV）";
    static final String CONTACT_DY10 = "单体电压10（mV）";
    static final String CONTACT_DY11 = "单体电压11（mV）";
    static final String CONTACT_DY12 = "单体电压12（mV）";
    static final String CONTACT_DY13 = "单体电压13（mV）";
    static final String CONTACT_DY14 = "单体电压14（mV）";
    static final String CONTACT_DY15 = "单体电压15（mV）";
    static final String CONTACT_DY16 = "单体电压16（mV）";
    static final String CONTACT_DY17 = "单体电压17（mV）";
    static final String CONTACT_DY18 = "单体电压18（mV）";
    static final String CONTACT_DY19 = "单体电压19（mV）";
    static final String CONTACT_DY20 = "单体电压20（mV）";
    static final String CONTACT_DY21 = "单体电压21（mV）";
    static final String CONTACT_DY22 = "单体电压22（mV）";
    static final String CONTACT_DY23 = "单体电压23（mV）";
    static final String CONTACT_DY24 = "单体电压24（mV）";
    static final String CONTACT_DYALL = "总电压（mV）";
    static final String CONTACT_DYZGJ = "电压最高节";
    static final String CONTACT_ZGJDY = "最高节电压（mV）";
    static final String CONTACT_DYZDJ = "电压最低节";
    static final String CONTACT_ZDJDY = "最低节电压（mV）";
    static final String CONTACT_DL = "电流（A）";
    static final String CONTACT_WD1 = "温度1";
    static final String CONTACT_WD2 = "温度2";
    static final String CONTACT_WD3 = "温度3";
    static final String CONTACT_WD4 = "温度4";
    static final String CONTACT_WD5 = "温度5";
    static final String CONTACT_WD6 = "温度6";
    static final String CONTACT_WD7 = "温度7";
    static final String CONTACT_WD8 = "温度8";
    static final String CONTACT_JLSJ = "记录时间——日时分秒";
    static final String CONTACT_CFDCS = "充放电次数";
    static final String CONTACT_GFCS = "过放次数";
    static final String CONTACT_GCCS = "过充次数";
    static final String CONTACT_GLCS = "过流次数";
    static final String CONTACT_GWCS = "过温次数";



    static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + USER_TABLE + " ("
            + CONTACT_ID + " INTEGER PRIMARY KEY,"
            + CONTACT_TIME + " TEXT,"
            + CONTACT_DATA + " TEXT,"
            + CONTACT_DY1  + " TEXT,"
            + CONTACT_DY2  + " TEXT,"
            + CONTACT_DY3  + " TEXT,"
            + CONTACT_DY4  + " TEXT,"
            + CONTACT_DY5  + " TEXT,"
            + CONTACT_DY6  + " TEXT,"
            + CONTACT_DY7  + " TEXT,"
            + CONTACT_DY8  + " TEXT,"
            + CONTACT_DY9  + " TEXT,"
            + CONTACT_DY10 + " TEXT,"
            + CONTACT_DY11 + " TEXT,"
            + CONTACT_DY12 + " TEXT,"
            + CONTACT_DY13 + " TEXT,"
            + CONTACT_DY14 + " TEXT,"
            + CONTACT_DY15 + " TEXT,"
            + CONTACT_DY16 + " TEXT,"
            + CONTACT_DY17 + " TEXT,"
            + CONTACT_DY18  + " TEXT,"
            + CONTACT_DY19  + " TEXT,"
            + CONTACT_DY20  + " TEXT,"
            + CONTACT_DY21  + " TEXT,"
            + CONTACT_DY22  + " TEXT,"
            + CONTACT_DY23  + " TEXT,"
            + CONTACT_DY24  + " TEXT,"
            + CONTACT_DYALL + " TEXT,"
            + CONTACT_DYZGJ + " TEXT,"
            + CONTACT_ZGJDY + " TEXT,"
            + CONTACT_DYZDJ + " TEXT,"
            + CONTACT_ZDJDY + " TEXT,"
            + CONTACT_DL  + " TEXT,"

            + CONTACT_WD1 + " TEXT,"
            + CONTACT_WD2 + " TEXT,"
            + CONTACT_WD3 + " TEXT,"
            + CONTACT_WD4 + " TEXT,"
            + CONTACT_WD5 + " TEXT,"
            + CONTACT_WD6 + " TEXT,"
            + CONTACT_WD7 + " TEXT,"
            + CONTACT_WD8 + " TEXT,"

            + CONTACT_JLSJ  + " TEXT,"
            + CONTACT_CFDCS + " TEXT,"
            + CONTACT_GFCS  + " TEXT,"
            + CONTACT_GCCS + " TEXT,"
            + CONTACT_GLCS + " TEXT,"
            + CONTACT_GWCS +" TEXT)";

    static final String SELECT_QUERY = "SELECT * FROM " + USER_TABLE;

}
