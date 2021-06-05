package com.ecnu.poemcloud.activity.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.ecnu.poemcloud.R;

import java.io.IOException;

public class PlayMusicService extends Service {
    private MediaPlayer mediaPlayer;

    public static final int PLAY_MUSIC=1;
    public static final int STOP_MUSIC=0;

    public PlayMusicService() {
    }

    @Override
    /* 与Activity交互 */
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        if (mediaPlayer==null){
            mediaPlayer=new MediaPlayer().create(this,R.raw.china);
            mediaPlayer.setLooping(true);
        }
    }

    @Override
    /* 每次服务启动时调用 */
    public int onStartCommand(Intent intent,int flags,int startId){
        switch (intent.getIntExtra("type",-1)){
            case PLAY_MUSIC:
                mediaPlayer.start();
                break;
            case STOP_MUSIC:
                if (mediaPlayer!=null){
                    //停止之后要开始播放音乐
                    mediaPlayer.stop();
                    try {
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
        return START_NOT_STICKY;
    }
}
