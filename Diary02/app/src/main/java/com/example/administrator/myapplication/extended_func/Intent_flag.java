package com.example.administrator.myapplication.extended_func;


import java.io.Serializable;

/**
 * author：created by zxf
 * email：1463149917@qq.com
 * csdn: https://blog.csdn.net/qq_44387002
 * date：2021/12/17 09
 */
public class Intent_flag implements Serializable {

    private static final long serialVersionUID = 1;

    private String intentFlag;

    public Intent_flag(String intentFlag) {
        this.intentFlag = intentFlag;
    }

    public Intent_flag() {
    }

    public String getIntentFlag() {
        return intentFlag;
    }

    public void setIntentFlag(String intentFlag) {
        this.intentFlag = intentFlag;
    }
}
