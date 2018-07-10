package com.abs.springabsfeign.controller;

import com.abs.springabsfeign.service.UserFeignClient;
import com.abs.springabsfeign.service.UserFeignOrderClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 20:12 2018/6/14
 **/
@RestController
public class UserFeignApi {

//    @Autowired
//    private UserFeignClient userFeignClient;

//    @Autowired
//    private UserFeignOrderClient userFeignOrderClient;

   /* @GetMapping("/test5")
    public String listUsers(){
        String a=userFeignClient.test2();
        return a;
    }*/

    @GetMapping("/test6")
    public String test6(){
        return "test66666";
    }

   /* @GetMapping("/test8")
    @HystrixCommand(fallbackMethod = "defaultRest")
    public String test8(){
        String b=userFeignOrderClient.test();
        return b;
    }*/

//    public String defaultRest() {
//        return "default HystrixCommand hahaha";
//    }
}
