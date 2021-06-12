package com.ecnu.poemcloud.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.ecnu.poemcloud.R;
import com.ecnu.poemcloud.adapter.ArticleAdapter;
import com.ecnu.poemcloud.entity.Common_sense;
import com.ecnu.poemcloud.utils.HttpRequest;

import java.util.ArrayList;
import java.util.List;

public class ArticleActivity extends BaseActivity {

    private List<Common_sense> commonSenseList = new ArrayList<>();

    private int high_id = 1000;
    private int low_id = 1;
    private int id_theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        if(theme==R.style.Theme_White){
            this.getWindow().setBackgroundDrawableResource(R.drawable.lightback);
        }
        else{
            this.getWindow().setBackgroundDrawableResource(R.drawable.darkback);
        }

        Intent intent=getIntent();
        id_theme=intent.getIntExtra("id_theme",1);

        init();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.article_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ArticleAdapter adapter = new ArticleAdapter(commonSenseList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ArticleAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Common_sense tmp = commonSenseList.get(position);
//                add title
                String title = tmp.title;
                String content = tmp.text;
                Intent intent = new Intent(ArticleActivity.this, ContentActivity.class);

                intent.putExtra("title"+"content",title+"\n"+"\n"+content);
                startActivity(intent);
            }
        });

    }

    private void init() {
        List<Common_sense> tmp = HttpRequest.getCommonSenseBetweenLowHigh(low_id, high_id, id_theme);
        commonSenseList = tmp;
    }
}