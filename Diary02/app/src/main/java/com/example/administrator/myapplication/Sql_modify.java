package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.Book_operation.Book_search;
import com.example.administrator.myapplication.DB.Book_sql;

/**
 * Created by Administrator on 2021/11/16.
 */

public class Sql_modify extends Activity implements View.OnClickListener{
    String numb;
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.sql_modify);
        Intent intent=getIntent();
        numb=intent.getStringExtra("numb");
        String name=intent.getStringExtra("name");
        String author=intent.getStringExtra("author");
        String location=intent.getStringExtra("location");
        TextView tnumb=(TextView)findViewById(R.id.sql_modify1);
        TextView tname=(TextView)findViewById(R.id.sql_modify2);
        TextView tauthor=(TextView)findViewById(R.id.sql_modify3);
        TextView tlocation=(TextView)findViewById(R.id.sql_modify4);
        tnumb.setText(numb);
        tname.setText(name);
        tauthor.setText(author);
        tlocation.setText(location);
        Button button=(Button)findViewById(R.id.sqlmodify);
        button.setOnClickListener(this);

    }
    public void onClick(View view){
        TextView tnumb=(TextView)findViewById(R.id.sql_modify1);
        TextView tname=(TextView)findViewById(R.id.sql_modify2);
        TextView tauthor=(TextView)findViewById(R.id.sql_modify3);
        TextView tlocation=(TextView)findViewById(R.id.sql_modify4);
        String numb=tnumb.getText().toString();
        String name=tname.getText().toString();
        String author=tauthor.getText().toString();
        String location=tlocation.getText().toString();
        Book_sql dbc=new Book_sql(this);
        dbc.db.execSQL("update book set numb='"+numb+"',name='" + name + "',author='" + author + "',location='" + location + "' where numb='"+this.numb+"'");
        Toast.makeText(this, "成功修改数据", Toast.LENGTH_LONG).show();
        Intent intent=new Intent(this, Book_search.class);
        startActivity(intent);
    }


}
