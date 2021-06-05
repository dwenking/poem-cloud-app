package com.ecnu.poemcloud.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.ecnu.poemcloud.R;
import com.ecnu.poemcloud.activity.pra.ChoiceActivity;
import com.ecnu.poemcloud.activity.pra.FeihuaActivity;
import com.ecnu.poemcloud.activity.pra.TiankongActivity;
import com.ecnu.poemcloud.utils.HttpRequest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TaskActivity extends BaseActivity implements View.OnClickListener {

    private int id_theme;
    Button task1;
    Button task2;
    Button task3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        //Log.d("TaskActivity",String.valueOf(score));

        if(theme==R.style.Theme_White){
            this.getWindow().setBackgroundDrawableResource(R.drawable.lightback);
        }
        else{
            this.getWindow().setBackgroundDrawableResource(R.drawable.darkback);
        }

        Intent intent=getIntent();
        id_theme=intent.getIntExtra("id_theme",1);

        task1=findViewById(R.id.task1);
        task2=findViewById(R.id.task2);
        task3=findViewById(R.id.task3);

        task1.setOnClickListener(this);
        task2.setOnClickListener(this);
        task3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.task1:
                Intent intent1=new Intent(TaskActivity.this, TiankongActivity.class);
                intent1.putExtra("id_theme",id_theme);
                startActivity(intent1);
                finish();
                break;
            case R.id.task2:
                Intent intent2=new Intent(TaskActivity.this, ChoiceActivity.class);
                intent2.putExtra("id_theme",id_theme);
                startActivity(intent2);
                finish();
                break;
            case R.id.task3:
                Intent intent3=new Intent(TaskActivity.this, FeihuaActivity.class);
                intent3.putExtra("id_theme",id_theme);
                startActivity(intent3);
                finish();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        score = HttpRequest.getScoreByEmail(user_email);

        if(score<=(id_theme-1)*3){
            task1.setBackgroundResource(R.drawable.choicedark);
            task2.setBackgroundResource(R.drawable.choicedark);
            task3.setBackgroundResource(R.drawable.choicedark);
        }
        else if(score==(id_theme-1)*3+1){
            task1.setBackgroundResource(R.drawable.choicelight);
            task2.setBackgroundResource(R.drawable.choicedark);
            task3.setBackgroundResource(R.drawable.choicedark);
        }
        else if(score==(id_theme-1)*3+2){
            task1.setBackgroundResource(R.drawable.choicelight);
            task2.setBackgroundResource(R.drawable.choicelight);
            task3.setBackgroundResource(R.drawable.choicedark);
        }
        else {
            task1.setBackgroundResource(R.drawable.choicelight);
            task2.setBackgroundResource(R.drawable.choicelight);
            task3.setBackgroundResource(R.drawable.choicelight);
        }
    }
}