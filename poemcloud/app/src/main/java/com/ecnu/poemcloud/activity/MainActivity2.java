package com.ecnu.poemcloud.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ecnu.poemcloud.R;
import com.ecnu.poemcloud.utils.ActivityCollector;
import com.ecnu.poemcloud.utils.HttpRequest;
import com.ecnu.poemcloud.utils.UiUtils;

public class MainActivity2 extends BaseActivity implements View.OnClickListener{

    private int id_theme=2;
    ImageButton bottom;
    ImageButton left;
    ImageButton center;
    ImageButton right;
    ImageButton stage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(theme);
        setContentView(R.layout.activity_main);

        //设置按钮
        bottom = findViewById(R.id.bottom);
        left = findViewById(R.id.left);
        center = findViewById(R.id.center);
        right = findViewById(R.id.right);
        stage = findViewById(R.id.stage);
        setButton();

        bottom.setOnClickListener(this);
        left.setOnClickListener(this);
        center.setOnClickListener(this);
        right.setOnClickListener(this);
        stage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.left:
                Intent intent1=new Intent(MainActivity2.this,CuotiActivity.class);
                startActivity(intent1);
                break;
            case R.id.center:
                Intent intent2=new Intent(MainActivity2.this,MapActivity.class);
                startActivity(intent2);
                break;
            case R.id.right:
                if(score<3&&theme==R.style.Theme_White){
                    Toast.makeText(MainActivity2.this,"提升等级到3才能解锁关卡！",Toast.LENGTH_SHORT).show();
                    break;
                }
                Intent intent3=new Intent(MainActivity2.this, TaskActivity.class);
                if(theme != R.style.Theme_White) {
                    intent3 = new Intent(MainActivity2.this, ArticleActivity.class);
                }
                intent3.putExtra("id_theme",id_theme);
                startActivity(intent3);
                break;
            case R.id.bottom:
                UiUtils.switchAppTheme(MainActivity2.this);
                load();
                break;
            case R.id.stage:
                Intent intent4=new Intent(MainActivity2.this,PoemGenActivity.class);
                startActivity(intent4);
                break;
        }
    }

    public void load() {
        Intent intent = new Intent(MainActivity2.this, MainActivity2.class);
        overridePendingTransition(R.anim.in, R.anim.out);//进入动画
        finish();
        overridePendingTransition(R.anim.in, R.anim.out);
        startActivity(intent);
    }

    public void setButton(){
        center.setImageResource(R.drawable.lightcen);
        score= HttpRequest.getScoreByEmail(user_email);

        if (theme != R.style.Theme_White) {
            bottom.setImageResource(R.drawable.darkbottom);
            left.setImageResource(R.drawable.darkleft);
            right.setImageResource(R.drawable.darkright);
            stage.setImageResource(R.drawable.darkstage21);
        } else {
            bottom.setImageResource(R.drawable.lightbottom);
            left.setImageResource(R.drawable.lightleft);
            right.setImageResource(R.drawable.lightright);
            stage.setImageResource(R.drawable.lightstage23);

            if(score<=3){
                stage.setImageResource(R.drawable.lightstage21);
            }
            if(score==5) {
                stage.setImageResource(R.drawable.lightstage22);
            }
        }
    }

    private long clickTime=0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        Intent intent=new Intent(MainActivity2.this,SettingActivity.class);
        startActivity(intent);
    }
}