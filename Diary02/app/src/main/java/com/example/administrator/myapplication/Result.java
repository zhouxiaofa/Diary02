package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.SpannableStringBuilder;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Administrator on 2021/10/15.
 */

public class Result extends Activity {
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.result);
        Intent i1 = getIntent();
        Book book = (Book) i1.getSerializableExtra("book");
        String snumber = book.getBooknumber();
        String sname = book.getBookname();
        String sauthor = book.getBookauthor();
        String sloction = book.getBookloction();
        TextView t[] = new TextView[4];
        t[0] = (TextView) findViewById(R.id.rtv1);
        t[1] = (TextView) findViewById(R.id.rtv2);
        t[2] = (TextView) findViewById(R.id.rtv3);
        t[3] = (TextView) findViewById(R.id.rtv4);
        t[0].setText(snumber);
        t[1].setText(sname);
        t[2].setText(sauthor);
        t[3].setText(sloction);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        Float size = Float.parseFloat(sp.getString("size_name", "20"));
        String color=sp.getString("color","");
        String name=sp.getString("name","游客");
        for (int i = 0; i < 4; i++) {
            t[i].setTextSize(size);
            t[i].setTextColor (Color.parseColor (color));
        }




        /*Typeface typeface=Typeface.createFromAsset(sp.getAll("typeface"," "));
        for (int i = 0; i < 4; i++) {
            t[i].setTypeface(typeface);

        }*/
    }
}
       /* SharedPreferences.Editor editor = getSharedPreferences("size_name", 0).edit();
        editor.commit();*/


        /*Book_add.Book book =(Book_add.Book) intent.getSerializableExtra("book1");
        String number=book.getBooknumber();
        String name=book.getBookname();
        String author=book.getBookauthor();
        String location=book.getBookloction();
        TextView t[]=new TextView[4];
        t[0]=(TextView) findViewById(R.id.addedit1);
        t[1]=(TextView) findViewById(R.id.addedit2);
        t[2]=(TextView)findViewById(R.id.addedit3);
        t[3]=(TextView)findViewById(R.id.addedit4);
        t[0].setText(number);
        t[1].setText(name);
        t[2].setText(author);
        t[3].setText(location);
      setContentView(t[4]);


    }

}*/
