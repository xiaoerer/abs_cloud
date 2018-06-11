package com.abs.springconfigclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 16:06 2018/6/8
 **/
@RestController
public class TestController {

    @Value("${hello}")
    String name;

    @RequestMapping(value = "/hi")
    public String hi(){
        return name;
    }

}
