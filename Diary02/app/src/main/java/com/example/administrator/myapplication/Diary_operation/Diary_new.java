package com.example.administrator.myapplication.Diary_operation;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.DB.Diary_sql;
import com.example.administrator.myapplication.Page.HomePage;
import com.example.administrator.myapplication.Page.User_main;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.Admin_operation.Admin_diary_manage;
import com.example.administrator.myapplication.extended_func.Func;
import com.example.administrator.myapplication.extended_func.Intent_flag;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2021/12/1.
 */

public class Diary_new extends Activity implements View.OnClickListener{

    public static Diary_sql diarySql;
    public String authorName;
    Func func = new Func();
    TextView author,time;
    EditText title1,author1,time1,content1;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate (bundle);
        setContentView (R.layout.diary_new);
        diarySql = new Diary_sql(this, Diary_sql.DATABASE_NAME, null, Diary_sql.DATABASE_VERSION);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("yyyy年MM月dd日");
        Date date = new Date(System.currentTimeMillis ());
        String str1 = simpleDateFormat.format(date);
        time = findViewById (R.id.diary_new_time);//时间
        time.setText (str1);

        authorName = getIntent().getStringExtra("loginUser");
        author = findViewById(R.id.diary_new_author);
        author.setText(authorName);

        TextView[] t = new TextView[2];
        t[0] = findViewById(R.id.diary_new_title);
        t[1] = findViewById(R.id.diary_new_content);
        func.setText(t, 2);
    }

    //点击"保存"按钮
    public void onClick(View view) {

        title1 = findViewById (R.id.diary_new_title);
        author1 = findViewById(R.id.diary_new_author);
        time1 = findViewById (R.id.diary_new_time);
        content1 = findViewById (R.id.diary_new_content);
        String curTitle = title1.getText ().toString ();
        String curAuthor = author1.getText().toString();
        String curTime = time1.getText ().toString ();
        String curContent = content1.getText ().toString ();

        SQLiteDatabase db = diarySql.getWritableDatabase();
        if(!curTitle.equals ("")){
            if(!curContent.equals ("")) {
                String user = getIntent().getStringExtra("loginUser");
                Intent_flag intent_flag = (Intent_flag) getIntent().getSerializableExtra("intentFlag");
                String flag = intent_flag.getIntentFlag();
                String[] selectionArgs1 = new String[]{curTitle, user};
                Cursor cursor = db.query("diary", null, "title=? and user=?", selectionArgs1, null, null, null);
                if(cursor.moveToFirst()){
                    Toast.makeText (this, "存在相同标题日记,请修改标题再存储!", Toast.LENGTH_LONG).show ();
                }else{
                    db.execSQL ("insert into diary(title,author,user,time,content) values('" + curTitle + "','" + curAuthor + "','" + user + "','" + curTime + "','" + curContent + "')");
                    Toast.makeText (this, "保存成功!", Toast.LENGTH_LONG).show ();
                }
                Intent intent;
                System.out.println(flag);
                if(flag.equals("admin_diary_manage")){
                    intent = new Intent(this, Admin_diary_manage.class);
                }else if(flag.equals("user_main")){
                    intent = new Intent(this, User_main.class);
                }else {
                    intent = new Intent(this, HomePage.class);
                }
                startActivity(intent);
            } else
                Toast.makeText (this, "日记内容不能为空呀宝!", Toast.LENGTH_LONG).show ();
        } else
            Toast.makeText (this, "日记标题不能为空呀宝!", Toast.LENGTH_LONG).show ();
    }

    //点击"返回个人界面"按钮
    public void onClick1(View view){
        Intent_flag intentFlag = (Intent_flag)getIntent().getSerializableExtra("intentFlag");
        String flag = intentFlag.getIntentFlag();
        Intent intent;
        if(flag.equals("admin_diary_manage")){
            intent = new Intent(this, Admin_diary_manage.class);
        }else if(flag.equals("user_main")){
            intent = new Intent(this, User_main.class);
        }else {
            intent = new Intent(this, HomePage.class);
        }
        startActivity(intent);
    }

}
