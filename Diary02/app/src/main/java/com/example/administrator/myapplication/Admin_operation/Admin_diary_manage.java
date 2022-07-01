package com.example.administrator.myapplication.Admin_operation;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.myapplication.DB.Diary_sql;
import com.example.administrator.myapplication.Diary_operation.Diary_modify;
import com.example.administrator.myapplication.Diary_operation.Diary_new;
import com.example.administrator.myapplication.Diary_operation.Diary_search;
import com.example.administrator.myapplication.Page.Admin_main;
import com.example.administrator.myapplication.Page.See_anywhere;
import com.example.administrator.myapplication.Page.User_main;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.extended_func.Func;
import com.example.administrator.myapplication.extended_func.Intent_flag;

import java.util.ArrayList;
import java.util.List;

/**
 * author：created by zxf
 * email：1463149917@qq.com
 * csdn: https://blog.csdn.net/qq_44387002
 * date：2021/12/15 16
 */
public class Admin_diary_manage extends Activity implements View.OnClickListener {

    public static Diary_sql diarySql;
    private SQLiteDatabase db;
    private List<String> diary = new ArrayList<>();
    ArrayAdapter<String> adapter;
    private ListView listView;
    private String select_item;
    public String adminName;
    Func func = new Func();
    Intent_flag intentFlag = new Intent_flag("admin_diary_manage");

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.admin_diary_manage);
        adminName = getIntent().getStringExtra("account");
        diarySql = new Diary_sql(this, Diary_sql.DATABASE_NAME, null, Diary_sql.DATABASE_VERSION);
        init();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String id1 = getDiaryId1(position);
                //ListView点击进入日记修改界面
                Intent intent = new Intent(Admin_diary_manage.this, Diary_modify.class);
                func.setAll(id1, null, adminName, intentFlag, intent);
                startActivity(intent);
            }
        });
    }

    //点击"新建日记"按钮
    public void onClick(View view) {
        Intent intent = new Intent (this, Diary_new.class);
        func.setAll(null, adminName, adminName, intentFlag, intent);
        startActivity (intent);
    }

    //点击"查询日记"按钮
    public void onClick1(View view) {
        Intent intent = new Intent (this, Diary_search.class);
        func.setAll(null, null, adminName, intentFlag, intent);
        startActivity (intent);
    }

    //点击"进圈"按钮
    public void onClick2(View view) {
        Intent intent = new Intent(this, See_anywhere.class);
        func.setAll(null, adminName, adminName, intentFlag, intent);
        startActivity(intent);
    }

    //点击"返回上一级"按钮
    public void onClick3(View view) {
        Intent intent = new Intent (this, Admin_main.class);
        func.setAll(null, adminName, adminName, intentFlag, intent);
        startActivity (intent);
    }



    private void init(){
        db = diarySql.getWritableDatabase();
        diary.clear();
        //查询数据库，将title一列添加到列表项目中
        Cursor cursor = db.query("diary",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            String id;
            do{
                id = cursor.getString(cursor.getColumnIndex("id"));
                diary.add(id);
            }while(cursor.moveToNext());
        }
        cursor.close();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, diary);
        listView = findViewById(R.id.admin_diary_manage_lv1);
        listView.setAdapter(adapter);
    }

    //获取所点击日记的id(显示参数为ID)
    private String getDiaryId1(int position){
        select_item = diary.get(position);
        return select_item;
    }

}
