package com.example.administrator.myapplication;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

/**
 * Created by Administrator on 2021/10/14.
 */
    public class Four4 implements View.OnClickListener {
        Activity a;

        public Four4(Activity a) {
            this.a = a;
        }

        public void onClick(View view) {
            if (view.getId() == R.id.b2) {
                EditText e41 = a.findViewById(R.id.edit1);
                e41.getText().clear();
                EditText e42 = a.findViewById(R.id.edit2);
                e42.getText().clear();
                EditText e43 = a.findViewById(R.id.edit3);
                e43.getText().clear();
                RadioButton bu41 = a.findViewById(R.id.rb1);
                bu41.setChecked(false);
                RadioButton bu42 = a.findViewById(R.id.rb2);
                bu42.setChecked(false);
            }
        }
    }
