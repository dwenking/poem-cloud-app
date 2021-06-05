package com.ecnu.poemcloud.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.ecnu.poemcloud.R;

public class ContentActivity extends BaseActivity {

    TextView content; //接收content并展示

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Intent intent=getIntent();
        String data=intent.getStringExtra("content");
        content=findViewById(R.id.content);
        content.setText(data);
    }
}