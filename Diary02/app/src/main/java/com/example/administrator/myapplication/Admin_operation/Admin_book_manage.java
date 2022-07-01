package com.example.administrator.myapplication.Admin_operation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.administrator.myapplication.Book_operation.Book_add;
import com.example.administrator.myapplication.Book_operation.Book_search;
import com.example.administrator.myapplication.Book_operation.Book_internal_add;
import com.example.administrator.myapplication.Book_operation.Book_out_add;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.Book_operation.Book_chatRoom;
import com.example.administrator.myapplication.Set;
import com.example.administrator.myapplication.Book_operation.Book_intoSql_add;

/**
 * Created by Administrator on 2021/10/15.
 */

public class Admin_book_manage extends Activity {
    private static final int a= Menu.FIRST;
    private static final int b= Menu.FIRST+1;
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.admin_book_manage);
    }
    //图书查询
    public void click1(View view1){
        Intent i=new Intent(Admin_book_manage.this, Book_search.class);
        Admin_book_manage.this.startActivity(i);
    }
    //图书添加
    public void click2(View view2){
        Intent i=new Intent(Admin_book_manage.this, Book_add.class);
        Admin_book_manage.this.startActivity(i);
    }
    //将数据添加到内部
    public void click3(View view3){
        Intent i=new Intent(Admin_book_manage.this, Book_internal_add.class);
        Admin_book_manage.this.startActivity(i);
    }
    //将数据添加到外部
    public void click4(View view4){
        Intent i=new Intent(Admin_book_manage.this, Book_out_add.class);
        Admin_book_manage.this.startActivity(i);
    }
    //将数据添加到SQL数据库
    public void click5(View view5){
        Intent i=new Intent(Admin_book_manage.this, Book_intoSql_add.class);
        Admin_book_manage.this.startActivity(i);
    }
    //聊天室
    public void click6(View view5){
        Intent k=new Intent(Admin_book_manage.this, Book_chatRoom.class);
        Admin_book_manage.this.startActivity(k);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(0,a,0,"退出");
        menu.add(0,b,0,"设置");
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem menuItem){
        if (menuItem.getItemId()==b) {
            Intent intent = new Intent();
            intent.setClass(Admin_book_manage.this, Set.class);
           Admin_book_manage.this.startActivity(intent);
        }
        else
            this.finish();
        return true;

    }
}
