package com.ecnu.poemcloud.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.ecnu.poemcloud.utils.ActivityCollector;
import com.ecnu.poemcloud.utils.HttpRequest;
import com.ecnu.poemcloud.utils.UiUtils;

public class BaseActivity extends AppCompatActivity {

    public int theme=0; // 白天黑夜主题
    public int id_user;
    public String user_email; // 用户名
    public int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            theme = UiUtils.getAppTheme(this);
        } else {
            theme = savedInstanceState.getInt("theme");
        }
        super.onCreate(savedInstanceState);

        GlobalApplication global = (GlobalApplication) getApplication();
        id_user=global.getId_user();
        user_email=global.getUser();
        score= HttpRequest.getScoreByEmail(user_email);

        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}