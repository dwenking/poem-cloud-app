package com.ecnu.poemcloud.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.ecnu.poemcloud.R;
import com.ecnu.poemcloud.adapter.CuotiAdapter;
import com.ecnu.poemcloud.entity.QuestionBasis;
import com.ecnu.poemcloud.entity.QuestionBlank;
import com.ecnu.poemcloud.entity.QuestionChoice;
import com.ecnu.poemcloud.utils.HttpRequest;

import java.util.ArrayList;
import java.util.List;

//错题本滚动页面
public class CuotiActivity extends BaseActivity {

    private List<QuestionBasis> diaryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuoti);

        if(theme==R.style.Theme_White){
            this.getWindow().setBackgroundDrawableResource(R.drawable.lightback);
        }
        else{
            this.getWindow().setBackgroundDrawableResource(R.drawable.darkback);
        }

        init();

        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.cuoti_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        CuotiAdapter adapter = new CuotiAdapter(diaryList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new CuotiAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                QuestionBasis questionBasis = diaryList.get(position);
                int id_question = questionBasis.id_question;
                int type = questionBasis.question_type;

                String content = null;
                String text = null;
                String answer = null;
                if (type == 0) {
                    // 填空题
                    QuestionBlank questionBlank = (QuestionBlank) HttpRequest.getQuestionById(id_question, type);
                    text = questionBlank.text;
                    answer = questionBlank.answer;
                } else if (type == 1) {
                    // 单选题
                    QuestionChoice questionChoice = (QuestionChoice) HttpRequest.getQuestionById(id_question, type);
                    text = questionChoice.text + "\nA." +
                            questionChoice.option_a + "\nB." +
                            questionChoice.option_b + "\nC." +
                            questionChoice.option_c + "\nD." +
                            questionChoice.option_d;
                    answer = questionChoice.answer;
                }

                content = text + "\n\n解答：" + answer;

                Intent intent=new Intent(CuotiActivity.this,ContentActivity.class);
                intent.putExtra("content",content);
                startActivity(intent);
            }
        });

    }


    private void init() {
        List<QuestionBasis> list = HttpRequest.getWrongQuestionListByIdUser(id_user);
        diaryList = list;
    }
}