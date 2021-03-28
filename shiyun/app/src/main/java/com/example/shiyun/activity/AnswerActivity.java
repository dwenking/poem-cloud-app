package com.example.shiyun.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.shiyun.R;
import com.example.shiyun.db.MyApplication;
import com.example.shiyun.pra.FeiHua;
import com.example.shiyun.pra.ShiYi;
import com.example.shiyun.pra.TianKong;

public class AnswerActivity extends AppCompatActivity {

    private String user="";
    private String level="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        MyApplication my = (MyApplication) getApplication();
        user=my.getUser();
        level=my.getLevel();

        Button but1=findViewById(R.id.choice1);
        Button but2=findViewById(R.id.choice2);
        Button but3=findViewById(R.id.choice3);

        if(level.equals("0")){
            but1.setBackgroundResource(R.drawable.choicedark);
            but2.setBackgroundResource(R.drawable.choicedark);
            but3.setBackgroundResource(R.drawable.choicedark);
        }
        else if(level.equals("1")){
            but1.setBackgroundResource(R.drawable.choicelight);
            but2.setBackgroundResource(R.drawable.choicedark);
            but3.setBackgroundResource(R.drawable.choicedark);
        }
        else if(level.equals("2")){
            but1.setBackgroundResource(R.drawable.choicelight);
            but2.setBackgroundResource(R.drawable.choicelight);
            but3.setBackgroundResource(R.drawable.choicedark);
        }
        else {
            but1.setBackgroundResource(R.drawable.choicelight);
            but2.setBackgroundResource(R.drawable.choicelight);
            but3.setBackgroundResource(R.drawable.choicelight);
        }

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent=new Intent(AnswerActivity.this, TianKong.class);
                startActivity(intent);
            }
        });

        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(!level.equals("0")) {
                    Intent intent = new Intent(AnswerActivity.this, ShiYi.class);
                    startActivity(intent);
                }
            }
        });

        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(!level.equals("0")&&!level.equals("1")) {
                    Intent intent = new Intent(AnswerActivity.this, FeiHua.class);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        MyApplication my = (MyApplication) getApplication();
        user=my.getUser();
        level=my.getLevel();

        Button but1=findViewById(R.id.choice1);
        Button but2=findViewById(R.id.choice2);
        Button but3=findViewById(R.id.choice3);

        if(level.equals("0")){
            but1.setBackgroundResource(R.drawable.choicedark);
            but2.setBackgroundResource(R.drawable.choicedark);
            but3.setBackgroundResource(R.drawable.choicedark);
        }
        else if(level.equals("1")){
            but1.setBackgroundResource(R.drawable.choicelight);
            but2.setBackgroundResource(R.drawable.choicedark);
            but3.setBackgroundResource(R.drawable.choicedark);
        }
        else if(level.equals("2")){
            but1.setBackgroundResource(R.drawable.choicelight);
            but2.setBackgroundResource(R.drawable.choicelight);
            but3.setBackgroundResource(R.drawable.choicedark);
        }
        else {
            but1.setBackgroundResource(R.drawable.choicelight);
            but2.setBackgroundResource(R.drawable.choicelight);
            but3.setBackgroundResource(R.drawable.choicelight);
        }
    }
}