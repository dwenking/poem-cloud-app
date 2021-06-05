package com.ecnu.poemcloud.activity.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.ecnu.poemcloud.R;
import com.ecnu.poemcloud.activity.MainActivity1;

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

                mn.notify((int)System.currentTimeMillis(),notification);
            }
        }).start();
        return START_STICKY;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}