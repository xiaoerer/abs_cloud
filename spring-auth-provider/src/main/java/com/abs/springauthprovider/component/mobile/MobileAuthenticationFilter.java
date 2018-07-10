package com.abs.springauthprovider.component.mobile;

import com.abs.springabscommon.constant.SecurityConstants;
import com.abs.springauthprovider.util.MobileAuthenticationToken;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 19:06 2018/7/2
 * 手机号登录验证filter
 **/
public class MobileAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public static final String SPRING_SECURITY_FROM_MOBILE_KEY="mobile";

    private String mobileParameter = SPRING_SECURITY_FROM_MOBILE_KEY;   //新建一个副本-用私有属性存储

    protected MobileAuthenticationFilter() {
        super(new AntPathRequestMatcher(SecurityConstants.MOBILE_TOKEN_URL,"POST"));
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        if (true && !httpServletRequest.getMethod().equals(HttpMethod.POST.name())){
            throw new AuthenticationServiceException("Authentication method not supported: " + httpServletRequest.getMethod());
        }

        String mobile=getMobile(httpServletRequest);
        if(null==mobile){
            mobile="";
        }

        mobile=mobile.trim();   //去两端空格
        MobileAuthenticationToken mobileAuthenticationToken=new MobileAuthenticationToken(mobile);

        setDetails(httpServletRequest,mobileAuthenticationToken);


        return this.getAuthenticationManager().authenticate(mobileAuthenticationToken);
    }

    //通过request获得手机号为键mobile传过来对应的值
    protected String getMobile(HttpServletRequest httpServletRequest){
        return httpServletRequest.getParameter(SPRING_SECURITY_FROM_MOBILE_KEY);
    }

    protected void setDetails(HttpServletRequest httpServletRequest,
                              MobileAuthenticationToken mobileAuthenticationToken){
        mobileAuthenticationToken.setDetails(authenticationDetailsSource.buildDetails(httpServletRequest));

    }



}
