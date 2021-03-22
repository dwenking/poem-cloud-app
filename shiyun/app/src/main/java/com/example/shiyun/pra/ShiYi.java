package com.example.shiyun.pra;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shiyun.R;
import com.example.shiyun.Question;
import com.example.shiyun.Question$Table;
import com.example.shiyun.User;
import com.example.shiyun.User$Table;
import com.example.shiyun.db.MyApplication;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;

public class ShiYi extends AppCompatActivity{
    private String user="";
    private String level="";
    MyApplication my;

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
    private static final int MAX_COUNT = 2; // 最大题数

    /** 题目列表 **/
    List<Question> mQuestions = new ArrayList<>();
    /** 标准答案 **/
    List<String> mAnswers = new ArrayList<>();
    /** 用户答案 **/
    List<String> mUserAnswers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shiyi);

        my=(MyApplication) getApplication();

        user=my.getUser();
        level=my.getLevel();

        /** 获取题目 **/
        mQuestions = selectQuestions();

        /** 获取答案 **/
        mAnswers = getQuizAnswers(mQuestions);

        mQuestion = (TextView) findViewById(R.id.question);

        /** option A **/
        mOptionA = (Button) findViewById(R.id.optionA);
        mOptionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnableOptions();
                mOptionA.setEnabled(false);
                updateUserAnswers("A");
            }
        });

        /** option B **/
        mOptionB = (Button) findViewById(R.id.optionB);
        mOptionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnableOptions();
                mOptionB.setEnabled(false);
                updateUserAnswers("B");
            }
        });

        /** option C **/
        mOptionC = (Button) findViewById(R.id.optionC);
        mOptionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnableOptions();
                mOptionC.setEnabled(false);
                updateUserAnswers("C");
            }
        });

        /** option D **/
        mOptionD = (Button) findViewById(R.id.optionD);
        mOptionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnableOptions();
                mOptionD.setEnabled(false);
                updateUserAnswers("D");
            }
        });

        /** 下一题按钮 **/
        mNextButton = (Button) findViewById(R.id.nextButton);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentCount++;
                EnableOptions();
                updateQuestion();
                if (mCurrentCount == MAX_COUNT - 1) {
                    mNextButton.setEnabled(false);
                    Toast.makeText(ShiYi.this,
                            R.string.end,
                            Toast.LENGTH_SHORT).show();
                    mSubmitButton.setEnabled(true);
                }
            }
        });

        /** 提交按钮 **/
        mSubmitButton = (Button) findViewById(R.id.submitButton);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswers();
            }
        });

        /** 刷新界面 **/
        mSubmitButton.setEnabled(false);
        updateQuestion();
    }

    /** 加载选择题内容 **/
    public List<Question> selectQuestions() {

        List<Question> questions = new ArrayList<>();
        int id = 1;
        int count = 0;

        while(count < MAX_COUNT)
        {
            questions.add(new Select()
                    .from(Question.class)
                    .where(Condition.column(Question$Table.QUESTIONID).is(id))
                    .querySingle());
            count++;
            id++;
        }

        return questions;
    }

    /** 获取题目答案 **/
    public List<String> getQuizAnswers(List<Question> questions) {

        List<String> answers = new ArrayList<>();

        for(Question question : questions)
        {
            answers.add(question.getAnswer());
        }

        return answers;
    }

    /** 更新当前题目 **/
    public void updateQuestion() {
        if (mCurrentCount < MAX_COUNT) {
            Question newQuestion = mQuestions.get(mCurrentCount);
            mQuestion.setText(newQuestion.getQuestion());
            mOptionA.setText(newQuestion.getOptionA());
            mOptionB.setText(newQuestion.getOptionB());
            mOptionC.setText(newQuestion.getOptionC());
            mOptionD.setText(newQuestion.getOptionD());
        }
    }

    /** 更新用户答案 **/
    public void updateUserAnswers(String answer) {
        if (mUserAnswers.size()-1 < mCurrentCount) {
            mUserAnswers.add(answer);
        } else {
            mUserAnswers.set(mCurrentCount, answer);
        }
    }

    /** 检查用户提交的答案 **/
    public void checkAnswers() {
        mSubmitButton.setEnabled(false);

        /** 计算成绩 **/
        int score = 0;
        for (int i = 0; i < mAnswers.size(); i++)
        {
            String answer = mAnswers.get(i);
            String userAnswer = mUserAnswers.get(i);
            if (answer.compareTo(userAnswer) == 0) {
                score++;
            }
        }

        /** 闯关信息组合 **/
        String result = "你总共答对" + score + "道题，成绩是" + score*10 + "分！";
        String title = null;
        if (score >= 80) {
            result += "\n恭喜你通关！";
            title = "闯关成功";
            if(level.equals("1")) {
                my.setLevel("2");
                level = "2";
                User pro = new Select()
                        .from(User.class)
                        .where(Condition.column(User$Table.MAIL).is(user))
                        .querySingle();//区别于queryList(),返回的是实体
                if (pro != null) {
                    pro.setLevel(level);
                    pro.update();
                }
            }
        } else {
            result += "\n闯关失败，请继续努力！";
            title = "闯关失败";
        }

        /** 弹窗提醒 **/
        AlertDialog notion = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(result)
                .setPositiveButton(R.string.dialog_confirm,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(level.equals("1")) {
                                    my.setLevel("2");
                                    level = "2";
                                    User pro = new Select()
                                            .from(User.class)
                                            .where(Condition.column(User$Table.MAIL).is(user))
                                            .querySingle();//区别于queryList(),返回的是实体
                                    if (pro != null) {
                                        pro.setLevel(level);
                                        pro.update();
                                    }
                                }
                                finish();
                            }
                        })
//                                            .setPositiveButton(R.string.dialog_redo,
//                                                    new DialogInterface.OnClickListener() {
//                                                        @Override
//                                                        public void onClick(DialogInterface dialog, int which) {
//
//                                                        }
//                                                    })
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
}

