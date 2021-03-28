package com.example.shiyun.poemGenerate;

import com.huawei.nlp.client.constant.PoemConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<String> main(InputStream is) {
//        filePath = "res/drawable-pic/sakura.png";
        String result = AdvancedGeneral.advancedGeneral(is);
        result = result.substring(result.indexOf("["), result.indexOf("]") + 1);
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        List<JSONObject> result_list = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                result_list.add((JSONObject) jsonArray.get(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        String keyword = null;
        try {
            keyword = result_list.get(1).getString("keyword");
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        keyword = keyword.substring(keyword.indexOf("-") + 1);
        System.out.println(keyword);

        List<String> poem;
        poem = PoemGenerate.poemDemo(keyword, PoemConstant.FOUR_LINE_WITH_FIVE_CHAR);
        System.out.println(poem);

        return poem;
    }
}