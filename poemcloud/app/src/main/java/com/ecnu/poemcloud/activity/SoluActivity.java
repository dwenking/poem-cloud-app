package com.ecnu.poemcloud.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ecnu.poemcloud.R;
import com.ecnu.poemcloud.utils.HttpRequest;

import java.util.ArrayList;

public class SoluActivity extends BaseActivity implements View.OnClickListener{

    private int i = 0;
    private int id_theme;
    private ArrayList<String> quesList = new ArrayList<String>();
    private ArrayList<String> ansList = new ArrayList<String>();
    private ArrayList<String> idList = new ArrayList<String>();

    Button before;
    Button next;
    Button add;

    TextView question;
    TextView solution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solu);

        Intent intent=getIntent();
        quesList = intent.getStringArrayListExtra("quesList");
        ansList = intent.getStringArrayListExtra("ansList");
        idList = intent.getStringArrayListExtra("idList");
        id_theme=intent.getIntExtra("id_theme",1);

        question = (TextView) findViewById(R.id.question_solu);
        solution = (TextView) findViewById(R.id.solution);

        question.setText(quesList.get(i));
        solution.setText(ansList.get(i));

        before=findViewById(R.id.before_solu);
        next=findViewById(R.id.next_solu);
        add=findViewById(R.id.add);

        before.setOnClickListener(this);
        next.setOnClickListener(this);
        add.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.before_solu:
                if(i <= 0){
                    Toast.makeText(SoluActivity.this, "没有上一题了！", Toast.LENGTH_SHORT).show();
                }
                else{
                    i--;
                    question.setText(quesList.get(i));
                    solution.setText(ansList.get(i));
                }
                break;
            case R.id.next_solu:
                if(i >= quesList.size() - 1){
                    Toast.makeText(SoluActivity.this, "没有下一题了！", Toast.LENGTH_SHORT).show();
                }
                else{
                    i++;
                    question.setText(quesList.get(i));
                    solution.setText(ansList.get(i));
                }
                break;
            case R.id.add:
                HttpRequest.updateWrongFlagByIdUserQuestion(id_user, Integer.parseInt(idList.get(i)), 1);
                Toast.makeText(SoluActivity.this, "加入错题本成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent=new Intent(SoluActivity.this,TaskActivity.class);
            intent.putExtra("id_theme",id_theme);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}