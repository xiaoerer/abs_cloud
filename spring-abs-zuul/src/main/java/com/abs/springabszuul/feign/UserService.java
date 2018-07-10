package com.abs.springabszuul.feign;

import com.abs.springabscommon.vo.UserVo;
import com.abs.springabszuul.feign.fallback.UserServiceFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 12:05 2018/7/6
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
}
