package com.abs.springabsfeign.service;

import com.abs.springabsfeign.fallback.UserFallback;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 14:24 2018/6/20
 **/
//@FeignClient(name = "spring-abs-order", fallback=UserFallback.class)
public interface UserFeignOrderClient {

//    @GetMapping(value = "/test")
//    public String test();

}
