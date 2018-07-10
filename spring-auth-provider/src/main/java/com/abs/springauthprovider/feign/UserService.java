package com.abs.springauthprovider.feign;

import com.abs.springabscommon.vo.UserVo;
import com.abs.springauthprovider.feign.fallback.UserServiceFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 18:35 2018/7/2
 **/
@FeignClient(name = "pig-upms-service", fallback = UserServiceFallbackImpl.class)
public interface UserService {

    /**
     * 通过用户名查询用户、角色信息
     *
     * @param username 用户名
     * @return UserVo
     */
    @GetMapping("/user/findUserByUsername/{username}")
    UserVo findUserByUsername(@PathVariable("username") String username);

    /**
     * 通过手机号查询用户、角色信息
     *
     * @param mobile 手机号
     * @return UserVo
     */
    @GetMapping("/user/findUserByMobile/{mobile}")
    UserVo findUserByMobile(@PathVariable("mobile") String mobile);

    /**
     * 根据OpenId查询用户信息
     * @param openId openId
     * @return UserVo
     */
    @GetMapping("/user/findUserByOpenId/{openId}")
    UserVo findUserByOpenId(@PathVariable("openId") String openId);
}
