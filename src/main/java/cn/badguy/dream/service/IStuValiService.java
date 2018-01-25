package cn.badguy.dream.service;

import cn.badguy.dream.common.ServerResponse;
import okhttp3.OkHttpClient;

import javax.servlet.http.HttpServletResponse;

public interface IStuValiService {
    ServerResponse validateStudentTest(OkHttpClient client, String username, String password, int min_size, boolean output_prob);

    ServerResponse<String> validateStudentPic(OkHttpClient client, String username, String password, HttpServletResponse response);

    ServerResponse validateStudentParam(OkHttpClient client, String username, String password);

    ServerResponse validateStudent(OkHttpClient client, String username, String password);
}
