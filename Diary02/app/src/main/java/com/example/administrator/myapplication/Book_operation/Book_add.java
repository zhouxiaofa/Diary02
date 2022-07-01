package com.example.administrator.myapplication.Book_operation;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.myapplication.Book;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.Result;

/**
 * Created by Administrator on 2021/10/15.
 */

public class Book_add extends Activity {
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.book_add);
        Button b1 = (Button) findViewById(R.id.addb1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText[] et = new EditText[4];
                et[0] = (EditText) findViewById(R.id.addedit1);
                et[1] = (EditText) findViewById(R.id.addedit2);
                et[2] = (EditText) findViewById(R.id.addedit3);
                et[3] = (EditText) findViewById(R.id.addedit4);
                Intent intent = new Intent(Book_add.this, Result.class);
                Bundle bundle = new Bundle();
                String snumber = et[0].getText().toString();
                String sname = et[1].getText().toString();
                String sauthor = et[2].getText().toString();
                String sloction = et[3].getText().toString();
                Book book = new Book(snumber, sname, sauthor, sloction);
                bundle.putSerializable("book", book);
                intent.putExtras(bundle);
                Book_add.this.startActivity(intent);
            }
        });
    }
}





                   /* TextView t[]=new TextView[4];
                    t[0]=(TextView)findViewById(R.id.addedit1);
                    t[1]=(TextView)findViewById(R.id.addedit2);
                    t[2]=(TextView)findViewById(R.id.addedit3);
                    t[3]=(TextView)findViewById(R.id.addedit4);
                    Intent intent=new Intent(Book_add.this,Result.class);
                    Bundle bundle=new Bundle();
                    String snumb=t[0].getText().toString();
                    String sname=t[1].getText().toString();
                    String sauthor=t[2].getText().toString();
                    String sloction=t[3].getText().toString();
                    Book book=new Book(snumb,sname,sauthor,sloction);
                    bundle.putSerializable("book1",book);
                    intent.putExtras(bundle);
                    /*Book_add.this.startActivity(intent)*/;
              /*  }
            });
        }*/


