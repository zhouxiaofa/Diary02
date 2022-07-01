package com.example.administrator.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.SubMenu;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2021/10/12.
 */

public class A extends Activity {
    private LinearLayout d;
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.book_search);
        d=(LinearLayout)findViewById(R.id.line1);
        registerForContextMenu(d);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        SubMenu i=menu.addSubMenu(1,1,1,"首页");
        SubMenu j=menu.addSubMenu(1,2,2,"退出");
        return  true;
    }
    public void onCreateContextMenu(ContextMenu menu, View view,ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,view,menuInfo);
        menu.add(1,3,3,"借书");
        menu.add(1,4,4,"详情");
        menu.add(1,5,5,"删除");
        menu.add(1,6,6,"修改");
    }
}
