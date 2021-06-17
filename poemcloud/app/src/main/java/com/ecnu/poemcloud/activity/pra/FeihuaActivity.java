package com.ecnu.poemcloud.activity.pra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ecnu.poemcloud.R;
import com.ecnu.poemcloud.activity.BaseActivity;
import com.ecnu.poemcloud.activity.TaskActivity;
import com.ecnu.poemcloud.utils.HttpRequest;

import java.util.Collections;
import java.util.List;

public class FeihuaActivity extends BaseActivity {

    private int id_theme;
    private EditText one;
    private EditText two;
    private EditText three;
    //private EditText four;

    private int i = 1;
    private static final int MAX_COUNT = 3;

    List<String> words;
    String all="风,桥,山,秋,归,月,花,酒,柳,愁,夜,雪,梦,云,天,寒";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feihua);

        Intent intent=getIntent();
        id_theme=intent.getIntExtra("id_theme",1);
        score = HttpRequest.getScoreByEmail(user_email);

        one = findViewById(R.id.ans1);
        two = findViewById(R.id.ans2);
        three =findViewById(R.id.ans3);
        //four = findViewById(R.id.ans4);

        String[] arrayStr =all.split(",");

        words= java.util.Arrays.asList(arrayStr);
        Collections.shuffle(words);

        TextView textView = (TextView) findViewById(R.id.word);
        textView.setText(words.get(i-1));

        Button but=findViewById(R.id.submit_feihua);
        but.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String oneText=one.getText().toString().trim();
                String twoText=two.getText().toString().trim();
                String threeText=three.getText().toString().trim();
                //String fourText=four.getText().toString().trim();
                String key=textView.getText().toString().trim();

                if(oneText.contains(key)&&twoText.contains(key)&&threeText.contains(key)
                        &&oneText.length()>3&&twoText.length()>3&&threeText.length()>3){
                    if(i==MAX_COUNT){
                        String content="本关卡已通关！";
                        Toast.makeText(FeihuaActivity.this, content,Toast.LENGTH_SHORT).show();
                        if(score==(id_theme-1)*3+2){
                            HttpRequest.addScore(id_user, 1);
                        }

                        Intent intent=new Intent(FeihuaActivity.this, TaskActivity.class);
                        intent.putExtra("id_theme",id_theme);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        i++;
                        //String content="作答正确，还有"+Integer.toString(MAX_COUNT-i)+"题通关";
                        //Toast.makeText(FeihuaActivity.this, content,Toast.LENGTH_SHORT ).show();
                    }

                    textView.setText(words.get(i-1));
                    one.setText("");
                    two.setText("");
                    three.setText("");
                    //four.setText("");
                }
                else{
                    String content="回答不正确或不符合诗句格式要求，请继续尝试";
                    Toast.makeText(FeihuaActivity.this, content,Toast.LENGTH_SHORT ).show();
                }
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent=new Intent(FeihuaActivity.this, TaskActivity.class);
            intent.putExtra("id_theme",id_theme);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}