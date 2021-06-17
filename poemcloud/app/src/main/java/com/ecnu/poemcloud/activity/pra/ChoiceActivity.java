package com.ecnu.poemcloud.activity.pra;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
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

public class ChoiceActivity extends BaseActivity implements View.OnClickListener{

    private int id_theme;

    /** 问题框 **/
    private TextView mQuestion;

    /** 选项 **/
    private Button mOptionA;
    private Button mOptionB;
    private Button mOptionC;
    private Button mOptionD;

    /** 下一题按钮 提交按钮 **/
    private Button mNextButton;
    private Button mSubmitButton;

    /** 题目计数 **/
    private int mCurrentCount = 0; // 当前题号
    private static final int MAX_COUNT = 5; // 最大题数
    private static final int SUCCESS_SCORE = 3; // 通关题数

    /** 获取题目 **/
    List<QuestionChoice> choiceList = new ArrayList<>();
    List<String> userAnswerList = new ArrayList<>();
    List<Integer> scoreList = new ArrayList<>();
    List<String> idList = new ArrayList<>();

    private List<Integer> ques_list=new ArrayList<>();

    QuestionChoice now_question = null;

    String my_answer;

    private int count=1;
    private int null_flag = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        Intent intent=getIntent();
        id_theme=intent.getIntExtra("id_theme",1);
        score = HttpRequest.getScoreByEmail(user_email);

        mQuestion = (TextView) findViewById(R.id.question);

        //Log.d("Choice",String.valueOf(id_theme));
        // 初始化ques_list
        ques_list = HttpRequest.getIdQuestionList(id_theme, 1, MAX_COUNT);

        /** option A **/
        mOptionA = (Button) findViewById(R.id.optionA);
        mOptionA.setOnClickListener(ChoiceActivity.this);

        /** option B **/
        mOptionB = (Button) findViewById(R.id.optionB);
        mOptionB.setOnClickListener(ChoiceActivity.this);

        /** option C **/
        mOptionC = (Button) findViewById(R.id.optionC);
        mOptionC.setOnClickListener(ChoiceActivity.this);

        /** option D **/
        mOptionD = (Button) findViewById(R.id.optionD);
        mOptionD.setOnClickListener(ChoiceActivity.this);

        /** 下一题按钮 **/
        mNextButton = (Button) findViewById(R.id.nextButton);
        mNextButton.setOnClickListener(ChoiceActivity.this);

        /** 提交按钮 **/
        mSubmitButton = (Button) findViewById(R.id.submitButton);
        mSubmitButton.setOnClickListener(ChoiceActivity.this);

        /** 刷新界面 **/
        mSubmitButton.setEnabled(false);
        if (updateQuestion() == -1) {
            null_question_handler();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.optionA:
                EnableOptions();
                mOptionA.setEnabled(false);
                my_answer = "A";
                break;

            case R.id.optionB:
                EnableOptions();
                mOptionB.setEnabled(false);
                my_answer = "B";
                break;

            case R.id.optionC:
                EnableOptions();
                mOptionC.setEnabled(false);
                my_answer = "C";
                break;

            case R.id.optionD:
                EnableOptions();
                mOptionD.setEnabled(false);
                my_answer = "D";
                break;

            case R.id.nextButton:
                updateUserAnswers(my_answer);
                if (updateQuestion() == -1) {
                    null_question_handler();
                    null_flag = 1;
                    mSubmitButton.setVisibility(View.VISIBLE);
                    mSubmitButton.setEnabled(true);
                    return ;
                }
                mCurrentCount++;
                EnableOptions();
                if (mCurrentCount == MAX_COUNT - 1) {
                    mNextButton.setEnabled(false);
                    //Toast.makeText(ChoiceActivity.this,"最后一题",Toast.LENGTH_SHORT).show();
                    mSubmitButton.setEnabled(true);
                }
                if (userAnswerList.size() != mCurrentCount)
                    updateUserAnswers("E");
                break;

            case R.id.submitButton:
                if (null_flag == 0)
                    updateUserAnswers(my_answer);
                checkAnswers();
                break;

        }
    }

    /** 更新当前题目 **/
    public int updateQuestion() {
        if (mCurrentCount < MAX_COUNT) {

            now_question = (QuestionChoice) HttpRequest.getQuestionById(ques_list.get(count-1), 1);
            if (now_question == null)
                return -1;

            if (choiceList.isEmpty() || choiceList.get(choiceList.size()- 1).id_question != now_question.id_question)
                choiceList.add(now_question);



            String content = now_question.text;
            mQuestion.setText(content);
            mOptionA.setText(now_question.option_a);
            mOptionB.setText(now_question.option_b);
            mOptionC.setText(now_question.option_c);
            mOptionD.setText(now_question.option_d);

//            if (!idList.isEmpty() && !idList.get(idList.size()- 1).equals(String.valueOf(now_question.id_question)))
//                idList.add(String.valueOf(now_question.id_question));
            count++;
            return 1;
        }
        return -1;
    }

    /** 更新用户答案 **/
    public int updateUserAnswers(String answer) {
//        if (userAnswerList.size() - 1 < mCurrentCount) {
            userAnswerList.add(answer);

            int tmp = HttpRequest.doQuestion(id_user, now_question.id_question, answer);

            if (tmp == 0) {
                //Toast.makeText(this, "wrong answer!", Toast.LENGTH_SHORT).show();
                return -1;
            }
            else
            scoreList.add(tmp);

//        } else {
//            userAnswerList.set(mCurrentCount, answer);
//        }

        return 0;
    }

    /** 检查用户提交的答案 **/
    public void checkAnswers() {
        mSubmitButton.setEnabled(false);

        /** 计算成绩 **/
        int total = 0;
        for (int i = 0; i < scoreList.size(); i++)
        {
            total += scoreList.get(i);
        }

        /** 闯关信息组合 **/
        String result = "你总共答对" + total + "道题";
        String title = null;


        if (total >= SUCCESS_SCORE) {
            result += "\n恭喜你通关！";
            title = "闯关成功";
            if(score==(id_theme-1)*3+1)
                HttpRequest.addScore(id_user, 1);
        } else {
            result += "\n闯关失败，请继续努力！";
            title = "闯关失败";
        }


        /** 弹窗提醒 **/
        AlertDialog notion = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(result)
                .setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                /** 启动解析界面 **/
                                Intent intent = new Intent(ChoiceActivity.this, SoluActivity.class);
                                intent.putStringArrayListExtra("ansList", getSoluAns());
                                intent.putStringArrayListExtra("quesList", getSoluQues());
                                intent.putStringArrayListExtra("idList", getIdQues());
                                startActivity(intent);
                                finish();
                            }
                        })
                .create();
        notion.show();
    }

    /** 激活所有选项按钮 **/
    public void EnableOptions() {
        mOptionA.setEnabled(true);
        mOptionB.setEnabled(true);
        mOptionC.setEnabled(true);
        mOptionD.setEnabled(true);
    }

    private ArrayList<String> getSoluQues() {
        ArrayList<String> soluQues = new ArrayList<>();
        String text;
        for (int i = 0; i < choiceList.size(); i++)
        {
            text = choiceList.get(i).text;
            soluQues.add(text);
        }
        return soluQues;
    }

    private ArrayList<String> getIdQues() {
        ArrayList<String> IdQues = new ArrayList<>();
        for (int i = 0; i < choiceList.size(); i++)
        {
            IdQues.add(String.valueOf(choiceList.get(i).id_question));

        }
        return IdQues;
    }

    private ArrayList<String> getSoluAns() {
        ArrayList<String> ans = new ArrayList<>();


        for (int i = 0; i < choiceList.size(); i++)
        {
            QuestionChoice questionChoice = choiceList.get(i);
            String temp = questionChoice.answer;
            switch (temp) {
                case "A":
                    temp += "." + questionChoice.option_a;break;
                case "B":
                    temp += "." + questionChoice.option_b;break;
                case "C":
                    temp += "." + questionChoice.option_c;break;
                case "D":
                    temp += "." + questionChoice.option_d;break;
            }
            ans.add(temp);
        }
        return ans;
    }

    public void null_question_handler() {
        mQuestion.setText("没有更多的题了！");
        mNextButton.setVisibility(View.INVISIBLE);
        mSubmitButton.setVisibility(View.INVISIBLE);
        mOptionA.setVisibility(View.INVISIBLE);
        mOptionB.setVisibility(View.INVISIBLE);
        mOptionC.setVisibility(View.INVISIBLE);
        mOptionD.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent=new Intent(ChoiceActivity.this, TaskActivity.class);
            intent.putExtra("id_theme",id_theme);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
