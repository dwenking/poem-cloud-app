package com.example.shiyun.poemGenerate;

import com.example.shiyun.poemGenerate.utils.Base64Util;
import com.example.shiyun.poemGenerate.utils.FileUtil;
import com.example.shiyun.poemGenerate.utils.HttpUtil;

import java.io.File;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * 通用物体和场景识别
 */
public class AdvancedGeneral {

    public static String advancedGeneral(InputStream is) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/image-classify/v2/advanced_general";
        try {
            // 本地文件路径
            byte[] imgData = FileUtil.toByteArray(is);
//            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getAuth();

            String result = HttpUtil.post(url, accessToken, param);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}