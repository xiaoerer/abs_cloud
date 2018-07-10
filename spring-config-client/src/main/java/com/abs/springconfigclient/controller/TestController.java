package com.abs.springconfigclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 16:06 2018/6/8
 **/
@RestController
//开启更新机制,GitLab服务端配置修改,系统里面的引用的内容自动更新的功能
//不过需要手动请求才能刷新,如果修改为 自动刷新的,则需要集成webhook.
//Webhook 是当某个事件发生时，通过发送 HTTP POST 请求的方式来通知信息接收方.
//但是还是不方面,每次就该都要去修改配置webhook里面的配置路径,所以springcloud提供了springcloud Bus消息总线,用消息的方式去更新配置内容
//我用的是rabbitmq
@RefreshScope
public class TestController {

    @Value("${hello}")
    String name;

    @RequestMapping(value = "/hi")
    public String hi(){
        return name;
    }

}
