package com.example.administrator.myapplication.Diary_operation;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.Admin_operation.Admin_diary_manage;
import com.example.administrator.myapplication.DB.Diary_share_sql;
import com.example.administrator.myapplication.DB.Diary_sql;
import com.example.administrator.myapplication.Page.HomePage;
import com.example.administrator.myapplication.Page.See_anywhere;
import com.example.administrator.myapplication.Page.User_main;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.extended_func.Func;
import com.example.administrator.myapplication.extended_func.Intent_flag;

/**
 * author：created by zxf
 * email：1463149917@qq.com
 * csdn: https://blog.csdn.net/qq_44387002
 * date：2022/1/6 14
 */
public class Diary_see extends Activity implements View.OnClickListener{
    public static Diary_share_sql diaryShareSql;
    Func func = new Func();

    //实例化界面
    public void onCreate(Bundle bundle) {
        super.onCreate (bundle);
        setContentView (R.layout.diary_see);
        diaryShareSql = new Diary_share_sql(Diary_see.this, Diary_share_sql.DATABASE_NAME, null, Diary_share_sql.DATABASE_VERSION);
        SQLiteDatabase db = diaryShareSql.getWritableDatabase();
        String id = getIntent().getStringExtra("id");
        String[] selectionArgs1 = new String[]{id};
        Cursor cursor1 = db.query("diary_share", null, "share_id=?", selectionArgs1, null, null, null);
        while (cursor1.moveToNext()) {
            String id1 = cursor1.getString(cursor1.getColumnIndex("share_id"));
            String title1 = cursor1.getString(cursor1.getColumnIndex("share_title"));
            String author1 = cursor1.getString(cursor1.getColumnIndex("share_user"));
            String time1 = cursor1.getString(cursor1.getColumnIndex("share_time"));
            String content1 = cursor1.getString(cursor1.getColumnIndex("share_content"));

            TextView sId = findViewById(R.id.diary_share_id);
            TextView sTitle = findViewById(R.id.diary_share_title);
            TextView sAuthor = findViewById(R.id.diary_share_author);
            TextView sTime = findViewById(R.id.diary_share_time);
            TextView sContent = findViewById(R.id.diary_share_content);
            sId.setEnabled(false);
            sTitle.setEnabled(false);
            sAuthor.setEnabled(false);
            sTime.setEnabled(false);
            sContent.setEnabled(false);
            sId.setText(id1);
            sTitle.setText(title1);
            sAuthor.setText(author1);
            sTime.setText(time1);
            sContent.setText(content1);
        }
        Button button = findViewById(R.id.diary_share);
        button.setOnClickListener(this);
    }

    //点击修改，将修改后的界面保存至数据库
/*    @Override
    public void onClick(View view) {
        Intent intent0 = getIntent();
        String id = intent0.getStringExtra("id");
        TextView sTitle = findViewById(R.id.diary_modify_title);
        TextView sAuthor = findViewById(R.id.diary_modify_author);
        TextView sTime = findViewById(R.id.diary_modify_time);
        TextView sContent = findViewById(R.id.diary_modify_content);
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
    }*/

    @Override
    public void onClick(View view){
        Intent_flag intent_flag = (Intent_flag)getIntent().getSerializableExtra("intentFlag");
        String id = getIntent().getStringExtra("id");
        String intentFlag0 = intent_flag.getIntentFlag();
        Intent_flag intentFlag = new Intent_flag(intentFlag0);
        Intent intent = new Intent(Diary_see.this, See_anywhere.class);
        func.setAll(id, null, null, intentFlag, intent);
        startActivity(intent);
    }

}
