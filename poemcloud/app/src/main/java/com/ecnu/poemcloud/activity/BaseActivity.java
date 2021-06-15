package com.ecnu.poemcloud.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.ecnu.poemcloud.R;
import com.ecnu.poemcloud.activity.service.NoticeService;
import com.ecnu.poemcloud.activity.service.PlayMusicService;
import com.ecnu.poemcloud.utils.ActivityCollector;
import com.ecnu.poemcloud.utils.HttpRequest;
import com.ecnu.poemcloud.utils.UiUtils;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{

    Button checkrank;
    Button logout;
    ImageButton project;
    Switch bgm;
    Switch notice;
    TextView username;
    TextView levelnum;

    String bgm_pref;
    String notice_pref;
    SharedPreferences pref;

    public int theme=0; // 白天黑夜主题
    public int id_user;
    public String user_email; // 用户名
    public int score;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        if (savedInstanceState == null) {
            theme = UiUtils.getAppTheme(this);
        } else {
            theme = savedInstanceState.getInt("theme");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GlobalApplication global = (GlobalApplication) getApplication();
        id_user=global.getId_user();
        user_email=global.getUser();
        score= HttpRequest.getScoreByEmail(user_email);

        username=findViewById(R.id.user);
        levelnum=findViewById(R.id.level);

        checkrank=findViewById(R.id.checkrank);
        logout=findViewById(R.id.logout);
        project=findViewById(R.id.project);
        bgm=findViewById(R.id.bgm);
        notice=findViewById(R.id.notice);

        checkrank.setOnClickListener(this);
        logout.setOnClickListener(this);
        project.setOnClickListener(this);
        bgm.setOnCheckedChangeListener(this);
        notice.setOnCheckedChangeListener(this);

        /* 用户设置 */

        username.setText(user_email);
        levelnum.setText("等级   "+String.valueOf(score));

        pref=getSharedPreferences("setting",MODE_PRIVATE);
        bgm_pref=pref.getString("bgm","off");
        notice_pref=pref.getString("notice","off");

        if(bgm_pref.equals("on")){
            bgm.setChecked(true);
        }else if(bgm_pref.equals("off")){
            bgm.setChecked(false);
        }
        if(notice_pref.equals("on")){
            notice.setChecked(true);
        }else if(notice_pref.equals("off")){
            notice.setChecked(false);
        }

        ActivityCollector.addActivity(this);
    }

    @Override protected void onResume() {
        super.onResume();
        score= HttpRequest.getScoreByEmail(user_email);
        levelnum.setText("等级   "+String.valueOf(score));

        bgm_pref=pref.getString("bgm","off");
        notice_pref=pref.getString("notice","off");

        if(bgm_pref.equals("on")){
            bgm.setChecked(true);
        }else if(bgm_pref.equals("off")){
            bgm.setChecked(false);
        }
        if(notice_pref.equals("on")){
            notice.setChecked(true);
        }else if(notice_pref.equals("off")){
            notice.setChecked(false);
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkrank:
                Intent intent1=new Intent(this, RankActivity.class);
                startActivity(intent1);
                break;
            case R.id.logout:
                Intent intent2=new Intent(this,LoginActivity.class);
                pref.edit().clear().apply();
                startActivity(intent2);
                break;
            case R.id.project:
                Intent intent3 =new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("https://github.com/dwenking/PoetCloud");
                intent3.setData(uri);
                startActivity(intent3);
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView,boolean isChecked){
        SharedPreferences.Editor editor=getSharedPreferences("setting",MODE_PRIVATE).edit();
        switch(buttonView.getId()){
            case R.id.bgm:
                if(isChecked) {
                    editor.putString("bgm","on").apply();
                    playMusic(PlayMusicService.PLAY_MUSIC);
                }
                else{
                    editor.remove("bgm").apply();
                    playMusic(PlayMusicService.STOP_MUSIC);
                }
                break;

            case R.id.notice:
                if(isChecked) {
                    editor.putString("notice","on").apply();
                    studyNotice(NoticeService.SHOW);
                }
                else{
                    editor.remove("notice").apply();
                    studyNotice(NoticeService.UNSHOW);
                }
                break;
        }
    }

    private void playMusic(int type) {
        //启动服务，播放或停止音乐
        Intent intent=new Intent(this, PlayMusicService.class);
        intent.putExtra("type",type);
        startService(intent);
    }

    private void studyNotice(int type){
        Intent intent = new Intent(this, NoticeService.class);
        intent.putExtra("type",type);
        startService(intent);
    }

}