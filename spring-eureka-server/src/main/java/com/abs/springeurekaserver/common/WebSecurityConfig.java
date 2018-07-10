package com.abs.springeurekaserver.common;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 9:15 2018/7/2
 **/
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();    //方式一:关闭csrf检测
        http.csrf().ignoringAntMatchers("/eureka/**");      //方式二 过滤/eureka/** 也就是过滤掉eureka的管理访问
        super.configure(http);
    }
}
