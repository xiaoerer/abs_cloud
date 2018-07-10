package com.abs.springabscommon.web;

import com.abs.springabscommon.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 17:08 2018/7/3
 **/
public class BaseController {

    @Autowired
    private HttpServletRequest request;

    /**
     * 根据请求heard中的token获取用户角色
     * @return
     */
    public List<String> getRole(){
        return UserUtils.getRole(request);
    }
}
