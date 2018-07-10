package com.abs.springauthprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableEurekaClient
//@EnableAuthorizationServer
public class SpringAuthProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAuthProviderApplication.class, args);
    }
}
