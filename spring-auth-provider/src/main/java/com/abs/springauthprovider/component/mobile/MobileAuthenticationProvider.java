package com.abs.springauthprovider.component.mobile;

import com.abs.springabscommon.vo.UserVo;
import com.abs.springauthprovider.feign.UserService;
import com.abs.springauthprovider.util.MobileAuthenticationToken;
import com.abs.springauthprovider.util.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 14:03 2018/7/6
 * 手机号登录校验逻辑
 **/
public class MobileAuthenticationProvider implements AuthenticationProvider {

    //通过setter方式依赖注入
    private UserService userService;

    /**
     * 认证
     * @param authentication
     * @return
     * @throws AuthenticationException
     * 从authentication里面获取信息,如果有登录的话,能取到认证后的信息的
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MobileAuthenticationToken mobileAuthenticationToken= (MobileAuthenticationToken) authentication;
        UserVo userVo=userService.findUserByMobile((String) mobileAuthenticationToken.getPrincipal());
        if(null==userVo){
            throw new UsernameNotFoundException("手机号不存在:" + mobileAuthenticationToken.getPrincipal());
        }

        //获得springsecurity管理的用户信息封装类
        UserDetailsImpl userDetails=new UserDetailsImpl(userVo);
        MobileAuthenticationToken authenticationToken= new MobileAuthenticationToken(userDetails.getAuthorities(), userDetails);
        authenticationToken.setDetails(mobileAuthenticationToken.getDetails());
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return MobileAuthenticationToken.class.isAssignableFrom(aClass);
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
