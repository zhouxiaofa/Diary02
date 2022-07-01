package com.example.administrator.myapplication.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2021/12/1.
 */

public class Diary_sql extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "diary.db";
    public static SQLiteDatabase db;

    private Context mContext;

    public Diary_sql(Context context, String name,
                     SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE diary("
                + "id integer not null primary key autoincrement,"
                + "title text,"
                + "author text,"//可编辑作者字段
                + "time text,"
                + "content text,"
                + "user text,"//不可编辑，此日记的拥有者字段
                + "changeFlag int default 0)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
