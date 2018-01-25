package cn.badguy.dream.controller;

import cn.badguy.dream.dao.GirlMapper;
import cn.badguy.dream.pojo.Girl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.MXBean;

@RestController
public class TestController {



    @Autowired
    private GirlMapper girlMapper;

    @GetMapping("/test/test")
    public void test() {

        Girl g = new Girl();
        g.setName("okokokokok");
        g.setId(10);
        g.setAge(18);

        girlMapper.insert(g);
    }
}
