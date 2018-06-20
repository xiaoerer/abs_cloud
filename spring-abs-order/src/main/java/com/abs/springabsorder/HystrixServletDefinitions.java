package com.abs.springabsorder;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 13:16 2018/6/12
 **/
@Configurable
public class HystrixServletDefinitions{

    @Bean(name = "hystrixRegistrationBean")
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean registration = new ServletRegistrationBean(
                new HystrixMetricsStreamServlet(), "/hystrix.stream");
        registration.setName("hystrixServlet");
        registration.setLoadOnStartup(1);
        return registration;
    }
}
