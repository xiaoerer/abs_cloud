package com.abs.springabsproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
//开启断路器功能--使用Hystrix检测功能
@EnableHystrix
public class SpringAbsProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAbsProductApplication.class, args);
    }

    @Bean       //注入容器
    @LoadBalanced //支持负载均衡
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
