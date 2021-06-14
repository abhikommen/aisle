package com.jenni.aisleappchallange.util;

import android.content.Context;
import android.content.SharedPreferences;

public class TokenUtil {

    private static final String TAG = TokenUtil.class.getSimpleName();
    private static final String DEFAULT_SHARE_NAME = "mytokensave";
    private static SharedPreferences sharedPreferences;

    public static void init(Context context) {
        sharedPreferences = context.getSharedPreferences(DEFAULT_SHARE_NAME, Context.MODE_PRIVATE);
    }

    public static void saveToken(String value) {
        sharedPreferences.edit().putString(DEFAULT_SHARE_NAME, value).apply();
    }

    public static String fetchToken() {
        return sharedPreferences.getString(DEFAULT_SHARE_NAME, null);
    }

}
