package com.ecnu.poemcloud.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ecnu.poemcloud.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ContentActivity extends BaseActivity {


    TextView content; //接收content并展示


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Intent intent=getIntent();
//        String data=intent.getStringExtra("title"+"content");
//        content=findViewById(R.id.content);
//        content.setText(data);

        String data1=intent.getStringExtra("content");
        content=findViewById(R.id.content);
        content.setText(data1);

        ScrollView Scroll = (ScrollView)findViewById(R.id.scrollView);


        Button Btn1 = (Button)findViewById(R.id.Button01);//获取按钮资源
        Btn1.setOnClickListener(new Button.OnClickListener(){//创建监听
            @Override
            public void onClick(View content) {
                Scroll.smoothScrollTo(0,0);
            }
        });
    }


}