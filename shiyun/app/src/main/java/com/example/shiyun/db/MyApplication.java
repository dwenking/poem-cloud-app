package com.example.shiyun.db;

import android.app.Application;

import com.example.shiyun.Diary;
import com.example.shiyun.Blank;
import com.example.shiyun.Question;
import com.raizlabs.android.dbflow.config.FlowManager;

public class MyApplication extends Application {
    private String user;
    private String level="0";

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
        createDiary();
        createBlank();
        createShiyi();
    }

    private void createDiary(){
        Diary one=new Diary();
        one.setContent("床前明月光，疑是地上霜。\n举头望明月，低头思故乡。");
        one.setTitle("静夜思");
        one.save();

        Diary two=new Diary();
        two.setContent("床前明月光，疑是地上霜。\n举头望明月，低头思故乡。");
        two.setTitle("将进酒");
        two.save();

        Diary three=new Diary();
        three.setContent("床前明月光，疑是地上霜。\n举头望明月，低头思故乡。");
        three.setTitle("望庐山瀑布");
        three.save();

        Diary four=new Diary();
        four.setContent("床前明月光，疑是地上霜。\n举头望明月，低头思故乡。");
        four.setTitle("春江花月夜");
        four.save();

        Diary five=new Diary();
        five.setContent("床前明月光，疑是地上霜。\n举头望明月，低头思故乡。");
        five.setTitle("临江仙");
        five.save();

        Diary six=new Diary();
        six.setContent("床前明月光，疑是地上霜。\n举头望明月，低头思故乡。");
        six.setTitle("水调歌头");
        six.save();

        Diary seven=new Diary();
        seven.setContent("床前明月光，疑是地上霜。\n举头望明月，低头思故乡。");
        seven.setTitle("清平乐");
        seven.save();

        Diary eight=new Diary();
        eight.setContent("床前明月光，疑是地上霜。\n举头望明月，低头思故乡。");
        eight.setTitle("采桑子");
        eight.save();

        Diary nine=new Diary();
        nine.setContent("床前明月光，疑是地上霜。\n举头望明月，低头思故乡。");
        nine.setTitle("蝶恋花");
        nine.save();

    }

    private void createBlank(){
        Blank one=new Blank();
        one.setContent("东风不与周郎便，铜雀春深锁二乔。");
        one.setTag("怀古咏史");
        one.save();

        Blank two=new Blank();
        two.setContent("商女不知亡国恨，隔江犹唱后庭花。");
        two.setTag("怀古咏史");
        two.save();

        Blank three=new Blank();
        three.setContent("出师未捷身先死，长使英雄泪满襟。");
        three.setTag("怀古咏史");
        three.save();

        Blank four=new Blank();
        four.setContent("一骑红尘妃子笑，无人知是荔枝来。");
        four.setTag("怀古咏史");
        four.save();

        Blank five=new Blank();
        five.setContent("金戈铁马，气吞万里如虎。");
        five.setTag("怀古咏史");
        five.save();
    }

    private void createShiyi(){
        Question question1 = new Question();
        question1.setQuestionId(1);
        question1.setQuestion("李白是哪个朝代的诗人？");
        question1.setOptions("唐朝", "宋朝", "元朝", "明朝");
        question1.setAnswer("A");
        question1.save();

        Question question2 = new Question();
        question2.setQuestionId(2);
        question2.setQuestion("杜甫是哪个朝代的诗人？");
        question2.setOptions("唐朝", "宋朝", "元朝", "明朝");
        question2.setAnswer("A");
        question2.save();
    }
}
