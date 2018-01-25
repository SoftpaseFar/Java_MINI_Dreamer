package cn.badguy.dream.service;

import javax.servlet.http.HttpServletResponse;

public interface ICaptcahService {
    String getCaptcah(HttpServletResponse response);
}
