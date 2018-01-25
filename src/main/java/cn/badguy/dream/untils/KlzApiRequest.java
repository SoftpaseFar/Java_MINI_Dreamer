package cn.badguy.dream.untils;

import cn.badguy.dream.exception.ParamException;
import okhttp3.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KlzApiRequest {

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


    public static Response postMethod(String url, Map<String, String> bodys, Map<String, String> headers) {

        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> body : bodys.entrySet()) {
            builder.add(body.getKey(), body.getValue());
        }
        RequestBody body = builder.build();

        Request.Builder b = new Request.Builder();
        for (Map.Entry<String, String> header : headers.entrySet()) {
            b.addHeader(header.getKey(), header.getValue());
        }
        b.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
                .url(url)
                .post(body);
        Request request = b.build();

        try {
            return client.newCall(request).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ParamException(10001, "请求失败");
    }


    public static Response getMethod(String url, Map<String, String> headers) {


        Request.Builder builder = new Request.Builder();
        for (Map.Entry<String, String> header : headers.entrySet()) {
            builder.addHeader(header.getKey(), header.getValue());
        }
        builder.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
                .url(url);
        Request request = builder.build();


        try {
            return client.newCall(request).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ParamException(10001, "请求失败");
    }


}
