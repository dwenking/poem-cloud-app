package com.ecnu.poemcloud.utils.poemGenerate;

import com.huawei.nlp.client.NlgClient;
import com.huawei.nlp.client.auth.AuthInfo;
import com.huawei.nlp.client.auth.AuthMode;
import com.huawei.nlp.client.exception.NlpException;
import com.huawei.nlp.restapi.model.PoemReq;
import com.huawei.nlp.restapi.model.PoemResp;

import java.util.List;

public class PoemGenerate {

    private static String ak = "TP1RP6MVPFGQU5YGXW2H";
    private static String sk = "wTnbRRfcamRrSU1rUokMM3H1ukFUNF3be3lKJZ77";
    private static String project_id = "0a59786f5c00f4672fc0c0188e79fa34";
    private static String service_region = "cn-north-4";
    private static AuthInfo authInfo = new AuthInfo(ak, sk, service_region, project_id);
    private static NlgClient nlgClient = new NlgClient(AuthMode.AKSK,authInfo);


    public static List<String> poemDemo(String keyWord, int type) {
        PoemReq req = new PoemReq();
        req.setTitle(keyWord);
        req.setType(type);
        req.setAcrostic(false);
        try {
            PoemResp resp = nlgClient.composePoem(req);
            return resp.getPoem();
        } catch (NlpException e) {
            throw new RuntimeException(e);
        }
    }

}
