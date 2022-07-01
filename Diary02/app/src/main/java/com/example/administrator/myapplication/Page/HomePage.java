package com.example.administrator.myapplication.Page;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myapplication.R;

import java.io.File;

/**
 * Created by Administrator on 2021/12/6.
 */

public class HomePage extends AppCompatActivity {

    private Button login,register,seeAny;

    @Override
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.homepage);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        seeAny = findViewById(R.id.seeAny);

        //点击"登录"按钮
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*                File[] files = new File[6];
                files[3] = new File("/data/data/com.example.administrator.myapplication/databases/diary_share.db");
                files[3].delete();
                files[2] = new File("/data/data/com.example.administrator.myapplication/databases/diary_share.db-journal");
                files[2].delete();*/
                Intent intent = new Intent (HomePage.this, Login.class);
                startActivity(intent);
            }
        });

        //点击"注册"按钮
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (HomePage.this, Register.class);
                startActivity(intent);
            }
        });

        //点击"随便看看"按钮
        seeAny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (HomePage.this, See_anywhere.class);
                startActivity(intent);
            }
        });
    }

}
