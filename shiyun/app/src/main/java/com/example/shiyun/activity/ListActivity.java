package com.example.shiyun.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.shiyun.Diary;
import com.example.shiyun.R;
import com.example.shiyun.ui.DiaryAdapter;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private List<Diary> diaryList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        init();

        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.diary_list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DiaryAdapter adapter=new DiaryAdapter(diaryList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new DiaryAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Diary tmp=diaryList.get(position);
                String content=tmp.getContent();
                Intent intent=new Intent(ListActivity.this,ContentActivity.class);
                intent.putExtra("content",content);
                startActivity(intent);
            }
        });

    }



    private void init() {
        List<Diary> list = new Select()
                .from(Diary.class)
                .queryList();
        diaryList=list;
    }
}