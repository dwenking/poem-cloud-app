package com.ecnu.poemcloud.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ecnu.poemcloud.R;
import com.ecnu.poemcloud.utils.HttpRequest;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText accountEdit;
    private EditText passwordEdit;
    Button signup;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        accountEdit = findViewById(R.id.email);
        passwordEdit = findViewById(R.id.password);

        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);

        signup.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signup:
                String emailStr1 = accountEdit.getText().toString();
                String passwordStr1 = passwordEdit.getText().toString();


                boolean result = emailStr1.matches("[a-zA-Z0-9]+");//true:全文英文
                if(!result){
                    Toast.makeText(LoginActivity.this,"用户名仅支持英文和数字！", Toast.LENGTH_SHORT).show();
                    break;
                }
                if(passwordStr1==null||passwordStr1.length()==0){
                    Toast.makeText(LoginActivity.this,"密码不能为空！", Toast.LENGTH_SHORT).show();
                    break;
                }

                Log.d("LoginActivity", emailStr1);
                Log.d("LoginActivity", passwordStr1);

                String register_response = HttpRequest.register(emailStr1, passwordStr1);
                Log.d("LoginActivity", register_response);
                Toast.makeText(LoginActivity.this, register_response, Toast.LENGTH_SHORT).show();
                break;

            case R.id.login:
                String emailStr2 = accountEdit.getText().toString();
                String passwordStr2 = passwordEdit.getText().toString();

                String login_response = HttpRequest.login(emailStr2, passwordStr2);

                //Log.d("LoginActivity", login_response);
                Toast.makeText(LoginActivity.this, login_response, Toast.LENGTH_SHORT).show();

                if (login_response.equals("Login succeed")) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity1.class);

                    int score = HttpRequest.getScoreByEmail(emailStr2);
                    //Toast.makeText(LoginActivity.this, String.valueOf(score), Toast.LENGTH_SHORT).show();
                    if (score == -1) {
                        Log.e("LoginActivity", "[error] get score by email");
                    }

                    GlobalApplication now_user = (GlobalApplication) getApplication();    // 这个是用户
                    now_user.setUser(emailStr2);
                    now_user.setId_user(HttpRequest.getIdByEmail(emailStr2));

                    //Log.d("LoginActivity", emailStr2);

                    startActivity(intent);
                    finish();
                    break;
                }
        }

    }
}