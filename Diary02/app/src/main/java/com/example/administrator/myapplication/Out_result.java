package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Administrator on 2021/11/3.
 */

public class Out_result extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.out_result);
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File f = Environment.getExternalStorageDirectory();
            String data = "";
            try {
                FileInputStream out = new FileInputStream(f.getAbsolutePath() + "/out.txt");
                byte[] buffer = new byte[10];
                int x = 0;
                while ((x = out.read(buffer)) != -1) {
                    data += new String(buffer, 0, x);
                }
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            TextView tv = (TextView) findViewById(R.id.out_result1);
            tv.setText(data);
        }
    }
}





