package com.ecnu.poemcloud.utils;

public class HttpCallbackListener {

    private String response;

    public void onFinish(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

}