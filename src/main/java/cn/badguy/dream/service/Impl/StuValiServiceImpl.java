package cn.badguy.dream.service.Impl;

import cn.badguy.dream.common.ServerResponse;
import cn.badguy.dream.exception.ParamException;
import cn.badguy.dream.json.Student;
import cn.badguy.dream.service.IStuValiService;
import cn.badguy.dream.untils.RE;
import cn.badguy.dream.vo.StudentVo;
import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.regex.Matcher;

@Service("iStuValiService")
public class StuValiServiceImpl implements IStuValiService {


    public ServerResponse validateStudent(OkHttpClient client, String username, String
            password) {
        String lt = getLt(client);
        login(client, username, password, lt);
        String pid = getPid(client);
        byte[] message = getMessage(client, pid);
        String info = analysisByBaidu(client, message).trim();
        logout(client);
        return null;
    }


    private String analysisByBaidu(OkHttpClient client, byte[] message) {
        BASE64Encoder encoder = new BASE64Encoder();
        String str = encoder.encode(message);
        String urlString = URLEncoder.encode(str);
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), urlString);

        Request request = new Request
                .Builder()
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
                .addHeader("apikey", "7XUZWQZ2SX4RzLGdIGblIU5B")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .url("https://aip.baidubce.com/rest/2.0/ocr/v1/accurate")
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    public ServerResponse validateStudentParam(OkHttpClient client, String username, String
            password) {
        String lt = getLt(client);
        login(client, username, password, lt);
        String pid = getPid(client);
        byte[] message = getMessage(client, pid);
        String info = analysis(client, message, 13, true).trim();
        logout(client);
        StudentVo student = getStudentVo(info);
        return ServerResponse.createBySuccess(student);
    }


    private StudentVo getStudentVo(String info) {
        info = RE.replaceAll("([\\r\\n])[\\s]+", info, "");
        Gson gson = new Gson();
        Student student = gson.fromJson(info, Student.class);


        StudentVo studentVo = new StudentVo();

        studentVo.setName(student.getRets().get(1).getWord());
        studentVo.setId(student.getRets().get(21).getWord());
        studentVo.setUniversity(student.getRets().get(5).getWord());
        studentVo.setCollege(student.getRets().get(16).getWord());
        studentVo.setProfession(student.getRets().get(9).getWord());
        studentVo.setPhoneNum("15865369579");
        studentVo.setStuCertify(true);

        return studentVo;
    }


    public ServerResponse validateStudentTest(OkHttpClient client, String username, String
            password, int min_size, boolean output_prob) {
        String lt = getLt(client);
        login(client, username, password, lt);
        String pid = getPid(client);
        byte[] message = getMessage(client, pid);
        String info = analysis(client, message, min_size, output_prob).trim();
        info = RE.replaceAll("([\\r\\n])[\\s]+", info, "");
        Gson gson = new Gson();
        Student student = gson.fromJson(info, Student.class);
        logout(client);
        getStuInfo(info);
        if (info != "" && info != null) {
            return ServerResponse.createBySuccess(student);
        } else {
            return ServerResponse.createByErrorMessage("解析失败");
        }

    }


    private void getStuInfo(String info) {
//        Matcher m = RE.match("\\\"word\\\":\\\"(.*)?\\\"}", info);
//        System.out.println(m.group());
    }


    private String analysis(OkHttpClient client, byte[] message, int min_size, boolean output_prob) {

        BASE64Encoder encoder = new BASE64Encoder();
        String str = encoder.encode(message);

        String json = "{\"image\":\"" + str + "\",\"configure\":\"{\\\"min_size\\\":" + min_size + ",\\\"output_prob\\\":" + output_prob + "}\"}";
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);

        Request request = new Request
                .Builder()
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
                .addHeader("Authorization", "APPCODE c32864769a9c469fba86e02fff83ad79")
                .url("http://tysbgpu.market.alicloudapi.com/api/predict/ocr_general")
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    public ServerResponse<String> validateStudentPic(OkHttpClient client, String username, String password, HttpServletResponse response) {
        String lt = getLt(client);
        login(client, username, password, lt);
        String pid = getPid(client);
        byte[] message = getMessage(client, pid);
        logout(client);
        response.setHeader("Content-Type", "image/jpeg");
        try {
            response.getOutputStream().write(message);
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ServerResponse.createByErrorMessage("验证失败");
    }


    private byte[] getMessage(OkHttpClient client, String pid) {
        //https://my.chsi.com.cn/archive/gdjy/xj/show.action
        Request request = new Request
                .Builder()
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
                .url("https://my.chsi.com.cn/archive/gdjy/photo/show.action?" + pid)
                .get()
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().bytes();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new ParamException(10001, "验证失败");
    }


    private String getPid(OkHttpClient client) {
        //show.action?pid=09519f9de9f96581dfbda8b0f1462a30"  />
        Request request = new Request
                .Builder()
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
                .url("https://my.chsi.com.cn/archive/gdjy/xj/show.action")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                Matcher m = RE.match("action\\?(pid.*)?\"  />", response.body().string());
                if (m.find()) {
                    return m.group(1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new ParamException(10001, "验证失败");
    }


    private void logout(OkHttpClient client) {


        Request request = new Request
                .Builder()
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
                .url("https://account.chsi.com.cn/passport/logout?service=https://my.chsi.com.cn/archive/index.jsp")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                response.close();
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new ParamException(10001, "验证失败");

    }


    private ServerResponse<String> validate(String content) {
        Matcher m = RE.match("国际合作申请", content);
        if (m.find()) {
            return ServerResponse.createBySuccessMessage("验证成功");
        } else {
            return ServerResponse.createByErrorMessage("验证失败");
        }
    }


    private String login(OkHttpClient client, String username, String password, String lt) {

        RequestBody body = new FormBody
                .Builder()
                .add("username", username)
                .add("password", password)
                .add("lt", lt)
                .add("_eventId", "submit")
                .add("submit", "登  录")
                .build();


        Request request = new Request
                .Builder()
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
                .url("https://account.chsi.com.cn/passport/login?service=https%3A%2F%2Fmy.chsi.com.cn%2Farchive%2Fj_spring_cas_security_check")
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String result = response.body().string() + "";
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ParamException(10001, "验证失败");
    }


    private String getLt(OkHttpClient client) {
        String lt = "";
        Request request = new Request
                .Builder()
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
                .url("https://account.chsi.com.cn/passport/login?service=https%3A%2F%2Fmy.chsi.com.cn%2Farchive%2Fj_spring_cas_security_check")
                .build();
        try {
            Response response = client.newCall(request).execute();
            Matcher m = RE.match("name=\"lt\" value=\"(.*)?\" />", response.body().string());
            if (m.find()) {
                lt = m.group(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lt;
    }


}
