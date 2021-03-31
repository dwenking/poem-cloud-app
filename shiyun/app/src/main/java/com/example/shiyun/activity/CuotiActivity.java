package com.example.shiyun.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.shiyun.Blank$Table;
import com.example.shiyun.Diary;
import com.example.shiyun.R;
import com.example.shiyun.UserBook;
import com.example.shiyun.UserBook$Table;
import com.example.shiyun.db.MyApplication;
import com.example.shiyun.ui.CuotiAdapter;
import com.example.shiyun.ui.DiaryAdapter;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;

//错题本滚动页面
public class CuotiActivity extends AppCompatActivity {

    private String user="";
    private List<UserBook> diaryList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        MyApplication my = (MyApplication) getApplication();
        user=my.getUser();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuoti);

        init();

        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.cuoti_list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        CuotiAdapter adapter=new CuotiAdapter(diaryList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new CuotiAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                UserBook tmp=diaryList.get(position);
                String content=tmp.getQuestion()+"\n\n解答："+tmp.getSolution();
                Intent intent=new Intent(CuotiActivity.this,ContentActivity.class);
                intent.putExtra("content",content);
                startActivity(intent);
            }
        });

    }



    private void init() {
        List<UserBook> list = new Select()
                .from(UserBook.class)
                .where(Condition.column(UserBook$Table.MAIL).is(user))
                .queryList();
        diaryList=list;
    }
}