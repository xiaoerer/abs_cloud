package com.abs.springabsorder.service;

import com.abs.springabsorder.fallback.MemberFallBack;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 18:03 2018/6/5
 **/
//feign
//@FeignClient(value = "eureka-client-register")
@FeignClient(value = "eureka-client-register")
@Component
public interface MemberFeign {

    @RequestMapping("/user/register")
//    @HystrixCommand(defaultFallback = MemberFallBack.class)
    public String getToOrderMemberAll();
}
