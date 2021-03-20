package com.example.shiyun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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
    }



    private void init() {
        List<Diary> list = new Select()
                .from(Diary.class)
                .queryList();
        diaryList=list;
    }
}