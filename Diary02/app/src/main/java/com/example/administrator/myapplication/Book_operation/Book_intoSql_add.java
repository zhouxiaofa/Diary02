package com.example.administrator.myapplication.Book_operation;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.myapplication.DB.Book_sql;
import com.example.administrator.myapplication.R;

/**
 * Created by Administrator on 2021/11/11.
 */
public class Book_intoSql_add extends Activity implements View.OnClickListener {

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.book_intosql_add);
        Button button = findViewById(R.id.sqladd);
        button.setOnClickListener(this);

    }

    public void onClick(View v) {
        Book_sql dbc = new Book_sql(this);
        EditText numb = (EditText) findViewById(R.id.sql_add1);
        EditText name = (EditText) findViewById(R.id.sql_add2);
        EditText author = (EditText) findViewById(R.id.sql_add3);
        EditText location = (EditText) findViewById(R.id.sql_add4);
        String snumb = numb.getText().toString();
        String sname = name.getText().toString();
        String sauthor = author.getText().toString();
        String slocation = location.getText().toString();
        if (!numb.getText().toString().equals("")) {
            if (!name.getText().toString().equals("")) {
                if (!author.getText().toString().equals("")) {
                    if (!location.getText().toString().equals("")) {
                        dbc.db.execSQL("insert into book(numb,name,author,location)values('" + snumb + "','" + sname + "','" + sauthor + "','" + slocation + "')");
                        Toast.makeText(Book_intoSql_add.this, "成功添加到SQL", Toast.LENGTH_LONG).show();
                        numb.setText("");
                        name.setText("");
                        author.setText("");
                        location.setText("");
                    } else Toast.makeText(Book_intoSql_add.this, "馆藏地址不能为空", Toast.LENGTH_LONG).show();
                } else Toast.makeText(Book_intoSql_add.this, "作者姓名不能为空", Toast.LENGTH_LONG).show();
            } else Toast.makeText(Book_intoSql_add.this, "图书名称不能为空", Toast.LENGTH_LONG).show();
        } else Toast.makeText(Book_intoSql_add.this, "图书编号不能为空", Toast.LENGTH_LONG).show();
    }

    public void onClick1(View v) {
        Intent intent = new Intent(Book_intoSql_add.this, Book_search.class);
        Book_intoSql_add.this.startActivity(intent);

    }
}