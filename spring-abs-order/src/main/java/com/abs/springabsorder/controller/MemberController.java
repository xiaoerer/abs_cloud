package com.abs.springabsorder.controller;

import com.abs.springabsorder.service.MemberFeign;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 18:07 2018/6/5
 **/
@RestController
public class MemberController {

    @Autowired
    private MemberFeign memberFeign;

    @RequestMapping(value = "/getToOrderMemberAll")
    @HystrixCommand(fallbackMethod="listUsersByRibbonFallback")
    public String getToOrderMemberAll(){
        System.out.println("order fegin 工程调用register工程");
        return memberFeign.getToOrderMemberAll();
    }

    public String listUsersByRibbonFallback(){
        return "listUsersByRibbon异常，端口：";
    }

    @RequestMapping(value = "/getToOrderMemberOne")
    public String getToOrderMemberOne(){
        System.out.println("getToOrderMemberOne");
        return "getToOrderMemberOne";
    }
}
