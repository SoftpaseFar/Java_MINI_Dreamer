package cn.badguy.dream.controller;

import cn.badguy.dream.common.ServerResponse;
import cn.badguy.dream.pojo.Classmate;
import cn.badguy.dream.service.IBmobService;
import cn.badguy.dream.service.IExcelService;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/bmob")
public class BmobController {
    private final static HashMap<String, List<Cookie>> cookieStore = new HashMap<>();
    private static OkHttpClient client = new OkHttpClient.Builder().cookieJar(new CookieJar() {
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
    private IBmobService iBmobService;

    @Autowired
    private IExcelService iExcelService;


    @GetMapping("/user/create")
    public String createUserByExcel() {
        return iBmobService.createUserByExcel(client);
    }

    @GetMapping("/users/create")
    public String createUsersByExcel() {
        List<Classmate> students = iExcelService.getStudentsList();
        return iBmobService.createUsersByExcel(client, students);
    }

    @GetMapping("/cms/test")
    public String sendMessage() {
        return iBmobService.sendMessage(client);
    }

    @GetMapping("/cms/request")
    public String requestSmsCode() {
        return iBmobService.requestSmsCode(client);
    }

    @GetMapping("/cms/verify")
    public String verifySmsCode(String smsCode) {
        return iBmobService.verifySmsCode(client,smsCode);
    }


    @GetMapping("/email/verify")
    public ServerResponse<String> requestEmailVerify() {
        return iBmobService.requestEmailVerify(client);
    }




}
