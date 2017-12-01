package com.ajts.androidmads.sqlite2xlDemo.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Users {

    private String userId;
    private String contactTime;
    private String contactData;

    public ArrayList<String> CONTACT_DY = new ArrayList<>(24);
    public String CONTACT_DYALL;
    public String CONTACT_DYZGJ;      //电压最高节
    public String CONTACT_ZGJDY;      //最高节电压(mV)
    public String CONTACT_DYZDJ;      //电压最低节
    public String CONTACT_ZDJDY;      //最低节电压(mV)
    public String CONTACT_DL	;
    public ArrayList<String> CONTACT_WD = new ArrayList<>(8);

    public String CONTACT_JLSJ ;
    public String CONTACT_CFDCS;
    public String CONTACT_GFCS ;
    public String CONTACT_GCCS ;
    public String CONTACT_GLCS ;
    public String CONTACT_GWCS ;

    private int mDianchiCount = 0;
    private int mWenduCount = 0;

    public Users(String contactTime, String contactData) {
        this.contactTime = contactTime;
        this.contactData = contactData;
        for (int i=0;i<24;i++) {
            this.CONTACT_DY.add("");
        }
        for (int i=0;i<8;i++) {
            this.CONTACT_WD.add("");
        }

        ////解析数据
        //电池组数字
        int index = 0;
        byte[] byteData = hexStringToBytes(contactData);


        byte dianchiCount = byteData[index];
        index++;
        byte wenduCount = byteData[index];
        index++;

        int dianchiIntCount = byteToInt(dianchiCount);
        int wenduIntCount = byteToInt(wenduCount);
        mDianchiCount = dianchiIntCount;
        mWenduCount = wenduIntCount;

        int sum = 0;
        int hei = 0;
        int heiindex = 0;
        int low = 10000;  //最大电压值
        int lowindex=0;
        for (int i=0; i<dianchiIntCount; i++) {
            byte lowbyte = byteData[index];
            index++;
            byte heibyte = byteData[index];
            index++;
            int dianya = byteToInt(heibyte)*256+  byteToInt(lowbyte);
            sum += dianya;

            if (hei<dianya) {
                hei = dianya;
                heiindex=i;
            }

            if (low>dianya) {
                low = dianya;
                lowindex=i;
            }

            CONTACT_DY.add(i, String.valueOf(dianya));
        }

        CONTACT_DYALL = String.valueOf(sum);

        CONTACT_DYZGJ = String.valueOf(heiindex+1);
        CONTACT_ZGJDY = String.valueOf(hei);

        CONTACT_DYZDJ = String.valueOf(lowindex+1);
        CONTACT_ZDJDY = String.valueOf(low);


        //温度
        for (int i=0; i<wenduIntCount; i++) {
            byte wendu = byteData[index];
            index++;
            int wenduint = byteToInt(wendu);
            wenduint = (int) (wenduint * 0.5 - 40);

            CONTACT_WD.add(i, String.valueOf(wenduint));
        }

        //电流
        byte dianliudi = byteData[index];
        index++;
        byte dianliugao = byteData[index];
        index++;

        CONTACT_DL = byteToInt(dianliugao)*256+ byteToInt(dianliudi) + "";


        //SOC
        byte scodi = byteData[index];
        index++;
        byte socgao = byteData[index];
        index++;

        //guanji
        byte guanjidi = byteData[index];
        index++;
        byte guanjigao = byteData[index];
        index++;


        //充放电
        byte chongdi = byteData[index];
        index++;
        byte chonggao = byteData[index];
        index++;

        CONTACT_CFDCS = byteToInt(chonggao)*256+ byteToInt(chongdi) + "";

        //过放
        byte guofangdi = byteData[index];
        index++;
        byte guofangao = byteData[index];
        index++;

        int guofangini = byteToInt(guofangao) *256+ byteToInt(guofangdi);
        CONTACT_GFCS = guofangini+"";



        //过充
        byte guochongdi = byteData[index];
        index++;
        byte guochongao = byteData[index];
        index++;

        int guochongint = byteToInt(guochongao)*256+ byteToInt(guochongdi);
        CONTACT_GCCS = guochongint + "";


        //过流
        byte guoliudi = byteData[index];
        index++;
        byte guoliugao = byteData[index];
        index++;

        CONTACT_GLCS = byteToInt(guoliugao)*256+ byteToInt(guoliudi) + "";

        //过温
        byte guowendi = byteData[index];
        index++;
        byte guowengao = byteData[index];
        index++;

        CONTACT_GWCS = byteToInt(guowengao)*256+ byteToInt(guowendi) + "";


        //时间：
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("ddhhmmss");
        CONTACT_JLSJ =  df.format(date);
    }



    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    public Users(String userId, String contactTime, String contactData) {
        this.userId = userId;
        this.contactTime = contactTime;
        this.contactData = contactData;
        for (int i=0;i<24;i++) {
            this.CONTACT_DY.add("");
        }
        for (int i=0;i<8;i++) {
            this.CONTACT_WD.add("");
        }
    }

    public String getUserId() {
        return userId;
    }

    public String getContactTime() {
        return contactTime;
    }

    public String getContactData() {
        return contactData;
    }


    public static int byteToInt(byte b) {
        //Java 总是把 byte 当做有符处理；我们可以通过将其和 0xFF 进行二进制与得到它的无符值
        return b & 0xFF;
    }

    public int getDianchiCount() {
        return mDianchiCount;
    }

    public void setDianchiCount(final int dianchiCount) {
        this.mDianchiCount = dianchiCount;
    }

    public int getWenduCount() {
        return mWenduCount;
    }

    public void setWenduCount(final int wenduCount) {
        this.mWenduCount = wenduCount;
    }
}
