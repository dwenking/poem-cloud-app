package com.ecnu.poemcloud.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;

import com.ecnu.poemcloud.R;

import site.gemus.openingstartanimation.OpeningStartAnimation;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        OpeningStartAnimation openingStartAnimation = new OpeningStartAnimation.Builder(this)
                .setAppIcon(ContextCompat.getDrawable(LaunchActivity.this, R.drawable.open)) //设置图标
                .setAnimationFinishTime(3000)
                .setAppName("")
                .setAppStatement("人，诗意的栖居")
                .create();
        openingStartAnimation.show(this);

        Thread t=new Thread(){ //定义线程
            @Override
            public void run() {
                super.run();
                try {
                    sleep(3000);//睡眠2秒
                    Intent intent=new Intent(LaunchActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();//注销此界面
                } catch (InterruptedException e) {//抛出异常
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }
}