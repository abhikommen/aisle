package com.jenni.aisleappchallange;

import android.app.Application;

import com.jenni.aisleappchallange.util.TokenUtil;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        TokenUtil.init(getApplicationContext());
    }
}
