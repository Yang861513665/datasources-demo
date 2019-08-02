package com.yangxj.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author yangxj
 * @date 2019/7/28-11:51
 */
@RestController
public class TestAopControlller {
    @RequestMapping("testAop")
    public String testAop(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello aop!";
    }
}
