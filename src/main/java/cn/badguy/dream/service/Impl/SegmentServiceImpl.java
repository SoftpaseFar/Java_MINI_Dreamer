package cn.badguy.dream.service.Impl;

import cn.badguy.dream.common.ServerResponse;
import cn.badguy.dream.pojo.Dogs;
import cn.badguy.dream.service.ISegmentService;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;


import org.springframework.stereotype.Service;

@Service("iSegmentService")
public class SegmentServiceImpl implements ISegmentService {
    public ServerResponse<String> getSegments() {

        //http://{API区域域名}/nlp/segment/bitspaceman?apikey={您的apikey}
        //rnvOZ9IT7ewNTJp5WwooWzjVzLxU6Xin3xrtHPJzVw4YXmrd5SILAQi3mQWnsZEV
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody
                .Builder()
                .add("text", "哈士奇泰迪藏獒松狮牧羊犬")
                .build();

        Request request = new Request
                .Builder()
                .url("https://api01.bitspaceman.com/nlp/segment/bitspaceman?apikey=rnvOZ9IT7ewNTJp5WwooWzjVzLxU6Xin3xrtHPJzVw4YXmrd5SILAQi3mQWnsZEV")
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            Gson gson = new Gson();
            java.lang.reflect.Type type = new TypeToken<Dogs>() {
            }.getType();
            Dogs dogs = gson.fromJson(response.body().string(), type);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


//    public static void main(String[] args) {
//        OkHttpClient client = new OkHttpClient();
//
//        RequestBody body = new FormBody
//                .Builder()
//                .add("text", "哈士奇泰迪藏獒松狮牧羊犬")
//                .build();
//
//        ParamRequest request = new ParamRequest
//                .Builder()
//                .url("https://api01.bitspaceman.com/nlp/segment/bitspaceman?apikey=rnvOZ9IT7ewNTJp5WwooWzjVzLxU6Xin3xrtHPJzVw4YXmrd5SILAQi3mQWnsZEV")
//                .post(body)
//                .build();
//
//        try {
//            Response response = client.newCall(request).execute();
//            Gson gson = new Gson();
//            java.lang.reflect.Type type = new TypeToken<Dogs>() {
//            }.getType();
//            Dogs dogs = gson.fromJson(response.body().string(), type);
//            System.out.println(dogs);
//        } catch (exception e) {
//            e.printStackTrace();
//        }
//    }
}
