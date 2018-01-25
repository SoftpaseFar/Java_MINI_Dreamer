package cn.badguy.dream.untils;

import cn.badguy.dream.exception.ParamException;
import okhttp3.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParamRequest {

    private static OkHttpClient client;


    static {
        final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();
        client = new OkHttpClient.Builder().cookieJar(new CookieJar() {
            @Override
            public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
                cookieStore.put(httpUrl.host(), list);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl httpUrl) {
                List<Cookie> cookies = cookieStore.get(httpUrl.host());
                return cookies != null ? cookies : new ArrayList<>();
            }
        }).build();
    }

    public static Response bmobPostJson(String url, String json) {

        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request
                .Builder()
                .addHeader("X-Bmob-Application-Id", "ace4aea47d00899badb08cd1ef861617")
                .addHeader("X-Bmob-REST-API-Key", "0fafa3ab55f0d2716a1940d3c0497c69")
                .addHeader("Content-Type", "application/json")
                .url(url)
                .post(requestBody)
                .build();
        try {
            return client.newCall(request).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ParamException(10001, "请求失败");
    }


    public static Response bmobPostBody(String url, Map<String, String> bodys) {

        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> body : bodys.entrySet()) {
            builder.add(body.getKey(), body.getValue());
        }
        RequestBody body = builder.build();

        Request request = new Request
                .Builder()
                .addHeader("X-Bmob-Application-Id", "ace4aea47d00899badb08cd1ef861617")
                .addHeader("X-Bmob-REST-API-Key", "0fafa3ab55f0d2716a1940d3c0497c69")
                .addHeader("Content-Type", "application/json")
                .url(url)
                .post(body)
                .build();
        try {
            return client.newCall(request).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ParamException(10001, "请求失败");
    }


    public static Response postMethod(String url, Map<String, String> bodys) {

        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> body : bodys.entrySet()) {
            builder.add(body.getKey(), body.getValue());
        }
        RequestBody body = builder.build();

        Request request = new Request
                .Builder()
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
                .url(url)
                .post(body)
                .build();
        try {
            return client.newCall(request).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ParamException(10001, "请求失败");
    }


    public static Response getMethod(String url) {
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
