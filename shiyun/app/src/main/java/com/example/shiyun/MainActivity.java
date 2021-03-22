package com.example.shiyun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.shiyun.db.MyApplication;
import com.example.shiyun.ui.UiUtils;

public class MainActivity extends AppCompatActivity {
    private int theme = 0;
    private String user="";
    private String level="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            theme = UiUtils.getAppTheme(MainActivity.this);
        } else {
            theme = savedInstanceState.getInt("theme");
        }
        //设置主题的方法 在oncreate之前调用
        setTheme(theme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyApplication my = (MyApplication) getApplication();
        user=my.getUser();
        level=my.getLevel();

        ImageButton imageButton = findViewById(R.id.bottom);
        ImageButton imageButton1 = findViewById(R.id.left);
        ImageButton imageButton2 = findViewById(R.id.stage);
        ImageButton imageButton3 = findViewById(R.id.right);

        //黑白模式改变
        if (theme != R.style.Theme_Shiyun) {
            imageButton.setImageResource(R.drawable.darkbottom);
            imageButton1.setImageResource(R.drawable.darkleft);
            imageButton3.setImageResource(R.drawable.darkright);
            imageButton2.setImageResource(R.drawable.dark3);
        } else {
            imageButton.setImageResource(R.drawable.lightbottom);
            imageButton1.setImageResource(R.drawable.lightleft);
            imageButton2.setImageResource(R.drawable.light3);
            imageButton3.setImageResource(R.drawable.lightright);
            if(level.equals("1")||level.equals("0")){
                imageButton2.setImageResource(R.drawable.light1);
            }
            if(level.equals("2")){
                imageButton2.setImageResource(R.drawable.light2);
            }
            if(level.equals("3")){
                imageButton2.setImageResource(R.drawable.light3);
            }
        }

        //切换模式图标
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtils.switchAppTheme(MainActivity.this);
                load();
            }
        });

        //设置图标
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, SetActivity.class);
                startActivity(intent);
            }
        });

        //地图图标
        ImageButton imageButton4 =findViewById(R.id.center);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MapActivity.class);
                startActivity(intent);
            }
        });

        //记事本图标
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=null;
                if(theme != R.style.Theme_Shiyun) {
                    intent = new Intent(MainActivity.this, ListActivity.class);
                }
                else{
                    intent = new Intent(MainActivity.this, AnswerActivity.class);
                }
                startActivity(intent);
            }
        });
    }

    //切换之间的动画
    public void load() {
        Intent intent = getIntent();
        overridePendingTransition(R.anim.in2, R.anim.out2);//进入动画
        //this.getWindow().setBackgroundDrawableResource(R.drawable.lightback);
        finish();
        overridePendingTransition(R.anim.in2, R.anim.out2);
        //this.getWindow().setBackgroundDrawableResource(R.drawable.lightback);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        MyApplication my = (MyApplication) getApplication();
        user=my.getUser();
        level=my.getLevel();

        ImageButton imageButton2 = findViewById(R.id.stage);

        if((level.equals("1")||level.equals("0"))&&theme== R.style.Theme_Shiyun){
            imageButton2.setImageResource(R.drawable.light1);
        }
        else if(level.equals("2")&&theme== R.style.Theme_Shiyun){
            imageButton2.setImageResource(R.drawable.light2);
        }
        else if(level.equals("3")&&theme== R.style.Theme_Shiyun){
            imageButton2.setImageResource(R.drawable.light3);
        }
        else{
            imageButton2.setImageResource(R.drawable.dark3);
        }
    }
}