package com.ecnu.poemcloud.activity;

import com.ecnu.poemcloud.R;
import com.ecnu.poemcloud.activity.service.NoticeService;
import com.ecnu.poemcloud.activity.service.PlayMusicService;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;
import java.util.TimeZone;

public class SettingActivity extends BaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    Button checkbook;
    Button logout;
    ImageButton project;
    Switch bgm;
    Switch notice;
    TextView username;
    TextView levelnum;

    String bgm_pref;
    String notice_pref;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        username=findViewById(R.id.user);
        levelnum=findViewById(R.id.level);

        checkbook=findViewById(R.id.checkbook);
        logout=findViewById(R.id.logout);
        project=findViewById(R.id.project);
        bgm=findViewById(R.id.bgm);
        notice=findViewById(R.id.notice);

        checkbook.setOnClickListener(this);
        logout.setOnClickListener(this);
        project.setOnClickListener(this);
        bgm.setOnCheckedChangeListener(this);
        notice.setOnCheckedChangeListener(this);

        /* 用户设置 */

        username.setText(user_email);
        levelnum.append(String.valueOf(score));

        pref=getSharedPreferences("setting",MODE_PRIVATE);
        bgm_pref=pref.getString("bgm","off");
        notice_pref=pref.getString("notice","off");

        if(bgm_pref.equals("on"))bgm.setChecked(true);
        if(notice_pref.equals("on"))notice.setChecked(true);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkbook:
                Intent intent1=new Intent(SettingActivity.this,CuotiActivity.class);
                startActivity(intent1);
                break;
            case R.id.logout:
                Intent intent2=new Intent(SettingActivity.this,LoginActivity.class);
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
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, 0);
        if(type==NoticeService.UNSHOW){
            stopService(intent);
        }
        else{
            long intervalMillis = 1000*60*24*60;

            long firstTime = SystemClock.elapsedRealtime();    // 开机之后到现在的运行时间(包括睡眠时间)
            long systemTime = System.currentTimeMillis();

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            // 这里时区需要设置一下，不然会有8个小时的时间差
            calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            calendar.set(Calendar.MINUTE, 30);
            calendar.set(Calendar.HOUR_OF_DAY, 10);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            // 选择的定时时间
            long selectTime = calendar.getTimeInMillis();
            // 如果当前时间大于设置的时间，那么就从第二天的设定时间开始
            if(systemTime > selectTime) {
                //Toast.makeText(SettingActivity.this,"设置的时间小于当前时间", Toast.LENGTH_SHORT).show();
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                selectTime = calendar.getTimeInMillis();
            }
            // 计算现在时间到设定时间的时间差
            long time = selectTime - systemTime;
            firstTime += time;
            // 进行闹铃注册
            AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
            manager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    firstTime, intervalMillis, pendingIntent);

        }
    }

}