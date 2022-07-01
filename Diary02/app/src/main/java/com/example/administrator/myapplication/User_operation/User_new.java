package com.example.administrator.myapplication.User_operation;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.myapplication.Admin_operation.Admin_user_manage;
import com.example.administrator.myapplication.DB.User_sql;
import com.example.administrator.myapplication.Page.User_main;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.extended_func.Intent_flag;

/**
 * author：created by zxf
 * email：1463149917@qq.com
 * csdn: https://blog.csdn.net/qq_44387002
 * date：2021/12/14 12
 */
public class User_new extends Activity implements View.OnClickListener{

    private static User_sql userSql;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.user_new);
        userSql = new User_sql(User_new.this, User_sql.DATABASE_NAME, null, User_sql.DATABASE_VERSION);
    }

    //返回管理员的用户管理界面
    @Override
    public void onClick(View view){
        Intent_flag intent_flag = (Intent_flag) getIntent().getSerializableExtra("intentFlag");
        String intentFlag = intent_flag.getIntentFlag();
        Intent intent = new Intent();
        if (intentFlag.equals("admin_user_manage")) {
            intent.setClass(this, Admin_user_manage.class);
        } else if (intentFlag.equals("user_main")) {
            intent.setClass(this, User_main.class);
        }
        startActivity(intent);
    }

    //注册新的用户，注册完成后返回到用户管理界面
    public void onClick1(View view){
        EditText account = findViewById(R.id.user_new_account);
        EditText password = findViewById(R.id.user_new_password);
        CheckBox checkBox = findViewById(R.id.user_new_checkBox);
        String sAccount = account.getText().toString();
        String sPassword = password.getText().toString();

        SQLiteDatabase db = userSql.getReadableDatabase();
        ContentValues cv = new ContentValues();
        String  flag;
        if(!sAccount.equals ("")){
            if(!sPassword.equals ("")) {
                String[] selectionArgs = new String[]{sAccount};
                Cursor cursor = db.query("user", null, "account=?", selectionArgs, null, null, null, null);
                //账号存在,需要重新输入账号注册
                if (cursor != null && cursor.moveToFirst()){
                    Toast.makeText(this, "账号已存在,请重新注册!", Toast.LENGTH_LONG).show();
                    cursor.close();
                }
                //账号不存在,注册成功后返回登录界面
                else {
                    if(checkBox.isChecked())
                        flag = "1";//管理员用户标志
                    else
                        flag = "0";//普通用户标志
                    cv.put("account", sAccount);
                    cv.put("password", sPassword);
                    cv.put("flag",flag);
                    db.insert("user","account", cv);
                    db.close();
                    Toast.makeText(this, "注册成功!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, Admin_user_manage.class);
                    startActivity(intent);
                }
                //密码为空，显示提示信息
            }else
                Toast.makeText(this,"密码不能为空!",Toast.LENGTH_LONG).show();
            //账号输入为空，显示提示信息
        }else
            Toast.makeText(this,"账号、密码不能为空,请重新输入!",Toast.LENGTH_LONG).show();
    }
}

