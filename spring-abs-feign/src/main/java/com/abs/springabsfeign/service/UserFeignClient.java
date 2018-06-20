package com.abs.springabsfeign.service;

import com.abs.springabsfeign.fallback.UserFallback;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 20:07 2018/6/14
 **/
@FeignClient(name = "spring-abs-product", fallback=UserFallback.class)
public interface UserFeignClient {

    @GetMapping(value = "/test2")
    public String test2();
}
