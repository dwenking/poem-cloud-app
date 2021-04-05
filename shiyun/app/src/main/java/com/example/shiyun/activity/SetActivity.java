package com.example.shiyun.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

        //错题本
        Button but1=findViewById(R.id.checkbook);

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SetActivity.this,CuotiActivity.class);
                startActivity(intent);
            }
        });

        Button but2=findViewById(R.id.logout);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SetActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}