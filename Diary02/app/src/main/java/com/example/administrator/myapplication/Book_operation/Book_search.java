package com.example.administrator.myapplication.Book_operation;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.Admin_operation.Admin_book_manage;
import com.example.administrator.myapplication.DB.Book_sql;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.Sql_modify;

import java.util.ArrayList;

/**
 * Created by Administrator on 2013/1/2.
 */
public class Book_search extends Activity {
    private EditText e1;
    private EditText e2;
    private EditText e3;
    private RadioButton bu1;
    private RadioButton bu2;
    TableRow t_r;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.book_search);
        e1 = (EditText) findViewById(R.id.edit1);
        e2 = (EditText) findViewById(R.id.edit2);
        e3 = (EditText) findViewById(R.id.edit3);
        bu1 = (RadioButton) findViewById(R.id.rb1);
        bu2 = (RadioButton) findViewById(R.id.rb2);
        show(null, null);

    }

    public void onClick(View view) {
        Intent i = new Intent(this, Admin_book_manage.class);
        Book_search.this.startActivity(i);
    }

    public void onClick1(View v) {
        e1.getText().clear();
        e2.getText().clear();
        e3.getText().clear();
        bu1.setChecked(false);
        bu2.setChecked(false);
        show(null,null);
    }
    public  void onClick2(View view){
        String where_state =" 1=1 ";
        EditText numb =(EditText)findViewById(R.id.edit1);
        EditText name =(EditText)findViewById(R.id.edit2);
        EditText author=(EditText)findViewById(R.id.edit3);
        ArrayList<String> list =new ArrayList<String>();
        if(!numb.getText().toString().equals("")){
            where_state+= "  and  numb  like  ?";
            list.add("%"+numb.getText().toString()+"%");
        }
        if(!name.getText().toString().equals("")){
            where_state+= "  and  name  like  ?";
            list.add("%"+name.getText().toString()+"%");
        }
        if(!author.getText().toString().equals("")){
            where_state+= "  and  author  like  ?";
            list.add("%"+author.getText().toString()+"%");
        }
        String[] arg4 =new String[list.size()];
        list.toArray(arg4);
        show(where_state, arg4);
        Toast.makeText(this,"查询成功",Toast.LENGTH_LONG).show();
        numb.setText("");
        name.setText("");
        author.setText("");
    }

    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0, 1, 0, "删除");
        menu.add(0, 2, 0, "修改");
        t_r=(TableRow)view ;
        //tableLayout1= (TableLayout) view;
        super.onCreateContextMenu(menu, view, menuInfo);
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 1:
                TextView textView = (TextView) t_r.getChildAt(0);
                String numb = textView.getText().toString();
                Book_sql dbc = new Book_sql(this);
                dbc.db.execSQL("delete from book where numb='" + numb + "'");
                Toast.makeText(this, "成功删除数据", Toast.LENGTH_LONG).show();
                show(null, null);
                break;
            case 2:
                TextView qnumb = ( TextView) t_r.getChildAt(0);
                TextView qname = ( TextView) t_r.getChildAt(1);
                TextView qauthor = ( TextView) t_r.getChildAt(2);
                TextView qlocation = ( TextView)t_r.getChildAt(3);
                String q_numb = qnumb.getText().toString();
                String q_name = qname.getText().toString();
                String q_author = qauthor.getText().toString();
                String q_location = qlocation.getText().toString();
                Intent intent = new Intent(this, Sql_modify.class);
                intent.putExtra("numb", q_numb);
                intent.putExtra("name", q_name);
                intent.putExtra("author", q_author);
                intent.putExtra("location", q_location);
                startActivity(intent);
                break;
        }

        return true;
    }

    public  void show(String where, String[] arg4) {
        Book_sql dbc = new Book_sql(this);
        TableLayout table=(TableLayout) findViewById(R.id.tl) ;
        Cursor c = dbc.db.query("book", null, where, arg4, null, null, null, null);
        for (int i = 1; i < table.getChildCount() - 1; ) {
            table.removeViewAt(i);
        }
        int i = 0;
        while (c.moveToNext()) {
            int num_snumb = c.getColumnIndex("numb");
            String snumb = c.getString(num_snumb);
            int num_sname = c.getColumnIndex("name");
            String sname = c.getString(num_sname);
            int num_sauthor = c.getColumnIndex("author");
            String sauthor = c.getString(num_sauthor);
            int num_slocation = c.getColumnIndex("location");
            String slocation = c.getString(num_slocation);
            TextView tv_numb = new TextView(this);
            tv_numb.setText(snumb);
            tv_numb.setTextSize(20);
            TextView tv_name = new TextView(this);
            tv_name.setText(sname);
            tv_name.setTextSize(20);
            TextView tv_author = new TextView(this);
            tv_author.setText(sauthor);
            tv_author.setTextSize(20);
            TextView tv_location = new TextView(this);
            tv_location.setText(slocation);
            tv_location.setTextSize(20);
            TableRow tableRow = new TableRow(this);
            tableRow.addView(tv_numb);
            tableRow.addView(tv_name);
            tableRow.addView(tv_author);
            tableRow.addView(tv_location);
            table.addView(tableRow, 1);
            //tableLayout.addView(tableRow, 1);
            i++;
            registerForContextMenu(tableRow);//确定菜单范围
        }
        TextView tv = (TextView) findViewById(R.id.tv_select);
        tv.setText("查询结果共" + i + "条");
    }

}