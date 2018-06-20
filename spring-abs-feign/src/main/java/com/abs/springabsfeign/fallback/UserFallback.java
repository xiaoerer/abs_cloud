package com.abs.springabsfeign.fallback;

import com.abs.springabsfeign.service.UserFeignClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 20:25 2018/6/14
 **/
@Component
public class UserFallback implements UserFeignClient {

    @Override
    public String test2() {
        return "服务调用失败";
    }
}
