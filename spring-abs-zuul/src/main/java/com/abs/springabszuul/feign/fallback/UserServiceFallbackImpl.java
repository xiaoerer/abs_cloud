package com.abs.springabszuul.feign.fallback;

import com.abs.springabscommon.vo.UserVo;
import com.abs.springabszuul.feign.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 12:07 2018/7/6
 **/
@Service
public class UserServiceFallbackImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public UserVo findUserByUsername(String username) {
        logger.error("调用{}异常:{}", "findUserByUsername", username);
        return null;
    }
}
