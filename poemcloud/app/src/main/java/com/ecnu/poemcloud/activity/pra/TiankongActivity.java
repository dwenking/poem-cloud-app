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
import com.ecnu.poemcloud.activity.SoluActivity;
import com.ecnu.poemcloud.activity.TaskActivity;
import com.ecnu.poemcloud.entity.QuestionBlank;
import com.ecnu.poemcloud.entity.QuestionChoice;
import com.ecnu.poemcloud.utils.HttpRequest;

import java.util.ArrayList;
import java.util.List;

public class TiankongActivity extends BaseActivity {

    private static final int MAX_COUNT = 5;
    private int id_theme;

    private List<String> soluList = new ArrayList<>(); //传给solution的题目list
    private List<String> textList = new ArrayList<>();
    private List<String> idList = new ArrayList<>();
    private QuestionBlank now_question;
    private List<Integer> ques_list=new ArrayList<>();

    private EditText editText_answer;
    private TextView text_question;
    private Button but_submit;

    private int count = 1;
    private boolean null_flag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiankong);

        Intent intent=getIntent();
        id_theme=intent.getIntExtra("id_theme",1);
        score = HttpRequest.getScoreByEmail(user_email);

        editText_answer = findViewById(R.id.ans1_tiankong);
        text_question = findViewById(R.id.word_tiankong);
        but_submit = findViewById(R.id.submit_tiankong);

        // 初始化ques_list
        ques_list = HttpRequest.getIdQuestionList(id_theme, 0, MAX_COUNT);

        if (updateQuestion() == -1) {
            null_question_handler();
        }



        but_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (null_flag == true) {
                    Intent intent = new Intent(TiankongActivity.this, SoluActivity.class);
                    intent.putStringArrayListExtra("ansList", (ArrayList<String>) soluList);
                    intent.putStringArrayListExtra("quesList", (ArrayList<String>) textList);
                    intent.putStringArrayListExtra("idList", (ArrayList<String>) idList);
                    startActivity(intent);
                    return ;
                }

                String user_answer = editText_answer.getText().toString();

                if(HttpRequest.doQuestion(id_user, now_question.id_question, user_answer) == 1) {
                    if(count == MAX_COUNT ){
                        if(score==(id_theme-1)*3){
                            HttpRequest.addScore(id_user, 1);
                        }

                        Intent intent = new Intent(TiankongActivity.this, SoluActivity.class);
                        intent.putStringArrayListExtra("ansList", (ArrayList<String>) soluList);
                        intent.putStringArrayListExtra("quesList", (ArrayList<String>) textList);
                        intent.putStringArrayListExtra("idList", (ArrayList<String>) idList);
                        startActivity(intent);

                        String content = "本关卡已通关！";
                        Toast.makeText(TiankongActivity.this, content,Toast.LENGTH_SHORT).show();
                        finish();

                    }  else {
                        count++;
                        if (updateQuestion() == -1) {
                            null_question_handler();
                            but_submit.setVisibility(View.VISIBLE);
                            but_submit.setEnabled(true);
                        }
                        //String content = "作答正确，还有" + Integer.toString(MAX_COUNT - i) + "题通关";
                        //Toast.makeText(TiankongActivity.this, content, Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    String content="回答不正确，请继续尝试";
                    Toast.makeText(TiankongActivity.this, content,Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private int updateQuestion() {
        now_question = (QuestionBlank) HttpRequest.getQuestionById(ques_list.get(count-1), 0);

        if (now_question == null)
            return -1;

        textList.add(now_question.text);
        soluList.add(now_question.answer);
        idList.add(String.valueOf(now_question.id_question));
        text_question.setText(now_question.text);
        editText_answer.setText("");
        return 1;
    }

    private void null_question_handler() {
        null_flag = true;
        text_question.setText("没有更多的题了！");
        editText_answer.setVisibility(View.INVISIBLE);
        but_submit.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent=new Intent(TiankongActivity.this, TaskActivity.class);
            intent.putExtra("id_theme",id_theme);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
