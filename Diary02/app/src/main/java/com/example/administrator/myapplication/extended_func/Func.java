package com.example.administrator.myapplication.extended_func;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * author：created by zxf
 * email：1463149917@qq.com
 * csdn: https://blog.csdn.net/qq_44387002
 * date：2021/12/23 11
 */
public class Func {

    /** id          --->    日记的唯一标识
        account     --->    用户的账号(用户自己定义的名字)
        loginUser   --->    用户的唯一标识(user字段)
        intentFlag  --->    返回上一层级的标识
        intent      --->    事务*/
    public void setAll(String id, String account, String loginUser, Intent_flag intentFlag, Intent intent){
        intent.putExtra("id", id);
        intent.putExtra("account", account);
        intent.putExtra("loginUser", loginUser);
        intent.putExtra("intentFlag", intentFlag);
    }

    public String[] getDiaryInfoByCursor(Cursor cursor, String id, String title, String author, String time, String content, String user, String changeFlag){
        String[] sql = new String[]{id, title, author, time, content, user, changeFlag};
        String[] info;
        info = new String[7];
        for(int i=0; i<7; i++){
            int temp = cursor.getColumnIndex(sql[i]);
            info[i] = cursor.getString(temp);
        }
        return info;
    }

    public String[] getUserInfoByCursor(Cursor cursor, String id, String account, String password, String flag){
        String[] sql = new String[]{id, account, password, flag};
        String[] info;
        info = new String[4];
        for(int i=0; i<4; i++){
            int temp = cursor.getColumnIndex(sql[i]);
            info[i] = cursor.getString(temp);
        }
        return info;
    }

    public String[] getDiaryInfoByCursor1(Cursor cursor){
        String[] sql = new String[]{"id", "title", "author", "time", "content", "user", "changeFlag"};
        String[] info;
        info = new String[7];
        for(int i=0; i<7; i++){
            int temp = cursor.getColumnIndex(sql[i]);
            info[i] = cursor.getString(temp);
        }
        return info;
    }

    /**
     * Diary_search.class中显示搜索日记的结果
     * @param tv
     * @param num
     * @param str
     */
    public void setTableRowView(TextView[] tv, int num, String[] str){
        for(int i=0; i<num; i++){
            tv[i].setText(str[i]);
            tv[i].setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1));
        }
    }

    public void addTableRow(TableRow tableRow, TextView[] trs, int num){
        for(int i=0; i<num; i++){
            tableRow.addView(trs[i]);
        }
    }

    public void setText(TextView[] tv, int num){
        for(int i=0; i<num; i++){
            tv[i].setText(tv[i].getText().toString());
        }
    }

}
