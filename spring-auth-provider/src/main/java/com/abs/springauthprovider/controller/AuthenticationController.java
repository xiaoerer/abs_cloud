package com.abs.springauthprovider.controller;

import com.abs.springabscommon.constant.SecurityConstants;
import com.abs.springabscommon.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 18:10 2018/7/2
 **/
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    //认证页面
    @GetMapping("/require")
    public ModelAndView require(){
        return new ModelAndView("login");
    }

    //用户信息校验
    @RequestMapping("/user")
    public Object user(Authentication authentication){
        return authentication.getPrincipal();
    }

    //清除Redis中 accesstoken  refreshToken
    @PostMapping("/removeToken")
    @CacheEvict(value = SecurityConstants.TOKEN_USER_DETAIL, key = "#accesstoken")
    public R<Boolean> removeToken(String accesstoken, String refreshToken) {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        tokenStore.removeRefreshToken(refreshToken);
        tokenStore.removeAccessToken(accesstoken);
        return new R<>(Boolean.TRUE);
    }
}
