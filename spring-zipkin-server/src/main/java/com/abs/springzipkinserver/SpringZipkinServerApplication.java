package com.abs.springzipkinserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//@EnableZipkinServer
@EnableEurekaClient
public class SpringZipkinServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringZipkinServerApplication.class, args);
    }
}
