package com.abs.springabszuul;

import com.abs.springabszuul.filter.AccessTokenFilter;
import com.abs.springabszuul.filter.AccessTokenFilter1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//住测中心注册服务
@EnableEurekaClient
//开启zuul功能
@EnableZuulProxy
public class SpringAbsZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAbsZuulApplication.class, args);
    }

    /**
     * 这里是用@Bean注解,把它交给spring容器去管理.跟corsFilter类在其类上加@Component作用类似
     * @return
     */
    //配置过滤器
//    @Bean
//    public AccessTokenFilter accessTokenFilter(){
//        return new AccessTokenFilter();
//    }

    // 配置多个过滤器
//    @Bean
//    public AccessTokenFilter1 accessTokenFilter1(){
//        return new AccessTokenFilter1();
//    }
}
