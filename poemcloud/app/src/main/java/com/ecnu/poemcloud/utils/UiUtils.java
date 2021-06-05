package com.ecnu.poemcloud.utils;
import android.content.Context;
import com.ecnu.poemcloud.R;
import android.content.SharedPreferences;

public class UiUtils {
    public static int getAppTheme(Context ctx) {
        //给他的默认值是1默认是白天模式
        String value = Preferences.getString(ctx, "activity_theme", "1");
        switch (Integer.valueOf(value)) {
            case 1:
                return R.style.Theme_White;//白色主题
            case 2:
                return R.style.Theme_Black;
            default:
                return R.style.Theme_White;//默认白色
        }
    }

    //通过点击时给activity_theme不同的值
    public static void switchAppTheme( Context ctx){

        String value = Preferences.getString(ctx, "activity_theme", "1");

        switch (Integer.valueOf(value)){
            case 1:
                Preferences.setString(ctx, "activity_theme", "2");
                break;
            case 2:
                Preferences.setString(ctx, "activity_theme", "1");
                break;
            default:
                Preferences.setString(ctx, "activity_theme", "1");
                break;

        }

    }
}

class Preferences {
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