package com.abs.springabsorder;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

//@SpringCloudApplication  //替换@SpringBootApplication 可以简化配置 默认开启@SpringBootApplication,@EnableDiscoveryClient,@EnableCircuitBreaker等
/**
 * @Target({ElementType.TYPE})
 * @Retention(RetentionPolicy.RUNTIME)
 * @Documented
 * @Inherited
 * @SpringBootApplication
 * @EnableDiscoveryClient
 * @EnableCircuitBreaker
 * public @interface SpringCloudApplication {
 * }
 */
@SpringBootApplication
//注册到配置中心
@EnableEurekaClient
//开启Feign,简化了消费者调用服务端,自带负载均衡,自动集成了Ribbon
@EnableFeignClients
//开启断路器,解决雪崩效应
@EnableHystrix
// 断路器仪表盘
@EnableHystrixDashboard
public class SpringAbsOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAbsOrderApplication.class, args);
    }

    // 这个是2.02要添加的，不然仪表盘不显示
    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
