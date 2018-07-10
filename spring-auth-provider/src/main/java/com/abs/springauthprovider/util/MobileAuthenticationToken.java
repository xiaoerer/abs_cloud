package com.abs.springauthprovider.util;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 18:03 2018/7/2
 **/
public class MobileAuthenticationToken extends AbstractAuthenticationToken {

    public static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final Object principal;

    public MobileAuthenticationToken(String mobile) {
        super(null);
        this.principal=mobile;
        setAuthenticated(false);
    }

    public MobileAuthenticationToken(Collection<? extends GrantedAuthority> authorities, Object principal) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    //获得凭据
    @Override
    public Object getCredentials() {
        return null;
    }

    //获得主要信息(用户信息:账号,手机号,等)
    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    //是否认证?
    @Override
    public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
        if (authenticated){
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(authenticated);
    }

    //擦除认证凭据
    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }
}
