package com.abs.springabsorder.fallback;

import com.abs.springabsorder.service.MemberFeign;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 10:23 2018/6/6
 * 去实现接口
 **/
public class MemberFallBack implements MemberFeign {


    @Override
    public String getToOrderMemberAll() {
        //服务降级处理
        return "fallback,服务发生异常!!!正常生成环境中的是返回errorMsg+errorCode";
    }

}
