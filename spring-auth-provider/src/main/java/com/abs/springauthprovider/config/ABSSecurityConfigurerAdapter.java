package com.abs.springauthprovider.config;

import com.abs.springabscommon.bean.config.FilterUrlsPropertiesConfig;
import com.abs.springauthprovider.component.mobile.MobileSecurityConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 14:08 2018/7/9
 **/
@Configuration
@EnableWebSecurity
public class ABSSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    private FilterUrlsPropertiesConfig filterUrlsPropertiesConfig;
    @Autowired
    private MobileSecurityConfigurer mobileSecurityConfigurer;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry=
                http.formLogin().loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .and()
                .authorizeRequests();

        for (String url: filterUrlsPropertiesConfig.getAnon()){
            //白名单过滤
            registry.antMatchers(url).permitAll();  //全部允许
        }
        registry.anyRequest().authenticated()
                .and()
                .csrf().disable();  //csrf检查关闭
        http.apply(mobileSecurityConfigurer);
    }

}
