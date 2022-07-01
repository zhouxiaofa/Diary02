package com.example.administrator.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Administrator on 2021/11/19.
 */

public class RoomTheard extends Thread {
    Handler h;
    String name;

    OutputStream out;
    InputStream in;

    public RoomTheard(Handler h, String name) {
        this.h = h;
        this.name = name;
    }

    public void run() {
        try {
            Socket  s = new Socket("10.0.2.2", 8008);
            out = s.getOutputStream();
            in = s.getInputStream();
            Log.e("22222",(name + "上线了\n"));
            out.write((name + "上线了\n").getBytes());
            Log.e("33333",(name + "上线了\n"));
            while (true) {
                int x = 0;
                byte[] buffer = new byte[1024];
                String str="";
                while ((x=in.read(buffer))!=-1){
                    str+=new String(buffer,0,x);
                    Log.e("4444444",str);
                    if(str.endsWith("\n")){
                        break;
                    }}
                Log.e("5555555555",str);
                Bundle bundle=new Bundle();
                bundle.putString("str",str);
                Message message=new Message();
                message.setData(bundle);
                h.sendMessage (message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    //发送数据
    public void send(String str){
        try {
            out.write((name +":"+str+"\n").getBytes());
        } catch (IOException e) {
        }
    }
}
