package com.example.administrator.myapplication.Book_operation;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.RoomTheard;


/**
 * Created by Administrator on 2021/11/19.
 */

public class Book_chatRoom extends Activity implements View.OnClickListener {
    TextView t_Room;

    RoomTheard rt;
    Handler handler = new Handler() {//解封装
        public void handleMessage(Message m) {
            Bundle bundle = m.getData();
            String str = bundle.getString("str");
            Log.e("1111",str);
            t_Room.append(str);
        }
    };

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.book_chatroom);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String name = sp.getString("name", "007");
        Button  button = (Button) findViewById(R.id.room_b);
        button.setOnClickListener(this);
        t_Room = (TextView) findViewById(R.id.room_tv1);
        rt = new RoomTheard(handler, name);
        rt.start();
    }

    @Override
    public void onClick(View view) {
        EditText e = (EditText) findViewById(R.id.room_e);
        String str=e.getText().toString();
        rt.send(str);
        e.setText("");
    }
}
