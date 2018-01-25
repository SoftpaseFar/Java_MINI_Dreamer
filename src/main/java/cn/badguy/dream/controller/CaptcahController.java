package cn.badguy.dream.controller;

import cn.badguy.dream.service.ICaptcahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class CaptcahController {

    @Autowired
    private ICaptcahService iCaptcahService;

    @GetMapping("/captcah/get")
    public String getCaptcah(HttpServletResponse response) {
        return iCaptcahService.getCaptcah(response);
    }
}
