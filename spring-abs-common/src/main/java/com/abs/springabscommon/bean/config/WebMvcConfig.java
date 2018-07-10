package com.abs.springabscommon.bean.config;

import com.abs.springabscommon.bean.resolver.TokenArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cache.CacheManager;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 17:58 2018/7/3
 * ---mvc配置
 **/
@Configurable
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private CacheManager cacheManager;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new TokenArgumentResolver(cacheManager));
    }
}
