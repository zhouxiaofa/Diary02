package com.example.administrator.myapplication.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2021/11/26.
 */

public class User_sql extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "user.db";
    public static SQLiteDatabase db;

    private Context mContext;

    public User_sql(Context context, String name,
                    SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE user("
                + "id integer primary key autoincrement,"
                + "account VARCHAR(30),"
                + "password VARCHAR(10),"
                + "flag VARCHAR(1))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean deleteDatabase(Context context){
        return context.deleteDatabase(DATABASE_NAME);
    }
}
