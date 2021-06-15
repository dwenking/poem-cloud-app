package com.ecnu.poemcloud.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ecnu.poemcloud.R;

import com.ecnu.poemcloud.adapter.UserAdapter;
import com.ecnu.poemcloud.entity.User;
import com.ecnu.poemcloud.utils.HttpRequest;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


import java.util.ArrayList;
import java.util.List;

public class RankActivity extends AppCompatActivity {


    private List<User> userList= new ArrayList<>();

    private RefreshLayout mRefreshLayout;
    private ListView listView ;
    private ArrayAdapter<String> adapter ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        init();




        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() { //下拉刷新
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);
                init();
                //传入false表示刷新失败
            }
        });

        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() { //上拉加载更多
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                init();
            }
        });
    }
    private void init() {

        userList=HttpRequest.getUserListOrderly("score");
        UserAdapter adapter = new UserAdapter(RankActivity.this,R.layout.user_item,userList);
        listView=(ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

    }
}