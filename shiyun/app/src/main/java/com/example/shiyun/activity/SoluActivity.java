package com.example.shiyun.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shiyun.R;
import com.example.shiyun.UserBook;
import com.example.shiyun.db.MyApplication;
import com.example.shiyun.pra.TianKong;

import java.util.ArrayList;

public class SoluActivity extends AppCompatActivity {
    private int i=0;
    private ArrayList<String> quesList = new ArrayList<String>();
    private ArrayList<String> ansList = new ArrayList<String>();
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solu);

        //获取当前登录用户
        MyApplication my = (MyApplication) getApplication();
        user=my.getUser();

        quesList = getIntent().getStringArrayListExtra("quesList");
        ansList = getIntent().getStringArrayListExtra("ansList");

        TextView question = (TextView) findViewById(R.id.question_solu);
        TextView solution = (TextView) findViewById(R.id.solution);

        question.setText(quesList.get(i));
        solution.setText(ansList.get(i));

        Button but1=findViewById(R.id.before_solu);
        Button but2=findViewById(R.id.next_solu);
        Button but3=findViewById(R.id.add);

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i<=0){
                    Toast.makeText(SoluActivity.this, "没有上一题了！", Toast.LENGTH_SHORT).show();
                }
                else{
                    i--;
                    question.setText(quesList.get(i));
                    solution.setText(ansList.get(i));
                }
            }
        });

        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i>=4){
                    Toast.makeText(SoluActivity.this, "没有下一题了！", Toast.LENGTH_SHORT).show();
                }
                else{
                    i++;
                    question.setText(quesList.get(i));
                    solution.setText(ansList.get(i));
                }
            }
        });

        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserBook ub=new UserBook();
                ub.setMail(user);
                ub.setQuestion(quesList.get(i));
                ub.setSolution(ansList.get(i));
                ub.save();
            }
        });

    }
}