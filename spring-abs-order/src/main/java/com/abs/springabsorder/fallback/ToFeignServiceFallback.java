package com.abs.springabsorder.fallback;

import com.abs.springabsorder.service.ToFeignService;
import org.springframework.stereotype.Service;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 10:02 2018/6/21
 **/
//@Service
public class ToFeignServiceFallback implements ToFeignService {
    @Override
    public String test6() {
        return "test6失败";
    }
}
