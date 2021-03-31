package com.example.shiyun.pra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shiyun.Blank;
import com.example.shiyun.Blank$Table;
import com.example.shiyun.R;
import com.example.shiyun.User;
import com.example.shiyun.User$Table;
import com.example.shiyun.activity.SoluActivity;
import com.example.shiyun.db.MyApplication;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TianKong extends AppCompatActivity {

    private String user="";
    private String level="";

    private List<Blank> mList=new ArrayList<>();
    private EditText one;
    private int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiankong);

        MyApplication my = (MyApplication) getApplication();
        user=my.getUser();
        level=my.getLevel();

        init();
        Collections.shuffle(mList);

        one=findViewById(R.id.ans1_tiankong);
        TextView textView = (TextView) findViewById(R.id.word_tiankong);
        Button but=findViewById(R.id.submit_tiankong);

        ArrayList<String> solu=new ArrayList<>(); //传给solution的题目list
        ArrayList<String> ques=new ArrayList<>(); //传给solution的题目list

        String cont=getContentBack(mList.get(i));
        textView.setText("____________，\n"+cont);
        solu.add(mList.get(i).getContent());
        ques.add("________________，\n"+cont);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oneText=one.getText().toString();

                if(oneText.equals(getContentFront(mList.get(i)))){
                    if(i==4){
                        String content="本关卡已通关！";
                        if(level.equals("0")) {
                            my.setLevel("1");
                            level = "1";
                            User pro = new Select()
                                    .from(User.class)
                                    .where(Condition.column(User$Table.MAIL).is(user))
                                    .querySingle();//区别于queryList(),返回的是实体
                            if (pro != null) {
                                pro.setLevel(level);
                                pro.update();
                            }
                        }
                        Toast.makeText(TianKong.this, content,Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(TianKong.this, SoluActivity.class);
                        intent.putStringArrayListExtra("ansList", solu);
                        intent.putStringArrayListExtra("quesList", ques);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        i++;
                        String content = "作答正确，还有" + Integer.toString(5 - i) + "题通关";
                        Toast.makeText(TianKong.this, content, Toast.LENGTH_SHORT).show();
                    }
                    String cont=getContentBack(mList.get(i));
                    textView.setText("________________，\n         "+cont);
                    solu.add(mList.get(i).getContent());
                    ques.add("________________，\n"+cont);

                    one.setText("");
                }
                else{
                    String content="回答不正确，请继续尝试";
                    Toast.makeText(TianKong.this, content,Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void init() {
        List<Blank> list = new Select()
                .from(Blank.class)
                .where(Condition.column(Blank$Table.TAG).is("怀古咏史"))
                .queryList();
        mList=list;
    }

    private String getContentBack(Blank b){
        String[] tmp=b.getContent().split("，");
        String a;
        a=tmp[1];
        return a;
    }

    private String getContentFront(Blank b){
        String[] tmp=b.getContent().split("，");
        String a;
        a=tmp[0];
        return a;
    }

}