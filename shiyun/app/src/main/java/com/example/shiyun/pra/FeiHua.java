package com.example.shiyun.pra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shiyun.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FeiHua extends AppCompatActivity {

    private EditText one;
    private EditText two;
    private EditText three;
    private EditText four;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feihua);

        one =findViewById(R.id.ans1);
        two =findViewById(R.id.ans2);
        three =findViewById(R.id.ans3);
        four =findViewById(R.id.ans4);

        String all="风,花,柳,桥,山,秋,月,归,雪";
        String[] arrayStr =all.split(",");
        List<String> words= java.util.Arrays.asList(arrayStr);
        Collections.shuffle(words);

        TextView textView = (TextView) findViewById(R.id.word);
        textView.setText(words.get(i));

        Button but=findViewById(R.id.submit_feihua);
        but.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String oneText=one.getText().toString();
                String twoText=two.getText().toString();
                String threeText=three.getText().toString();
                String fourText=four.getText().toString();
                String key=textView.getText().toString();
                if(oneText.contains(key)&&twoText.contains(key)&&threeText.contains(key)&&fourText.contains(key)){
                    if(i==9){
                        String content="本关卡已通关！";
                        Toast.makeText(FeiHua.this, content,Toast.LENGTH_LONG ).show();
                    }
                    else {
                        i++;
                        String content="作答正确，还有"+Integer.toString(10-i)+"题通关";
                        Toast.makeText(FeiHua.this, content,Toast.LENGTH_LONG ).show();
                    }
                    textView.setText(words.get(i));
                    one.setText("");
                    two.setText("");
                    three.setText("");
                    four.setText("");
                }
                else{
                    String content="回答不正确，请继续尝试";
                    Toast.makeText(FeiHua.this, content,Toast.LENGTH_LONG ).show();
                }
            }
        });

    }
}