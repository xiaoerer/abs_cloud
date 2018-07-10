package com.abs.springabsorder.common;

import com.abs.springabsorder.service.MemberFeign;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 9:02 2018/6/21
 **/
public abstract class TestSuper implements MemberFeign {

    public abstract String getToOrderMemberAll();
}
