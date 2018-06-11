package com.abs.springabszuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.sun.xml.internal.ws.client.ResponseContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 10:53 2018/6/1
 *
 * 跨域过滤器
 **/
@Component
public class CORSFilter extends ZuulFilter {

    private static Logger log=LoggerFactory.getLogger(CORSFilter.class);

    @Override
    public String filterType() {
        return "pre";
//        return null;      //默认
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
//        return false; //默认
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext=RequestContext.getCurrentContext();
        HttpServletRequest request=requestContext.getRequest();
        log.info(String.format("%s >>> %s",request.getMethod(),request.getRequestURL().toString()));
        Object accessToken=request.getParameter("token");
        //可以通过获取来源的service-id来判断执行,进行跨域
        if(null!=accessToken){
            return null;
        }
        log.warn("token is empty");
        requestContext.setSendZuulResponse(false);
        requestContext.setResponseStatusCode(401);
        try {
            requestContext.getResponse().getWriter().write("token is empty");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
