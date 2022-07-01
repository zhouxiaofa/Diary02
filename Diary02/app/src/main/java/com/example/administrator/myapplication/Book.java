package com.example.administrator.myapplication;

import java.io.Serializable;

/**
 * Created by Administrator on 2021/10/28.
 */

public class  Book implements Serializable {
    public String getBooknumber(){
        return snumber;
    }
    public String getBookname(){
        return sname;
    }
    public String getBookauthor(){
        return sauthor;
    }
    public String getBookloction(){
        return sloction;
    }
    private String sname=null;
    private String snumber=null;
    private String sauthor=null;
    private  String sloction=null;
    public Book(String snumber,String sname,String sauthor,String sloction){
        this.snumber=snumber;
        this.sauthor=sauthor;
        this.sname=sname;
        this.sloction=sloction;




    }}

