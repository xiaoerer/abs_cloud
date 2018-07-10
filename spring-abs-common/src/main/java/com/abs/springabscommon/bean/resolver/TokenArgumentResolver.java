package com.abs.springabscommon.bean.resolver;

import com.abs.springabscommon.constant.SecurityConstants;
import com.abs.springabscommon.util.UserUtils;
import com.abs.springabscommon.vo.SysRole;
import com.abs.springabscommon.vo.UserVo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 17:23 2018/7/3
 * Token转化UserVo
 **/
public class TokenArgumentResolver implements HandlerMethodArgumentResolver {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private CacheManager cacheManager;

    public TokenArgumentResolver(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    /**
     * 1.入参筛选
     * @param methodParameter   参数集合
     * @return  格式化后的参数
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(UserVo.class);
    }

    /**
     * 1. 先从 cache 中判断token 是否已经有缓存
     * 2. 不存在缓存情况,解析token获取用户信息
     * 3. 不存在缓存情况,在AOP进行缓存添加，因为这里添加只会对入参含有 UserVo的生效，而不是全局
     * @param methodParameter   入参集合
     * @param modelAndViewContainer model 和 view
     * @param nativeWebRequest  web相关
     * @param webDataBinderFactory  入参解析
     * @return  包装对象
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
        //获取到request
        HttpServletRequest request=nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        String token=UserUtils.getToken(request);
        if (StringUtils.isBlank(token)) {
            logger.error("resolveArgument error token is empty");
            return null;
        }
        Optional<UserVo> optional = Optional.ofNullable(cacheManager.getCache(SecurityConstants.TOKEN_USER_DETAIL).get(token, UserVo.class));
        if (optional.isPresent()) { //如果存在
            logger.info("return cache user vo,token :{}", token);
            return optional.get();
        }
        return optional.orElseGet(() -> generatorByToken(request, token));
    }

    private UserVo generatorByToken(HttpServletRequest request, String token) {
        String username = UserUtils.getUserName(request);
        List<String> roles = UserUtils.getRole(request);
        logger.info("Auth-Token-User:{}-Roles:{}", username, roles);
        UserVo userVo = new UserVo();
        userVo.setUsername(username);
        List<SysRole> sysRoleList = new ArrayList<>();
        roles.stream().forEach(role -> {
            SysRole sysRole = new SysRole();
            sysRole.setRoleName(role);
            sysRoleList.add(sysRole);
        });
        userVo.setRoleList(sysRoleList);
        //添加到缓存
        cacheManager.getCache(SecurityConstants.TOKEN_USER_DETAIL).put(token, userVo);
        return userVo;
    }
}
