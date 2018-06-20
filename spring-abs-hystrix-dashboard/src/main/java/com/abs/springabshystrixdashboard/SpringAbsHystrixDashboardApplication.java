package com.abs.springabshystrixdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
//启用 Hystrix Dashboard 功能
@EnableHystrix
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableEurekaClient
//访问http://localhost:1121/hystrix
public class SpringAbsHystrixDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAbsHystrixDashboardApplication.class, args);
    }
}
