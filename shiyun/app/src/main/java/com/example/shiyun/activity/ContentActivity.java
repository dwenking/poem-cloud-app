package com.example.shiyun.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.shiyun.R;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Intent intent=getIntent();
        String data=intent.getStringExtra("content");
        TextView t=findViewById(R.id.news_content);
        t.setText(data);
    }
}