package com.example.administrator.myapplication.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * author：created by zxf
 * email：1463149917@qq.com
 * csdn: https://blog.csdn.net/qq_44387002
 * date：2021/12/20 16
 */
public class Diary_share_sql extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "diary_share.db";
    public static SQLiteDatabase db;

    private Context mContext;

    public Diary_share_sql(Context context, String name,
                     SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE diary_share("
                + "share_id integer not null primary key autoincrement,"
                + "diary_share_id integer,"
                + "share_title text,"
                + "share_time text,"
                + "share_content text,"
                + "share_user text)";//不可编辑，此日记的拥有者字段
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
