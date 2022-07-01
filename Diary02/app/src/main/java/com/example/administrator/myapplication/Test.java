package com.example.administrator.myapplication;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2021/10/16.
 */

public class Test extends ListActivity {
    private static final String[]books=new String[]{"0","1","2"};
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setListAdapter(new ArrayAdapter<String>(this,R.layout.test,books));
        //把books里面的数据映射到setListAdapter里面，setListAdapter用于绑定视图和内容并显示
        ListView listView=getListView();
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),((TextView) view).getText(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
