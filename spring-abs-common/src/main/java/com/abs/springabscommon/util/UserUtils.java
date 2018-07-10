package com.abs.springabscommon.util;

import com.abs.springabscommon.constant.CommonConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.List;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 15:27 2018/7/3
 * 用户相关工具类 关于通过token获取用户相关信息
 **/
public class UserUtils {

    private static Logger logger=LoggerFactory.getLogger(UserUtils.class);

    private static final ThreadLocal<String> THREAD_LOCAL_USER=new ThreadLocal<>();
    private static final String KEY_USER="user";

    /**
     * 根据用户请求中的token获取用户名
     * @param request
     * @return
     */
    public static String getUserName(HttpServletRequest request){
        String username="";
        String authorization=request.getHeader(CommonConstant.REQ_HEADER);
        if (StringUtils.isEmpty(authorization)){
            return username;
        }
        String token=StringUtils.substringAfter(authorization,CommonConstant.TOKEN_SPLIT);
        if(StringUtils.isEmpty(token)){
            return username;
        }
        String key= Base64.getEncoder().encodeToString(CommonConstant.SIGN_KEY.getBytes());

        try {
            Claims claims=Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            username=claims.get("user_name").toString();
        }catch (Exception e){
            logger.error("用户名解析异常,token:{},key:{}", token, key);
        }

        return username;
    }

    /**
     * 根据请求heard中的token获取用户角色
     * @param httpServletRequest
     * @return  角色名
     */
    public static List<String> getRole(HttpServletRequest httpServletRequest){
        String token=getToken(httpServletRequest);
        //密码盐 base64加密的String key
        String key=Base64.getEncoder().encodeToString(CommonConstant.SIGN_KEY.getBytes());
        Claims claims=Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        List<String> roleNames= (List<String>) claims.get("authorities");
        return roleNames;
    }

    /**
     * 获取请求中token
     *
     * @param httpServletRequest request
     * @return token
     */
    public static String getToken(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader(CommonConstant.REQ_HEADER);
        return StringUtils.substringAfter(authorization, CommonConstant.TOKEN_SPLIT);
    }

    /**
     * 设置用户信息
     *
     * @param username 用户名
     */
    public static void setUser(String username) {
        THREAD_LOCAL_USER.set(username);

        MDC.put(KEY_USER, username);
    }

    /**
     * 从threadlocal 获取用户名
     *
     * @return 用户名
     */

    public static String getUser() {
        return THREAD_LOCAL_USER.get();
    }

    /**
     * 如果没有登录，返回null
     *
     * @return 用户名
     */
    public static String getUserName() {
        return THREAD_LOCAL_USER.get();
    }

    public static void clearAllUserInfo() {
        THREAD_LOCAL_USER.remove();
        MDC.remove(KEY_USER);
    }

}
