package com.abs.springabsorder.service;

import com.abs.springabsorder.fallback.ToFeignServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 9:58 2018/6/21
 **/
@FeignClient(name = "spring-abs-feign",fallback = ToFeignServiceFallback.class)
@Component
public interface ToFeignService {

    @RequestMapping("/test6")
//    @HystrixCommand(defaultFallback = MemberFallBack.class)
    public String test6();
}
