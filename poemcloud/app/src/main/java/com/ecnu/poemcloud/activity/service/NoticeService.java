package com.ecnu.poemcloud.activity.service;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.ecnu.poemcloud.R;
import com.ecnu.poemcloud.activity.MainActivity1;

import java.util.Calendar;
import java.util.TimeZone;

public class NoticeService extends Service {

    public static final int SHOW=1;
    public static final int UNSHOW=0;

    public NoticeService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public int onStartCommand(final Intent intent, int flags, int startId) {
        AlarmManager manager=(AlarmManager) getSystemService(ALARM_SERVICE);
        Intent i=new Intent(this,NoticeService.class);
        i.putExtra("type",SHOW);
        PendingIntent pi=PendingIntent.getService(this,0,i,0);

        switch (intent.getIntExtra("type",-1)){
            case UNSHOW:
                manager.cancel(pi);
                break;
            case SHOW:
                long intervalMillis = 1000*60*24*60; // 间隔一天

                long firstTime = SystemClock.elapsedRealtime();    // 开机之后到现在的运行时间(包括睡眠时间)
                long systemTime = System.currentTimeMillis();

                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                // 这里时区需要设置一下，不然会有8个小时的时间差
                calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                calendar.set(Calendar.MINUTE, 0);
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

                new Thread(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void run() {
                        NotificationManager mn= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        String id ="channel_1";//channel的id
                        String description = "123";//channel的描述信息
                        int importance = NotificationManager.IMPORTANCE_LOW;//channel的重要性
                        NotificationChannel channel = new NotificationChannel(id, description, importance);//生成channel
                        mn.createNotificationChannel(channel);

                        Intent notificationIntent = new Intent(NoticeService.this, MainActivity1.class);//点击跳转位置
                        PendingIntent contentIntent = PendingIntent.getActivity(NoticeService.this,0,notificationIntent,0);

                        Notification notification = new Notification.Builder(NoticeService.this,id)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentText("快进入诗云学习吧！") //下拉通知内容
                                .setContentTitle("学习提醒⏰")//下拉通知栏标题
                                .setAutoCancel(true)
                                .setDefaults(Notification.DEFAULT_ALL)
                                .setContentIntent(contentIntent)
                                .build();

                        try {
                            Thread.sleep(time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mn.notify((int)System.currentTimeMillis(),notification);
                    }
                }).start();

                // 进行闹铃注册
                manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        firstTime, pi);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}