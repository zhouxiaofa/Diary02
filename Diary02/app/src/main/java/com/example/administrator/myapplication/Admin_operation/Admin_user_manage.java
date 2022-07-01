package com.example.administrator.myapplication.Admin_operation;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.myapplication.DB.User_sql;
import com.example.administrator.myapplication.Page.Admin_main;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.User_operation.User_modify;
import com.example.administrator.myapplication.User_operation.User_new;
import com.example.administrator.myapplication.User_operation.User_search;
import com.example.administrator.myapplication.extended_func.Func;
import com.example.administrator.myapplication.extended_func.Intent_flag;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.myapplication.DB.User_sql.DATABASE_NAME;
import static com.example.administrator.myapplication.DB.User_sql.DATABASE_VERSION;

/**
 * author：created by zxf
 * email：1463149917@qq.com
 * csdn: https://blog.csdn.net/qq_44387002
 * date：2021/12/14 13
 */
public class Admin_user_manage extends Activity implements View.OnClickListener {

    public static User_sql userSql;
    private SQLiteDatabase db;
    private List<String> users = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView listView;
    private String account;
    private String select_item;
    Intent_flag intentFlag = new Intent_flag("admin_user_manage");
    Func func = new Func();

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.admin_user_manage);
        userSql = new User_sql(this, DATABASE_NAME, null, DATABASE_VERSION);
        init();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                account = getUserAccount(position);
                //用户修改
                Intent intent = new Intent(Admin_user_manage.this, User_modify.class);
                func.setAll(null, account, account, intentFlag, intent);
                startActivity(intent);
            }
        });
    }

    //点击"新建用户"按钮
    @Override
    public void onClick(View view){
        Intent intent = new Intent(this, User_new.class);
        intent.putExtra("intentFlag", intentFlag);
        startActivity(intent);
    }

    //点击"查询用户"按钮
    public void onClick1(View view){
        Intent intent = new Intent(this, User_search.class);
        startActivity(intent);
    }

    //点击返回上一级按钮
    public void onClick2(View view){
        Intent intent = new Intent(this, Admin_main.class);
        startActivity(intent);
    }

    private void init(){
        db = userSql.getWritableDatabase();
        users.clear();
        //查询数据库，将title一列添加到列表项目中
        Cursor cursor = db.query("user",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            String account;
            do{
                account = cursor.getString(cursor.getColumnIndex("account"));
                users.add(account);
            }while(cursor.moveToNext());
        }
        cursor.close();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,users);
        listView = findViewById(R.id.admin_user_manage_lv1);
        listView.setAdapter(adapter);
    }

    private String getUserAccount(int position){
        //获取所点击的日记的title
        String account;
        select_item = users.get(position);
        //获取id
        db = userSql.getWritableDatabase();
        Cursor cursor = db.query("user",null,"account=?",
                new String[]{select_item},null,null,null);
        cursor.moveToFirst();
        account = cursor.getString(cursor.getColumnIndex("account"));
        return account;
    }

}
