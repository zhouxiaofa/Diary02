package com.example.administrator.myapplication.Page;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.Admin_operation.Admin_diary_manage;
import com.example.administrator.myapplication.DB.Diary_share_sql;
import com.example.administrator.myapplication.DB.Diary_sql;
import com.example.administrator.myapplication.DB.User_sql;
import com.example.administrator.myapplication.Diary_operation.Diary_modify;
import com.example.administrator.myapplication.Diary_operation.Diary_search;
import com.example.administrator.myapplication.Diary_operation.Diary_see;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.extended_func.Func;
import com.example.administrator.myapplication.extended_func.Intent_flag;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.myapplication.DB.User_sql.DATABASE_NAME;
import static com.example.administrator.myapplication.DB.User_sql.DATABASE_VERSION;

/**
 * author：created by zxf
 * email：1463149917@qq.com
 * csdn: https://blog.csdn.net/qq_44387002
 * date：2021/12/20 16
 */
public class See_anywhere extends Activity implements View.OnClickListener{

    public static Diary_share_sql diaryShareSql;
    private SQLiteDatabase db;
    private List<String> diaryShare = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView listView;
    TableRow t_r;
    Func func = new Func();
    private String select_item;
    Intent_flag intentFlag = new Intent_flag();

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.see_anywhere);
        diaryShareSql = new Diary_share_sql(See_anywhere.this, Diary_share_sql.DATABASE_NAME, null, Diary_share_sql.DATABASE_VERSION);
        init();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String id1 = getDiaryId1(position);
                //ListView点击进入日记修改界面
                Intent_flag intent_flag = (Intent_flag)getIntent().getSerializableExtra("intentFlag");
                String intentFlag0 = intent_flag.getIntentFlag();
                intentFlag.setIntentFlag(intentFlag0);
                Intent intent = new Intent(See_anywhere.this, Diary_see.class);
                func.setAll(id1, null, null, intentFlag,intent);
                startActivity(intent);
            }
        });
    }

    //获取所点击日记的id(显示参数为ID)
    private String getDiaryId1(int position){
        select_item = diaryShare.get(position);
        return select_item;
    }

    //返回上一级界面
    @Override
    public void onClick(View view) {
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

    private void init(){
        db = diaryShareSql.getWritableDatabase();
        diaryShare.clear();
        //查询数据库，将title一列添加到列表项目中
        Cursor cursor = db.query("diary_share",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            String share_id;
            do{
                share_id = cursor.getString(cursor.getColumnIndex("share_id"));
                diaryShare.add(share_id);
            }while(cursor.moveToNext());
        }
        cursor.close();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, diaryShare);
        listView = findViewById(R.id.see_anywhere_lv1);
        listView.setAdapter(adapter);
    }

    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0, 0, 0, "删除");
        menu.add(0, 1, 0, "修改");
        t_r = (TableRow)view ;
        super.onCreateContextMenu(menu, view, menuInfo);
    }

    //点击删除修改发生的事件
    public boolean onContextItemSelected(MenuItem menuItem) {
        SQLiteDatabase db = diaryShareSql.getWritableDatabase();
        switch (menuItem.getItemId()) {
            case 0://点击删除
                TextView id0 = (TextView)t_r.getChildAt(0);
                String sId0 = id0.getText().toString();
                db.delete("diary", "id=?", new String[]{sId0});
                Toast.makeText(this, "删除数据成功!", Toast.LENGTH_LONG).show();
                break;

            case 1://点击修改
                TextView id1 =(TextView)t_r.getChildAt(0);
                String sId1 = id1.getText().toString();
                Intent_flag intentFlag = new Intent_flag();
                intentFlag.setIntentFlag("admin_diary_manage");
                Intent intent = new Intent(this, Diary_modify.class);
                func.setAll(sId1, null, null, intentFlag, intent);
                startActivity(intent);
                break;
        }
        return true;
    }
}
