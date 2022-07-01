package com.example.administrator.myapplication.Page;

import android.app.Activity;
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

public class Login extends Activity implements View.OnClickListener{

    private EditText account,password;
    public static User_sql userSql;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.login);
        account = findViewById(R.id.login_account);
        password = findViewById(R.id.login_password);
        userSql = new User_sql(Login.this, User_sql.DATABASE_NAME, null, User_sql.DATABASE_VERSION);
    }

    //点击"返回"按钮，跳转至HomePage界面
    public void onClick(View view){
        Intent intent = new Intent();
        intent.setClass(this, HomePage.class);
        startActivity(intent);
    }

    //注册账号
    public void onClick1(View view) {
        String sAccount = account.getText().toString();
        String sPassword = password.getText().toString();
        SQLiteDatabase db = userSql.getWritableDatabase();
        if(!sAccount.equals("")){
            if(!sPassword.equals("")){
                if(sAccount.equals("admin") && sPassword.equals("123456")){
                    Intent intent = new Intent(this, Admin_main.class);
                    startActivity(intent);
                }else{
                    Cursor cursor1 = db.query("user",null,"account=?",new String[]{sAccount},null,null,null);
                    if (!cursor1.moveToNext()){
                        Toast.makeText(this, "账号不正确,请重新输入!!", Toast.LENGTH_LONG).show();
                        cursor1.close();
                    }else {
                        Cursor cursor2 = db.query("user", null, "account=? and password=?", new String[]{sAccount, sPassword}, null, null, null);
                        if(!cursor2.moveToNext()){
                            Toast.makeText(this, "密码不正确,请重新输入!!", Toast.LENGTH_LONG).show();
                        }else {//账号+密码输入正确
                            String flag;
                            if(cursor2.moveToFirst()){
                                flag = cursor2.getString(cursor2.getColumnIndex("flag"));
                                Intent intent;
                                if(flag.equals("1")){
                                    intent = new Intent(this, Admin_main.class);
                                }else {
                                    intent = new Intent(this, User_main.class);
                                }
                                intent.putExtra("account", sAccount);//--->user
                                startActivity(intent);
                            }
                        }
                    }
                }

            }else
                Toast.makeText(this, "密码不能为空,请输入!", Toast.LENGTH_LONG).show();
        }else
            Toast.makeText(this, "账号不能为空,请输入!", Toast.LENGTH_LONG).show();
    }

    //点击"注册"按钮,跳转至Register界面
    public void onClick2(View view){
        Intent intent = new Intent();
        intent.setClass(this, Register.class);
        startActivity(intent);
    }

}
