package com.example.administrator.myapplication.Page;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.example.administrator.myapplication.Admin_operation.Admin_book_manage;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.Admin_operation.Admin_diary_manage;
import com.example.administrator.myapplication.Admin_operation.Admin_user_manage;
import com.example.administrator.myapplication.extended_func.Func;
import com.example.administrator.myapplication.extended_func.Intent_flag;

/**
 * author：created by zxf
 * email：1463149917@qq.com
 * CSDN: https://blog.csdn.net/qq_44387002
 * date：2021/12/14 09
 */
public class Admin_main extends Activity implements View.OnClickListener{

    public String adminName;
    Func func = new Func();
    Intent_flag intentFlag = new Intent_flag("admin_main");

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.admin_main);
        adminName = getIntent().getStringExtra("account");
    }

    //点击"日记管理"按钮
    public void onClick(View view){
        Intent intent = new Intent(this, Admin_diary_manage.class);
        func.setAll(null, adminName, adminName, intentFlag, intent);
        startActivity(intent);
    }

    //点击"用户管理"按钮
    public void onClick1(View view){
        Intent intent = new Intent(this, Admin_user_manage.class);
        startActivity(intent);
    }

    //点击"图书管理"按钮
    public void onClick2(View view){
        Intent intent = new Intent(this, Admin_book_manage.class);
        startActivity(intent);
    }

    //点击"注销"按钮
    public void onClick3(View view){
        Intent intent = new Intent (this, Login.class);
        startActivity (intent);
    }
}
