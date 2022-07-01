package com.example.administrator.myapplication.Book_operation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.myapplication.Out_result;
import com.example.administrator.myapplication.R;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Administrator on 2021/10/29.
 */

public class Book_out_add extends Activity implements View.OnClickListener {
    protected void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.book_out_add);
        Button bb2 = (Button) findViewById(R.id.out_add);
        bb2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText numb=(EditText)findViewById(R.id.out_addedit1);
        EditText name=(EditText)findViewById(R.id.out_addedit2);
        EditText author=(EditText)findViewById(R.id.out_addedit3);
        EditText location=(EditText)findViewById(R.id.out_addedit4);
        String data=numb.getText().toString()+","+name.getText().toString()+","+author.getText().toString()+","+location.getText().toString();
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File f=Environment.getExternalStorageDirectory();
            try {
                FileOutputStream out=new FileOutputStream(f.getAbsolutePath()+"/out.txt");
                out.write(data.getBytes());
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Intent i=new Intent(Book_out_add.this, Out_result.class);
            startActivity(i);
        }
    }
}



