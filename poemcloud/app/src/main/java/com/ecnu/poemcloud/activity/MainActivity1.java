package com.ecnu.poemcloud.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ecnu.poemcloud.R;
import com.ecnu.poemcloud.utils.ActivityCollector;
import com.ecnu.poemcloud.utils.HttpRequest;
import com.ecnu.poemcloud.utils.UiUtils;

public class MainActivity1 extends BaseActivity implements View.OnClickListener{

    private int id_theme=1;
    LinearLayout mainback;
    ImageButton bottom;
    ImageButton left;
    ImageButton center;
    ImageButton right;
    ImageView stage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(theme);

        //设置按钮
        bottom = findViewById(R.id.bottom);
        left = findViewById(R.id.left);
        center = findViewById(R.id.center);
        right = findViewById(R.id.right);
        stage = findViewById(R.id.stage);
        mainback=findViewById(R.id.mainback);
        setButton();

        bottom.setOnClickListener(this);
        left.setOnClickListener(this);
        center.setOnClickListener(this);
        right.setOnClickListener(this);
        stage.setOnClickListener(this);
    }

    @Override
    public void onRestart() {
        super.onRestart();
        setTheme(theme);
        setButton();
    }

    @Override
    public void onClick(View v){
        super.onClick(v);
        switch (v.getId()){
            case R.id.left:
                Intent intent1=new Intent(MainActivity1.this,CuotiActivity.class);
                startActivity(intent1);
                break;
            case R.id.center:
                Intent intent2=new Intent(MainActivity1.this,MapActivity.class);
                startActivity(intent2);
                break;
            case R.id.right:
                Intent intent3=new Intent(MainActivity1.this, TaskActivity.class);
                if(theme != R.style.Theme_White) {
                    intent3 = new Intent(MainActivity1.this, ArticleActivity.class);
                }
                intent3.putExtra("id_theme",id_theme);
                startActivity(intent3);
                break;
            case R.id.bottom:
                UiUtils.switchAppTheme(MainActivity1.this);
                load();
                break;
            case R.id.stage:
                /*
                Intent intent4=new Intent(MainActivity1.this,PoemGenActivity.class);
                startActivity(intent4);

                 */
                break;
        }
    }

    public void load() {
        Intent intent = new Intent(MainActivity1.this, MainActivity1.class);
        overridePendingTransition(R.anim.in, R.anim.out);//进入动画
        finish();
        overridePendingTransition(R.anim.in, R.anim.out);
        startActivity(intent);
    }


    public void setButton(){
        center.setImageResource(R.drawable.lightcen);
        score= HttpRequest.getScoreByEmail(user_email);

        if (theme != R.style.Theme_White) {
            mainback.setBackgroundResource(R.drawable.darkback);
            bottom.setImageResource(R.drawable.darkbottom);
            left.setImageResource(R.drawable.darkleft);
            right.setImageResource(R.drawable.darkright);
            stage.setImageResource(R.drawable.darkstage11);
        } else {
            mainback.setBackgroundResource(R.drawable.lightback);
            bottom.setImageResource(R.drawable.lightbottom);
            left.setImageResource(R.drawable.lightleft);
            right.setImageResource(R.drawable.lightright);
            stage.setImageResource(R.drawable.lightstage13);

            if(score<=1){
                stage.setImageResource(R.drawable.lightstage11);
            }
            if(score==2) {
                stage.setImageResource(R.drawable.lightstage12);
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /*
   private long clickTime=0;
   private void exit() {
        if ((System.currentTimeMillis() - clickTime) > 2000) {
            Toast.makeText(MainActivity1.this, "再次点击退出",  Toast.LENGTH_SHORT).show();
            clickTime = System.currentTimeMillis();
        } else {
            ActivityCollector.finishAll();
            System.exit(0);
        }
    }
    */
}