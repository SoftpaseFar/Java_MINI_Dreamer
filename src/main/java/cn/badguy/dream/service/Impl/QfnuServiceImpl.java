package cn.badguy.dream.service.Impl;

import cn.badguy.dream.common.ServerResponse;
import cn.badguy.dream.exception.ParamException;
import cn.badguy.dream.json.*;
import cn.badguy.dream.pojo.Classroom;
import cn.badguy.dream.pojo.Student;
import cn.badguy.dream.service.IQfnuService;
import cn.badguy.dream.untils.KlzApiRequest;
import cn.badguy.dream.untils.RE;
import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

@Service("iQfnuService")
@ApplicationScope
public class QfnuServiceImpl implements IQfnuService {

    public ServerResponse<List<ListOfLost>> getLostRecord(String token) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", token);

        Response response = KlzApiRequest.getMethod("https://api.qfnu.tech/urp/grade/fail", headers);
        if (response.isSuccessful()) {
            Gson gson = new Gson();
            try {
                LostRecord lostRecord = gson.fromJson(response.body().string(), LostRecord.class);

                List<ListOfLost> ListOfLosts = new ArrayList<>();
                for (ListOfLost ListOfLost : lostRecord.getData()) {
                    ListOfLosts.add(ListOfLost);
                }
                return ServerResponse.createBySuccess(ListOfLosts);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        throw new ParamException(10001, "获取失败");
    }


    public ServerResponse<List<ListOfNow>> getNowRecord(String token) {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", token);

        Response response = KlzApiRequest.getMethod("https://api.qfnu.tech/urp/grade/current", headers);
        if (response.isSuccessful()) {
            Gson gson = new Gson();
            try {
                NowRecord nowRecord = gson.fromJson(response.body().string(), NowRecord.class);
                List<ListOfNow> ListOfNows = new ArrayList<>();
                for (ListOfNow ListOfAll : nowRecord.getData()) {
                    ListOfNows.add(ListOfAll);
                }
                return ServerResponse.createBySuccess(ListOfNows);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        throw new ParamException(10001, "获取失败");
    }


    public ServerResponse<List<ListOfAll>> getAllRecord(String token) {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", token);


        Response response = KlzApiRequest.getMethod("https://api.qfnu.tech/urp/grade", headers);

        try {

            Gson gson = new Gson();
            AllRecord allRecord = gson.fromJson(response.body().string(), AllRecord.class);

            List<ListOfAll> ListOfAlls = new ArrayList<>();
            for (ListOfAll ListOfAll : allRecord.getData()) {
                ListOfAlls.add(ListOfAll);
            }
            return ServerResponse.createBySuccess(ListOfAlls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new ParamException(10001, "获取失败");
    }


    public ServerResponse<List<Room>> getEmptyClassroom(Classroom classroom, String token) {

        Map<String, String> body = new HashMap<>();
        body.put("building", classroom.getBuilding());
        body.put("campus", classroom.getCampus());
        body.put("week", classroom.getWeek());
        body.put("time", classroom.getTime());
        body.put("session", classroom.getSession());

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", token);
        Response response = KlzApiRequest.postMethod("https://api.qfnu.tech/urp/free-room", body, headers);

        try {
            /*
             *Json解析返回数据
             */
            Gson gson = new Gson();
            cn.badguy.dream.json.Classroom calssrooms = gson.fromJson(response.body().string(), cn.badguy.dream.json.Classroom.class);
            List<Room> rooms = new ArrayList<>();
            for (Room room : calssrooms.getData()) {
                rooms.add(room);
            }
            return ServerResponse.createBySuccess(rooms);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new ParamException(10001, "获取失败");

    }


    public ServerResponse<String> getToken(String username, String password) {
        Map<String, String> body = new HashMap<>();
        body.put("user_id", username);
        body.put("password", password);
        body.put("captcha", "");

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        Response response = KlzApiRequest.postMethod("https://api.qfnu.tech/login", body, headers);
        Gson gson = new Gson();
        try {
            KlzResult klzResult = gson.fromJson(response.body().string(), KlzResult.class);
            return ServerResponse.createBySuccessMessage(klzResult.getData().getToken());
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new ParamException(10001, "获取失败");
    }


    public ServerResponse<String> feedback(OkHttpClient client, String username, String title, String describe) {

        String json = "{\"id\":\"" + username + "\",\"title\":\"" + title + "\",\"describe\":\"" + describe + "\"}";
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request
                .Builder()
                .addHeader("X-Bmob-Application-Id", "df35531048bf2ad0d3c3520d0ca45e2a")
                .addHeader("X-Bmob-REST-API-Key", "d1429634e69f82c7cdc54c9f7b25499e")
                .addHeader("Content-Type", "application/json")
                .url("https://api.bmob.cn/1/classes/Feedback")
                .post(requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                System.out.println(response.body().string());
                return ServerResponse.createBySuccessMessage("反馈成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ParamException(10001, "请求失败");
    }


    public ServerResponse<String> Login(Student student, OkHttpClient client) {
        //获取lt和cookie
        //OkHttpClient client = new OkHttpClient();
        Request request = new Request
                .Builder()
                .addHeader("Host", "ids.qfnu.edu.cn")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                .url("http://ids.qfnu.edu.cn/authserver/login?service=http://my.qfnu.edu.cn/login.portal")
                .build();
        String lt = "";
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                lt = this.getLt(response.body().string());
            } else {
                throw new ParamException(10001, "登陆失败");
            }
        } catch (IOException e) {
            throw new ParamException(10001, e.getMessage());
        }

        //模拟登录
        FormBody body = new FormBody
                .Builder()
                .add("lt", lt)
                .add("username", student.getUsername())
                .add("password", student.getPassword())
                .add("execution", "e1s1")
                .add("_eventId", "submit")
                .add("rmShown", "1")
                .build();

        request = new Request
                .Builder()
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                .url("http://ids.qfnu.edu.cn/authserver/login?service=http://my.qfnu.edu.cn/login.portal")
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                if (isLogin(response.body().string())) {
                    return ServerResponse.createBySuccess("登陆成功");
                } else {
                    return ServerResponse.createByErrorMessage("登陆失败");
                }
            } else {
                throw new ParamException(10001, "获取失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new ParamException(10001, "获取失败");
    }

    private boolean isLogin(String line) {
        Matcher m = RE.match("退出", line);
        if (m.find()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isLogout(String line) {
        Matcher m = RE.match("登录", line);
        if (m.find()) {
            return true;
        } else {
            return false;
        }
    }

    private String getLt(String line) {
        Matcher m = RE.match("value=\"(LT-.*)\"", line);
        if (m.find()) {
            return m.group(1);
        } else {
            return "";
        }
    }


    public ServerResponse<String> Logout(OkHttpClient client) {

        FormBody body = new FormBody
                .Builder()
                .build();

        Request request = new Request
                .Builder()
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                .url("http://202.194.188.19/logout.do")
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                if (isLogout(response.body().string())) {
                    return ServerResponse.createBySuccessMessage("退出成功");
                } else {
                    return ServerResponse.createByErrorMessage("退出失败");
                }
            } else {
                throw new ParamException(10001, "退出失败");
            }
        } catch (IOException e) {
            throw new ParamException(10001, e.toString());
        }
    }

    public ServerResponse<String> getSchedule(OkHttpClient client) {
        //202.194.188.19/xkAction.do?actionType=6
        //"http://ids.qfnu.edu.cn/authserver/login?service=http://202.194.188.19/xkAction.do?actionType=6"
        Request request = new Request
                .Builder()
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                .url("http://202.194.188.19")
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                request = new Request
                        .Builder()
                        .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                        .url("http://ids.qfnu.edu.cn/authserver/login?service=http://202.194.188.19/xkAction.do?actionType=6")
                        .build();
                response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    String result = "";
                    result = this.analysis(response.body().string());
                    return ServerResponse.createBySuccessMessage(result);
                } else {
                    throw new ParamException(10001, "获取失败");
                }
            } else {
                throw new ParamException(10001, "获取失败");
            }
        } catch (IOException e) {
            throw new ParamException(10001, e.toString());
        }
    }

    private String analysis(String content) {
        String result = RE.replaceAll("\\n|\\r|\\t| |\\s", content, "");
        result = RE.replaceAll("<[^>]+>", result, "");
        result = RE.replaceAll("&nbsp", result, "@");
        return result;

    }


}
