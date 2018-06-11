package com.abs.springabsproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
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
