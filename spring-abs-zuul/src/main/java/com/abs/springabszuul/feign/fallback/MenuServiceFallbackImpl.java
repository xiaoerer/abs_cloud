package com.abs.springabszuul.feign.fallback;

import com.abs.springabscommon.vo.MenuVo;
import com.abs.springabszuul.feign.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 12:06 2018/7/6
 **/
@Service
public class MenuServiceFallbackImpl implements MenuService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Set<MenuVo> findMenuByRole(String role) {
        logger.error("调用{}异常{}","findMenuByRole",role);
        return null;
    }


}
