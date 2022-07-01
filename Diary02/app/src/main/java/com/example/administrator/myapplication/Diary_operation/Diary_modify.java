package com.example.administrator.myapplication.Diary_operation;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.DB.Diary_sql;
import com.example.administrator.myapplication.Page.HomePage;
import com.example.administrator.myapplication.Page.User_main;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.Admin_operation.Admin_diary_manage;
import com.example.administrator.myapplication.extended_func.Func;
import com.example.administrator.myapplication.extended_func.Intent_flag;


/**
 * Created by Administrator on 2021/12/6.
 */

public class Diary_modify extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{

    private SQLiteDatabase db;
    public static Diary_sql diarySql;
    private Switch switch1;
    private TextView text1;
    private TextView sTitle,sAuthor,sTime,sContent;
    private Button modify_button;
    Func func = new Func();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.diary_modify);
        switch1 = findViewById(R.id.diary_modify_switch);
        switch1.setOnCheckedChangeListener(this);
        text1 = findViewById(R.id.diary_modify_text);
        modify_button = findViewById(R.id.diary_modify_button1);
        modify_button.setOnClickListener(this);
        sTitle = findViewById(R.id.diary_modify_title);
        sAuthor = findViewById(R.id.diary_modify_author);
        sTime = findViewById(R.id.diary_modify_time);
        sContent = findViewById(R.id.diary_modify_content);
        diarySql = new Diary_sql(Diary_modify.this, Diary_sql.DATABASE_NAME, null, Diary_sql.DATABASE_VERSION);
        x();
    }

    private void x(){
        db = diarySql.getWritableDatabase();
        String id = getIntent().getStringExtra("id");
        Cursor cursor1 = db.query("diary", null, "id=?", new String[]{id}, null, null, null);
        while (cursor1.moveToNext()) {
            String[] info = func.getDiaryInfoByCursor(cursor1, "id", "title", "author", "time", "content", "user", "changeFlag");
            sTitle.setText(info[1]);
            sAuthor.setText(info[2]);
            sTime.setText(info[3]);
            sContent.setText(info[4]);
            sTitle.setEnabled(false);
            sAuthor.setEnabled(false);
            sTime.setEnabled(false);
            sContent.setEnabled(false);
            modify_button.setEnabled(false);
        }
    }

    //点击修改，将修改后的界面保存至数据库
    @Override
    public void onClick(View view) {
        Intent intent0 = getIntent();
        String id = intent0.getStringExtra("id");
        String title = sTitle.getText ().toString ();
        String author = sAuthor.getText ().toString ();
        String time = sTime.getText ().toString ();
        String content = sContent.getText ().toString ();
        SQLiteDatabase db = diarySql.getWritableDatabase();
        Cursor cursor = db.query("diary",null, "id=?", new String[]{id}, null, null,null);
        while (cursor.moveToNext()){
            int changeFlag = cursor.getInt(cursor.getColumnIndex("changeFlag"));
            changeFlag += 1;
            String sql = "update diary set title='" + title + "',author='" + author + "',time='" + time + "',content='" + content + "',changeFlag="+ changeFlag + " where id=" + id;
            db.execSQL(sql);
        }
        Toast.makeText(this, "日记信息修改成功!", Toast.LENGTH_LONG).show();
        Intent_flag intent_flag = (Intent_flag)getIntent().getSerializableExtra("intentFlag");
        String intentFlag = intent_flag.getIntentFlag();
        Intent intent = new Intent();
        if(intentFlag.equals("admin_diary_manage")){
            intent.setClass(this, Admin_diary_manage.class);
        }else if(intentFlag.equals("user_main")){
            intent.setClass(this, User_main.class);
        }
        startActivity(intent);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.diary_modify_switch:
                if(isChecked){
                    text1.setText("开");
                    sTitle.setEnabled(true);
                    sAuthor.setEnabled(true);
                    sTime.setEnabled(true);
                    sContent.setEnabled(true);
                    modify_button.setEnabled(true);

                }else {
                    text1.setText("关");
                    sTitle.setEnabled(false);
                    sAuthor.setEnabled(false);
                    sTime.setEnabled(false);
                    sContent.setEnabled(false);
                    modify_button.setEnabled(false);
                }
                break;
            default:
                break;
        }
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
