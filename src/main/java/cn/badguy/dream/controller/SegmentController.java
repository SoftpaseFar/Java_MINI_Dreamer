package cn.badguy.dream.controller;

import cn.badguy.dream.common.ServerResponse;
import cn.badguy.dream.service.ISegmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SegmentController {

    @Autowired
    private ISegmentService iSegmentService;

    @GetMapping("/segments/{name}/{nums}")
    public ServerResponse<String> getSegments(@PathVariable String name, @PathVariable Integer nums) {
        return iSegmentService.getSegments();
    }
}
