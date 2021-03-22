package com.example.shiyun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shiyun.db.MyApplication;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText accountEdit;
    private EditText passwordEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        accountEdit=findViewById(R.id.email);
        passwordEdit=findViewById(R.id.password);

        Button signup=findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //获得用户输入的信息
                String emailStr = accountEdit.getText().toString();
                String passwordStr = passwordEdit.getText().toString();

                List<User> users = new Select().
                        from(User.class).
                        where(Condition.column(User$Table.MAIL).is(emailStr),
                                Condition.column(User$Table.CODE).is(passwordStr)).
                        queryList();

                if(users!=null && !users.isEmpty()){
                    Toast.makeText(LoginActivity.this, "用户已存在，请重新输入", Toast.LENGTH_SHORT).show();
                }
                else if (!TextUtils.isEmpty(emailStr) && !TextUtils.isEmpty(passwordStr)) {
                        //调用RequestApiData中的getRegistData（）方法进行注册，传入用户输入的昵称，邮箱、密码，以及解析数据的bean对象和callback对象（回调到自身）
                    User user = new User();
                    user.setMail(emailStr);
                    user.setCode(passwordStr);
                    user.setLevel("0");
                    user.save();

                    Toast.makeText(LoginActivity.this, "注册成功，请登录", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "输入信息错误", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button login=findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                String emailStr = accountEdit.getText().toString();
                String passwordStr = passwordEdit.getText().toString();

                User user = new Select().
                        from(User.class).
                        where(Condition.column(User$Table.MAIL).is(emailStr),
                                Condition.column(User$Table.CODE).is(passwordStr)).
                        querySingle();

                if(user!=null){
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    MyApplication my = (MyApplication) getApplication();
                    my.setUser(user.getMail());
                    my.setLevel(user.getLevel());
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(LoginActivity.this, "用户不存在或密码错误，请重新输入", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}