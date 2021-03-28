package com.example.shiyun.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.shiyun.R;
import com.example.shiyun.db.MyApplication;

public class SetActivity extends AppCompatActivity {
    private String user="";
    private String level="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        MyApplication my = (MyApplication) getApplication();
        user=my.getUser();
        level=my.getLevel();
    }
}