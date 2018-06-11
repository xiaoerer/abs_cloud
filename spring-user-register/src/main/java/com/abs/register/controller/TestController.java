package com.abs.register.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 16:22 2018/5/31
 **/
@RestController
public class TestController {

    @RequestMapping(value = "/test2",method = RequestMethod.GET)
    public String getTest2(){
        return "user测试";
    }

}
