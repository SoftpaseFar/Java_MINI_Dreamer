package cn.badguy.dream.service.Impl;

import cn.badguy.dream.common.ServerResponse;
import cn.badguy.dream.pojo.Classmate;
import cn.badguy.dream.pojo.CmsMessage;
import cn.badguy.dream.pojo.Email;
import cn.badguy.dream.service.IBmobService;
import cn.badguy.dream.untils.ParamRequest;
import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iBmobService")
public class BmobServiceImpl implements IBmobService {


    public String createUserByExcel(OkHttpClient client) {

        String json = "{\"id\":1,\"name\":\"badguy\",\"sex\":\"男\"}";
        System.out.println(json);

        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);

        Request request = new Request
                .Builder()
                .addHeader("X-Bmob-Application-Id", "ace4aea47d00899badb08cd1ef861617")
                .addHeader("X-Bmob-REST-API-Key", "0fafa3ab55f0d2716a1940d3c0497c69")
                .addHeader("Content-Type", "application/json")
                .url("https://api.bmob.cn/1/classes/User")
                .post(requestBody)
                .build();


        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                return "no.";
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return "ok";
    }


    public String createUsersByExcel(OkHttpClient client, List<Classmate> students) {


        RequestBody requestBody = null;
        Request request = null;
        for (Classmate student : students) {
            Gson gson = new Gson();
            String json = gson.toJson(student);
            requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);

            request = new Request
                    .Builder()
                    .addHeader("X-Bmob-Application-Id", "ace4aea47d00899badb08cd1ef861617")
                    .addHeader("X-Bmob-REST-API-Key", "0fafa3ab55f0d2716a1940d3c0497c69")
                    .addHeader("Content-Type", "application/json")
                    .url("https://api.bmob.cn/1/classes/User")
                    .post(requestBody)
                    .build();

            try {
                client.newCall(request).execute();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return "Your request is success.";
    }


    public String sendMessage(OkHttpClient client) {
        CmsMessage cmsMessage = new CmsMessage("15865369579", "Nice to meet you.");
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(cmsMessage));
        Request request = new Request
                .Builder()
                .addHeader("X-Bmob-Application-Id", "ace4aea47d00899badb08cd1ef861617")
                .addHeader("X-Bmob-REST-API-Key", "0fafa3ab55f0d2716a1940d3c0497c69")
                .addHeader("Content-Type", "application/json")
                .url("https://api.bmob.cn/1/requestSms")
                .post(requestBody)
                .build();
        try {
            client.newCall(request).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Send message success.";
    }


    public String requestSmsCode(OkHttpClient client) {
        CmsMessage cmsMessage = new CmsMessage("15865369579");
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(cmsMessage));
        Request request = new Request
                .Builder()
                .addHeader("X-Bmob-Application-Id", "ace4aea47d00899badb08cd1ef861617")
                .addHeader("X-Bmob-REST-API-Key", "0fafa3ab55f0d2716a1940d3c0497c69")
                .addHeader("Content-Type", "application/json")
                .url("https://api.bmob.cn/1/requestSmsCode")
                .post(requestBody)
                .build();
        try {
            client.newCall(request).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "This is requestSmsCode.";
    }

    public String verifySmsCode(OkHttpClient client, String smsCode) {
        //https://api.bmob.cn/1/verifySmsCode/smsCode(用户收到的6位短信验证码)

        CmsMessage cmsMessage = new CmsMessage("15865369579");
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(cmsMessage));
        Request request = new Request
                .Builder()
                .addHeader("X-Bmob-Application-Id", "ace4aea47d00899badb08cd1ef861617")
                .addHeader("X-Bmob-REST-API-Key", "0fafa3ab55f0d2716a1940d3c0497c69")
                .addHeader("Content-Type", "application/json")
                .url("https://api.bmob.cn/1/verifySmsCode/" + smsCode)
                .post(requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "This is verifySmsCode.";
    }


    public ServerResponse<String> requestEmailVerify(OkHttpClient client) {
        //https://api.bmob.cn/1/requestEmailVerify

        Email email = new Email("kang_hui1314@126.com");
        String json = new Gson().toJson(email);

        Response response = ParamRequest.bmobPostJson("https://api.bmob.cn/1/requestEmailVerify", json);
        if (response.isSuccessful()) {
            return ServerResponse.createBySuccessMessage("success");
        } else {
            return ServerResponse.createByErrorMessage("This is requestEmailVerify");
        }
    }

}
