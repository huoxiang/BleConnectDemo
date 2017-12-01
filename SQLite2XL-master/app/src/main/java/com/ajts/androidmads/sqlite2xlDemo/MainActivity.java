package com.ajts.androidmads.sqlite2xlDemo;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ajts.androidmads.library.SQLiteToExcel;
import com.ajts.androidmads.sqlite2xlDemo.db.DBHelper;
import com.ajts.androidmads.sqlite2xlDemo.db.DBQueries;
import com.ajts.androidmads.sqlite2xlDemo.model.Users;
import com.ajts.androidmads.sqlite2xlDemo.util.Utils;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private Context mContext;
    private BleManager manager;

    Button btnExport;

    DBHelper dbHelper;
    DBQueries dbQueries;

    TextView txstatus;
    Button btnconnect;
    Button sendCommand;
    Timer timer;
    boolean timerOpen = false;

    String characteristicData = "";
    int characteristicIndex = 0;
    int charLen = 0;










    ////////////////////////////////
    private TextView your_first_text_view;
    private TextView your_second_text_view;

    private TextView dianchigeshu;
    private TextView wendugeshu;

    private TextView zuigaodianya;
    private TextView zuididianya;

    private View l1 ;
    private View l2 ;
    private View l3 ;
    private View l4 ;
    private View l5 ;
    private View l6 ;
    private View l7 ;
    private View l8 ;
    private View l9 ;
    private View l10;
    private View l11;
    private View l12;
    private View l13;
    private View l14;
    private View l15;
    private View l16;
    private View l17;
    private View l18;
    private View l19;
    private View l20;
    private View l21;
    private View l22;
    private View l23;
    private View l24;

    private TextView danti1 ;
    private TextView danti2 ;
    private TextView danti3 ;
    private TextView danti4 ;
    private TextView danti5 ;
    private TextView danti6 ;
    private TextView danti7 ;
    private TextView danti8 ;
    private TextView danti9 ;
    private TextView danti10;
    private TextView danti11;
    private TextView danti12;
    private TextView danti13;
    private TextView danti14;
    private TextView danti15;
    private TextView danti16;
    private TextView danti17;
    private TextView danti18;
    private TextView danti19;
    private TextView danti20;
    private TextView danti21;
    private TextView danti22;
    private TextView danti23;
    private TextView danti24;
    /////////////////////////////////



    ///////////////////////////
    private View w1 ;
    private View w2 ;
    private View w3 ;
    private View w4 ;
    private View w5 ;
    private View w6 ;
    private View w7 ;
    private View w8 ;

    private TextView wendu1 ;
    private TextView wendu2 ;
    private TextView wendu3 ;
    private TextView wendu4 ;
    private TextView wendu5 ;
    private TextView wendu6 ;
    private TextView wendu7 ;
    private TextView wendu8 ;
    ////////////////////////////

    private TextView CFDCS;
    private TextView GFCS;
    private TextView GCCS;
    private TextView GLCS;
    private TextView GWCS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        timer = new Timer();

        initBluetooth();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        initUsersView();



        txstatus = (TextView) findViewById(R.id.txstatus);

        btnconnect = (Button) findViewById(R.id.btnconnect);
        btnconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (manager != null) {
                    manager.startLeScan();  //调用此方法，如果搜索到有设备则自然的去连接它了，到时候连接时回回调mGattCallback 这个回调函数，如果成功你可以发送一个广播出来提醒用户蓝牙与设备连接成功
                }
            }
        });

        sendCommand = (Button) findViewById(R.id.btnSendCommand);
        sendCommand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //开启定时器
                if (timerOpen) {
                    timerOpen = false;
                    Toast.makeText(mContext, "关闭定时器", Toast.LENGTH_LONG);
                    sendCommand.setText(getResources().getString(R.string.close));
                    timer.cancel();
                } else {
                    timerOpen = true;
                    Toast.makeText(mContext, "开启定时器发送指令", Toast.LENGTH_LONG);
                    sendCommand.setText(getResources().getString(R.string.open));
                    timer = new Timer();
                    timer.scheduleAtFixedRate(new Mytack(), 100, 1000);
                }
            }
        });

        manager = new BleManager(this);  //这个this是一个上下文，只要在上面的BleManager工具类定义一个有参数就好
        manager.setReadCallBack(new BleManager.BTReadCharCallback() {
            @Override
            public void onCharacteristicChanged(final BluetoothGatt gatt, final BluetoothGattCharacteristic characteristic) {

                if (characteristicIndex==0) {
                    characteristicIndex++;

                    byte[] charValue = characteristic.getValue();
                    byte dianchiCount = charValue[0];
                    byte wenduCount = charValue[1];

                    int dianchiIntCount = byteToInt(dianchiCount);
                    int wenduIntCount = byteToInt(wenduCount);

                    /**
                     * 很容易算的啊！除了温度和电池个数是变化的其他都是固定的！除去
                     * 温度和电池，其他数据为18个字节，即返回字节数为：
                     * 18+电池个数*2+温度个数，这就是整个长度！
                     * */
                    charLen = (18 + dianchiIntCount*2 + wenduIntCount) * 2;
                    characteristicData = BleManager.bytesToHexString(characteristic.getValue());

                    return;
                }

                if (characteristicIndex>0) {

                    characteristicData += BleManager.bytesToHexString(characteristic.getValue());

                    //接收完毕
                    if (characteristicData.length() == charLen) {
                        dbQueries.open();
                        Date date = new Date();
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
                        final Users users = new Users(df.format(date), characteristicData);

                        //USER解析
                        dbQueries.insertUser(users);

                        Activity a = (Activity) mContext;
                        a.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setViewData(users);
                            }
                        });
                        dbQueries.close();

                        characteristicData = "";
                        characteristicIndex=0;
                        charLen=0;
                    }

                }
            }

            @Override
            public void status(final String log) {
                Activity a = (Activity) mContext;
                a.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (txstatus != null) {
                            txstatus.setText(log);
                        }
                    }
                });

            }

            @Override
            public void onConnected(final int status) {
                Activity a = (Activity) mContext;
                a.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (status==2) {
                            sendCommand.setEnabled(true);
                            sendCommand.setClickable(true);
                            sendCommand.setBackgroundResource(R.color.colorPrimary);
                            sendCommand.setTextColor(getResources().getColor(R.color.colorAccent));
                        } else {
                            sendCommand.setEnabled(false);
                            sendCommand.setClickable(false);
                            sendCommand.setBackgroundResource(R.color.colorPrimaryDisable);
                            sendCommand.setTextColor(getResources().getColor(R.color.colorAccentDisable));

                            timerOpen = false;
                            sendCommand.setText(getResources().getString(R.string.close));
                            timer.cancel();
                        }
                    }
                });


            }
        });

        dbHelper = new DBHelper(getApplicationContext());
        dbQueries = new DBQueries(getApplicationContext());

        btnExport = (Button) findViewById(R.id.btn_export);
        btnExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String directory_path = Environment.getExternalStorageDirectory().getPath() + "/Backup/";
                File file = new File(directory_path);
                if (!file.exists()) {
                    file.mkdirs();
                }
                // Export SQLite DB as EXCEL FILE
                SQLiteToExcel sqliteToExcel = new SQLiteToExcel(getApplicationContext(), DBHelper.DB_NAME, directory_path);
                sqliteToExcel.exportAllTables("DJBatteryInfo.xls", new SQLiteToExcel.ExportListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onCompleted(String filePath) {
                        Utils.showSnackBar(view, "保持在内部存储的Backup文件夹下，命名为DJBatteryInfo.xls");
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
            }
        });
    }

    private void initUsersView() {
        your_first_text_view = (TextView) findViewById(R.id.listview_firsttextview);
        your_second_text_view = (TextView) findViewById(R.id.listview_secondtextview);

        dianchigeshu = (TextView) findViewById(R.id.text_dianchigeshu);
        wendugeshu = (TextView) findViewById(R.id.text_wendugeshu);

        zuigaodianya = (TextView) findViewById(R.id.zuigaodianya);
        zuididianya = (TextView) findViewById(R.id.zuididianya);

        l1  = findViewById(R.id.l1 );
        l2  = findViewById(R.id.l2 );
        l3  = findViewById(R.id.l3 );
        l4  = findViewById(R.id.l4 );
        l5  = findViewById(R.id.l5 );
        l6  = findViewById(R.id.l6 );
        l7  = findViewById(R.id.l7 );
        l8  = findViewById(R.id.l8 );
        l9  = findViewById(R.id.l9 );
        l10 = findViewById(R.id.l10);
        l11 = findViewById(R.id.l11);
        l12 = findViewById(R.id.l12);
        l13 = findViewById(R.id.l13);
        l14 = findViewById(R.id.l14);
        l15 = findViewById(R.id.l15);
        l16 = findViewById(R.id.l16);
        l17 = findViewById(R.id.l17);
        l18 = findViewById(R.id.l18);
        l19 = findViewById(R.id.l19);
        l20 = findViewById(R.id.l20);
        l21 = findViewById(R.id.l21);
        l22 = findViewById(R.id.l22);
        l23 = findViewById(R.id.l23);
        l24 = findViewById(R.id.l24);

        danti1  = (TextView) findViewById(R.id.danti1 );
        danti2  = (TextView) findViewById(R.id.danti2 );
        danti3  = (TextView) findViewById(R.id.danti3 );
        danti4  = (TextView) findViewById(R.id.danti4 );
        danti5  = (TextView) findViewById(R.id.danti5 );
        danti6  = (TextView) findViewById(R.id.danti6 );
        danti7  = (TextView) findViewById(R.id.danti7 );
        danti8  = (TextView) findViewById(R.id.danti8 );
        danti9  = (TextView) findViewById(R.id.danti9 );
        danti10 = (TextView) findViewById(R.id.danti10);
        danti11 = (TextView) findViewById(R.id.danti11);
        danti12 = (TextView) findViewById(R.id.danti12);
        danti13 = (TextView) findViewById(R.id.danti13);
        danti14 = (TextView) findViewById(R.id.danti14);
        danti15 = (TextView) findViewById(R.id.danti15);
        danti16 = (TextView) findViewById(R.id.danti16);
        danti17 = (TextView) findViewById(R.id.danti17);
        danti18 = (TextView) findViewById(R.id.danti18);
        danti19 = (TextView) findViewById(R.id.danti19);
        danti20 = (TextView) findViewById(R.id.danti20);
        danti21 = (TextView) findViewById(R.id.danti21);
        danti22 = (TextView) findViewById(R.id.danti22);
        danti23 = (TextView) findViewById(R.id.danti23);
        danti24 = (TextView) findViewById(R.id.danti24);

        w1  = findViewById(R.id.w1 );
        w2  = findViewById(R.id.w2 );
        w3  = findViewById(R.id.w3 );
        w4  = findViewById(R.id.w4 );
        w5  = findViewById(R.id.w5 );
        w6  = findViewById(R.id.w6 );
        w7  = findViewById(R.id.w7 );
        w8  = findViewById(R.id.w8 );

        wendu1  = (TextView) findViewById(R.id.wendu1 );
        wendu2  = (TextView) findViewById(R.id.wendu2 );
        wendu3  = (TextView) findViewById(R.id.wendu3 );
        wendu4  = (TextView) findViewById(R.id.wendu4 );
        wendu5  = (TextView) findViewById(R.id.wendu5 );
        wendu6  = (TextView) findViewById(R.id.wendu6 );
        wendu7  = (TextView) findViewById(R.id.wendu7 );
        wendu8  = (TextView) findViewById(R.id.wendu8 );



        CFDCS = (TextView) findViewById(R.id.CFDCS);
        GFCS = (TextView) findViewById(R.id.GFCS);
        GCCS = (TextView) findViewById(R.id.GCCS);
        GLCS = (TextView) findViewById(R.id.GLCS);
        GWCS = (TextView) findViewById(R.id.GWCS);

    }

    private void setViewData(Users users) {
        your_first_text_view.setText(users.getContactTime());
        your_second_text_view.setText(users.getContactData());

        dianchigeshu.setText(users.getDianchiCount()+"");
        wendugeshu.setText(users.getWenduCount()+"");

        zuigaodianya.setText("第"+ users.CONTACT_DYZGJ +"个  " + getVDianYa(users.CONTACT_ZGJDY));
        zuididianya.setText("第"+ users.CONTACT_DYZDJ +"个  " + getVDianYa(users.CONTACT_ZDJDY));


        if (!"".equals(users.CONTACT_DY.get(0))) {
            l1 .setVisibility(View.VISIBLE);
            danti1 .setText(getVDianYa(users.CONTACT_DY.get(0)));
        }

        if (!"".equals(users.CONTACT_DY.get(1 ))) {
            l2 .setVisibility(View.VISIBLE);
            danti2 .setText(getVDianYa(users.CONTACT_DY.get(1 )));
        }

        if (!"".equals(users.CONTACT_DY.get(2 ))) {
            l3 .setVisibility(View.VISIBLE);
            danti3 .setText(getVDianYa(users.CONTACT_DY.get(2 )));
        }

        if (!"".equals(users.CONTACT_DY.get(3 ))) {
            l4 .setVisibility(View.VISIBLE);
            danti4 .setText(getVDianYa(users.CONTACT_DY.get(3 )));
        }

        if (!"".equals(users.CONTACT_DY.get(4 ))) {
            l5 .setVisibility(View.VISIBLE);
            danti5 .setText(getVDianYa(users.CONTACT_DY.get(4 )));
        }

        if (!"".equals(users.CONTACT_DY.get(5 ))) {
            l6 .setVisibility(View.VISIBLE);
            danti6 .setText(getVDianYa(users.CONTACT_DY.get(5 )));
        }

        if (!"".equals(users.CONTACT_DY.get(6 ))) {
            l7 .setVisibility(View.VISIBLE);
            danti7 .setText(getVDianYa(users.CONTACT_DY.get(6 )));
        }

        if (!"".equals(users.CONTACT_DY.get(7 ))) {
            l8 .setVisibility(View.VISIBLE);
            danti8 .setText(getVDianYa(users.CONTACT_DY.get(7 )));
        }

        if (!"".equals(users.CONTACT_DY.get(8 ))) {
            l9 .setVisibility(View.VISIBLE);
            danti9 .setText(getVDianYa(users.CONTACT_DY.get(8 )));
        }

        if (!"".equals(users.CONTACT_DY.get(9 ))) {
            l10.setVisibility(View.VISIBLE);
            danti10.setText(getVDianYa(users.CONTACT_DY.get(9 )));
        }

        if (!"".equals(users.CONTACT_DY.get(10))) {
            l11.setVisibility(View.VISIBLE);
            danti11.setText(getVDianYa(users.CONTACT_DY.get(10)));
        }

        if (!"".equals(users.CONTACT_DY.get(11))) {
            l12.setVisibility(View.VISIBLE);
            danti12.setText(getVDianYa(users.CONTACT_DY.get(11)));
        }

        if (!"".equals(users.CONTACT_DY.get(12))) {
            l13.setVisibility(View.VISIBLE);
            danti13.setText(getVDianYa(users.CONTACT_DY.get(12)));
        }

        if (!"".equals(users.CONTACT_DY.get(13))) {
            l14.setVisibility(View.VISIBLE);
            danti14.setText(getVDianYa(users.CONTACT_DY.get(13)));
        }

        if (!"".equals(users.CONTACT_DY.get(14))) {
            l15.setVisibility(View.VISIBLE);
            danti15.setText(getVDianYa(users.CONTACT_DY.get(14)));
        }

        if (!"".equals(users.CONTACT_DY.get(15))) {
            l16.setVisibility(View.VISIBLE);
            danti16.setText(getVDianYa(users.CONTACT_DY.get(15)));
        }

        if (!"".equals(users.CONTACT_DY.get(16))) {
            l17.setVisibility(View.VISIBLE);
            danti17.setText(getVDianYa(users.CONTACT_DY.get(16)));
        }

        if (!"".equals(users.CONTACT_DY.get(17))) {
            l18.setVisibility(View.VISIBLE);
            danti18.setText(getVDianYa(users.CONTACT_DY.get(17)));
        }

        if (!"".equals(users.CONTACT_DY.get(18))) {
            l19.setVisibility(View.VISIBLE);
            danti19.setText(getVDianYa(users.CONTACT_DY.get(18)));
        }

        if (!"".equals(users.CONTACT_DY.get(19))) {
            l20.setVisibility(View.VISIBLE);
            danti20.setText(getVDianYa(users.CONTACT_DY.get(19)));
        }

        if (!"".equals(users.CONTACT_DY.get(20))) {
            l21.setVisibility(View.VISIBLE);
            danti21.setText(getVDianYa(users.CONTACT_DY.get(20)));
        }

        if (!"".equals(users.CONTACT_DY.get(21))) {
            l22.setVisibility(View.VISIBLE);
            danti22.setText(getVDianYa(users.CONTACT_DY.get(21)));
        }

        if (!"".equals(users.CONTACT_DY.get(22))) {
            l23.setVisibility(View.VISIBLE);
            danti23.setText(getVDianYa(users.CONTACT_DY.get(22)));
        }

        if (!"".equals(users.CONTACT_DY.get(23))) {
            l24.setVisibility(View.VISIBLE);
            danti24.setText(getVDianYa(users.CONTACT_DY.get(23)));
        }





        if (!"".equals(users.CONTACT_WD.get(0))) {
            w1 .setVisibility(View.VISIBLE);
            wendu1 .setText(users.CONTACT_WD.get(0) + "℃");
        }

        if (!"".equals(users.CONTACT_WD.get(1 ))) {
            w2 .setVisibility(View.VISIBLE);
            wendu2 .setText(users.CONTACT_WD.get(1 ) + "℃");
        }

        if (!"".equals(users.CONTACT_WD.get(2 ))) {
            w3 .setVisibility(View.VISIBLE);
            wendu3 .setText(users.CONTACT_WD.get(2 ) + "℃");
        }

        if (!"".equals(users.CONTACT_WD.get(3 ))) {
            w4 .setVisibility(View.VISIBLE);
            wendu4 .setText(users.CONTACT_WD.get(3 ) + "℃");
        }

        if (!"".equals(users.CONTACT_WD.get(4 ))) {
            w5 .setVisibility(View.VISIBLE);
            wendu5 .setText(users.CONTACT_WD.get(4 ) + "℃");
        }

        if (!"".equals(users.CONTACT_WD.get(5 ))) {
            w6 .setVisibility(View.VISIBLE);
            wendu6 .setText(users.CONTACT_WD.get(5 ) + "℃");
        }

        if (!"".equals(users.CONTACT_WD.get(6 ))) {
            w7 .setVisibility(View.VISIBLE);
            wendu7 .setText(users.CONTACT_WD.get(6 ) + "℃");
        }

        if (!"".equals(users.CONTACT_WD.get(7 ))) {
            w8 .setVisibility(View.VISIBLE);
            wendu8 .setText(users.CONTACT_WD.get(7 ) + "℃");
        }

        CFDCS.setText(users.CONTACT_CFDCS);
        GFCS.setText(users.CONTACT_GFCS);
        GCCS.setText(users.CONTACT_GCCS);
        GLCS.setText(users.CONTACT_GLCS);
        GWCS.setText(users.CONTACT_GWCS);
    }

    private String getVDianYa(String dianyaMv) {
        double DianyaMv = Double.parseDouble(dianyaMv);
        double dianyaV = DianyaMv / 1000;
        return formatDoubleLength(dianyaV, 3) + "V";
    }

    /**
     * 格式化double字符到指定长度
     */
    public static String formatDoubleLength(double distanceValue, int length) {
        try {
            if (length < 1) {
                return distanceValue + "";
            }
            length = length < 1 ? 1 : length;
            String format = "0.";
            for (int i = 0; i < length; i++) {
                format += "0";
            }
            DecimalFormat decimalFormat = new DecimalFormat(format);//构造方法的字符格式这里如果小数不足2位,会以0补足.
            return decimalFormat.format(distanceValue);
        } catch (Exception e) {
            return "";
        }
    }


    private void initBluetooth() {
        BluetoothManager mBluetoothManager = (BluetoothManager) this.getSystemService(Context.BLUETOOTH_SERVICE);
        if (mBluetoothManager != null) {
            BluetoothAdapter mBluetoothAdapter = mBluetoothManager.getAdapter();
            if (mBluetoothAdapter != null) {
                if (!mBluetoothAdapter.isEnabled()) {
                    mBluetoothAdapter.enable();  //打开蓝牙
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        if(item.getItemId()==R.id.github){
//            String url = "https://github.com/androidmads/SQLite2XL/";
//            Intent i = new Intent(Intent.ACTION_VIEW);
//            i.setData(Uri.parse(url));
//            startActivity(i);
//        }
        return true;
    }


    private class Mytack extends TimerTask {// public abstract class TimerTask implements Runnable{}

        @Override
        public void run() {
            byte[] data = {0x23, 0x41, 0x23};
            manager.write(data);
            Log.e(TAG, "1秒定时器正在执行中", null);
        }
    }

    public static int byteToInt(byte b) {
        //Java 总是把 byte 当做有符处理；我们可以通过将其和 0xFF 进行二进制与得到它的无符值
        return b & 0xFF;
    }
}
