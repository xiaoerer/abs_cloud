package com.abs.springabscommon.bean.aop;

import com.abs.springabscommon.constant.SecurityConstants;
import com.abs.springabscommon.util.UserUtils;
import com.abs.springabscommon.vo.UserVo;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 11:52 2018/7/3
 * Controller增强
 **/
@Aspect     //切面
@Component //注入到springbean容器管理
public class ControllerAop {

    private static final Logger logger=LoggerFactory.getLogger(ControllerAop.class);

    @Autowired
    private CacheManager cacheManager;

    @Pointcut(value = "execution(public com.abs.springabscommon.util.R *(..))")
    public void pointCutR(){
    }

    /**
     * 拦截器具体实现
     *
     * @param pjp 切点 所有返回对象R
     * @return R  结果包装
     */
    @Around("pointCutR()")
    public Object methodRHandler(ProceedingJoinPoint pjp) {
        return methodHandler(pjp);
    }

    @Pointcut("execution(public com.baomidou.mybatisplus.plugins.Page *(..))")
    public void pointCutPage() {
    }

    /**
     * 拦截器具体实现
     *
     * @param pjp 切点 所有返回对象Page
     * @return R  结果包装
     */
    @Around("pointCutPage()")
    public Object methodPageHandler(ProceedingJoinPoint pjp) {
        return methodHandler(pjp);
    }

    private Object methodHandler(ProceedingJoinPoint pjp){
        long startTime=System.currentTimeMillis();
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();

        String token=UserUtils.getToken(request);
        UserVo userVo=null;
        if(null==token){
            userVo=cacheManager.getCache(SecurityConstants.TOKEN_USER_DETAIL).get(token,UserVo.class);
        }
        String username;
        if (null==userVo){
            username=UserUtils.getUserName(request);
            if(StringUtils.isNotEmpty(username)){
                UserUtils.setUser(username);
            }
        }else{
            username=userVo.getUsername();
            UserUtils.setUser(username);
        }
        logger.info("Controller AOP get username:{}", username);

        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(pjp.getArgs()));

        Object result;

        try {
            result = pjp.proceed();
            logger.info(pjp.getSignature() + "use time:" + (System.currentTimeMillis() - startTime));
        } catch (Throwable e) {
            logger.error("异常信息：", e);
            throw new RuntimeException(e);
        } finally {
            if (StringUtils.isNotEmpty(username)) {
                UserUtils.clearAllUserInfo();
            }
        }

        return result;



    }

}
