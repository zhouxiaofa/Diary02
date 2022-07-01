package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Administrator on 2021/10/29.
 */

public class internal_result extends Activity {
    protected void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.internal_result);
        Context c=getBaseContext();
        byte[] buffer=new byte[10];
        String data="";
        try{
            FileInputStream in=c.openFileInput("internal.txt");
            int x=0;
            while((x=in.read(buffer))!=-1){
                data +=new String(buffer,0,x);
            }
            in.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        TextView tv=(TextView)findViewById(R.id.internal_result1);
        tv.setText(data);
    }
}





