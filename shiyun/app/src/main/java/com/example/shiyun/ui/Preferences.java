package com.example.shiyun.ui;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
    private static final String shared_name="guide";

    private static SharedPreferences sp;
    public static String getString(Context context, String key, String defaultValues) {
        sp = context.getSharedPreferences(shared_name, context.MODE_PRIVATE);
        return sp.getString(key, defaultValues);
    }

    public static void setString(Context context, String key, String Values) {

        sp = context.getSharedPreferences(shared_name, context.MODE_PRIVATE);
        sp.edit().putString(key, Values).commit();

    }
}
