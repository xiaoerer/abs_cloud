package com.abs.springabscommon.bean.cache;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 16:29 2018/7/5
 * 缓存方法注册接口
 **/
public interface InvocationRegistry {
    void registerInvocation(Object invokedBean, Method invokedMethod, Object[] invocationArguments, Set<String> cacheNames);

}
