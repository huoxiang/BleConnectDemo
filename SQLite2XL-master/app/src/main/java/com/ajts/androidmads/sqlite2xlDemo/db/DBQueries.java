package com.ajts.androidmads.sqlite2xlDemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ajts.androidmads.sqlite2xlDemo.model.Users;

import java.util.ArrayList;

public class DBQueries {

    private Context context;
    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public DBQueries(Context context) {
        this.context = context;
    }

    public DBQueries open() throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    // Users
    public boolean insertUser(Users users) {
        ContentValues values = new ContentValues();
        values.put(DBConstants.CONTACT_TIME, users.getContactTime());
        values.put(DBConstants.CONTACT_DATA, users.getContactData());

        values.put(DBConstants.CONTACT_DY1  , users.CONTACT_DY.get(0)  );
        values.put(DBConstants.CONTACT_DY2  , users.CONTACT_DY.get(1)  );
        values.put(DBConstants.CONTACT_DY3  , users.CONTACT_DY.get(2)  );
        values.put(DBConstants.CONTACT_DY4  , users.CONTACT_DY.get(3) );
        values.put(DBConstants.CONTACT_DY5  , users.CONTACT_DY.get(4) );
        values.put(DBConstants.CONTACT_DY6  , users.CONTACT_DY.get(5) );
        values.put(DBConstants.CONTACT_DY7  , users.CONTACT_DY.get(6) );
        values.put(DBConstants.CONTACT_DY8  , users.CONTACT_DY.get(7) );
        values.put(DBConstants.CONTACT_DY9  , users.CONTACT_DY.get(8) );
        values.put(DBConstants.CONTACT_DY10 , users.CONTACT_DY.get(9) );
        values.put(DBConstants.CONTACT_DY11 , users.CONTACT_DY.get(10) );
        values.put(DBConstants.CONTACT_DY12 , users.CONTACT_DY.get(11) );
        values.put(DBConstants.CONTACT_DY13 , users.CONTACT_DY.get(12) );
        values.put(DBConstants.CONTACT_DY14 , users.CONTACT_DY.get(13) );
        values.put(DBConstants.CONTACT_DY15 , users.CONTACT_DY.get(14) );
        values.put(DBConstants.CONTACT_DY16 , users.CONTACT_DY.get(15) );
        values.put(DBConstants.CONTACT_DY17 , users.CONTACT_DY.get(16) );
        values.put(DBConstants.CONTACT_DY18 , users.CONTACT_DY.get(17) );
        values.put(DBConstants.CONTACT_DY19 , users.CONTACT_DY.get(18) );
        values.put(DBConstants.CONTACT_DY20 , users.CONTACT_DY.get(19) );
        values.put(DBConstants.CONTACT_DY21 , users.CONTACT_DY.get(20) );
        values.put(DBConstants.CONTACT_DY22 , users.CONTACT_DY.get(21) );
        values.put(DBConstants.CONTACT_DY23 , users.CONTACT_DY.get(22) );
        values.put(DBConstants.CONTACT_DY24 , users.CONTACT_DY.get(23) );
        values.put(DBConstants.CONTACT_DYALL, users.CONTACT_DYALL);
        values.put(DBConstants.CONTACT_DYZGJ, users.CONTACT_DYZGJ);
        values.put(DBConstants.CONTACT_ZGJDY, users.CONTACT_ZGJDY);
        values.put(DBConstants.CONTACT_DYZDJ, users.CONTACT_DYZDJ);
        values.put(DBConstants.CONTACT_ZDJDY, users.CONTACT_ZDJDY);
        values.put(DBConstants.CONTACT_DL	, users.CONTACT_DL	);

        values.put(DBConstants.CONTACT_WD1 , users.CONTACT_WD.get(0) );
        values.put(DBConstants.CONTACT_WD2 , users.CONTACT_WD.get(1) );
        values.put(DBConstants.CONTACT_WD3 , users.CONTACT_WD.get(2) );
        values.put(DBConstants.CONTACT_WD4 , users.CONTACT_WD.get(3) );
        values.put(DBConstants.CONTACT_WD5 , users.CONTACT_WD.get(4) );
        values.put(DBConstants.CONTACT_WD6 , users.CONTACT_WD.get(5) );
        values.put(DBConstants.CONTACT_WD7 , users.CONTACT_WD.get(6) );
        values.put(DBConstants.CONTACT_WD8 , users.CONTACT_WD.get(7) );




        values.put(DBConstants.CONTACT_JLSJ , users.CONTACT_JLSJ );
        values.put(DBConstants.CONTACT_CFDCS, users.CONTACT_CFDCS);
        values.put(DBConstants.CONTACT_GFCS , users.CONTACT_GFCS );
        values.put(DBConstants.CONTACT_GCCS , users.CONTACT_GCCS );
        values.put(DBConstants.CONTACT_GLCS , users.CONTACT_GLCS );
        values.put(DBConstants.CONTACT_GWCS , users.CONTACT_GWCS );

        return database.insert(DBConstants.USER_TABLE, null, values) > -1;
    }

    public ArrayList<Users> readUsers() {
        ArrayList<Users> list = new ArrayList<>();
        try {
            Cursor cursor;
            database = dbHelper.getReadableDatabase();
            cursor = database.rawQuery(DBConstants.SELECT_QUERY, null);
            list.clear();
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        String contactId = cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_ID));
                        String conPerson = cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_TIME));
                        String conNo = cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_DATA));
                        Users users = new Users(contactId,conPerson,conNo);

                        users.CONTACT_DY.add(0,  cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_DY1  )));
                        users.CONTACT_DY.add(1 , cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_DY2  )));
                        users.CONTACT_DY.add(2 , cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_DY3  )));
                        users.CONTACT_DY.add(3 , cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_DY4  )));
                        users.CONTACT_DY.add(4 , cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_DY5  )));
                        users.CONTACT_DY.add(5 , cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_DY6  )));
                        users.CONTACT_DY.add(6 , cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_DY7  )));
                        users.CONTACT_DY.add(7 , cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_DY8  )));
                        users.CONTACT_DY.add(8 , cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_DY9  )));
                        users.CONTACT_DY.add(9 , cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_DY10 )));
                        users.CONTACT_DY.add(10, cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_DY11 )));
                        users.CONTACT_DY.add(11, cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_DY12 )));
                        users.CONTACT_DY.add(12, cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_DY13 )));
                        users.CONTACT_DY.add(13, cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_DY14 )));
                        users.CONTACT_DY.add(14, cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_DY15 )));
                        users.CONTACT_DY.add(15, cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_DY16 )));
                        users.CONTACT_DY.add(16, cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_DY17 )));
                        users.CONTACT_DY.add(17, cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_DY18 )));
                        users.CONTACT_DY.add(18, cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_DY19 )));
                        users.CONTACT_DY.add(19, cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_DY20 )));
                        users.CONTACT_DY.add(20, cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_DY21 )));
                        users.CONTACT_DY.add(21, cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_DY22 )));
                        users.CONTACT_DY.add(22, cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_DY23 )));
                        users.CONTACT_DY.add(23, cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_DY24 )));


                        users.CONTACT_DYALL = cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_DYALL));
                        users.CONTACT_DYZGJ = cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_DYZGJ));
                        users.CONTACT_ZGJDY = cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_ZGJDY));
                        users.CONTACT_DYZDJ = cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_DYZDJ));
                        users.CONTACT_ZDJDY = cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_ZDJDY));
                        users.CONTACT_DL	 = cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_DL	));

                        users.CONTACT_WD.add(0,  cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_WD1  )));
                        users.CONTACT_WD.add(1 , cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_WD2  )));
                        users.CONTACT_WD.add(2 , cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_WD3  )));
                        users.CONTACT_WD.add(3 , cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_WD4  )));
                        users.CONTACT_WD.add(4 , cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_WD5  )));
                        users.CONTACT_WD.add(5 , cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_WD6  )));
                        users.CONTACT_WD.add(6 , cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_WD7  )));
                        users.CONTACT_WD.add(7 , cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_WD8  )));


                        users.CONTACT_JLSJ  = cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_JLSJ ));
                        users.CONTACT_CFDCS = cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_CFDCS));
                        users.CONTACT_GFCS  = cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_GFCS ));
                        users.CONTACT_GCCS  = cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_GCCS ));
                        users.CONTACT_GLCS  = cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_GLCS ));
                        users.CONTACT_GWCS  = cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_GWCS ));


                        list.add(users);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
        } catch (Exception e) {
            Log.v("Exception", e.getMessage());
        }
        return list;
    }

}
