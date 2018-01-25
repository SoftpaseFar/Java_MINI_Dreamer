package cn.badguy.dream.service;

import cn.badguy.dream.common.ServerResponse;
import cn.badguy.dream.pojo.Classmate;
import okhttp3.OkHttpClient;

import java.util.List;

public interface IBmobService {
    String createUserByExcel(OkHttpClient client);
    String createUsersByExcel(OkHttpClient client, List<Classmate> students);
    String sendMessage(OkHttpClient client);
    String requestSmsCode(OkHttpClient client);
    String verifySmsCode(OkHttpClient client, String smsCode);
    ServerResponse<String> requestEmailVerify(OkHttpClient client);
}
