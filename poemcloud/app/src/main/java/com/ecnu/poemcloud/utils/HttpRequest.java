package com.ecnu.poemcloud.utils;

import com.ecnu.poemcloud.entity.Common_sense;
import com.ecnu.poemcloud.entity.Poem;
import com.ecnu.poemcloud.entity.Question;
import com.ecnu.poemcloud.entity.QuestionBasis;
import com.ecnu.poemcloud.entity.QuestionBlank;
import com.ecnu.poemcloud.entity.QuestionChoice;
import com.ecnu.poemcloud.entity.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRequest {

    private static Gson gson = new Gson();

    public static String login(String email, String password) {
        Map<String, String> param = new HashMap<>();
        param.put("email", email);
        param.put("password", password);
        String address = com.ecnu.poemcloud.utils.HttpUtils.appendUrl("/login", param);
        com.ecnu.poemcloud.utils.HttpCallbackListener listener = new com.ecnu.poemcloud.utils.HttpCallbackListener();

        com.ecnu.poemcloud.utils.HttpUtils.sendHttpRequest(address, listener, "GET");
        return listener.getResponse();
    }

    public static int getIdByEmail(String email) {
        Map<String, String> param = new HashMap<>();
        param.put("email", email);
        String address = com.ecnu.poemcloud.utils.HttpUtils.appendUrl("/getIdByEmail", param);
        com.ecnu.poemcloud.utils.HttpCallbackListener listener = new com.ecnu.poemcloud.utils.HttpCallbackListener();

        com.ecnu.poemcloud.utils.HttpUtils.sendHttpRequest(address, listener, "GET");
        return Integer.parseInt(listener.getResponse());
    }

    public static String register(String email, String password) {
        Map<String, String> param = new HashMap<>();
        param.put("email", email);
        param.put("password", password);
        String address = com.ecnu.poemcloud.utils.HttpUtils.appendUrl("/register", param);
        com.ecnu.poemcloud.utils.HttpCallbackListener listener = new com.ecnu.poemcloud.utils.HttpCallbackListener();

        com.ecnu.poemcloud.utils.HttpUtils.sendHttpRequest(address, listener, "GET");
        return listener.getResponse();
    }

    public static int getScoreByEmail(String email) {
        Map<String, String> param = new HashMap<>();
        param.put("email", email);
        String address = com.ecnu.poemcloud.utils.HttpUtils.appendUrl("/getScoreByEmail", param);
        com.ecnu.poemcloud.utils.HttpCallbackListener listener = new com.ecnu.poemcloud.utils.HttpCallbackListener();

        com.ecnu.poemcloud.utils.HttpUtils.sendHttpRequest(address, listener, "GET");
        return Integer.parseInt(listener.getResponse());
    }

    public static List<QuestionBasis> getWrongQuestionListByIdUser(int id_user) {
        Map<String, String> param = new HashMap<>();
        param.put("id_user", String.valueOf(id_user));

        String address = com.ecnu.poemcloud.utils.HttpUtils.appendUrl("/getWrongQuestionListByIdUser", param);
        com.ecnu.poemcloud.utils.HttpCallbackListener listener = new com.ecnu.poemcloud.utils.HttpCallbackListener();

        com.ecnu.poemcloud.utils.HttpUtils.sendHttpRequest(address, listener, "GET");

        String response = listener.getResponse();

        List<QuestionBasis> ret = gson.fromJson(response, new TypeToken<List<QuestionBasis>>(){}.getType());

        return ret;
    }

    public static Question getQuestionById(int id_question, int question_type) {
        Map<String, String> param = new HashMap<>();
        param.put("id_question", String.valueOf(id_question));
        String address = com.ecnu.poemcloud.utils.HttpUtils.appendUrl("/getQuestionById", param);
        com.ecnu.poemcloud.utils.HttpCallbackListener listener = new com.ecnu.poemcloud.utils.HttpCallbackListener();

        com.ecnu.poemcloud.utils.HttpUtils.sendHttpRequest(address, listener, "GET");
        String response = listener.getResponse();

        Question ret = null;
        if (question_type == 0)
            ret = gson.fromJson(response, QuestionBlank.class);
        else if (question_type == 1)
            ret = gson.fromJson(response, QuestionChoice.class);
        return ret;
    }

    public static String updateWrongFlagByIdUserQuestion(int id_user, int id_question, int flag) {
        Map<String, String> param = new HashMap<>();
        param.put("id_user", String.valueOf(id_user));
        param.put("id_question", String.valueOf(id_question));
        param.put("wrong_flag", String.valueOf(flag));
        String address = com.ecnu.poemcloud.utils.HttpUtils.appendUrl("/updateWrongFlagByIdUserQuestion", param);
        com.ecnu.poemcloud.utils.HttpCallbackListener listener = new com.ecnu.poemcloud.utils.HttpCallbackListener();

        com.ecnu.poemcloud.utils.HttpUtils.sendHttpRequest(address, listener, "GET");
        String response = listener.getResponse();

        return response;
    }

    public static int getIdQuestionByIdUser(int id_user, int id_theme, int type) {
        Map<String, String> param = new HashMap<>();
        param.put("id_user", String.valueOf(id_user));
        param.put("id_theme", String.valueOf(id_theme));
        param.put("type", String.valueOf(type));
        String address = com.ecnu.poemcloud.utils.HttpUtils.appendUrl("/getIdQuestionByIdUser", param);
        com.ecnu.poemcloud.utils.HttpCallbackListener listener = new com.ecnu.poemcloud.utils.HttpCallbackListener();

        com.ecnu.poemcloud.utils.HttpUtils.sendHttpRequest(address, listener, "GET");
        String response = listener.getResponse();

        return Integer.parseInt(response);
    }

    public static QuestionChoice getNewQuestionChoice(int id_user, int id_theme) {
        int id_question = com.ecnu.poemcloud.utils.HttpRequest.getIdQuestionByIdUser(id_user, id_theme, 1);
        if (id_question == -1)
            return null;
        QuestionChoice questionChoice = (QuestionChoice) com.ecnu.poemcloud.utils.HttpRequest.getQuestionById(id_question, 1);
        return questionChoice;
    }

    public static QuestionBlank getNewQuestionBlank(int id_user, int id_theme) {
        int id_question = com.ecnu.poemcloud.utils.HttpRequest.getIdQuestionByIdUser(id_user, id_theme, 0);
        if (id_question == -1)
            return null;
        QuestionBlank questionBlank = (QuestionBlank) com.ecnu.poemcloud.utils.HttpRequest.getQuestionById(id_question, 0);
        return questionBlank;
    }

    public static int getQuestionTypeById(int id_question) {
        Map<String, String> param = new HashMap<>();
        param.put("id_question", String.valueOf(id_question));

        String address = com.ecnu.poemcloud.utils.HttpUtils.appendUrl("/getQuestionTypeById", param);
        com.ecnu.poemcloud.utils.HttpCallbackListener listener = new com.ecnu.poemcloud.utils.HttpCallbackListener();

        com.ecnu.poemcloud.utils.HttpUtils.sendHttpRequest(address, listener, "GET");
        String response = listener.getResponse();

        return Integer.parseInt(response);
    }

    public static int doQuestion(int id_user, int id_question, String answer) {
        Map<String, String> param = new HashMap<>();
        param.put("id_user", String.valueOf(id_user));
        param.put("id_question", String.valueOf(id_question));

        int question_type = getQuestionTypeById(id_question);

        boolean right_flag = false;
        if (question_type== 0) {
            QuestionBlank questionBlank = (QuestionBlank) getQuestionById(id_question, 0);
            if (questionBlank.answer.equals(answer))
                right_flag = true;
        } else if (question_type == 1) {
            QuestionChoice questionChoice = (QuestionChoice) getQuestionById(id_question, 1);
            if (questionChoice.answer.equals(answer))
                right_flag = true;
        }

        param.put("right_flag", String.valueOf(right_flag));

        String address = com.ecnu.poemcloud.utils.HttpUtils.appendUrl("/recordDidQuestion", param);
        com.ecnu.poemcloud.utils.HttpCallbackListener listener = new com.ecnu.poemcloud.utils.HttpCallbackListener();

        com.ecnu.poemcloud.utils.HttpUtils.sendHttpRequest(address, listener, "POST");
        String response = listener.getResponse();

        return Integer.parseInt(response);
    }

    public static int addScore(int id_user, int add_score) {
        Map<String, String> param = new HashMap<>();
        param.put("id_user", String.valueOf(id_user));
        param.put("add_score", String.valueOf(add_score));

        String address = com.ecnu.poemcloud.utils.HttpUtils.appendUrl("/addScore", param);
        com.ecnu.poemcloud.utils.HttpCallbackListener listener = new com.ecnu.poemcloud.utils.HttpCallbackListener();

        com.ecnu.poemcloud.utils.HttpUtils.sendHttpRequest(address, listener, "GET");
        String response = listener.getResponse();

        return Integer.parseInt(response);
    }

    public static Poem getPoemById(int id_poem) {
        Map<String, String> param = new HashMap<>();
        param.put("id_poem", String.valueOf(id_poem));

        String address = com.ecnu.poemcloud.utils.HttpUtils.appendUrl("/getPoemById", param);
        com.ecnu.poemcloud.utils.HttpCallbackListener listener = new com.ecnu.poemcloud.utils.HttpCallbackListener();

        com.ecnu.poemcloud.utils.HttpUtils.sendHttpRequest(address, listener, "GET");
        String response = listener.getResponse();

        return gson.fromJson(response, Poem.class);
    }

    public static List<Poem> getPoemBetweenLowHigh(int low_id, int high_id) {
        Map<String, String> param = new HashMap<>();
        param.put("low_id", String.valueOf(low_id));
        param.put("high_id", String.valueOf(high_id));

        String address = com.ecnu.poemcloud.utils.HttpUtils.appendUrl("/getPoemBetweenLowHigh", param);
        com.ecnu.poemcloud.utils.HttpCallbackListener listener = new com.ecnu.poemcloud.utils.HttpCallbackListener();

        com.ecnu.poemcloud.utils.HttpUtils.sendHttpRequest(address, listener, "GET");
        String response = listener.getResponse();

        return gson.fromJson(response, new TypeToken<List<Poem>>(){}.getType());
    }

    public static List<Common_sense> getCommonSenseBetweenLowHigh(int low_id, int high_id, int id_theme) {
        Map<String, String> param = new HashMap<>();
        param.put("low_id", String.valueOf(low_id));
        param.put("high_id", String.valueOf(high_id));
        param.put("id_theme", String.valueOf(id_theme));

        String address = com.ecnu.poemcloud.utils.HttpUtils.appendUrl("/getCommonSenseBetweenLowHigh", param);
        com.ecnu.poemcloud.utils.HttpCallbackListener listener = new com.ecnu.poemcloud.utils.HttpCallbackListener();

        com.ecnu.poemcloud.utils.HttpUtils.sendHttpRequest(address, listener, "GET");
        String response = listener.getResponse();

        return gson.fromJson(response, new TypeToken<List<Common_sense>>(){}.getType());
    }

    public static List<User> getUserListOrderly(String order) {
        Map<String, String> param = new HashMap<>();
        param.put("order", order);

        String address = HttpUtils.appendUrl("/getUserListOrderly", param);
        HttpCallbackListener listener = new HttpCallbackListener();

        HttpUtils.sendHttpRequest(address, listener, "GET");
        String response = listener.getResponse();

        return gson.fromJson(response, new TypeToken<List<User>>(){}.getType());
    }

    public static List<Integer> getIdQuestionList(int id_theme, int type, int limit) {
        Map<String, String> param = new HashMap<>();
        param.put("id_theme", String.valueOf(id_theme));
        param.put("type", String.valueOf(type));
        param.put("limit", String.valueOf(limit));

        String address = HttpUtils.appendUrl("/getIdQuestionList", param);
        HttpCallbackListener listener = new HttpCallbackListener();

        HttpUtils.sendHttpRequest(address, listener, "GET");
        String response = listener.getResponse();

        return gson.fromJson(response, new TypeToken<List<Integer>>(){}.getType());
    }

}


