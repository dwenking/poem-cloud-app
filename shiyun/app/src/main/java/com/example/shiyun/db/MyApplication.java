package com.example.shiyun.db;

import android.app.Application;

import com.example.shiyun.Diary;
import com.raizlabs.android.dbflow.config.FlowManager;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
        CreateDiary();
    }

    private void CreateDiary(){
        Diary one=new Diary();
        one.setContent("床前明月光，疑是地上霜。\n举头望明月，低头思故乡。");
        one.setTitle("静夜思");
        one.setUser("739475446@qq.com");
        one.save();

        Diary two=new Diary();
        two.setContent("床前明月光，疑是地上霜。\n举头望明月，低头思故乡。");
        two.setTitle("将进酒");
        two.setUser("739475446@qq.com");
        two.save();

        Diary three=new Diary();
        three.setContent("床前明月光，疑是地上霜。\n举头望明月，低头思故乡。");
        three.setTitle("望庐山瀑布");
        three.setUser("739475446@qq.com");
        three.save();

        Diary four=new Diary();
        four.setContent("床前明月光，疑是地上霜。\n举头望明月，低头思故乡。");
        four.setTitle("春江花月夜");
        four.setUser("739475446@qq.com");
        four.save();

        Diary five=new Diary();
        five.setContent("床前明月光，疑是地上霜。\n举头望明月，低头思故乡。");
        five.setTitle("临江仙");
        five.setUser("739475446@qq.com");
        five.save();

        Diary six=new Diary();
        six.setContent("床前明月光，疑是地上霜。\n举头望明月，低头思故乡。");
        six.setTitle("水调歌头");
        six.setUser("739475446@qq.com");
        six.save();

        Diary seven=new Diary();
        seven.setContent("床前明月光，疑是地上霜。\n举头望明月，低头思故乡。");
        seven.setTitle("清平乐");
        seven.setUser("739475446@qq.com");
        seven.save();

        Diary eight=new Diary();
        eight.setContent("床前明月光，疑是地上霜。\n举头望明月，低头思故乡。");
        eight.setTitle("采桑子");
        eight.setUser("739475446@qq.com");
        eight.save();

        Diary nine=new Diary();
        nine.setContent("床前明月光，疑是地上霜。\n举头望明月，低头思故乡。");
        nine.setTitle("蝶恋花");
        nine.setUser("739475446@qq.com");
        nine.save();

    }
}
