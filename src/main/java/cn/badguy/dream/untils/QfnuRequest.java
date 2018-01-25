package cn.badguy.dream.untils;

import cn.badguy.dream.exception.ParamException;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.Map;

public class QfnuRequest {

    public static Response postMethod(OkHttpClient client, String url, Map<String, String> bodys) {
        return getResponse(client, url, bodys);
    }

    static Response getResponse(OkHttpClient client, String url, Map<String, String> bodys) {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> body : bodys.entrySet()) {
            builder.add(body.getKey(), body.getValue());
        }
        FormBody body = builder.build();


        Request request = new Request
                .Builder()
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
                .addHeader("Host","ids.qfnu.edu.cn")
                .addHeader("Connection","keep-alive")
                .addHeader("Cache-Control","max-age=0")
                .addHeader("Origin","http://ids.qfnu.edu.cn")
                .addHeader("Upgrade-Insecure-Requests","1")
                .addHeader("Content-Type","application/x-www-form-urlencoded")
                .addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                .addHeader("Accept-Encoding","gzip, deflate")
                .addHeader("Accept-Language","zh-CN,zh;q=0.9")
                .url(url)
                .post(body)
                .build();

        try {
            System.out.println();
            return client.newCall(request).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ParamException(10001, "请求失败");
    }

    public static Response getMethod(OkHttpClient client, String url) {
        Request request = new Request
                .Builder()
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
                .url(url)
                .build();
        try {
            return client.newCall(request).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ParamException(10001, "请求失败");
    }
}
