package cn.badguy.dream.controller;

import cn.badguy.dream.common.ServerResponse;
import cn.badguy.dream.service.IStuValiService;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class StuValiController {
    private final static HashMap<String, List<Cookie>> cookieStore = new HashMap<>();
    private static OkHttpClient client = new OkHttpClient.Builder()
            .followRedirects(true)
            .followSslRedirects(true)
            .cookieJar(new CookieJar() {
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

    @Autowired
    private IStuValiService iStuValiService;

    @GetMapping("/vali/student/baidu")
    public ServerResponse validateStudent(String username, String password) {
        return iStuValiService.validateStudent(client, username, password);
    }


    @GetMapping("/vali/student/aliyun")
    public ServerResponse validateStudentParam(String username, String password) {
        return iStuValiService.validateStudentParam(client, username, password);
    }


    @GetMapping("/student/vali/{min_size}/{output_prob}")
    public ServerResponse validateStudentTest(String username, String password, @PathVariable Integer min_size, @PathVariable Boolean output_prob) {
        return iStuValiService.validateStudentTest(client, username, password, min_size, output_prob);
    }

    @GetMapping("/vali/stuPic")
    public ServerResponse<String> validateStudentPic(String username, String password, HttpServletResponse response) {
        return iStuValiService.validateStudentPic(client, username, password, response);
    }
}
