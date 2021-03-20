package com.example.shiyun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.shiyun.pra.FeiHua;
import com.example.shiyun.pra.ShiYi;
import com.example.shiyun.pra.TianKong;

public class AnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        Button but1=findViewById(R.id.choice1);
        Button but2=findViewById(R.id.choice2);
        Button but3=findViewById(R.id.choice3);

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent=new Intent(AnswerActivity.this, FeiHua.class);
                startActivity(intent);
            }
        });

        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent=new Intent(AnswerActivity.this, TianKong.class);
                startActivity(intent);
            }
        });

        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent=new Intent(AnswerActivity.this, ShiYi.class);
                startActivity(intent);
            }
        });

    }
}