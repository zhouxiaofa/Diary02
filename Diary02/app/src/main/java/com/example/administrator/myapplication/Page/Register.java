package com.example.administrator.myapplication.Page;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.myapplication.DB.User_sql;
import com.example.administrator.myapplication.R;

/**
 * Created by Administrator on 2021/11/22.
 */

public class Register extends Activity implements View.OnClickListener{

    public static User_sql userSql;

   @Override
    public void onCreate(Bundle bundle){
        super.onCreate (bundle);
        setContentView (R.layout.register);
        userSql = new User_sql(Register.this, User_sql.DATABASE_NAME, null, User_sql.DATABASE_VERSION);
    }

    //点击"返回"按钮，返回HomePage页面
    public void onClick(View view){
        this.finish ();
    }

    //注册账号
    public void onClick1(View view) {
        Cursor cursor;
        EditText account = findViewById(R.id.register_account);
        EditText password = findViewById(R.id.register_password);
        String sAccount = account.getText().toString();
        String sPassword = password.getText().toString();
        System.out.println(sAccount);
        System.out.println(sPassword);

        SQLiteDatabase db = userSql.getWritableDatabase();
        if(!sAccount.equals ("")){
            if(!sPassword.equals ("")) {
                String[] selectionArgs = new String[]{sAccount};
                cursor = db.query("user", null, "account=?", selectionArgs, null, null, null, null);
                if (cursor != null && cursor.moveToFirst()){//账号存在,需要重新输入账号注册
                    Toast.makeText(this, "账号已存在,请重新注册!", Toast.LENGTH_LONG).show();
                    cursor.close();
                }
                else {//账号不存在,注册成功后返回登录界面
                    ContentValues cv = new ContentValues();
                    cv.put("account", sAccount);
                    cv.put("password", sPassword);
                    cv.put("flag","0");
                    db.insert("user","account", cv);
                    db.close();
                    Toast.makeText(this, "注册成功!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, Login.class);
                    startActivity(intent);
                }
            }else//密码为空，显示提示信息
                Toast.makeText(this,"密码不能为空!",Toast.LENGTH_LONG).show();
        }else//账号输入为空，显示提示信息
            Toast.makeText(this,"账号、密码不能为空,请重新输入!",Toast.LENGTH_LONG).show();
    }

}
