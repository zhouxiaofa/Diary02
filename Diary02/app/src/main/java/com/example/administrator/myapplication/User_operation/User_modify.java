package com.example.administrator.myapplication.User_operation;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
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
 * date：2021/12/16 10
 */
public class User_modify extends Activity implements View.OnClickListener{

    public static User_sql userSql;

    public void onCreate(Bundle bundle) {
        super.onCreate (bundle);
        setContentView (R.layout.user_modify);
        userSql = new User_sql(User_modify.this, User_sql.DATABASE_NAME, null, User_sql.DATABASE_VERSION);
        SQLiteDatabase db = userSql.getWritableDatabase();

        String account = getIntent().getStringExtra("account");
        Cursor cursor = db.query("user", null, "account=?", new String[]{account}, null, null, null);
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("password"));

        TextView sAccount = findViewById(R.id.user_modify_account);
        sAccount.setEnabled(false);
        TextView sPassword = findViewById(R.id.user_modify_password);

        sAccount.setText(account);
        sPassword.setText(password);
    }

    //点击修改，将修改后的界面保存至数据库
    @Override
    public void onClick(View view) {
        String account = getIntent().getStringExtra("account");
        SQLiteDatabase db = userSql.getWritableDatabase();
        Cursor cursor = db.query("user", null, "account=?", new String[]{account}, null, null,null);
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("password"));
        TextView sPassword = findViewById(R.id.user_modify_password);
        String password1 = sPassword.getText ().toString ();
        if(password.equals(password1)){
            Toast.makeText(this, "密码未作修改", Toast.LENGTH_LONG).show();
        }else {
            String sql = "update user set password='" + password1 + "' where account='" + account + "'";
            db.execSQL(sql);
            Toast.makeText(this, "密码修改成功!", Toast.LENGTH_LONG).show();
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
    }

    public void onClick1(View view){
        Intent_flag intent_flag = (Intent_flag) getIntent().getSerializableExtra("intentFlag");
        String intentFlag = intent_flag.getIntentFlag();
        Intent intent = new Intent();
        if (intentFlag.equals("admin_user_manage")) {
            intent.setClass(this, Admin_user_manage.class);
        } else if (intentFlag.equals("user_main")) {
            intent.setClass(this, User_main.class);
        }else if(intentFlag.equals("user_search")) {
            intent.setClass(this, User_search.class);
        }
        startActivity(intent);
    }
}
