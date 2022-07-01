package com.example.administrator.myapplication;


import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Administrator on 2021/12/6.
 */

public class diary_set extends PreferenceActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.diary_set);
    }
}
