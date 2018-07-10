package com.abs.springauthprovider.util;

import com.abs.springabscommon.constant.CommonConstant;
import com.abs.springabscommon.constant.SecurityConstants;
import com.abs.springabscommon.vo.SysRole;
import com.abs.springabscommon.vo.UserVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 16:55 2018/7/2
 * 把用户信息登录交给验证spring Security管理
 * 自定义security框架的用户实体类(包含User)
 **/
public class UserDetailsImpl implements UserDetails {

    public static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String status;
    private List<SysRole> roleList=new ArrayList<>();

    public UserDetailsImpl(UserVo userVo) {
        this.username = userVo.getUsername();
        this.password = userVo.getPassword();
        this.status = userVo.getSalt();
        this.roleList = userVo.getRoleList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList=new ArrayList<>(); //权限列表
        for (SysRole role: roleList){
            authorityList.add(new SimpleGrantedAuthority(role.getRoleCode()));  //添加到权限列表中
        }
//        authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));// 默认一个角色  以前的一个系统是使用的默认ROLE_USER
        //所有的用户都额外添加基础的权限BASE_ROLE
        authorityList.add(new SimpleGrantedAuthority(SecurityConstants.BASE_ROLE)); //提取出去放到常量封装类中
        return authorityList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        //账户未过期?
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //账户未锁定?
        return StringUtils.equals(CommonConstant.STATUS_LOCK,status)?false:true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //凭借未过期?
        return true;
    }

    @Override
    public boolean isEnabled() {
        //开启?
        return StringUtils.equals(CommonConstant.STATUS_NORMAL,status)?true:false;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }
}
