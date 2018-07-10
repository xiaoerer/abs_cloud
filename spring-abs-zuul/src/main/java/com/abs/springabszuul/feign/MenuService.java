package com.abs.springabszuul.feign;

import com.abs.springabscommon.vo.MenuVo;
import com.abs.springabszuul.feign.fallback.MenuServiceFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 12:05 2018/7/6
 **/
@FeignClient(name = "pig-upms-service", fallback = MenuServiceFallbackImpl.class)
public interface MenuService {

    /**
     * 通过角色名查询菜单
     *
     * @param role 角色名称
     * @return 菜单列表
     */
    @GetMapping(value = "/menu/findMenuByRole/{role}")
    Set<MenuVo> findMenuByRole(@PathVariable("role") String role);

}
