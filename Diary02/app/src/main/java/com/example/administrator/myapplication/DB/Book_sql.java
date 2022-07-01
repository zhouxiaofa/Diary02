package com.example.administrator.myapplication.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2021/11/11.
 */

public class Book_sql extends SQLiteOpenHelper {
    private final static int DATABASE_VERSION=1;
    private  final static String DATABASE_NAME="book.db";
    public   static SQLiteDatabase db;

    public Book_sql(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
        db=getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE book("+"numb VARCHAR(30),name VARCHAR(30),author varchar(30),location varchar(30))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
    public void close(SQLiteDatabase db){
        db.close();
    }

}

