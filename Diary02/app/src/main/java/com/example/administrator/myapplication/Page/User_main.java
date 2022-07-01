package com.example.administrator.myapplication.Page;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.administrator.myapplication.Diary_operation.Diary_new;
import com.example.administrator.myapplication.Diary_operation.Diary_search;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.User_operation.User_modify;
import com.example.administrator.myapplication.extended_func.Func;
import com.example.administrator.myapplication.extended_func.Intent_flag;

/**
 * Created by Administrator on 2021/11/30.
 */

public class User_main extends Activity implements View.OnClickListener {

    public String userName;
    Func func = new Func();
    Intent_flag intentFlag = new Intent_flag("user_main");

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.user_main);
        userName = getIntent().getStringExtra("account");
    }

    //点击"新建日记"按钮
    public void onClick(View view) {
        Intent intent = new Intent(this, Diary_new.class);
        func.setAll(null, userName, userName, intentFlag, intent);
        startActivity (intent);
    }

    //注销登录
    public void onClick1(View view) {
        Intent intent = new Intent (this, Login.class);
        startActivity (intent);
    }

    //点击"进圈"按钮
    public void onClick2(View view) {
        Intent intent = new Intent(User_main.this, See_anywhere.class);
        func.setAll(null, userName, userName, intentFlag, intent);
        startActivity(intent);
    }

    //修改密码
    public void onClick3(View view){
        Intent intent = new Intent(User_main.this, User_modify.class);
        func.setAll(null, userName, userName, intentFlag, intent);
        startActivity(intent);
    }

    //点击"日记搜索"按钮
    public void onClick4(View view){
        Intent intent = new Intent(User_main.this, Diary_search.class);
        func.setAll(null, userName, userName, intentFlag, intent);
        startActivity(intent);
    }

}
