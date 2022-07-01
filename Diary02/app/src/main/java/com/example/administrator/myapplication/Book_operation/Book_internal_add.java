package com.example.administrator.myapplication.Book_operation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.internal_result;

import java.io.FileOutputStream;

/**
 * Created by Administrator on 2021/10/29.
 */

public  class Book_internal_add extends Activity  {
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.book_internal_add);
        Button bb = (Button) findViewById(R.id.internaladd);
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText id = (EditText) findViewById(R.id.internal_add1);
                EditText name = (EditText) findViewById(R.id.internal_add2);
                EditText author = (EditText) findViewById(R.id.internal_add3);
                EditText location = (EditText) findViewById(R.id.internal_add4);
                Context c = getBaseContext();
                try {
                    FileOutputStream out = c.openFileOutput("internal.txt", Book_internal_add.MODE_WORLD_WRITEABLE);
                    String data = id.getText().toString() + "," + name.getText().toString() + "," + author.getText().toString() + "," + location.getText().toString();
                    out.write(data.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(Book_internal_add.this, internal_result.class);
                startActivity(i);
            }
        });
    }
}


