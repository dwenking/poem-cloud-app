package com.ecnu.poemcloud.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ecnu.poemcloud.R;

public class MapActivity extends BaseActivity implements View.OnClickListener{

    Button map1;
    Button map2;
    Button map3;
    Button map4;
    Button map5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        map1 =findViewById(R.id.map1);
        map2 =findViewById(R.id.map2);
        map3=findViewById(R.id.map3);
        map4=findViewById(R.id.map4);
        map5=findViewById(R.id.map5);

        map1.setOnClickListener(this);
        map2.setOnClickListener(this);
        map3.setOnClickListener(this);
        map4.setOnClickListener(this);
        map5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.map1:
                Intent intent1=new Intent(MapActivity.this, MainActivity1.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.map2:
                Intent intent2=new Intent(MapActivity.this,MainActivity2.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.map3:
                Intent intent3=new Intent(MapActivity.this, MainActivity3.class);
                startActivity(intent3);
                finish();
                break;
            case R.id.map4:
                Intent intent4=new Intent(MapActivity.this,MainActivity4.class);
                startActivity(intent4);
                finish();
                break;
            case R.id.map5:
                Intent intent5=new Intent(MapActivity.this,MainActivity5.class);
                startActivity(intent5);
                finish();
                break;
        }
    }

}