package com.ecnu.poemcloud.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ecnu.poemcloud.R;

import com.ecnu.poemcloud.adapter.rank_userAdapter;
import com.ecnu.poemcloud.entity.rank_user;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


import java.util.ArrayList;
import java.util.List;

public class RankActivity extends AppCompatActivity {


    private List<rank_user> rank_userList= new ArrayList<>();

    private RefreshLayout mRefreshLayout;
    private ListView listView ;
    private ArrayAdapter<String> adapter ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        init();

        rank_userAdapter adapter = new rank_userAdapter(RankActivity.this,R.layout.rank_user_item,rank_userList);
        listView=(ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);


        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() { //下拉刷新
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });

        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() { //上拉加载更多
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
    }
    private void init() {
       // List<User> tmp = HttpRequest.getCommonSenseBetweenLowHigh();
       // UserList = tmp;

        for (int i=0; i<50;i++) {
            String str = Integer.toString(i);
            rank_user temp = new rank_user(str,"name","score");
            rank_userList.add(temp);
        }


    }
}