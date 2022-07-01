package com.example.administrator.myapplication.Diary_operation;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.Admin_operation.Admin_diary_manage;
import com.example.administrator.myapplication.DB.Diary_share_sql;
import com.example.administrator.myapplication.DB.Diary_sql;
import com.example.administrator.myapplication.Page.HomePage;
import com.example.administrator.myapplication.Page.Login;
import com.example.administrator.myapplication.Page.See_anywhere;
import com.example.administrator.myapplication.Page.User_main;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.User_operation.User_modify;
import com.example.administrator.myapplication.extended_func.Func;
import com.example.administrator.myapplication.extended_func.Intent_flag;

/**
 * author：created by zxf
 * email：1463149917@qq.com
 * csdn: https://blog.csdn.net/qq_44387002
 * date：2022/1/11 15
 */
public class Diary_search extends Activity implements View.OnClickListener{

    public static Diary_sql diarySql;
    public static Diary_share_sql diaryShareSql;
    SQLiteDatabase diary_share_db,diary_db;
    TableLayout tableLayout;
    EditText title,content;
    public String account,user;
    TableRow t_r;
    Func func = new Func();
    Intent_flag intentFlag = new Intent_flag("diary_search");

    //实例化界面
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.diary_search);
        tableLayout = findViewById(R.id.diary_search_tl1);
        title = findViewById (R.id.diary_search_searchTitle);
        content = findViewById (R.id.diary_search_searchContent);
        diarySql = new Diary_sql(Diary_search.this, Diary_sql.DATABASE_NAME, null, Diary_sql.DATABASE_VERSION);
        diaryShareSql = new Diary_share_sql(Diary_search.this, Diary_share_sql.DATABASE_NAME, null, Diary_share_sql.DATABASE_VERSION);
    }

    //删除、修改、分享菜单
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0, 0, 0, "删除");
        menu.add(0, 1, 0, "修改");
        menu.add(0, 2, 0, "分享至圈");
        t_r = (TableRow)view ;
        super.onCreateContextMenu(menu, view, menuInfo);
    }

    //点击删除、修改、分享至圈发生的事件
    public boolean onContextItemSelected(MenuItem menuItem) {
        diary_db = diarySql.getWritableDatabase();
        diary_share_db = diaryShareSql.getWritableDatabase();
        intentFlag.setIntentFlag("user_main");
        String user = getIntent().getStringExtra("account");
        TextView id = (TextView)t_r.getChildAt(0);
        String sId = id.getText().toString();//日记ID
        switch (menuItem.getItemId()) {
            case 0://删除日记
                diary_db.delete("diary", "id=?", new String[]{sId});
                Toast.makeText(this, "删除数据成功!", Toast.LENGTH_LONG).show();
                break;

            case 1://修改日记
                Intent intent1 = new Intent(this, Diary_modify.class);
                func.setAll(sId, null, null, intentFlag, intent1);
                //func.setIdAndFlag(sId, intentFlag, intent1);
                startActivity(intent1);
                break;

            case 2://添加入圈
                //先查询圈中是否有此条日记
                Cursor cursor2 = diary_share_db.query("diary_share", null, "share_id=?", new String[]{sId}, null, null, null);
                if(cursor2.moveToFirst()){
                    Toast.makeText(this, "已存在!", Toast.LENGTH_LONG).show();
                }else {
                    Cursor cursor = diary_db.query("diary", null, "id=?", new String[]{sId}, null, null,null);
                    cursor.moveToFirst();
                    String[] info = func.getDiaryInfoByCursor(cursor, "id", "title", "author", "time", "content", "user", "changeFlag");
                    //将日记添加到数据库中
                    ContentValues cv = new ContentValues();
                    cv.put("share_title", info[1]);
                    cv.put("share_time", info[3]);
                    cv.put("share_content", info[4]);
                    cv.put("share_user", info[5]);
                    diary_share_db.insert("diary_share", "share_title", cv);
                }
                diary_share_db.close();
                Intent intent2 = new Intent(this, See_anywhere.class);
                func.setAll(sId, user, user, intentFlag, intent2);
                startActivity(intent2);
        }
        return true;
    }

    //点击"日记搜索"按钮
    public void onClick(View view) {
        tableLayout.removeAllViews();
        String where_state = "";
        String sTitle = title.getText().toString();
        String sContent = content.getText().toString();
        user = getIntent().getStringExtra("loginUser");
        String curTitle,curContent;
        if(!sTitle.equals("") && !sContent.equals("")){
            curTitle = "%" + sTitle + "%";
            curContent = "%" + sContent + "%";
            where_state = " title like ? and content like ? and user=?";
            show(where_state, new String[]{curTitle, curContent, user});
            Toast.makeText(this,"通过标题和内容查询成功!",Toast.LENGTH_LONG).show();
        }else if(!sTitle.equals("")){
            curTitle = "%" + sTitle + "%";
            where_state = " title like ? and user=?";
            show(where_state, new String[]{curTitle, user});
            Toast.makeText(this,"通过标题查询成功!",Toast.LENGTH_LONG).show();
        }else if(!sContent.equals("")){
            curContent = "%" + sContent + "%";
            where_state = " content like ? and user=?";
            show(where_state, new String[]{curContent, user});
            Toast.makeText(this,"通过内容查询成功!",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"请输入内容或者标题进行查询!",Toast.LENGTH_LONG).show();
        }
        title.setText("");
        content.setText("");
    }

    //点击"返回上一级"按钮
    public void onClick1(View view) {
        String user = getIntent().getStringExtra("account");
        Intent_flag intentFlag0 = (Intent_flag)getIntent().getSerializableExtra("intentFlag");
        String flag = intentFlag0.getIntentFlag();
        Intent intent;
        if(flag.equals("admin_diary_manage")){
            intent = new Intent(this, Admin_diary_manage.class);
        }else if(flag.equals("user_main")){
            intent = new Intent(this, User_main.class);
        }else {
            intent = new Intent(this, HomePage.class);
        }
        func.setAll(null, user, user, intentFlag, intent);
        startActivity(intent);
    }

    //刷新
    public void show(String selection, String[] selectionArgs) {
        SQLiteDatabase db = diarySql.getWritableDatabase();
        TextView tv_id, tv_title, tv_author, tv_time;
        Cursor c = db.query("diary", null, selection, selectionArgs, null, null, null, null);
        int i = 0;
        if(c.moveToFirst()){
            do{
                String[] info = func.getDiaryInfoByCursor(c, "id", "title", "author", "time", "content", "user", "changeFlag");
                TableRow tableRow = new TableRow(this);
                tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.MATCH_PARENT,4));

                tv_id = new TextView(this);
                tv_title = new TextView(this);
                tv_author = new TextView(this);
                tv_time = new TextView(this);

                TextView[] trs = new TextView[]{tv_id, tv_title, tv_author, tv_time};
                func.setTableRowView(trs, 4, info);
                func.addTableRow(tableRow, trs, 4);
                tableLayout.addView(tableRow);
                registerForContextMenu(tableRow);
                i++;
                //确定菜单范围
            }while (c.moveToNext());
        }
        TextView tv = findViewById(R.id.diary_search_select);
        tv.setText("查询结果共" + i + "条");
    }

}
