package com.ecnu.poemcloud.activity;

import android.app.Application;


public class GlobalApplication extends Application {

    private String user_email;
    private int id_user;

    public String getUser() {
        return user_email;
    }
    public void setUser(String user) {
        this.user_email = user;
    }

    public int getId_user() {
        return id_user;
    }
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

}