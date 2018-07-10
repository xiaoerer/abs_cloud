package com.abs.springauthprovider.service;

import com.abs.springabscommon.vo.UserVo;
import com.abs.springauthprovider.feign.UserService;
import com.abs.springauthprovider.util.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 18:32 2018/7/2
 * 自定义用户验证模块
 **/
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVo userVo=userService.findUserByUsername(username);
        if(null==userVo){
            throw new UsernameNotFoundException("用户名不存在");
        }
        return new UserDetailsImpl(userVo);
    }
}
