package com.abs.register.controller;

import com.abs.register.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 15:31 2018/5/30
 **/
@RestController
@RequestMapping(value = "/user")
public class RegisterController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private RegisterService registerService;

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register() throws InterruptedException {
        /*try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        Thread.sleep(3000);
        return "eeeeyyyy"+serverPort;
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return registerService.getProductById()+"**serverPort"+serverPort;
    }
}
