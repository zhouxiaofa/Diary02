package com.example.administrator.myapplication.User_operation;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.Admin_operation.Admin_diary_manage;
import com.example.administrator.myapplication.Admin_operation.Admin_user_manage;
import com.example.administrator.myapplication.DB.Diary_share_sql;
import com.example.administrator.myapplication.DB.Diary_sql;
import com.example.administrator.myapplication.DB.User_sql;
import com.example.administrator.myapplication.Diary_operation.Diary_modify;
import com.example.administrator.myapplication.Diary_operation.Diary_search;
import com.example.administrator.myapplication.Diary_operation.Diary_see;
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
 * date：2022/1/17 10
 */
public class User_search extends Activity implements View.OnClickListener{

    public static User_sql userSql;
    SQLiteDatabase user_db;
    TableLayout tableLayout;
    EditText account;
    String sAccount;
    TableRow t_r;
    Func func = new Func();
    Intent_flag intentFlag = new Intent_flag("user_search");

    //实例化界面
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.user_search);
        tableLayout = findViewById(R.id.user_search_tl1);
        account = findViewById (R.id.user_search_searchTitle);
        userSql = new User_sql(User_search.this, User_sql.DATABASE_NAME, null, User_sql.DATABASE_VERSION);
    }

    //删除、修改、分享菜单
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0, 0, 0, "删除");
        menu.add(0, 1, 0, "修改");
        t_r = (TableRow)view ;
        super.onCreateContextMenu(menu, view, menuInfo);
    }

    //点击删除、修改、分享至圈发生的事件
    public boolean onContextItemSelected(MenuItem menuItem) {
        user_db = userSql.getWritableDatabase();
        TextView  id = (TextView)t_r.getChildAt(0);
        String sId = id.getText().toString();//user账号
        switch (menuItem.getItemId()) {
            case 0://删除user
                user_db.delete("user", "id=?", new String[]{sId});
                Toast.makeText(this, "删除user成功!", Toast.LENGTH_LONG).show();
                break;

            case 1://修改user
                Intent intent1 = new Intent(this, User_modify.class);
                func.setAll(sId, null, null, intentFlag, intent1);
                startActivity(intent1);
                break;
        }
        return true;
    }

    //点击日记搜索按钮
    public void onClick(View view) {
        tableLayout.removeAllViews();
        String where_state = "";
        sAccount = account.getText().toString();
        String curAccount;
        if(!sAccount.equals("")){
            curAccount ="%" + sAccount + "%";
            where_state = "account like ?";
            show(where_state, new String[]{curAccount});
        }else {
            Toast.makeText(this,"请输入账号进行查询!",Toast.LENGTH_LONG).show();
        }
        account.setText("");
    }

    //点击返回上一级按钮
    public void onClick1(View view) {
        String user = getIntent().getStringExtra("account");
        Intent intent = new Intent (this, Admin_user_manage.class);
        func.setAll(null, sAccount, sAccount, intentFlag, intent);
        startActivity (intent);
    }

    //刷新
    public void show(String selection, String[] selectionArgs) {
        SQLiteDatabase db = userSql.getWritableDatabase();
        TextView tv_id, tv_account, tv_password, tv_flag;
        Cursor c = db.query("user", null, selection, selectionArgs, null, null, null, null);
        int i = 0;
        if(c.moveToFirst()){
            do{
                String[] info = func.getUserInfoByCursor(c, "id", "account", "password", "flag");
                TableRow tableRow = new TableRow(this);
                tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.MATCH_PARENT,4));

                tv_id = new TextView(this);
                tv_account = new TextView(this);
                tv_password = new TextView(this);
                tv_flag = new TextView(this);

                TextView[] trs = new TextView[]{tv_id, tv_account, tv_password, tv_flag};
                func.setTableRowView(trs, 4, info);
                func.addTableRow(tableRow, trs, 4);
                tableLayout.addView(tableRow);
                registerForContextMenu(tableRow);
                i++;
                //确定菜单范围
            }while (c.moveToNext());
        }
        TextView tv = findViewById(R.id.user_search_select);
        tv.setText("查询结果共" + i + "条");
    }
}
